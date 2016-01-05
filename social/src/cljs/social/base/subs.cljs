(ns social.base.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]
              [social.logger :as log]))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :name
    (fn [db]
        (reaction (:name @db))))

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