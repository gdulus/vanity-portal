(ns social.welcome.views
    (:require [social.welcome.subs]
              [social.welcome.handlers]
              [social.base.routes :refer [get-route]]
              [social.i18n :as i18n]))

(defn main-panel []
    (let []
        (fn []
            [:div.row {:id "intro"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.welcome.header.1")]
              [:h2.text-center (i18n/message "social.welcome.header.2")]]
             [:div.col-md-12.text-center
              [:a.btn.btn-default.facebook {:on-click #(.alert js/window "fb login")} (i18n/message "social.welcome.register.fb")]
              [:a.btn.btn-default.register {:href (get-route :registration)} (i18n/message "social.welcome.register.self")]]
             [:div.col-md-12.text-center.footer
              [:span (i18n/message "social.welcome.login.1")
               [:a {:href (get-route :login)} (i18n/message "social.welcome.login.2")]]]])))

