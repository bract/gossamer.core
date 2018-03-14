(defproject bract/gossamer.core "0.6.0-SNAPSHOT"
  :description "A micro web framework module for Bract"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :global-vars {*warn-on-reflection* true
                *assert* true
                *unchecked-math* :warn-on-boxed}
  :pedantic?    :warn
  :dependencies [[bract/bract.core "0.6.0-alpha1"]
                 ;; web
                 [calfpath         "0.5.0"]
                 ;; logging
                 [cambium/cambium.core           "0.9.1"]
                 [cambium/cambium.codec-cheshire "0.9.1"]
                 [cambium/cambium.logback.core   "0.4.1"]
                 [cambium/cambium.logback.json   "0.4.1"]]
  :profiles {:provided {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :coverage {:plugins [[lein-cloverage "1.0.9"]]}
             :rel {:min-lein-version "2.7.1"
                   :pedantic? :abort}
             :c17 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :c18 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :c19 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})