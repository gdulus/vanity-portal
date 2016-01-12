(ns social.commons.forms.handlers
    (:require [re-frame.core :as re-frame]
              [social.logger :as log]
              [social.ajax :as ajax]))


(re-frame/register-handler
    :select-country-init
    (fn [db [_ data]]
        (if (empty? data)
            (do
                (log/info "H(:select-country-init): Initializing country select")
                (ajax/do-get "/api/country"
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
        (ajax/do-get "/api/voivodeship"
                  {:countryId country-id}
                  #(re-frame/dispatch [:select-voivodeship-init (:data %)])
                  #(log/error %))
        (-> db
            (assoc-in [:select :voivodeship :loader] true)
            (assoc-in [:select :voivodeship :data] nil)
            (assoc-in [:select :city :data] nil))))

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
        (ajax/do-get "/api/city"
                  {:voivodeshipId voivodeship-id}
                  #(re-frame/dispatch-sync [:select-city-init (:data %)])
                  #(log/error %))
        (-> db
            (assoc-in [:select :city :loader] true)
            (assoc-in [:select :city :data] nil))))
