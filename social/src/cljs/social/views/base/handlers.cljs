(ns social.views.base.handlers
    (:require [re-frame.core :as re-frame]
              [social.db :as db]
              [social.logger :as log]
              [social.i18n :as i18n]
              [clojure.string :as str]
              [social.ajax :as ajax]
              [social.routes :as routes]
              [reagent-modals.modals :as reagent-modals]
              [social.views.welcome.views :as welcome]
              [social.views.registration.views :as registration]
              [social.views.registration-details.views :as registration-details]
              [social.views.registration-confirmation.views :as registration-confirmation]
              [social.views.account-activation.views :as account-activation]
              [social.views.login.views :as login]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :initialize-db
    (fn [_ _]
        (let [token (:token @db/storage)
              id (:user-id @db/storage)]
            (if (not (str/blank? token))
                (ajax/do-get "/api/user"
                             {:id id}
                             token
                             #(re-frame/dispatch [:store-user (:data %)])
                             #(re-frame/dispatch [:ajax-errors [:init] %])))
            (log/info "Got token" token)
            db/default-db)))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :clear-component-data
    (fn [db [_ path]]
        (log/info "Clearing data for component under path" path)
        (re-frame/dispatch [:form-errors path nil])
        (re-frame/dispatch [:form-data path nil])
        (-> db
            (assoc-in [:response-status] nil)
            (assoc-in [:loader] false))))

;; ----------------------------------------------------------------------------------------------

(defn- get-panel
    [panel-name]
    (case panel-name
        :welcome [welcome/main-panel]
        :registration [registration/main-panel]
        :registration-confirmation [registration-confirmation/main-panel]
        :registration-details [registration-details/main-panel]
        :account-activation [account-activation/main-panel]
        :login [login/main-panel]
        nil))

;(re-frame/register-handler
;    :set-active-panel
;    (fn [db [_ panel-name query-params]]
;        (let [acl (routes/get-acl panel-name)
;              user-status (db/get-user-status db)
;              default-route (routes/get-default-route user-status)]
;            (if (some #{user-status} acl)
;                (do
;                    (log/info "User status" user-status "Current panel" panel-name "with params" query-params "meet ACL" acl)
;                    (reagent-modals/modal! (get-panel panel-name) {:size :lg})
;                    (log/info "H(:store-query-params): Storing request query-params" query-params "for panel" panel-name)
;                    (assoc-in db [:query-params panel-name] query-params))
;                (do
;                    (log/info "User status" user-status "Current panel" panel-name "doesn't meet ACL" acl "Redirecting to" default-route)
;                    (re-frame/dispatch [:redirect default-route])
;                    db)))))

(re-frame/register-handler
    :set-active-panel
    (fn [db [_ panel-name query-params]]
        (let [acl (routes/get-acl panel-name)
              user-status (db/get-user-status db)
              default-route (routes/get-default-route user-status)]
            (if (some #{user-status} acl)
                (do
                    (log/info "User status" user-status "Current panel" panel-name "with params" query-params "meet ACL" acl)
                    (log/info "H(:store-query-params): Storing request query-params" query-params "for panel" panel-name)
                    (-> db
                        (assoc-in [:active-panel] (get-panel panel-name))
                        (assoc-in [:query-params panel-name] query-params)))
                (do
                    (log/info "User status" user-status "Current panel" panel-name "doesn't meet ACL" acl "Redirecting to" default-route)
                    (re-frame/dispatch [:redirect default-route])
                    db)))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :redirect
    (fn [db [_ panel-name]]
        (let [route (routes/get-route panel-name)]
            (log/info "H(:redirect): Redirecting to the route" route)
            (set! (.-location js/window) route)
            db)))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :form-data
    (fn [db [_ data-path data]]
        (let [path (into [:data] data-path)]
            (log/info "H(:form-data): Storing form data" data "under path" path)
            (assoc-in db path data))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :form-errors
    (fn [db [_ errors-path errors]]
        (let [path (into [:errors] errors-path)]
            (log/info "H(:form-errors): Storing form errors" (if (nil? errors) "nil" errors) "under path" path)
            (assoc-in db path errors))))

;; ----------------------------------------------------------------------------------------------

(defn- convert-errors
    [errors]
    (let [comverted-errors (map (fn [v] {(keyword (get v 0)) (i18n/message (get v 1))}) errors)]
        (into {} comverted-errors)))

(re-frame/register-handler
    :ajax-errors
    (fn [db [_ path response]]
        (let [comverted-errors (convert-errors (:data response))
              status (:status response)]
            (log/info "H(:ajax-errors): Storing ajax error" comverted-errors "with status" status "under path" path)
            (re-frame/dispatch [:form-errors path comverted-errors])
            (-> db
                (assoc-in [:response-status] status)
                (assoc-in [:loader] false)))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :store-user
    (fn [db [_ user]]
        (log/info "H(:store-user): Storing user" user)
        (-> db
            (assoc-in [:user] user)
            (assoc-in [:loader] false))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :store-token
    (fn [db [_ token]]
        (log/info "H(:store-token): Stroing token" token)
        (swap! db/storage assoc :token token)
        db))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :store-user-id
    (fn [db [_ user-id]]
        (log/info "H(:store-user-id): Stroing user id" user-id)
        (swap! db/storage assoc :user-id user-id)
        db))
