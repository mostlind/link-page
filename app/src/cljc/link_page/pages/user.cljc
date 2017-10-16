(ns link-page.pages.user
  #?(:cljs (:require-macros [cljs.core.async.macros :refer [go]]))
  #?(:cljs (:require [rum.core :as rum :refer [defc]]
                     [link-page.components :as c]
                     [cljs-http.client :as http]
                     [cljs.core.async :refer [<!]])
     :clj (:require [rum.core :as rum :refer [defc]]
                    [link-page.components :as c])))

#?(:cljs (js/console.log "in user.cljc"))
                  

(def *app-state (atom {:user-name ""
                       :links []
                       :user-page-href nil}))

(defn indexed
  [coll]
  (vec (map-indexed (fn [index item] [index item]) coll)))

(defn post-data []
  #?(:cljs 
     (go (let [response (<! (http/post "/create" {:json-params @*app-state}))]
           (println (get-in response [:body]))
           (swap! *app-state update-in [:user-page-href] #(:body response))))))

(defn update-with-value
  [path]
  (println @*app-state)
  (fn [e]
    (swap! *app-state update-in path #(str (.. e -currentTarget -value)))))

(defn add-link [_]
  #?(:cljs (js/console.log "hello"))
  (println @*app-state)
  (swap! *app-state update-in [:links] #(conj % {:title "" :href ""})))

(defc user-page < rum/reactive []
  [:div
    [:div.name-input {:class "name-input"}
      [:p "Name"]
      [:input { :type "text"
                :value (:user-name (rum/react *app-state))
                :on-change (update-with-value [:user-name])}]]
    (for [[index link] (indexed (:links (rum/react *app-state)))]
      [:div.site-input {:class "site-input"}
        [:p "Title"]
        [:input { :type "text"
                  :on-change (update-with-value [:links index :title])
                  :value (:title link)}]
        [:p "Link"]
        [:input { :type "text"
                  :on-change (update-with-value [:links index :href])
                  :value (:href link)}]])
    [:button 
      {:on-click add-link}
      "Add site"]
    [:button
      {:on-click post-data}
      "Get Link"]
    (when (:user-page-href @*app-state)
      [:p (str "Your link: ") 
        [:a 
          {:href (str "http://" (:user-page-href @*app-state))} 
          (:user-page-href @*app-state)]])])

(defn mount! [mount-el]
  (rum/mount (user-page) mount-el))

