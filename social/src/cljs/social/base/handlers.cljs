(ns social.base.handlers
    (:require [re-frame.core :as re-frame]
              [social.base.db :as db]
              [social.logger :as log]
              [social.i18n :as i18n]
              [social.base.routes :refer [get-panel get-route get-acl get-default-route]]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :initialize-db
    (fn [_ _]
        db/default-db))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :set-active-panel
    (fn [db [_ panel-name]]
        (let [panel (get-panel panel-name)
              acl (get-acl panel-name)
              user-status (db/get-user-status db)
              default-route (get-default-route user-status)]
            (if (some #{user-status} acl)
                (do
                    (log/info "User status" user-status "Current panel" panel-name "meet ACL" acl)
                    (assoc db :active-panel panel))
                (do
                    (log/info "User status" user-status "Current panel" panel-name "doesn't meet ACL" acl "Redirecting to" default-route)
                    (re-frame/dispatch [:redirect default-route])
                    db)))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :redirect
    (fn [db [_ panel-name]]
        (let [route (get-route panel-name)]
            (do
                (log/info "Selecting route" route)
                (set! (.-location js/window) route)
                db))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :form-data
    (fn [db [_ data-path data]]
        (let [path (into [:data] data-path)]
            (do
                (log/debug "Storing data" data "under" path)
                (assoc-in db path data)))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :form-errors
    (fn [db [_ errors-path errors]]
        (let [path (into [:errors] errors-path)]
            (do
                (log/debug "Storing errors" errors "under" path)
                (assoc-in db path errors)))))

;; ----------------------------------------------------------------------------------------------

(defn- convert-errors
    [errors]
    (let [comverted-errors (map (fn [v] {(keyword (get v 0)) (i18n/message (get v 1))}) errors)]
        (into {} comverted-errors)))

(re-frame/register-handler
    :ajax-errors
    (fn [db [_ path errors]]
        (let [comverted-errors (convert-errors errors)]
            (do
                (log/info "Handling AJAX errors for the path" path comverted-errors)
                (re-frame/dispatch [:form-errors path comverted-errors])
                (assoc-in db [:loader] false)))))


;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :user-login
    (fn [db [_ user]]
        (do
            (log/info "User" user "logged in")
            (assoc-in db [:user] user))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-handler
    :user-logout
    (fn [db _]
        (do
            (log/info "User logout")
            (assoc-in db [:user] nil))))