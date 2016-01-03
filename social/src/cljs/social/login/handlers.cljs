(ns social.login.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [ajax.core :refer [GET POST]]))

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

(re-frame/register-handler
    :login-authenticate
    (fn [db _]
        (let [data (get-in db [:data :login])
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "Triggering login with data" data)
                    (re-frame/dispatch [:form-errors [:login] nil])
                    (POST "/api/auth"
                          {:params          data
                           :format          :json
                           :response-format :json
                           :handler         #(re-frame/dispatch [:user-login %])
                           :error-handler   #(re-frame/dispatch [:ajax-errors [:login] (:response %)])})
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while submiting login data" errors)
                    (re-frame/dispatch [:form-errors [:login] errors])
                    db)))))
