(ns link-page.pages
  (:require
    [rum.core :as rum :refer [defc render-html]]
    [link-page.components :as c]
    [link-page.pages.user :refer [user-page]]))

(def pages {:user user-page})

(defc page [page-key]
  (str "<link rel=\"stylesheet\" href=\"css/main.css\">"
    "<body>"
    "<div id=\"app\">"
    (render-html ((page-key pages)))
    "</div>"
    "<script src=\"js/main.js\"></script>"
    "</body>"))
  
