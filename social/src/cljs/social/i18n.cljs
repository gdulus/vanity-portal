(ns social.i18n
    (:require [clojure.string :as s]
              [goog.string :as gstring]))

(def ^:private messages {"social.welcome.header.1"                   "Dołącz do loży hejterów"
                         "social.welcome.header.2"                   "Załóż szybko darmowy profil i rzucaj bombami z farbą w celebrytów i innych użytkowników. Utwórz swoją białą i czarną listę oraz otrzymuj powiadomienia o ustawkach i ważnych wydarzeniach."
                         "social.welcome.register.fb"                "Rejestruj przez Facebook"
                         "social.welcome.register.self"              "Rejestruj samodzielnie"
                         "social.welcome.login.1"                    "Jeśli posiadasz już konto "
                         "social.welcome.login.2"                    "kliknij i zaloguj się"
                         "social.form.error.email.nullable"          "Email nie może być pusty"
                         "social.form.error.default"                 "Pole wymagane lub nie poprawny format"
                         "social.form.username.label"                "Nazwa Twojego konta"
                         "social.form.username.info"                 "Pod tą nazwą twój profil będzie widoczny dla użytkowników."
                         "social.form.password.label"                "Hasło dostępu"
                         "social.form.password.info"                 "Musi zawierać co najmniej 8 znaków, w tym jedną cyfrę i wielką literę. Zapamiętaj je, aby ponownie zalogować się do profilu."
                         "social.form.email.label"                   "Adres email"
                         "social.form.email.info"                    "Warto podać prawdziwe dane, aby móc w razie problemów odzyskać hasło."
                         "social.form.gender.label"                  "Płec"
                         "social.form.man"                           "Mężczyzna"
                         "social.form.woman"                         "Kobieta"
                         "social.register.header"                    "Powiedz nam coś o sobie"
                         "social.register.explanation"               "Podaj kilka informacji, aby korzystać ze wszystkich funkcji portalu"
                         "social.button.cancel"                      "Anuluj"
                         "social.button.register"                    "Załóż darmowe konto"
                         "social.button.login"                       "Zaloguj się"
                         "social.button.later"                       "Dokończ później"
                         "social.button.save"                        "Zapisz dane"
                         "social.form.country.label"                 "Kraj"
                         "social.form.country.placeholder"           "wybierz pozycję"
                         "social.form.voivodeship.label"             "Województwo"
                         "social.form.voivodeship.placeholder"       "wybierz województwo"
                         "social.form.city.label"                    "Miasto"
                         "social.form.city.placeholder"              "wybierz miasto"
                         "social.form.avatar.label"                  "Wybierz swój awater"
                         "social.form.birthday.label"                "Data urodzin"
                         "social.form.birthday.birthday.placeholder" "RRRR-MM-DD"
                         "social.login.register.button"              "Kliknij i zarejestruj się za darmo"
                         "social.login.register.question"            "Nie masz jeszcze konta? "
                         "social.login.forgot-password.question"     "Nie pamiętasz hasła lub nazwy konta? "
                         "social.login.forgot-password.button"       "Kliknij i zresetuj hasło"
                         "social.register.confirm.info"              "Potwierdź znajomość "
                         "social.register.confirm.link"              "regulaminu"
                         "social.register-details.header"            "Witaj, %s :)"
                         "social.register-details.explanation"       "Twoje konta zostało założone, a na podany adres e-mail wiadomość z potwierdzeniem. Zanim jednak zaczniesz korzystać z serwisu, wybierz proszę swój avatar i podaj kilka danych, które pozwolą nam uwzględnić Twoje głosy w serwisowych rankingach:"
                         "social.login.welcome"                      "Witaj ponownie :)"
                         "social.login.explanation"                  "Zaloguj się do swojego konta"
                         "social.register-confirmation.header"       "Konto założone, %s :)"
                         "social.register-confirmation.explanation"  "Twoje konta zostało założone, a na podany adres e-mail wiadomość z potwierdzeniem i linkiem do aktywacjo konta."
                         "social.register-confirmation.back.1"       "Aby wrócić do strony głównej "
                         "social.register-confirmation.back.2"       "kliknij ten link"

                         "social.account-activation.header"          "Witaj :)"
                         "social.account-activation.explanation"     "Aktywacja konta w toku, prosimy o cierpliwosc"
                         "social.account-activation.back.1"          "Aby wrócić do strony głównej "
                         "social.account-activation.back.2"          "kliknij ten link"

                         "social.user.activated"                     "Twoje konto zastało aktywowane pomyślnie. Zanim jednak zaczniesz korzystać z serwisu, wybierz proszę swój avatar i podaj kilka danych, które pozwolą nam uwzględnić Twoje głosy w serwisowych rankingach"
                         "social.error.status.400"                   "Sprawdź poprawność danych"
                         "social.error.status.401"                   "Nazwa konta lub hasło są nie poprawne"
                         "social.error.status.500"                   "Wystąpił błąd po stronie serwera - spróbuj jeszcze raz"
                         "social.error.status.403"                   "Twoje konto wymaga aktywacji. Sprawdź email :)"
                         "email.bouncer.validators.required"         "Pole 'Email' jest polem wymaganym"
                         "gender.bouncer.validators.required"        "Proszę wybrać płeć"
                         "username.bouncer.validators.required"      "Pole 'Nazwa twojego konta' jest wymagane"
                         "password.bouncer.validators.required"      "Pole 'Hasło dostępu' jest wymagane"
                         "regulations.bouncer.validators.required"   "Musisz zaakceptować regulamin"
                         "regulations.bouncer.validators.member"     "Musisz zaakceptować regulamin"
                         "vanity.user.user.email.unique"             "Email jest już zajęty"
                         "vanity.user.user.username.unique"          "Nazwa użytkownika jest już zajęta"
                         "social.register.errors"                    "W formularzu wystąpiły błędy"
                         "country.bouncer.validators.required"       "Kraj jest polem wymaganym"
                         "voivodeship.bouncer.validators.required"   "Województwo jest polem wymaganym"
                         "city.bouncer.validators.required"          "Miasto jest polem wymaganym"
                         "birthday.bouncer.validators.required"      "Data urdzin jest polem wymaganym"
                         "avatar.bouncer.validators.required"        "Awatar jest wymagany"
                         "vanity.user.profile.birthday.format.error" "Nie poprawny format. Poprawny data to np.: 2000-01-01"})

(defn message
    ([code]
     (let [message (get messages code)]
         (if (s/blank? message)
             code
             message)))
    ([code & params]
     (let [message (message code)]
         (apply gstring/format message params))))
