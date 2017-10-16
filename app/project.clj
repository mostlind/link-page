(defproject link-page "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [rum "0.10.8"]
                 [ring "1.6.2"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.taoensso/carmine "2.16.0"]
                 [cljs-http "0.1.43"]]

  :source-paths ["src/clj" "src/cljc"]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.12.1"]]

  :cljsbuild {:builds [{:source-paths ["src/cljs" "src/cljc"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  
  :ring {:handler link-page.handler/app})
