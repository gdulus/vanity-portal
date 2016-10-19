(ns social.views.vip-photos-upload.views
    (:require [social.views.vip-photos-upload.subs]
              [social.views.vip-photos-upload.handlers]
              [re-frame.core :as re-frame]
              [social.commons.forms.views :as forms]
              [social.i18n :as i18n]
              [social.routes :as routes]
              [social.logger :as log]
              [reagent.core :as r]
              [social.db :as db]))

;; ----------------------------------------------------------------------------------------------

(defn- component-will-unmount-handeler
    []
    (do
        (log/info "Will unmount vip-photo-upload component")
        (re-frame/dispatch-sync [:clear-component-data [:vip-photo-upload]])))

;; ----------------------------------------------------------------------------------------------

(defn- submit-handler
    [event]
    (.preventDefault event)
    (let [files (-> (.getElementById js/document "image") .-files)
          file (.item files 0)
          form-data (new js/FormData)]
        (.append form-data "image" file)
        (re-frame/dispatch-sync [:upload-image form-data])))

;; ----------------------------------------------------------------------------------------------

(defn- form
    []
    [:div
     [forms/file :vip-photo-upload :image "social.form.vip-image.label"]])

;; ----------------------------------------------------------------------------------------------

(defn main-panel-renderer
    []
    (let [loading (re-frame/subscribe [:loader])
          vip (re-frame/subscribe [:vip])]
        (fn []
            [:div.row {:id "photo-upload"}
             [:div.col-md-12
              [:h1.text-center (i18n/message "social.vip-photos-upload.header" (@vip "name"))]
              [forms/flash-message]]
             [:div.col-md-12
              [forms/response-info]
              [:form {:id "vip-photo" :on-submit #(submit-handler %) :enctype "multipart/form-data"}
               [form]
               [:div.form-group
                [:div.buttons
                 [:a.link {:href (routes/get-route :empty)} (i18n/message "social.button.cancel")]
                 (if @loading
                     [:button.btn.btn-default.pull-right.loader {:disabled "disabled"} (i18n/message "social.button.save")]
                     [:button.btn.btn-default.pull-right (i18n/message "social.button.save")])]]]]])))

(defn main-panel
    []
    (r/create-class {:reagent-render         main-panel-renderer
                     :component-will-unmount component-will-unmount-handeler}))

