(ns social.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [social.base.handlers]
              [social.base.subs]
              [social.base.routes :as routes]
              [social.base.views :as views]
              [secretary.core :as secretary]
              [goog.events.EventType :as EventType]
              [social.events :as events]))

(defn init-start-button []
    (events/add-listener "user-button"
                         EventType/CLICK
                         #(do
                             (set! (.-location js/window) "#/izba-przyjec")
                             (secretary/dispatch! "/izba-przyjec"))))
(defn mount-root []
    (reagent/render [views/main-panel]
                    (.getElementById js/document "social")))

(defn ^:export init []
    (init-start-button)
    (routes/app-routes)
    (re-frame/dispatch-sync [:initialize-db])
    (mount-root))
