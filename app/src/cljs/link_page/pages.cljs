(ns link-page.client
  (:require [rum.core :as rum]
            [link-page.pages.user :as user-page]))

(enable-console-print!)

(println "will mount?")

(user-page/mount! (.getElementById js/document "app"))

(println "mounted?")

