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

;; ----------------------------------------------------------------------------------------------

(def config {:routes   {:welcome              {:uri   "/izba-przyjec"
                                               :panel [welcome/main-panel]
                                               :acl   [:not-logged-in]}

                        :registration         {:uri   "/porodowka"
                                               :panel [registration/main-panel]
                                               :acl   [:not-logged-in]}

                        :registration-details {:uri   "/registration-details"
                                               :panel [registration-details/main-panel]
                                               :acl   [:logged-in :first-time-logged-in]}

                        :login                {:uri   "/login"
                                               :panel [registration-details/main-panel]
                                               :acl   [:not-logged-in]}

                        :regulations          {:uri "/regulamin"}

                        }

             :defaults {:not-logged-in        :welcome
                        :first-time-logged-in :registration-details
                        :logged-in            :registration-details}})

;; ----------------------------------------------------------------------------------------------

(defn- hook-browser-navigation! []
    (doto (History.)
        (events/listen
            EventType/NAVIGATE
            (fn [event]
                (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; ----------------------------------------------------------------------------------------------

(defn get-route
    [name]
    (let [route (get-in config [:routes name])]
        (if (nil? (route :panel))
            (route :uri)
            (str "#" (route :uri)))))

;; ----------------------------------------------------------------------------------------------

(defn get-panel
    [name]
    (get-in config [:routes name :panel]))

;; ----------------------------------------------------------------------------------------------

(defn get-acl
    [name]
    (get-in config [:routes name :acl]))

;; ----------------------------------------------------------------------------------------------

(defn get-default-route
    [user-status]
    (get-in config [:defaults user-status]))

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
              (re-frame/dispatch [:set-active-panel :welcome]))

    (defroute "/porodowka"
              []
              (re-frame/dispatch [:set-active-panel :registration]))

    (defroute "/registration-details"
              []
              (re-frame/dispatch [:set-active-panel :registration-details]))

    ;; --------------------
    ;; Start
    ;; --------------------
    (hook-browser-navigation!))
