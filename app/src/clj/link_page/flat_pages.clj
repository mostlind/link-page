(ns link-page.flat-pages
  (:require
    [rum.core :as rum :refer [defc]]))

(defc links-page [id link-list]
  [:body.link-page
    [:div
      [:link {:rel "stylesheet" :href "css/main.css"}]
      [:h1 id]
      [:ul
        (for [link link-list]
          [:li
            [:a {:href (:href link)} (:title link)]])]]])
