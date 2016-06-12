(ns page.dom
  (:require [hoplon.core :as h]))

(defn page
  "The page."
  []
  (h/html
    (h/head)
    (h/body
      "Hello World!")))
      
