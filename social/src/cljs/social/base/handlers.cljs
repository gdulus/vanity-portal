(ns social.base.handlers
    (:require [re-frame.core :as re-frame]
              [social.base.db :as db]
              [social.logger :as log]
              [social.i18n :as i18n]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :initialize-db
    (fn [_ _]
        db/default-db))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-handler
    :set-active-panel
    (fn [db [_ active-panel]]
        (assoc db :active-panel active-panel)))

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
