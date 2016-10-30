(ns social-app.i18n
  (:require [clojure.string :as s]))

(def ^:private messages {"form.error.email.nullable" "Email nie może być pusty"
                         "form.error.default"        "Pole wymagane lub nie poprawny format"
                         "form.username.label"       "Nazwa Twojego konta"
                         "form.username.info"        "Pod tą nazwą twój profil będzie widoczny dla użytkowników."
                         "form.password.label"       "Hasło dostępu"
                         "form.password.info"        "Musi zawierać co najmniej 8 znaków, w tym jedną cyfrę i wielką literę. Zapamiętaj je, aby ponownie zalogować się do profilu."
                         "form.email.label"          "Adres email"
                         "form.email.info"           "Warto podać prawdziwe dane, aby móc w razie problemów odzyskać hasło."
                         "form.man"                  "Mężczyzna"
                         "form.woman"                "Kobieta"
                         "register.header"           "Powiedz nam coś o sobie"
                         "register.explanation"      "Podaj kilka informacji, aby korzystać ze wszystkich funkcji portalu"
                         "button.cancel"             "Anuluj"
                         "button.register"           "Załóż darmowe konto"
                         "register.confirm.info"     "Potwierdź znajomość "
                         "register.confirm.link"     "regulaminu"})

(defn message
  ([code]
    (get messages code))
  ([code default]
    (get code (message default))))

(defn form-error [error]
  (message (str "form.error." (s/join "." error)) "form.error.default"))
