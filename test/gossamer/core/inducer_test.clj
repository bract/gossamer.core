;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.inducer-test
  (:require
    [clojure.test :refer [deftest is testing]]
    [cambium.core          :as log]
    [gossamer.core.keydef  :as kdef]
    [gossamer.core.inducer :as inducer]))


(deftest test-mdc-codec-init
  (inducer/log-mdc-codec-init {})
  (log/with-logging-context {:foo {:bar [10 20]}}
    (is (= {"bar" [10 20]}
          (log/context-val :foo)))
    (is (= [10 20]
          (log/context-val [:foo :bar])))))


(def routes
  [{:uri "/user" :method :post :handler (constantly {:status 200 :body "new-user"})}
   {:uri "/user/:id" :nested [{:method :get :handler (constantly {:status 200 :body "user-get"})}
                              {:method :put :handler (constantly {:status 200 :body "user-put"})}]}])


(deftest test-calfpath-routes->ring-handler
  (let [handler (-> {:gossamer/calfpath-routes routes}
                  inducer/calfpath-routes->ring-handler
                  kdef/ctx-ring-handler)]
    (is (= {:status 200
            :body "new-user"}
          (handler {:uri "/user" :request-method :post})))
    (is (= {:status 200
            :body "user-get"}
          (handler {:uri "/user/12" :request-method :get})))))


(deftest test-apply-route-wrappers
  (let [wrapper1 (fn [routes context] (conj routes {:foo 10}))
        wrapper2 (fn [routes context value] (conj routes {:bar value}))
        context  (inducer/apply-route-wrappers {:gossamer/calfpath-routes []} [wrapper1
                                                                               [wrapper2 30]])]
    (is (= [{:foo 10}
            {:bar 30}]
          (kdef/ctx-calfpath-routes context)))))
