(ns social-app.register.core
  (:require [reagent-forms.core :refer [bind-fields]]
            [reagent.core :as r]
            [ajax.core :refer [POST]]))

;;
;;
;;

(def ^:private form-data (r/atom {}))
(def ^:private submiting (r/atom false))
(def ^:private errors-mapping {"email.nullable" [:email "Email nie może być pusty"]})

;;
;;
;;

(defn- print-error []
  (do (swap! submiting not) (_)))

(defn- with-submit-context [_]
  (do (swap! submiting not) (_)))

(defn- success-handler [response]
  (with-submit-context
    #(.log js/console (str response))))

(defn- error-handler [{:keys [response]}]
  (with-submit-context
    (fn [] (doall (map #(.log js/console (get errors-mapping %)) response)))))

(defn- form-submit-handler []
  (with-submit-context
    #(POST "/api/user" {:params @form-data :format :json :handler success-handler :error-handler error-handler})))

;;
;;
;;

(defn- input-field [id type label help]
  [:label {:for id} label
   [:div.input-group
    [:input.form-control {:field type :id id}]
    [:div.input-group-addon
     [:span.glyphicon.glyphicon-info-sign {:data-content help :data-placement :left}]]]])

(defn- form []
  ^{:component-did-mount (js/setTimeout #(.popover (js/$ ".glyphicon-info-sign")) 100)}
  [:div
   (input-field :username :text "Nazwa Twojego konta" "Pod tą nazwą twój profil będzie widoczny dla użytkowników.")
   (input-field :password :password "Hasło dostępu" "Musi zawierać co najmniej 8 znaków, w tym jedną cyfrę i wielką literę. Zapamiętaj je, aby ponownie zalogować się do profilu.")
   (input-field :email :email "Adres email" "Warto podać prawdziwe dane, aby móc w razie problemów odzyskać hasło.")
   [:label "Płeć"]
   [:div.input-group
    [:label.radio-inline [:input {:field :radio :value :WOMAN :name :gender} "Kobieta"]]
    [:label.radio-inline [:input {:field :radio :value :MAN :name :gender} "Mężczyzna"]]]
   [:div.input-group.regulations
    [:label.checkbox-inline [:input {:field :checkbox :id :regulations} [:span "Potwierdź znajomość " [:a.link {:href "#"} "regulaminu"]]]]]])

(defn presenter []
  (fn []
    [:div.row {:id "register"}
     [:div.col-md-12
      [:h1.text-center "Powiedz nam coś o sobie"]
      [:h2.text-center "Podaj kilka informacji, aby korzystać ze wszystkich funkcji portalu"]]
     [:div.col-md-12
      [:form
       [bind-fields (form) form-data]
       [:div.form-group
        [:div.buttons {:class (str (if @submiting "loader"))}
         [:a.link {:href "#/intro"} "Anuluj"]
         (if @submiting
           [:div.loader.pull-right]
           [:span.btn.btn-default.pull-right {:on-click form-submit-handler} "Załóż darmowe konto"])
         ]]]]]))


