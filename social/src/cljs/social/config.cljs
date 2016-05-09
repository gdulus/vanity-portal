(ns social.config)

;; ----------------------------------------------------------------------------------------------

(def debug?
    ^boolean js/goog.DEBUG)

(when debug?
    (enable-console-print!))

;; ----------------------------------------------------------------------------------------------

(def url-mapping {:routes   {:welcome                   {:uri "/izba-przyjec"
                                                         :acl [:not-logged-in]}

                             :registration              {:uri "/porodowka"
                                                         :acl [:not-logged-in]}

                             :registration-confirmation {:uri "/i-po-porodzie"
                                                         :acl [:not-logged-in]}

                             :registration-details      {:uri "/karta-pacjenta"
                                                         :acl [:logged-in :first-time-logged-in]}

                             :account-activation        {:uri "/aktywuj-konto"
                                                         :acl [:not-logged-in]}

                             :login                     {:uri "/zaloguj-sie"
                                                         :acl [:not-logged-in]}

                             :regulations               {:uri      "/regulamin"
                                                         :external true}}

                  :defaults {:not-logged-in        :welcome
                             :first-time-logged-in :registration-details
                             :logged-in            :registration-details}})