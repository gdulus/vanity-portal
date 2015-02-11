(ns social-app.register.core
  (:require [reagent-forms.core :refer [bind-fields]]
            [reagent.core :as r]
            [ajax.core :refer [POST]]))

(def ^:private form-data (r/atom {}))
(def ^:private submiting (r/atom false))

(defn- form-submit-handler []
  (do
    (reset! submiting true)
    (POST "/send-form-modern" {:params (str @form-data)})))

(defn- input-field [id type label help]
  [:label {:for id} label
   [:div.input-group
    [:input.form-control {:field type :id id}]
    [:div.input-group-addon
     [:span.glyphicon.glyphicon-info-sign {:data-content help :data-placement :left}]]]])

(defn- form []


    [:div
     (input-field :name :text "Nazwa Twojego konta" "Pod tą nazwą twój profil będzie widoczny dla użytkowników.")
     (input-field :password :password "Hasło dostępu" "Musi zawierać co najmniej 8 znaków, w tym jedną cyfrę i wielką literę. Zapamiętaj je, aby ponownie zalogować się do profilu.")
     (input-field :email :email "Adres email" "Warto podać prawdziwe dane, aby móc w razie problemów odzyskać hasło.")
     [:label "Płeć"]
     [:div.input-group
      [:label.radio-inline [:input {:field :radio :value :woman :name :sex} "Kobieta"]]
      [:label.radio-inline [:input {:field :radio :value :man :name :sex} "Mężczyzna"]]]
     [:div.input-group.regulations
      [:label.checkbox-inline [:input {:field :checkbox :id :regulations} [:span "Potwierdź znajomość " [:a.link {:href "#"} "regulaminu"]]]]]]
    [:div.form-group
     [:div.buttons {:class (str @submiting)}
      [:a.link {:href "#/intro"} "Anuluj"]
      ;; TODO remove to presenter
      (if @submiting
        [:span.loader.pull-right]
        [:span.btn.btn-default.pull-right {:on-click form-submit-handler} "Załóż darmowe konto"])
      ]]))

(defn presenter []
  (fn []
    [:div.row {:id "register"}
     [:div.col-md-12
      [:h1.text-center "Powiedz nam coś o sobie"]
      [:h2.text-center "Podaj kilka informacji, aby korzystać ze wszystkich funkcji portalu"]]
     [:div.col-md-12
      [bind-fields (form) form-data]
      [:div (str @form-data)]
      [:div (str @submiting)]]]))


