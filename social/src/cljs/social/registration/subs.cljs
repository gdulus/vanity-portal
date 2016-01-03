(ns social.registration.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-sub
    :registeration-errors
    (fn [db]
        (reaction (get-in @db [:registeration :account :errors]))))

;; ----------------------------------------------------------------------------------------------

(re-frame/register-sub
    :registeration-form-errors
    (fn [db field-name]
        (reaction (get-in @db [:registeration :account :errors field-name]))))