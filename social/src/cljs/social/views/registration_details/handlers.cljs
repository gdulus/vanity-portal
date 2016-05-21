(ns social.views.registration-details.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]))

;; ----------------------------------------------------------------------------------------------

(defn- validate
    [form-data]
    (validation/validate form-data
                         :country [v/required]
                         :voivodeship [v/required]
                         :city [v/required]
                         :birthday [v/datetime]))
(defn- success-handler
    [user]
    (log/info "User created" user)
    (re-frame/dispatch [:store-user user])
    (re-frame/dispatch [:redirect :registration-confirmation]))

(re-frame/register-handler
    :registeration-update-details
    (fn [db _]
        (let [data (get-in db [:data :registeration-details])
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "Triggering account registration with data" data)
                    (re-frame/dispatch [:form-errors [:registeration] nil])
                    (ajax/do-post "/api/user"
                                  data
                                  #(success-handler (:data %))
                                  #(re-frame/dispatch [:ajax-errors [:registeration] %]))
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while submiting registration data" errors)
                    (re-frame/dispatch [:form-errors [:registeration-details] errors])
                    db)))))
