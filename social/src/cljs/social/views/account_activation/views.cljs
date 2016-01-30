(ns social.views.account-activation.views
    (:require [social.views.registration-confirmation.subs]
              [social.views.registration-confirmation.handlers]
              [re-frame.core :as re-frame]
              [social.i18n :as i18n]))

;; ----------------------------------------------------------------------------------------------

(defn- main-panel
    []
    (let [user (re-frame/subscribe [:user])]
        (fn []
            [:div.row {:id "account-activation"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.account-activation.header" (get @user "username"))]
              [:div.text-center (i18n/message "social.account-activation.explanation")]
              [:div.loader]]])))

