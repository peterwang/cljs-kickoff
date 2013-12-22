(ns {{name}}.server
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as resources]
            [ring.util.response :as response]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [include-css include-js]]
            [cemerick.austin.repls :refer
             [browser-connected-repl-js browser-repl-env cljs-repl]])
  (:gen-class))

(defn render-app []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body
   (html
    [:html
     [:head (include-css "css/page.css")]
     [:body
      [:div [:p#clickable "click me"]]
      (include-js "js/cljs.js")
      [:script (browser-connected-repl-js)]]])})

(defn handler [request]
  (if (= "/" (:uri request))
      (response/redirect "/help.html")
      (render-app)))

(defn js
  []
  (def ^:private repl-env
    (reset! browser-repl-env (cemerick.austin/repl-env)))
  (cljs-repl repl-env)
  (println "Now go refresh the page"))

(def app
  (-> handler
    (resources/wrap-resource "public")))

(defn -main [& args]
  (jetty/run-jetty app {:port 8080 :join? false}))
