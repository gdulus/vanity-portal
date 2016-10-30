(ns social.ajax
    (:require [clojure.string :as str]
              [re-frame.core :as re-frame]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(defn- parse-response [jqXHR]
    (let [status (.-status jqXHR)
          token (.getResponseHeader jqXHR "X-Auth-Token")
          response (js->clj (.parse js/JSON (.-responseText jqXHR)))]
        (if (not (str/blank? token))
            (re-frame/dispatch [:store-token token]))
        (if (== status 401)
            (re-frame/dispatch [:unauthorized]))
        {:status status :data response}))

;; ----------------------------------------------------------------------------------------------

(defn- get-headers [token other]
    (let [content-type (if (not (false? (:contentType other))) {"Content-Type" "application/json"})
          x-auth-token (if (not (nil? token)) {"X-Auth-Token" token})]
        (merge content-type x-auth-token)))

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
    ([method url converter raw-data token success error]
     (execute-request method url converter raw-data token success error
                      {:dataType "json"}))
    ([method url converter raw-data token success error other]
     (let [data (converter raw-data)
           headers (get-headers token other)]
         (log/debug "Sending request to" url "with headers" headers)
         (.ajax js/$ (clj->js (merge {:type    method
                                      :url     url
                                      :data    data
                                      :headers headers
                                      :success (fn [_ _ jqXHR] (success (parse-response jqXHR)))
                                      :error   (fn [jqXHR _ _] (error (parse-response jqXHR)))}
                                     other
                                     ))))))

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

(defn- progress-handler
    [event]
    (if (.-lengthComputable event)
        (let [m (.-total event)
              c (.-loaded event)
              p (-> (* 100 c) (/ m) (Math/floor))]
            (re-frame/dispatch [:loader-progress p]))))

(defn- xhr-callback
    []
    (let [settings (.-ajaxSettings js/$)
          xhr (.xhr settings)
          xhr-upload (.-upload xhr)]
        (.addEventListener xhr-upload "progress" progress-handler false)
        xhr))

(defn do-file-upload
    ([url data token success error]
     (execute-request "POST" url null-converter data token success error
                      {:cache       false
                       :contentType false
                       :processData false
                       :xhr         xhr-callback}))
    ([url data success error]
     (do-file-upload url data nil success error)))

;; ----------------------------------------------------------------------------------------------

(defn do-get
    ([url data token success error]
     (execute-request "GET" url null-converter data token success error))
    ([url data success error]
     (do-get url data nil success error)))
