;   Copyright (c) Shantanu Kumar. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file LICENSE at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.


(ns gossamer.core.route
  (:require
    [bract.core.keydef :as core-kdef]
    [gossamer.core.keydef   :as kdef]))


(defn inner-ping
  "Add an inner ping route."
  [routes context]
  (let [config (core-kdef/ctx-config context)]
    (if (kdef/cfg-route-inner-ping? config)
      (let [uris (kdef/cfg-route-inner-ping-uris config)
            body (kdef/cfg-route-inner-ping-body config)
            type (kdef/cfg-route-inner-ping-type config)
            resp {:status 200
                  :body body
                  :headers {"Content-type" type}}
            fres (fn
                   ([request] resp)
                   ([request respond raise] (respond resp)))]
        (->> uris
          (map (fn [each-uri] {:uri each-uri
                               :handler fres}))
          (concat routes)
          vec))
      routes)))
