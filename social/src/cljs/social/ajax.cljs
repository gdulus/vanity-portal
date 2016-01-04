(ns social.ajax
    (:require [clojure.string :as str]))

(defn- parse-response [jqXHR]
    (let [status (.-status jqXHR)
          token (.getResponseHeader jqXHR "X-Auth-Token")
          response (js->clj (.parse js/JSON (.-responseText jqXHR)))]
        (if (str/blank? token)
            {:status status
             :data   response}
            {:status status
             :data   response
             :token  token})))

(defn- json-converter
    [data]
    (.stringify js/JSON (clj->js data)))

(defn- null-converter
    [data]
    data)

(defn- execute-request
    [method url converter raw-data headers success error]
    (let [data (converter raw-data)]
        (.ajax js/$ (clj->js {:dataType "json"
                              :type     method
                              :url      url
                              :data     data
                              :headers  headers
                              :success  (fn [_ _ jqXHR] (success (parse-response jqXHR)))
                              :error    (fn [jqXHR _ _] (error (parse-response jqXHR)))}))))
(defn post
    ([url data token success error]
     (execute-request "POST" url json-converter data {"X-Auth-Token" token "Content-Type" "application/json"} success error))
    ([url data success error]
     (execute-request "POST" url json-converter data {"Content-Type" "application/json"} success error)))

(defn get
    ([url data token success error]
     (execute-request "GET" url null-converter data {"X-Auth-Token" token "Content-Type" "application/json"} success error))
    ([url data success error]
     (execute-request "GET" url null-converter data {"Content-Type" "application/json"} success error)))
