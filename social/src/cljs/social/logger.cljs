(ns social.logger
    (:require [clojure.string :as string]
              [social.config :as config]))

(def ^:private log-level {:debug #(.debug js/console %) :info #(.info js/console %) :error #(.error js/console %)})

(defn- log [level message]
    (if (log-level level)
        ((log-level level) (string/join " " message))))

(defn info [& message]
    (log :info message))

(defn debug [& message]
    (when config/debug?
        (log :debug message)))

(defn error [& message]
    (log :error message))

(debug "dev mode")
