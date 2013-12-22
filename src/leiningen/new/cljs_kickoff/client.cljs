(ns hello-clojurescript
  (:require [clojure.browser.repl]
            [domina :refer [by-id]]
            [domina.events :refer [listen!]]))

(defn init []
  (listen!
   (by-id "clickable")
   :click
   (fn [] (js/alert "clicked"))))

(init)
