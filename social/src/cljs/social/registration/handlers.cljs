(ns social.registration.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [ajax.core :refer [GET POST]]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :registeration-will-unmount
    (fn [db _]
        (do
            (log/info "Clearing registration screen data")
            (re-frame/dispatch [:form-errors [:registeration] nil])
            (re-frame/dispatch [:form-data [:registeration] nil])
            (assoc-in db [:loader] false))))

;; ----------------------------------------------------------------------------------------------

(defn- validate
    [form-data]
    (validation/validate form-data
                         :username [v/required]
                         :password [v/required]
                         :email [v/required v/email]
                         :gender v/required
                         :regulations [v/required [v/member `(true)]]))

(defn error-handler [response]
    (.log js/console (str "something bad happened: " (:response response))))


(re-frame/register-handler
    :registeration-create-account
    (fn [db _]
        (let [data (get-in db [:data :registeration])
              errors (validate data)]
            (if (empty? errors)
                (do
                    (log/info "Triggering account registration with data" data)
                    (re-frame/dispatch [:form-errors [:registeration] nil])
                    (POST "/api/user"
                          {:params          data
                           :format          :json
                           :response-format :json
                           :handler         #(re-frame/dispatch [:user-created %])
                           :error-handler   #(re-frame/dispatch [:ajax-errors [:registeration] (:response %)])})
                    (assoc-in db [:loader] true))
                (do
                    (log/info "Validation errors while submiting registration data" errors)
                    (re-frame/dispatch [:form-errors [:registeration] errors])
                    db)))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :user-created
    (fn [db [_ user]]
        (do
            (log/info "User created" user)
            (re-frame/dispatch [:user-login user])
            (re-frame/dispatch [:redirect :registration-details])
            (assoc-in db [:loader] false))))
