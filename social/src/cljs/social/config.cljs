(ns social.config)

;; ----------------------------------------------------------------------------------------------

(def debug?
    ^boolean js/goog.DEBUG)

(when debug?
    (enable-console-print!))

;; ----------------------------------------------------------------------------------------------

(def url-mapping {:routes   {:welcome              {:uri "/izba-przyjec"
                                                    :acl [:not-logged-in]}

                             :registration         {:uri "/porodowka"
                                                    :acl [:not-logged-in]}

                             :registration-details {:uri "/registration-details"
                                                    :acl [:logged-in :first-time-logged-in]}

                             :login                {:uri "/zaloguj-sie"
                                                    :acl [:not-logged-in]}

                             :regulations          {:uri      "/regulamin"
                                                    :external true}}

                  :defaults {:not-logged-in        :welcome
                             :first-time-logged-in :registration-details
                             :logged-in            :registration-details}})