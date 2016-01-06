(ns social.base.handlers
    (:require [re-frame.core :as re-frame]
              [social.base.db :as db]
              [social.logger :as log]
              [social.i18n :as i18n]
              [clojure.string :as str]
              [social.ajax :as ajax]
              [social.base.routes :as routes]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :initialize-db
    (fn [_ _]
        (let [token (:token @db/storage)
              id (:user-id @db/storage)]
            (if (not (str/blank? token))
                (ajax/get "/api/user"
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

(re-frame/register-handler
    :set-active-panel
    (fn [db [_ panel-name]]
        (let [acl (routes/get-acl panel-name)
              user-status (db/get-user-status db)
              default-route (routes/get-default-route user-status)]
            (if (some #{user-status} acl)
                (do
                    (log/debug "User status" user-status "Current panel" panel-name "meet ACL" acl)
                    (assoc db :active-panel panel-name))
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
    :delete-user
    (fn [db _]
        (log/info "H(:delete-user): Removing user")
        (-> db
            (assoc-in [:user] nil)
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
    :select-country-init
    (fn [db [_ data]]
        (if (empty? data)
            (do
                (log/info "H(:select-country-init): Initializing country select")
                (ajax/get "/api/country"
                          {}
                          #(re-frame/dispatch [:select-country-init (:data %)])
                          #(log/error %))
                (assoc-in db [:select :country :loader] true))
            (do
                (log/info "H(:select-country-init): Storing country data" data)
                (-> db
                    (assoc-in [:select :country :loader] false)
                    (assoc-in [:select :country :data] data))))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :select-voivodeship-init
    (fn [db [_ data]]
        (log/info "H(:select-voivodeship-init): Storing country data" data)
        (-> db
            (assoc-in [:select :voivodeship :loader] false)
            (assoc-in [:select :voivodeship :data] data))))

(re-frame/register-handler
    :country-change
    (fn [db [_ country-id]]
        (log/info "H(:country-change): Initializing voivodeship select")
        (ajax/get "/api/voivodeship"
                  {:countryId country-id}
                  #(re-frame/dispatch [:select-voivodeship-init (:data %)])
                  #(log/error %))
        (assoc-in db [:select :voivodeship :loader] true)))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :select-city-init
    (fn [db [_ data]]
        (log/info "H(:select-city-init): Storing city data" (count data))
        (-> db
            (assoc-in [:select :city :loader] false)
            (assoc-in [:select :city :data] data))))

(re-frame/register-handler
    :voivodeship-change
    (fn [db [_ voivodeship-id]]
        (log/info "H(:voivodeship-change): Initializing cities data")
        (ajax/get "/api/city"
                  {:voivodeshipId voivodeship-id}
                  #(re-frame/dispatch-sync [:select-city-init (:data %)])
                  #(log/error %))
        (-> db
            (assoc-in [:select :city :loader] true)
            (assoc-in [:select :city :data] []))))
