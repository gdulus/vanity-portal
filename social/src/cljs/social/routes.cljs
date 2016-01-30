(ns social.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]
              [social.config :as config]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(defn get-route
    [name]
    (let [route (get-in config/url-mapping [:routes name])]
        (if (route :external)
            (route :uri)
            (str "#" (route :uri)))))

;; ----------------------------------------------------------------------------------------------

(defn get-default-route
    [user-status]
    (get-in config/url-mapping [:defaults user-status]))

;; ----------------------------------------------------------------------------------------------

(defn get-acl
    [name]
    (get-in config/url-mapping [:routes name :acl]))

;; ----------------------------------------------------------------------------------------------

(defn- hook-browser-navigation []
    (doto (History.)
        (events/listen
            EventType/NAVIGATE
            (fn [event]
                (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

(defn- register-route
    [route-name route-uri]
    (log/debug "Registering route" route-name "->" route-uri)
    (defroute (str route-uri) [query-params] (re-frame/dispatch [:set-active-panel route-name query-params])))

(defn app-routes []
    (let [routes (filter #(not (:external (val %))) (:routes config/url-mapping))]
        (secretary/set-config! :prefix "#")
        (doall (map #(register-route (key %) (:uri (val %))) routes))
        (hook-browser-navigation)))