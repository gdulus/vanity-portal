(ns social.views.vip-photos-upload.handlers
    (:require [re-frame.core :as re-frame]
              [social.logger :as log]
              [social.db :as db]))

(defn- success-handler
    []
    (log/info "Image uploaded")
    (re-frame/dispatch [:action-successful [:vip-photo-upload]]))

(re-frame/register-handler
    :upload-image
    (fn [db [_ form-data]]
        (let [vip-id (db/get-vip-id db)
              token (db/get-token)]
            (log/info "Uploading image for VIP" vip-id)
            (social.ajax/do-file-upload
                (str "/api/vip/" vip-id "/image")
                form-data
                token
                success-handler
                #(re-frame/dispatch [:ajax-errors [:vip-photo-upload] %]))
            (assoc-in db [:loader] true))))
