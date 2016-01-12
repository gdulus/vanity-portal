(ns social.views.registration-details.views
    (:require [social.views.registration-details.subs]
              [social.views.registration-details.handlers]
              [social.logger :as log]
              [re-frame.core :as re-frame]
              [social.forms :as forms]
              [social.i18n :as i18n]
              [social.routes :as routes]
              [reagent.core :as r]))

;; ----------------------------------------------------------------------------------------------

(defn- component-will-unmount-handeler
    []
    (do
        (log/info "Will unmount registration-details  component")
        (re-frame/dispatch-sync [:clear-component-data [:registeration-details]])))

;; ----------------------------------------------------------------------------------------------

(defn- submit-handler
    [event]
    (do
        (.preventDefault event)
        (re-frame/dispatch-sync [:registeration-update-details])))

;; ----------------------------------------------------------------------------------------------

(defn- form
    []
    [:div
     [forms/select-country :registeration-details]
     [forms/select-voivodeship :registeration-details]
     [forms/select-city :registeration-details]])

;; ----------------------------------------------------------------------------------------------

(defn- main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])
          user (re-frame/subscribe [:user])]
        (fn []
            [:div.row {:id "register-details"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.register-details.header" (get @user "username"))]
              [:h2.text-center (i18n/message "social.register-details.explanation")]]
             [:div.col-md-12
              [:form {:on-submit #(submit-handler %)}
               [form]
               [:div.form-group
                [:div.buttons
                 [:a.link {:href (routes/get-route :welcome)} (i18n/message "social.button.later")]
                 (if @loading
                     [:button.btn.btn-default.pull-right.loader {:disabled "disabled"} (i18n/message "social.button.save")]
                     [:button.btn.btn-default.pull-right (i18n/message "social.button.save")])]]]]])))

(defn main-panel
    []
    (r/create-class {:reagent-render         main-panel-renderer
                     :component-will-unmount component-will-unmount-handeler}))
