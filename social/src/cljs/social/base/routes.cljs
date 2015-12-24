(ns social.base.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]
              [social.welcome.views :as welcome]
              [social.registration.views :as registration]))

(defn- hook-browser-navigation! []
    (doto (History.)
        (events/listen
            EventType/NAVIGATE
            (fn [event]
                (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

(defn app-routes []
    (secretary/set-config! :prefix "#")

    ;; --------------------
    ;; ROUTES START
    ;; --------------------
    (defroute "/izba-przyjec" []
              (re-frame/dispatch [:set-active-panel [welcome/main-panel]]))

    (defroute "/porodowka" []
              (re-frame/dispatch [:set-active-panel [registration/main-panel]]))

    ;; --------------------
    ;; ROUTES END
    ;; --------------------

    (hook-browser-navigation!))
