(ns social.views.welcome.views
    (:require [social.views.welcome.subs]
              [social.views.welcome.handlers]
              [social.i18n :as i18n]
              [social.routes :as routes]))

(defn main-panel []
    (let []
        (fn []
            [:div.row {:id "intro"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.welcome.header.1")]
              [:h2.text-center (i18n/message "social.welcome.header.2")]]
             [:div.col-md-12.text-center
              ;;[:a.btn.btn-default.facebook {:on-click #(.alert js/window "fb login")} (i18n/message "social.welcome.register.fb")]
              [:a.btn.btn-default.register {:href (routes/get-route :registration)} (i18n/message "social.welcome.register.self")]]
             [:div.col-md-12.text-center.footer
              [:span (i18n/message "social.welcome.login.1")
               [:a {:href (routes/get-route :login)} (i18n/message "social.welcome.login.2")]]]])))

