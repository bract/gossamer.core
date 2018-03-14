;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.route-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [gossamer.core.keydef  :as kdef]
    [gossamer.core.inducer :as inducer]
    [gossamer.core.route   :as route]))


(deftest test-inner-ping
  (let [handler (-> {:bract.core/config {"gossamer.inner.ping.enabled" true
                                         "gossamer.inner.ping.endpoint.uris" ["/ping/inner"
                                                                              "/ping/inner/"]
                                         "gossamer.inner.ping.endpoint.body" "pong-inner"
                                         "gossamer.inner.ping.content.type"  "text/plain"}
                     :gossamer/calfpath-routes [{:uri "/foo" :method :get :handler (constantly {:status 200
                                                                                                :body "foo"})}]}
                  (inducer/apply-route-wrappers [route/inner-ping])
                  inducer/calfpath-routes->ring-handler
                  kdef/ctx-ring-handler)]
    (is (= {:status 200
            :body "pong-inner"
            :headers {"Content-type" "text/plain"}}
          (handler {:uri "/ping/inner" :request-method :get})))
    ))
