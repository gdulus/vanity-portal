(ns social.validation
    (:require [bouncer.core :as b]
              [clojure.string :as str]
              [social.i18n :as i18n]))

(defn- custom-message-fn
    [{:keys [path value metadata]}]
    (let [error (str/replace (str/replace (str (str/join "." path) "." (:validator metadata)) "/" ".") ":" "")]
        (i18n/message error)))

(defn validate
    [& args]
    (first (apply b/validate (concat [custom-message-fn] args))))