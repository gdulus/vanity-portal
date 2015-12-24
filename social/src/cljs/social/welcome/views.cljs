(ns social.welcome.views
    (:require [re-frame.core :as re-frame]
              [social.welcome.subs]
              [social.welcome.handlers]))

(defn main-panel []
    (let []
        (fn []
            [:div.row {:id "intro"}
             [:div.col-md-12
              [:h1.text-center "Dołącz do loży hejterów"]
              [:h2.text-center "Załóż szybko darmowy profil i rzucaj bombami z farbą w celebrytów i innych użytkowników. Utwórz swoją białą i czarną listę oraz otrzymuj powiadomienia o ustawkach i ważnych wydarzeniach."]]
             [:div.col-md-12.text-center
              [:a.btn.btn-default.facebook {:on-click #(.alert js/window "fb login")} "Rejestruj przez Facebook"]
              [:a.btn.btn-default.register {:href "#/porodowka"} "Rejestruj samodzielnie"]]
             [:div.col-md-12.text-center.footer
              [:span "Jeśli posiadasz już konto "
               [:a {:href "#/login"} "kliknij i zaloguj się"]]]])))

