(ns social.commons.forms.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]
              [social.logger :as log]))

(re-frame/register-sub
    :select
    (fn [db [_ raw-path]]
        (let [path (into [:select] raw-path)]
            (log/info "Creatng reaction get select data for a path" path)
            (reaction (get-in @db path)))))

;; --------------------------------------------------------------------------------------------

(re-frame/register-sub
    :flash-message
    (fn [db [_]]
        (reaction (get-in @db [:flash-message :data]))))

