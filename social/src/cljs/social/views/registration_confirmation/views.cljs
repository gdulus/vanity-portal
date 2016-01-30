(ns social.views.registration-confirmation.views
    (:require [social.views.registration-confirmation.subs]
              [social.views.registration-confirmation.handlers]
              [re-frame.core :as re-frame]
              [social.i18n :as i18n]
              [social.routes :as routes]))

;; ----------------------------------------------------------------------------------------------

(defn- main-panel
    []
    (let [user (re-frame/subscribe [:user])]
        (fn []
            [:div.row {:id "register-confirmation"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.register-confirmation.header" (get @user "username"))]
              [:div.text-center (i18n/message "social.register-confirmation.explanation")]]
             [:div.col-md-12.text-center.footer
              [:span (i18n/message "social.register-confirmation.back.1")
               [:a {:href (routes/get-route :welcome)} (i18n/message "social.register-confirmation.back.2")]]]])))

