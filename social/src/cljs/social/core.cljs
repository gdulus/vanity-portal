(ns social.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [social.views.base.handlers]
              [social.views.base.subs]
              [social.routes :as routes]
              [social.views.base.views :as base]
              [goog.events.EventType :as EventType]
              [social.events :as events]))

(defn init-start-button []
    (events/add-listener "user-button"
                         EventType/CLICK
                         #(re-frame/dispatch-sync [:toggle-main-view])))

(defn init-popovers []
    (.popover (js/$ ".init-popover")))

(defn mount-root []
    (reagent/render [base/main-panel]
                    (.getElementById js/document "social")))

(defn ^:export init []
    (init-start-button)
    (init-popovers)
    (routes/app-routes)
    (re-frame/dispatch-sync [:initialize-db])
    (mount-root))
