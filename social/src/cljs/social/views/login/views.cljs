(ns social.views.login.views
    (:require [social.views.login.subs]
              [social.views.login.handlers]
              [re-frame.core :as re-frame]
              [social.commons.forms.views :as forms]
              [social.i18n :as i18n]
              [social.routes :as routes]
              [social.logger :as log]
              [reagent.core :as r]))

;; ----------------------------------------------------------------------------------------------

(defn- component-will-unmount-handeler
    []
    (do
        (log/info "Will unmount login component")
        (re-frame/dispatch-sync [:clear-component-data [:login]])))

;; ----------------------------------------------------------------------------------------------

(defn- submit-handler
    [event]
    (do
        (.preventDefault event)
        (re-frame/dispatch-sync [:login-authenticate])))

;; ----------------------------------------------------------------------------------------------

(defn- form
    []
    [:div
     [forms/input :text :login :username "social.form.username.label"]
     [forms/input :password :login :password "social.form.password.label"]])

;; ----------------------------------------------------------------------------------------------

(defn main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])]
        (fn []
            [:div.row {:id "login"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.login.welcome")]
              [:h2.text-center (i18n/message "social.login.explanation")]]
             [:div.col-md-12
              [forms/response-errors]
              [:form {:on-submit #(submit-handler %)}
               [form]
               [:div.input
                (i18n/message "social.login.forgot-password.question") [:a.link {:href (routes/get-route :registration)} (i18n/message "social.login.forgot-password.button")]]
               [:div.input
                (i18n/message "social.login.register.question") [:a.link {:href (routes/get-route :registration)} (i18n/message "social.login.register.button")]]
               [:div.form-group
                [:div.buttons
                 [:a.link {:href (routes/get-route :welcome)} (i18n/message "social.button.cancel")]
                 (if @loading
                     [:button.btn.btn-default.pull-right.loader {:disabled "disabled"} (i18n/message "social.button.login")]
                     [:button.btn.btn-default.pull-right (i18n/message "social.button.login")])]]]]])))


(defn main-panel
    []
    (r/create-class {:reagent-render         main-panel-renderer
                     :component-will-unmount component-will-unmount-handeler}))

