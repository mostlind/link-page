(ns link-page.handler
  (:require [ring.middleware.defaults :refer [api-defaults wrap-defaults]] 
            [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.json :refer [wrap-json-body]]
            [rum.core :refer [render-static-markup]]
            [link-page.pages :as p]
            [link-page.redis :as r]
            [link-page.flat-pages :refer [links-page]]))
          
(defroutes app-routes
  (GET "/" [] (p/page :user))
  (GET "/:id" [id] (render-static-markup (links-page id (r/get id))))
  (POST "/create" request
    (let [id (get-in request [:body :user-name])
          links (get-in request [:body :links])
          link-page (str (get-in request [:headers "host"]) "/" id)
          redis-response (r/set id links)]
      (if (= redis-response "OK")
        link-page
        "ERR")))
  (resources "/")
  (not-found "404"))

(def app (-> app-routes
           (wrap-defaults api-defaults)
           (wrap-json-body {:keywords? true})))
