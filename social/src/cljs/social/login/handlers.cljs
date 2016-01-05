(ns social.login.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :login-will-unmount
    (fn [db _]
        (do
            (log/info "Clearing login screen data")
            (re-frame/dispatch [:form-errors [:login] nil])
            (re-frame/dispatch [:form-data [:login] nil])
            (assoc-in db [:loader] false))))

;; ----------------------------------------------------------------------------------------------

(defn- validate
    [form-data]
    (validation/validate form-data
                         :username [v/required]
                         :password [v/required]))

(defn- handle-authenticate-success
    [response]
    (let [user (:data response)]
        (re-frame/dispatch [:store-user-id (get user "id")])
        (re-frame/dispatch [:store-user user])))

(re-frame/register-handler
    :login-authenticate
    (fn [db _]
        (let [data (get-in db [:data :login])
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "H(:login-authenticate): Triggering login with data" data)
                    (re-frame/dispatch [:form-errors [:login] nil])
                    (ajax/post "/api/auth"
                               data
                               #(handle-authenticate-success %)
                               #(re-frame/dispatch [:ajax-errors [:login] %]))
                    (assoc-in db [:loader] true))
                (do
                    (log/info "H(:login-authenticate): Validation errors while submiting login data" errors)
                    (re-frame/dispatch [:form-errors [:login] errors])
                    db)))))
