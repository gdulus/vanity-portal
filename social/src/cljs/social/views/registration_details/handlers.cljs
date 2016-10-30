(ns social.views.registration-details.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.db :as db]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]
              [cljs-time.format :as f]
              [bouncer.core :as b]))

;; ----------------------------------------------------------------------------------------------

(defn- validate
    [form-data]
    (validation/validate form-data
                         :avatar [v/required]
                         :country [v/required]
                         :voivodeship [v/required]
                         :city [v/required]
                         :birthday [v/required]))

(defn- success-handler
    [user]
    (log/info "User updated" user)
    (re-frame/dispatch [:store-user user])
    (re-frame/dispatch [:action-successful [:registeration-details]]))

(re-frame/register-handler
    :registeration-update-details
    (fn [db _]
        (let [data (get-in db [:data :registeration-details])
              user-id (db/get-user-id db)
              token (db/get-token)
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "Triggering account update" data "for user" user-id)
                    (re-frame/dispatch [:form-errors [:registeration-details] nil])
                    (ajax/do-put (str "/api/user/" user-id)
                                 data
                                 token
                                 #(success-handler (:data %))
                                 #(re-frame/dispatch [:ajax-errors [:registeration-details] %]))
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while profile update" errors)
                    (re-frame/dispatch [:form-errors [:registeration-details] errors])
                    db)))))
