(defproject bract/gossamer.core "0.6.2-0.3.0"
  :description "A micro web framework module for Bract"
  :url "https://github.com/bract/gossamer.core"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :global-vars {*warn-on-reflection* true
                *assert* true
                *unchecked-math* :warn-on-boxed}
  :pedantic?    :warn
  :dependencies [[bract/bract.core "0.6.2"]
                 [bract/bract.cli  "0.6.2-0.1.1"]
                 [bract/bract.ring "0.6.2-0.2.0"]
                 ;; web routing
                 [calfpath         "0.8.1"]
                 ;; logging
                 [cambium/cambium.core           "1.0.0"]
                 [cambium/cambium.codec-cheshire "1.0.0"]
                 [org.slf4j/jul-to-slf4j         "1.7.30"]  ; direct java.util.logging logs to SLF4j
                 [org.slf4j/jcl-over-slf4j       "1.7.30"]  ; direct Java Commons-logging logs to SLF4j
                 [org.slf4j/log4j-over-slf4j     "1.7.30"]  ; direct Log4j logs to SLF4j
                 [cambium/cambium.logback.json   "0.4.4"]]
  :profiles {:provided {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :coverage {:plugins [[lein-cloverage "1.0.9"]]}
             :rel {:min-lein-version "2.7.1"
                   :pedantic? :abort}
             :c08 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :c09 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :c10 {:dependencies [[org.clojure/clojure "1.10.3-rc1"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :aliases {"test-all" ["with-profile" "c08:c09:c10" "test"]})
