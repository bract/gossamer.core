;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.inducer
  (:require
    [bract.core.echo      :as echo]
    [bract.core.inducer   :as core-inducer]
    [bract.core.keydef    :as core-kdef]
    [bract.core.type      :as core-type]
    [bract.core.util      :as core-util]
    [calfpath.route       :as croute]
    [cambium.codec        :as codec]
    [gossamer.core.keydef :as kdef]))


(defn abort
  "Abort the entire inducer chain."
  ;; TODO: Move this into bract.core.inducer
  ([context]
    (assoc context
      (key core-kdef/ctx-exit?) true))
  ([context message]
    (echo/abort message)
    (core-util/err-println "ERROR:" message)
    (abort context)))


(defn log-mdc-codec-init-only
  "Initialize SLF4j MDC codec for Logback."
  [context]
  ;; lazily load the namespace to avoid Logback auto-initialization during namespace load
  (core-util/let-var [set-decoder!  'cambium.logback.json.flat-layout/set-decoder!
                      find-context  'cambium.logback.core.util/find-logger-context
                      context-name  'cambium.logback.core.util/logger-context-name
                      start-context 'cambium.logback.core.util/start-logger-context]
    (set-decoder! codec/destringify-val)
    (let [logback-context (find-context)]
      (echo/echof "Starting Logback context '%s'" (context-name logback-context))
      (start-context logback-context)))
  context)


(defn register-logback-deinit
  "Register a deinit task to flush/clean up the Logback context."
  [context]
  (as-> (core-kdef/ctx-deinit context) $
    (vec $)
    (conj $ (fn []
              (core-util/let-var [find-context 'cambium.logback.core.util/find-logger-context
                                  context-name 'cambium.logback.core.util/logger-context-name
                                  stop-context 'cambium.logback.core.util/stop-logger-context]
                (let [logback-context (find-context)]
                  (echo/echof "Stopping Logback context '%s'" (context-name logback-context))
                  (stop-context logback-context)))))
    (assoc context (key core-kdef/ctx-deinit) $)))


(defn log-mdc-codec-init
  "Initialize Logback and register a corresponding deinit callback."
  [context]
  (-> context
    log-mdc-codec-init-only
    register-logback-deinit))


(defn calfpath-routes->ring-handler
  "Given a context, Calfpath routes and compilation options, compile the routes and produce a Ring handler."
  ([context]
    (calfpath-routes->ring-handler context (-> context
                                             core-kdef/ctx-config
                                             kdef/cfg-routes-compile-options)))
  ([context compile-options]
    (as-> (kdef/ctx-calfpath-routes context) $
      (croute/compile-routes $ (-> compile-options
                                 (echo/->echo "Compiling Calfpath routes with options")))
      (croute/make-dispatcher $)
      (assoc context (key kdef/ctx-ring-handler) $))))


(defn apply-route-wrappers
  "Given a context with Calfpath routes under context key :gossamer/calfpath-routes apply the route wrappers i.e.
  a seq of `(fn [routes context & more]) -> routes`, finally updating the context with the wrapped routes."
  ([context]
    (apply-route-wrappers context (kdef/ctx-route-wrappers context)))
  ([context route-wrappers]
    (->> route-wrappers
      (map (fn [wrapper-spec] (let [[wrapper-name & args] (core-util/as-vec wrapper-spec)]
                                (core-type/->Function
                                  (fn [ctx]
                                    (let [routes  (kdef/ctx-calfpath-routes ctx)
                                          wrapper (core-type/ifunc wrapper-name)]
                                      (->> args
                                        (apply wrapper routes ctx)
                                        (assoc ctx (key kdef/ctx-calfpath-routes)))))
                                  (core-type/iname wrapper-name)
                                  []))))
      (core-inducer/induce context))))
