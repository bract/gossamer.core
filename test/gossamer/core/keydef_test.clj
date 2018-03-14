;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.keydef-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [gossamer.core.keydef :as kdef]))


(deftest test-context
  (testing "Good context"
    (let [good-context {;; calfpath routes
                        :gossamer/calfpath-routes
                        [{:uri "/user"     :method :get  :handler (constantly {:status 200 :body "OK"})}
                         {:uri "/user"     :method :post :handler (constantly {:status 200 :body "OK"})}
                         {:uri "/user/:id" :method :put  :handler (constantly {:status 200 :body "OK"})}]
                        ;; ring handler
                        :bract.ring/ring-handler
                        (constantly {:status 200 :body "OK"})}]
      (is (vector? (kdef/ctx-calfpath-routes good-context)))
      (is (fn?     (kdef/ctx-ring-handler    good-context)))))
  (testing "Missing"
    (is (thrown? IllegalArgumentException (kdef/ctx-calfpath-routes {})))
    (is (thrown? IllegalArgumentException (kdef/ctx-ring-handler    {}))))
  (testing "Bad context"
    (let [bad-context {:gossamer/calfpath-routes 10
                       :bract.ring/ring-handler  10}]
      (is (thrown? IllegalArgumentException (kdef/ctx-calfpath-routes bad-context)))
      (is (thrown? IllegalArgumentException (kdef/ctx-ring-handler    bad-context))))))


(deftest test-config
  (testing "Good config"
    (let [good-config {"gossamer.inner.ping.enabled" true
                       "gossamer.inner.ping.endpoint.uris" ["/ping/inner" "/ping/inner/"]
                       "gossamer.inner.ping.endpoint.body" "pong-inner"
                       "gossamer.inner.ping.content.type"  "text/plain"}]
      (is (true?   (kdef/cfg-route-inner-ping?     good-config)))
      (is (vector? (kdef/cfg-route-inner-ping-uris good-config)))
      (is (string? (kdef/cfg-route-inner-ping-body good-config)))
      (is (string? (kdef/cfg-route-inner-ping-type good-config)))))
  (testing "Missing config"
    (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping?     {})))
    (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-uris {})))
    (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-body {})))
    (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-type {}))))
  (testing "Bad config"
    (let [bad-config {"gossamer.inner.ping.enabled"       10
                      "gossamer.inner.ping.endpoint.uris" 10
                      "gossamer.inner.ping.endpoint.body" 10
                      "gossamer.inner.ping.content.type"  10}]
      (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping?     bad-config)))
      (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-uris bad-config)))
      (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-body bad-config)))
      (is (thrown? IllegalArgumentException (kdef/cfg-route-inner-ping-type bad-config))))))
