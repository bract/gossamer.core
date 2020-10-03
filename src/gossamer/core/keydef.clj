;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.keydef
  "Context and config key definitions in the gossamer.core module."
  (:require
    [keypin.core :as keypin]
    [keypin.util :as kputil]))


(keypin/defkey  ; context keys
  ctx-calfpath-routes [:gossamer/calfpath-routes vector? "Vector of Calfpath routes"]
  ctx-route-wrappers  [:gossamer/route-wrappers  vector? "Vector of route wrapper functions (fully qualified names)"]
  ctx-ring-handler    [:bract.ring/ring-handler  fn?     "Application Ring handler"])


(keypin/defkey  ; config keys - routes
  cfg-routes-compile-options ["gossamer.routes.compile.options"   map?    "Compile options for Calfpath routes"])


(keypin/defkey  ; config keys - flags
  {:pred kputil/bool?
   :parser kputil/any->bool}
  cfg-route-inner-ping?      ["gossamer.inner.ping.enabled" {:doc "Add inner ping route for latency checks?"}])


(keypin/defkey  ; config keys - inner ping route
  cfg-route-inner-ping-uris  ["gossamer.inner.ping.endpoint.uris" vector? "Vector of inner ping endpoint URIs"
                              {:parser kputil/any->edn}]
  cfg-route-inner-ping-body  ["gossamer.inner.ping.endpoint.body" string? "String body for inner ping response"]
  cfg-route-inner-ping-type  ["gossamer.inner.ping.content.type"  string? "Content type for inner ping body"])


(keypin/defkey  ; config keys - application keys
  cfg-app-version  ["app.version"  string? "Application version"]
  cfg-app-hostname ["app.hostname" string? "Application hostname"])
