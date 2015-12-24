(ns social.events
    (:require [goog.events :as events]))

(defn add-listener
    [element-id event-type listener]
    (events/listen (.getElementById js/document element-id)
                   event-type
                   listener))