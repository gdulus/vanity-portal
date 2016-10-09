(ns social.views.vip-photos-upload.views
    (:require [social.views.vip-photos-upload.subs]
              [social.views.vip-photos-upload.handlers]
              [re-frame.core :as re-frame]
              [social.commons.forms.views :as forms]
              [social.i18n :as i18n]
              [social.routes :as routes]
              [social.logger :as log]
              [reagent.core :as r]))

(defn main-panel
    []
    (let []
        (fn []
            [:div "Photos upload"])))

;(defn main-panel
;    []
;    (r/create-class {:reagent-render main-panel-renderer}))

