(ns social.commons.forms.views
    (:require [re-frame.core :as re-frame]
              [clojure.string :as str]
              [social.i18n :as i18n]
              [social.commons.forms.subs]
              [social.commons.forms.handlers]
              [reagent.core :as r]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(defn response-errors
    []
    (let [status (re-frame/subscribe [:response-status])]
        (fn []
            (if @status
                [:div.errors-header
                 (i18n/message (str "social.error.status." @status))]))))

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

(defn checkbox
    [context name label]
    (let [errors (re-frame/subscribe [:form-errors [context name]])
          desc (if (seq? label) label (i18n/message label))]
        (fn [context name label]
            [:div
             [:label.checkbox-inline {:class (if (not-empty @errors) "error" "")}
              [:input {:type :checkbox :id :regulations :on-change #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-checked)])} desc]]
             (if (not-empty @errors) [:div {:class "errors"} @errors])])))

;; ----------------------------------------------------------------------------------------------

(defn- select-country-component-did-mount-handeler
    []
    (re-frame/dispatch-sync [:select-country-init]))

(defn- select-country-renderer
    [context]
    (let [state (re-frame/subscribe [:select [:country]])
          errors (re-frame/subscribe [:form-errors [context :country]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.country.label")]
             [:div.input
              [:select.form-control {:id name :on-change #(re-frame/dispatch-sync [:country-change (-> % .-target .-value)])}
               [:option {:value 0} (i18n/message "social.form.country.placeholder")]
               (for [country (:data @state)]
                   ^{:key country} [:option {:value (get country "id")} (get country "name")])]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

(defn select-country
    []
    (r/create-class {:reagent-render      select-country-renderer
                     :component-did-mount select-country-component-did-mount-handeler}))

;; ----------------------------------------------------------------------------------------------

(defn select-voivodeship
    [context]
    (let [state (re-frame/subscribe [:select [:voivodeship]])
          errors (re-frame/subscribe [:form-errors [context :voivodeship]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.voivodeship.label")]
             [:div.input
              [:select.form-control {:id        name
                                     :class     (if (and (not (nil? @state)) (:loader @state)) "loader" "")
                                     :disabled  (if (or (nil? @state) (empty? (:data @state))) "disabled" "")
                                     :on-change #(re-frame/dispatch-sync [:voivodeship-change (-> % .-target .-value)])}
               [:option {:value 0} (i18n/message "social.form.voivodeship.placeholder")]
               (for [voivodeship (:data @state)]
                   ^{:key voivodeship} [:option {:value (get voivodeship "id")} (get voivodeship "name")])]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

;; ----------------------------------------------------------------------------------------------

(defn select-city
    [context]
    (let [state (re-frame/subscribe [:select [:city]])
          errors (re-frame/subscribe [:form-errors [context :city]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.city.label")]
             [:div.input
              [:select.form-control {:id        name
                                     :class     (if (and (not (nil? @state)) (:loader @state)) "loader" "")
                                     :disabled  (if (or (nil? @state) (empty? (:data @state))) "disabled" "")
                                     :on-change #()}
               [:option {:value 0} (i18n/message "social.form.city.placeholder")]
               (for [voivodeship (:data @state)]
                   ^{:key voivodeship} [:option {:value (get voivodeship "id")} (get voivodeship "name")])]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

;; ----------------------------------------------------------------------------------------------

(defn brithday
    [context]
    (let [errors (re-frame/subscribe [:form-errors [context :birthday]])]
        (fn [context]
            [:div.birthday
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.birthday.label")]
             [:input.form-control {:type        "text"
                                   :id          "birthday"
                                   :placeholder (i18n/message "social.form.birthday.birthday.placeholder")
                                   :on-change   #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])}]])))

;; ----------------------------------------------------------------------------------------------

(defn- avatars-renderer
    [context]
    (let [errors (re-frame/subscribe [:form-errors [context :avatar]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.avatar.label")]
             [:div.avatars
              (for [index (range 1 26)]
                  ^{:key index} [:img {:src (str "/assets/social/icons/monsters/" index ".svg")}])
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

(defn- avatars-renderer-did-mount-handler
    []
    (log/debug "Did mount avatars component")
    (.slick (js/$ ".avatars") (clj->js {"dots"           false
                                        "infinite"       true
                                        "slidesToShow"   7
                                        "slidesToScroll" 5
                                        "focusOnSelect"  false
                                        "speed"          500
                                        "fade"           false})))
(defn avatars
    []
    (r/create-class {:reagent-render      avatars-renderer
                     :component-did-mount avatars-renderer-did-mount-handler}))

