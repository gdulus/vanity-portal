(ns social.i18n
    (:require [clojure.string :as s]
              [goog.string :as gstring]))

(def ^:private messages (js->clj js/SOCIAL_I18N))

(defn message
    ([code]
     (if (string? code)
         (let [scode (if (s/starts-with? code "social.") code (str "social." code))
               message (get messages scode)]
             (if (s/blank? message) scode message))
         code))
    ([code & params]
     (let [message (message code)]
         (apply gstring/format message params))))
