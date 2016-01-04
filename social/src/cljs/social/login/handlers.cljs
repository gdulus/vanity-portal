(ns social.login.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [ajax.core :refer [GET POST]]
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
    (let [userId (get response "userId")
          token (get response "token")]
        (log/info "Got token" token "and user id" userId)
        (re-frame/dispatch [:store-token token])

        (GET "/api/user"
             {:response-format :json
              :params          {:id userId}
              :headers         {"X-Auth-Token" token}
              :handler         #(re-frame/dispatch [:user-login %])
              :error-handler   #(re-frame/dispatch [:ajax-errors [:login] %])})))

(re-frame/register-handler
    :login-authenticate
    (fn [db _]
        (let [data (get-in db [:data :login])
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "Triggering login with data" data)
                    (re-frame/dispatch [:form-errors [:login] nil])
                    (ajax/post "/api/auth"
                               data
                               (fn [rsp] (log/info rsp))
                               (fn [rsp] (log/info rsp)))
                    ;(POST "/api/auth"
                    ;      {:params          data
                    ;       :format          :json
                    ;       :response-format :json
                    ;       :handler         #(handle-authenticate-success %)
                    ;       :error-handler   #(re-frame/dispatch [:ajax-errors [:login] %])})
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while submiting login data" errors)
                    (re-frame/dispatch [:form-errors [:login] errors])
                    db)))))
