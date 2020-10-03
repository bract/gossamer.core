(defproject bract/gossamer.core "0.6.2-0.3.0-SNAPSHOT"
  :description "A micro web framework module for Bract"
  :url "https://github.com/bract/gossamer.core"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :global-vars {*warn-on-reflection* true
                *assert* true
                *unchecked-math* :warn-on-boxed}
  :pedantic?    :warn
  :dependencies [[bract/bract.core "0.6.2-alpha1"]
                 [bract/bract.cli  "0.6.2-0.1.1-alpha1"]
                 [bract/bract.ring "0.6.2-0.2.0-alpha1"]
                 ;; web routing
                 [calfpath         "0.7.1"]
                 ;; logging
                 [cambium/cambium.core           "0.9.2"]
                 [cambium/cambium.codec-cheshire "0.9.2"]
                 [org.slf4j/jul-to-slf4j         "1.7.25"]  ; direct java.util.logging logs to SLF4j
                 [org.slf4j/jcl-over-slf4j       "1.7.25"]  ; direct Java Commons-logging logs to SLF4j
                 [org.slf4j/log4j-over-slf4j     "1.7.25"]  ; direct Log4j logs to SLF4j
                 [cambium/cambium.logback.json   "0.4.2"]]
  :profiles {:provided {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :coverage {:plugins [[lein-cloverage "1.0.9"]]}
             :rel {:min-lein-version "2.7.1"
                   :pedantic? :abort}
             :c07 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :c08 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :c09 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :c10 {:dependencies [[org.clojure/clojure "1.10.1"]]}
             :dln {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :aliases {"test-all" ["with-profile" "c07:c08:c09:c10" "test"]})
