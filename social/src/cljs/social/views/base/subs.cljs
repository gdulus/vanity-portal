(ns social.views.base.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]
              [social.logger :as log]))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :active-panel
    (fn [db _]
        (reaction (:active-panel @db))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :loader
    (fn [db _]
        (reaction (:loader @db))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :form-data
    (fn [db [_ data-path]]
        (let [path (into [:data] data-path)]
            (do
                (log/debug "Creating form data reaction under the path" path)
                (reaction (get-in @db path))))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :form-errors
    (fn [db [_ errors-path]]
        (let [path (into [:errors] errors-path)]
            (do
                (log/debug "Creating form error reaction under the path" path)
                (reaction (get-in @db path))))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :response-status
    (fn [db _]
        (reaction (:response-status @db))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :user
    (fn [db _]
        (reaction (:user @db))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :query-params
    (fn [db [_ panel-name]]
        (reaction (get-in @db [:query-params panel-name]))))