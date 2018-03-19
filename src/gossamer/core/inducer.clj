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


(defn log-mdc-codec-init
  "Initialize SLF4j MDC codec for Logback."
  [context]
  ;; lazily load the namespace to avoid Logback auto-initialization during namespace load
  (require 'cambium.logback.json.flat-layout)
  (if-let [f (find-var 'cambium.logback.json.flat-layout/set-decoder!)]
    (f codec/destringify-val)
    (throw (ex-info "Cannot find fn 'cambium.logback.json.flat-layout/set-decoder!' in classpath." {})))
  context)


(defn calfpath-routes->ring-handler
  "Given a context, Calfpath routes and compilation options, compile the routes and produce a Ring handler."
  ([context]
    (calfpath-routes->ring-handler context (-> context
                                             core-kdef/ctx-config
                                             kdef/cfg-routes-compile-options
                                             (echo/->echo "Compiling Calfpath routes with options:"))))
  ([context compile-options]
    (as-> (kdef/ctx-calfpath-routes context) $
      (croute/compile-routes $ compile-options)
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
