(ns social.views.registration.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]))

;; ----------------------------------------------------------------------------------------------

(defn- validate
    [form-data]
    (validation/validate form-data
                         :username [v/required]
                         :password [v/required]
                         :email [v/required v/email]
                         :gender v/required
                         :regulations [v/required [v/member `(true)]]))
(defn- success-handler
    [user]
    (log/info "User created" user)
    (re-frame/dispatch [:store-user user])
    (re-frame/dispatch [:redirect :registration-confirmation]))

(re-frame/register-handler
    :registeration-create-account
    (fn [db _]
        (let [data (get-in db [:data :registeration])
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
                    (re-frame/dispatch [:form-errors [:registeration] errors])
                    db)))))