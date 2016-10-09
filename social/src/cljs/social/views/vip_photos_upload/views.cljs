(ns social.views.vip-photos-upload.views
    (:require [social.views.vip-photos-upload.subs]
              [social.views.vip-photos-upload.handlers]
              [re-frame.core :as re-frame]
              [social.commons.forms.views :as forms]
              [social.i18n :as i18n]
              [social.routes :as routes]
              [social.logger :as log]
              [reagent.core :as r]))

;; ----------------------------------------------------------------------------------------------

(defn- submit-handler
    []
    (let [form-data (js/FormData. (.getElementById js/document "vip-photo-upload"))]
        (social.ajax/do-post "/api/auth" form-data #(log/info %) #(log/info %))))

;; ----------------------------------------------------------------------------------------------

(defn- form
    []
    [:div
     [forms/input :file :vip-photo-upload :image "social.form.vip-image.label"]])

;; ----------------------------------------------------------------------------------------------

(defn main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])
          vip (re-frame/subscribe [:vip])]
        (fn []
            [:div.row {:id "photo-upload"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.vip-photos-upload.header" (get @vip :name))]
              [forms/flash-message]]
             [:div.col-md-12
              [forms/response-errors]
              [:form {:on-submit #(submit-handler)}
               [form]
               [:div.form-group
                [:div.buttons
                 [:a.link {:href (routes/get-route :empty)} (i18n/message "social.button.cancel")]
                 (if @loading
                     [:button.btn.btn-default.pull-right.loader {:disabled "disabled"} (i18n/message "social.button.save")]
                     [:button.btn.btn-default.pull-right (i18n/message "social.button.save")])]]]]])))

(defn main-panel
    []
    (r/create-class {:reagent-render main-panel-renderer}))

