(ns social-app.logging)

(defn info [message]
  (.info js/console message))