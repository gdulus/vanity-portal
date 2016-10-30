(ns social.views.registration.views
    (:require [reagent.core :as r]
              [social.views.registration.subs]
              [social.views.registration.handlers]
              [social.commons.forms.views :as forms]
              [social.i18n :as i18n]
              [re-frame.core :as re-frame]
              [social.logger :as log]
              [social.routes :as routes]))

;; ----------------------------------------------------------------------------------------------

(defn- component-did-mount-handeler
    []
    (do
        (log/info "Did mount registration component")
        (js/setTimeout #(.popover (js/$ ".glyphicon-info-sign")) 100)))

;; ----------------------------------------------------------------------------------------------

(defn- component-will-unmount-handeler
    []
    (do
        (log/info "Will unmount registration component")
        (re-frame/dispatch-sync [:clear-component-data [:registeration]])))

;; ----------------------------------------------------------------------------------------------

(defn- submit-handler
    [event]
    (do
        (.preventDefault event)
        (re-frame/dispatch-sync [:registeration-create-account])))

;; ----------------------------------------------------------------------------------------------

(defn- form
    []
    [:div
     [forms/input :text :registeration :username "social.form.username.label" "social.form.username.info"]
     [forms/input :password :registeration :password "social.form.password.label" "social.form.password.info"]
     [forms/input :email :registeration :email "social.form.email.label" "social.form.email.info"]
     [forms/radio :registeration :gender "social.form.gender.label" [{:v "WOMAN" :l "social.form.woman"} {:v "MAN" :l "social.form.man"}]]
     [forms/checkbox :registeration :regulations [:span (i18n/message "social.register.confirm.info")
                                                  [:a.link {:href (routes/get-route :regulations) :target "_blank"} (i18n/message "social.register.confirm.link")]]]])

;; ----------------------------------------------------------------------------------------------

(defn- main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])]
        (fn []
            [:div.row {:id "register"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.register.header")]
              [:h2.text-center (i18n/message "social.register.explanation")]]
             [:div.col-md-12
              [forms/response-info]
              [:form {:on-submit #(submit-handler %)}
               [form]
               [:div.form-group
                [:div.buttons
                 [:a.link {:href (routes/get-route :welcome)} (i18n/message "social.button.cancel")]
                 (if @loading
                     [:button.btn.btn-default.pull-right.loader {:disabled "disabled"} (i18n/message "social.button.register")]
                     [:button.btn.btn-default.pull-right (i18n/message "social.button.register")])]]]]])))

(defn main-panel
    []
    (r/create-class {:reagent-render         main-panel-renderer
                     :component-did-mount    component-did-mount-handeler
                     :component-will-unmount component-will-unmount-handeler}))
