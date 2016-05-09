(ns social.ajax
    (:require [clojure.string :as str]
              [re-frame.core :as re-frame]))

;; ----------------------------------------------------------------------------------------------

(defn- parse-response [jqXHR]
    (let [status (.-status jqXHR)
          token (.getResponseHeader jqXHR "X-Auth-Token")
          response (js->clj (.parse js/JSON (.-responseText jqXHR)))]
        (if (not (str/blank? token))
            (re-frame/dispatch [:store-token token]))
        {:status status :data response}))

;; ----------------------------------------------------------------------------------------------

(defn- get-header [token]
    (if (nil? token)
        {"Content-Type" "application/json"}
        {"X-Auth-Token" token "Content-Type" "application/json"}))

;; ----------------------------------------------------------------------------------------------

(defn- json-converter
    [data]
    (.stringify js/JSON (clj->js data)))

;; ----------------------------------------------------------------------------------------------

(defn- null-converter
    [data]
    data)

;; ----------------------------------------------------------------------------------------------

(defn- execute-request
    [method url converter raw-data token success error]
    (let [data (converter raw-data)]
        (.ajax js/$ (clj->js {:dataType "json"
                              :type     method
                              :url      url
                              :data     data
                              :headers  (get-header token)
                              :success  (fn [_ _ jqXHR] (success (parse-response jqXHR)))
                              :error    (fn [jqXHR _ _] (error (parse-response jqXHR)))}))))

;; ----------------------------------------------------------------------------------------------

(defn do-put
    ([url data token success error]
     (execute-request "PUT" url json-converter data token success error))
    ([url data success error]
     (do-put url data nil success error)))

;; ----------------------------------------------------------------------------------------------

(defn do-post
    ([url data token success error]
     (execute-request "POST" url json-converter data token success error))
    ([url data success error]
     (do-post url data nil success error)))

;; ----------------------------------------------------------------------------------------------

(defn do-get
    ([url data token success error]
     (execute-request "GET" url null-converter data token success error))
    ([url data success error]
     (do-get url data nil success error)))
