(ns social.forms
    (:require [re-frame.core :as re-frame]
              [clojure.string :as str]
              [social.i18n :as i18n]))

;; ----------------------------------------------------------------------------------------------

(defn input
    ([type context name label info]
     (let [errors (re-frame/subscribe [:form-errors [context name]])]
         (fn [type context name label info]
             [:div
              [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message label)]
              [:div {:class (if (not (str/blank? info)) "input-group" "input")}
               [:input.form-control {:type type :id name :on-change #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])}]
               (if (not-empty @errors) [:div {:class "errors"} @errors])
               (if (not (str/blank? info))
                   [:div.input-group-addon
                    [:span.glyphicon.glyphicon-info-sign {:data-content (i18n/message info) :data-placement :left}]])]])))
    ([type context name label]
     (input type context name label nil)))

;; ----------------------------------------------------------------------------------------------

(defn radio
    [context name label elements]
    (let [errors (re-frame/subscribe [:form-errors [context name]])]
        (fn [context name label elements]
            [:label {:class (if (not-empty @errors) "error" "")} (i18n/message label)
             [:div.input-group
              (for [element elements]
                  ^{:key element} [:label.radio-inline [:input {:type      :radio
                                                                :value     (:v element)
                                                                :name      name
                                                                :on-change #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])}
                                                        (i18n/message (:l element))]])
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))


;; ----------------------------------------------------------------------------------------------

(defn- checkbox
    [context name label]
    (let [errors (re-frame/subscribe [:form-errors [context name]])
          desc (if (seq? label) label (i18n/message label))]
        (fn [context name label]
            [:div
             [:label.checkbox-inline {:class (if (not-empty @errors) "error" "")}
              [:input {:type :checkbox :id :regulations :on-change #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-checked)])} desc]]
             (if (not-empty @errors) [:div {:class "errors"} @errors])])))
