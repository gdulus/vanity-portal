(ns social.base.handlers
    (:require [re-frame.core :as re-frame]
              [social.base.db :as db]
              [social.logger :as log]))

(re-frame/register-handler
    :initialize-db
    (fn [_ _]
        db/default-db))

(re-frame/register-handler
    :set-active-panel
    (fn [db [_ active-panel]]
        (assoc db :active-panel active-panel)))

(re-frame/register-handler
    :form-data
    (fn [db [_ data-path data]]
        (let [path (into [:data] data-path)]
            (do
                (log/debug "Storing data" data "under" path)
                (assoc-in db path data)))))

(re-frame/register-handler
    :form-errors
    (fn [db [_ errors-path errors]]
        (let [path (into [:errors] errors-path)]
            (do
                (log/debug "Storing errors" errors "under" path)
                (assoc-in db path errors)))))
