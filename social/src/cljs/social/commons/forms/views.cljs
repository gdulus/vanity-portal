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

(defn- selected?
    [selected-value current-value]
    (if (== selected-value current-value)
        "seleted"
        nil))

(defn- select-country-component-did-mount-handeler
    [this]
    (let [name :country
          user (re-frame/subscribe [:user])
          context (last (r/argv this))
          user-country (@user "country")]
        (if (not (nil? user-country))
            (do
                (re-frame/dispatch-sync [:form-data [context name] user-country])
                (re-frame/dispatch-sync [:country-change user-country])))
        (re-frame/dispatch-sync [:select-country-init])))

(defn- select-country-renderer
    [context]
    (let [name :country
          selected (re-frame/subscribe [:form-data [context name]])
          state (re-frame/subscribe [:select [:country]])
          errors (re-frame/subscribe [:form-errors [context name]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.country.label")]
             [:div.input
              [:select.form-control {:id name :on-change #(do (re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])
                                                              (re-frame/dispatch-sync [:country-change (-> % .-target .-value)]))}
               [:option {:value 0} (i18n/message "social.form.country.placeholder")]
               (doall (for [country (:data @state)]
                          ^{:key country} [:option {:value    (get country "id")
                                                    :selected (selected? @selected (get country "id"))} (get country "name")]))]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))


(defn select-country
    []
    (r/create-class {:reagent-render      select-country-renderer
                     :component-did-mount select-country-component-did-mount-handeler}))

;; ----------------------------------------------------------------------------------------------

(defn- select-voivodeship-component-did-mount-handeler
    [this]
    (let [name :voivodeship
          user (re-frame/subscribe [:user])
          context (last (r/argv this))
          user-voivodeship (@user "voivodeship")]
        (if (not (nil? user-voivodeship))
            (do
                (re-frame/dispatch-sync [:form-data [context name] user-voivodeship])
                (re-frame/dispatch-sync [:voivodeship-change user-voivodeship])))
        (re-frame/dispatch-sync [:select-country-init])))

(defn select-voivodeship-renderer
    [context]
    (let [name :voivodeship
          selected (re-frame/subscribe [:form-data [context name]])
          state (re-frame/subscribe [:select [:voivodeship]])
          errors (re-frame/subscribe [:form-errors [context name]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.voivodeship.label")]
             [:div.input
              [:select.form-control {:id        name
                                     :class     (if (and (not (nil? @state)) (:loader @state)) "loader" "")
                                     :disabled  (if (or (nil? @state) (empty? (:data @state))) "disabled" "")
                                     :on-change #(do (re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])
                                                     (re-frame/dispatch-sync [:voivodeship-change (-> % .-target .-value)]))}
               [:option {:value 0} (i18n/message "social.form.voivodeship.placeholder")]
               (doall (for [voivodeship (:data @state)]
                          ^{:key voivodeship} [:option {:value    (get voivodeship "id")
                                                        :selected (selected? @selected (get voivodeship "id"))} (get voivodeship "name")]))]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

(defn select-voivodeship
    []
    (r/create-class {:reagent-render      select-voivodeship-renderer
                     :component-did-mount select-voivodeship-component-did-mount-handeler}))

;; ----------------------------------------------------------------------------------------------

(defn- select-city-renderer-did-mount-handeler
    [this]
    (let [name :city
          user (re-frame/subscribe [:user])
          context (last (r/argv this))
          user-city (@user "city")]
        (if (not (nil? user-city))
            (re-frame/dispatch-sync [:form-data [context name] user-city]))
        (re-frame/dispatch-sync [:select-country-init])))

(defn select-city-renderer
    [context]
    (let [name :city
          selected (re-frame/subscribe [:form-data [context name]])
          state (re-frame/subscribe [:select [:city]])
          errors (re-frame/subscribe [:form-errors [context name]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.city.label")]
             [:div.input
              [:select.form-control {:id        name
                                     :class     (if (and (not (nil? @state)) (:loader @state)) "loader" "")
                                     :disabled  (if (or (nil? @state) (empty? (:data @state))) "disabled" "")
                                     :on-change #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])}
               [:option {:value 0} (i18n/message "social.form.city.placeholder")]
               (doall (for [city (:data @state)]
                          ^{:key city} [:option {:value    (get city "id")
                                                 :selected (selected? @selected (get city "id"))} (get city "name")]))]
              (if (not-empty @errors) [:div {:class "errors"} @errors])]])))

(defn select-city
    []
    (r/create-class {:reagent-render      select-city-renderer
                     :component-did-mount select-city-renderer-did-mount-handeler}))

;; ----------------------------------------------------------------------------------------------

(defn brithday-renderer
    [context]
    (let [name :birthday
          form-data (re-frame/subscribe [:form-data [context name]])
          errors (re-frame/subscribe [:form-errors [context name]])]
        (fn [context]
            [:div.birthday
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.birthday.label")]
             [:input.form-control {:type        "text"
                                   :id          "birthday"
                                   :value       @form-data
                                   :placeholder (i18n/message "social.form.birthday.birthday.placeholder")
                                   :on-change   #(re-frame/dispatch-sync [:form-data [context name] (-> % .-target .-value)])}]
             (if (not-empty @errors) [:div {:class "errors"} @errors])])))

(defn- brithday-component-will-mount-handeler
    [this]
    (let [name :birthday
          user (re-frame/subscribe [:user])
          context (last (r/argv this))]
        (re-frame/dispatch-sync [:form-data [context name] (@user "birthday")])))

(defn brithday
    []
    (r/create-class {:reagent-render       brithday-renderer
                     :component-will-mount brithday-component-will-mount-handeler}))

;; ----------------------------------------------------------------------------------------------

(defn- get-image-data
    [event]
    (let [target (.-target event)
          img (js/$ target)
          src (.attr img "src")]
        src))

(defn- avatars-renderer
    [context]
    (let [name :avatar
          data (re-frame/subscribe [:form-data [context :avatar]])
          errors (re-frame/subscribe [:form-errors [context :avatar]])]
        (fn [context]
            [:div
             [:label {:for name :class (if (not-empty @errors) "error" "")} (i18n/message "social.form.avatar.label")]
             [:div.avatars
              (doall (for [index (range 1 25)]
                         (let [src (str "/assets/social/icons/monsters/" index ".svg")]
                             ^{:key index} [:img.picture
                                            {:src      src
                                             :class    (if (== src @data) "selected")
                                             :on-click #(re-frame/dispatch-sync [:form-data [context name] (get-image-data %)])}])))]
             (if (not-empty @errors) [:div {:class "errors"} @errors])])))

(defn- avatars-renderer-did-mount-handler
    [this]
    (let [name :avatar
          user (re-frame/subscribe [:user])
          context (last (r/argv this))]
        (re-frame/dispatch-sync [:form-data [context name] (@user "avatar")])
        (.slick (js/$ ".avatars") (clj->js {"dots"           false
                                            "infinite"       true
                                            "slidesToShow"   5
                                            "slidesToScroll" 5
                                            "focusOnSelect"  false
                                            "speed"          500
                                            "fade"           false}))))
(defn avatars
    []
    (r/create-class {:reagent-render      avatars-renderer
                     :component-did-mount avatars-renderer-did-mount-handler}))

;; ----------------------------------------------------------------------------------------------

(defn- flash-message-did-mount-handler
    []
    (log/info "Marking flash message as visible")
    (re-frame/dispatch-sync [:mark-flash-message]))

(defn- flash-message-will-unmount-handler
    []
    (log/info "Clearing flash message")
    (re-frame/dispatch-sync [:clear-flash-message]))

(defn- flash-message-renderer
    []
    (let [flash-message (re-frame/subscribe [:flash-message])]
        (fn []
            (if (not (nil? @flash-message))
                (let [message (i18n/message (:message @flash-message))
                      level (:level @flash-message)]
                    [:div.flash.text-center.col-md-12 {:class level} message])))))

(defn flash-message
    []
    (r/create-class {:reagent-render         flash-message-renderer
                     :component-did-mount    flash-message-did-mount-handler
                     :component-will-unmount flash-message-will-unmount-handler}))

