(ns link-page.redis
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def conn {:pool {} :spec {:uri "redis://redis:6379"}})
(defmacro wcar* [& body] `(car/wcar conn ~@body))

(defn set [key val]
  (wcar* (car/set key val)))

(defn get [key]
  (wcar* (car/get key)))


