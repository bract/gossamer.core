;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.util
  "Utility functions."
  (:require
    [bract.core.echo :as echo]
    [bract.core.keydef :as bc-kdef]
    [bract.core.util :as bc-util]
    [gossamer.core.keydef :as kdef]
    [cambium.core :as log]))


(defn command-print-version
  "CLI command - print application version."
  [context]
  (let [config (bc-kdef/ctx-config context)
        version (kdef/cfg-app-version config)]
    (println version))
  (reduced context))


(defn handle-uncaught-exception
  "Callback function for handling uncaught exceptions."
  [^Thread thread ^Throwable ex]
  (log/error (Throwable->map ex) ex (format "Uncaught exception on thread ID: %d, thread name: %s - (%s) %s"
                                      (.getId thread) (.getName thread) (class ex) (.getMessage ex))))


(defn start-placeholder-server
  [handler options]
  (let [message "ERROR: You must specify one of the following entries in your bract-context.edn resource:

:bract.ring/server-starter bract.ring.server/start-aleph-server    ; for Aleph server
:bract.ring/server-starter bract.ring.server/start-http-kit-server ; for HTTP Kit server
:bract.ring/server-starter bract.ring.server/start-immutant-server ; for Immutant server
:bract.ring/server-starter bract.ring.server/start-jetty-server    ; for Jetty server

*** No web server is started. ***"]
    (echo/echo message)
    (bc-util/err-println message))
  (fn []))
