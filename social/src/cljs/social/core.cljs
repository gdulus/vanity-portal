(ns social.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [social.views.base.handlers]
              [social.views.base.subs]
              [social.routes :as routes]
              [social.views.base.views :as base]
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
    (reagent/render [base/main-panel]
                    (.getElementById js/document "social")))

(defn ^:export init []
    (init-start-button)
    (routes/app-routes)
    (re-frame/dispatch-sync [:initialize-db])
    (mount-root))
