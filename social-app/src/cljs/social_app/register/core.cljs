(ns social-app.register.core
  (:require [reagent-forms.core :refer [bind-fields]]
            [reagent.core :as r]
            [ajax.core :refer [POST]]
            [social-app.i18n :as i18n]
            [social-app.logging :as logging]))

;;
;;
;;

(def ^:private form-data (r/atom {}))
(def ^:private submiting (r/atom false))

;;
;;
;;

(defn- show-error [error]
  (let [[field code] error
        message (i18n/form-error error)]
    (do (logging/info error)
        (logging/info message)
        (.append (js/$ "#errors") message)
        (.addClass (js/$ (str "#" field)) "error"))))

(defn- with-submit-context [executor]
  (do (swap! submiting not) (executor)))

(defn- success-handler [response]
  (with-submit-context
    #(.log js/console (str response))))

(defn- error-handler [{:keys [response]}]
  (with-submit-context
    (fn [] (doall (map show-error response)))))

(defn- form-submit-handler []
  (with-submit-context
    #(POST "/api/user" {:params @form-data :format :json :handler success-handler :error-handler error-handler})))

;;
;;
;;

(defn- input-field [id type label help]
  [:label {:for id} (i18n/message label)
   [:div.input-group
    [:input.form-control {:field type :id id}]
    [:div.input-group-addon
     [:span.glyphicon.glyphicon-info-sign {:data-content (i18n/message help) :data-placement :left}]]]])

(defn- form []
  ^{:component-did-mount (js/setTimeout #(.popover (js/$ ".glyphicon-info-sign")) 100)}
  [:div
   (input-field :username :text "form.username.label" "form.username.info")
   (input-field :password :password "form.password.label" "form.password.info")
   (input-field :email :email "form.email.label" "form.email.info")
   [:label "Płeć"]
   [:div.input-group
    [:label.radio-inline [:input {:field :radio :value :WOMAN :name :gender} (i18n/message "form.woman")]]
    [:label.radio-inline [:input {:field :radio :value :MAN :name :gender} (i18n/message "form.man")]]]
   [:div.input-group.regulations
    [:label.checkbox-inline [:input {:field :checkbox :id :regulations} [:span (i18n/message "register.confirm.info") [:a.link {:href "#"} (i18n/message "register.confirm.link")]]]]]])

(defn presenter []
  (fn []
    [:div.row {:id "register"}
     [:div.col-md-12
      [:h1.text-center (i18n/message "register.header")]
      [:h2.text-center (i18n/message "register.explanation")]]
     [:div.col-md-12
      [:form
       [:div {:id :errors}]
       [bind-fields (form) form-data]
       [:div.form-group
        [:div.buttons {:class (str (if @submiting "loader"))}
         [:a.link {:href "#/intro"} (i18n/message "button.cancel")]
         (if @submiting
           [:div.loader.pull-right]
           [:span.btn.btn-default.pull-right {:on-click form-submit-handler} (i18n/message "button.register")])]]]]]))


