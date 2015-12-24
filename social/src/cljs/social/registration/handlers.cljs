(ns social.registration.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]))

(re-frame/register-handler
    :registeration-will-unmount
    (fn [db _]
        (do
            (log/info "Clearing registration screen data")
            (re-frame/dispatch [:form-errors [:registeration] nil])
            (re-frame/dispatch [:form-data [:registeration] nil])
            (assoc-in db [:loader] false))))

(defn- validate
    [form-data]
    (validation/validate form-data
                         :username [v/required]
                         :password [v/required]
                         :email [v/required v/email]
                         :gender v/required
                         :regulations [v/required [v/member `(true)]]))

(re-frame/register-handler
    :registeration-create-account
    (fn [db _]
        (let [errors (validate (get-in db [:data :registeration]))]
            (if (empty? errors)
                (do
                    (log/info "Triggering account registration with with data")
                    (re-frame/dispatch [:form-errors [:registeration] nil])
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while submiting registration data" errors)
                    (re-frame/dispatch [:form-errors [:registeration] errors])
                    db)))))
