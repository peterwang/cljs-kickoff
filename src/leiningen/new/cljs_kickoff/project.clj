(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2127"]
                 [ring "1.2.1"]
                 [hiccup "1.0.4"]
                 [compojure "1.1.6"]
                 [domina "1.0.2"]]
  :plugins [[com.cemerick/austin "0.1.3"]
            [lein-cljsbuild "1.0.1"]
            [lein-ring "0.8.8"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :cljsbuild {
    :builds {
      :main {
        :source-paths ["src/cljs"]
        :compiler {:output-to "resources/public/js/cljs.js"
                   :optimizations :simple
                   :pretty-print true}
        :jar true}}}
  :main {{name}}.server
  :ring {:handler {{name}}.server/app})
