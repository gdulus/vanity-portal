(ns social.registration-details.views
    (:require [reagent.core :as r]
              [social.registration.subs]
              [social.registration.handlers]
              [re-frame.core :as re-frame]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(defn- component-did-mount-handeler
    []
    (do
        (log/info "Did mount registration details component")))

;; ----------------------------------------------------------------------------------------------

(defn- component-will-unmount-handeler
    []
    (do
        (log/info "Will unmount registration details component")
        (re-frame/dispatch-sync [:registeration-will-unmount])))

;; ----------------------------------------------------------------------------------------------

(defn main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])]
        (fn []
            [:div.row {:id "register"}
             "hiiiiiiiiii"])))

(defn main-panel
    []
    (r/create-class {:reagent-render         main-panel-renderer
                     :component-did-mount    component-did-mount-handeler
                     :component-will-unmount component-will-unmount-handeler}))
