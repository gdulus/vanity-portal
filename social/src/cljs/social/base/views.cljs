(ns social.base.views
    (:require [re-frame.core :as re-frame]
              [reagent-modals.modals :as reagent-modals]
              [social.welcome.views :as welcome]
              [social.registration.views :as registration]
              [social.registration-details.views :as registration-details]
              [social.login.views :as login]))

(defn- get-panel
    [panel-name]
    (case panel-name
        :welcome [welcome/main-panel]
        :registration [registration/main-panel]
        :registration-details [registration-details/main-panel]
        :login [login/main-panel]
        nil))

(defn main-panel []
    (let [active-panel (re-frame/subscribe [:active-panel])]
        (fn []
            [reagent-modals/modal-window (reagent-modals/modal! (get-panel @active-panel) {:size :lg})])))
