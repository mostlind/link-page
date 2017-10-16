(ns link-page.components
  (:require
    [rum.core :as rum :refer [defc]]))

(defc single-input < rum/reactive
  [*ref]
  (let [value (rum/react *ref)]
    [:input { :type "text"
              :value value
              :on-change (fn [e] reset! *ref (str (.. e -currentTarget -value)))}]))

(defc link-input < rum/reactive 
  [*link-ref]
  (let [value (rum/react *link-ref)]
    [:div.link-input
      [:p "Title"]
      [:input { :type "text"
                :value (:title value)
                :on-change (fn [e] swap! assoc :title (str (.. e -currentTarget -value)))}]
      [:p "Link"]
      [:input { :type "text"
                :value (:href value)
                :on-change (fn [e] swap! assoc :href (str (.. e -currentTarget -value)))}]]))

              
(defc name-input
  [ref*]
  [:div.name-input
    [:p "Name"]
    (single-input ref*)])

(defc hello
  []
  [:div
    [:h1 "Hello"]
    [:p "this page was rendered with rum"]])
  
