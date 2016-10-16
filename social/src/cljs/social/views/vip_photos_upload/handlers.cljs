(ns social.views.vip-photos-upload.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]
              [social.db :as db]))


(re-frame/register-handler
    :upload-image
    (fn [db [_ form-data]]
        (let [vip-id (db/get-vip-id db)
              token (db/get-token)]
            (log/info "Uploading image for VIP" vip-id)
            (social.ajax/do-file-upload (str "/api/vip/" vip-id "/image") form-data token #(log/info %) #(log/info %))
            db)))
