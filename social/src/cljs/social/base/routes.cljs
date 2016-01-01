(ns social.base.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]
              [social.welcome.views :as welcome]
              [social.registration.views :as registration]
              [social.registration-details.views :as registration-details]))

(defn- hook-browser-navigation! []
    (doto (History.)
        (events/listen
            EventType/NAVIGATE
            (fn [event]
                (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; ----------------------------------------------------------------------------------------------

(defn route
    [route]
    (case route
        :welcome "#/izba-przyjec"
        :registration "#/porodowka"
        :registration-details "#/registration-details"
        :login "#/login"
        :regulations "/regulamin"))

;; ----------------------------------------------------------------------------------------------

(defn app-routes []
    ;; --------------------
    ;; Config
    ;; --------------------
    (secretary/set-config! :prefix "#")

    ;; --------------------
    ;; Routes
    ;; --------------------
    (defroute "/izba-przyjec"
              []
              (re-frame/dispatch [:set-active-panel [welcome/main-panel]]))

    (defroute "/porodowka"
              []
              (re-frame/dispatch [:set-active-panel [registration/main-panel]]))

    (defroute "/registration-details"
              []
              (re-frame/dispatch [:set-active-panel [registration-details/main-panel]]))

    ;; --------------------
    ;; Start
    ;; --------------------
    (hook-browser-navigation!))
