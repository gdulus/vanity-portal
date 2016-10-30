(ns social.db
    (:require [alandipert.storage-atom :refer [local-storage]]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(def storage (local-storage (atom {}) :token))
(def default-db {:vip (js->clj js/VIP_DATA)})

;; ----------------------------------------------------------------------------------------------
;; USER
;; ----------------------------------------------------------------------------------------------

(defn get-user-status
    [db]
    (let [user (:user db)
          token (:token @storage)]
        (if (or (nil? user) (nil? token))
            :not-logged-in
            (if (get user "firstLogin")
                :first-time-logged-in
                :logged-in))))

(defn get-user-id
    [db]
    (let [user-id (get-in db [:user "id"])]
        (if (not (nil? user-id))
            user-id)
        (:user-id @storage)))

(defn save-user-id
    [user-id]
    (swap! storage assoc :user-id user-id))

;; ----------------------------------------------------------------------------------------------
;; VIP
;; ----------------------------------------------------------------------------------------------

(defn get-vip-id
    [db]
    (get-in db [:vip "id"]))

(defn get-vip-name
    [db]
    (get-in db [:vip "name"]))

;; ----------------------------------------------------------------------------------------------
;; LOADER
;; ----------------------------------------------------------------------------------------------

;; ----------------------------------------------------------------------------------------------
;; FORM
;; ----------------------------------------------------------------------------------------------

;; ----------------------------------------------------------------------------------------------
;; TOKEN
;; ----------------------------------------------------------------------------------------------

(defn save-token
    [token]
    (swap! storage assoc :token token))

(defn get-token
    []
    (:token @storage))

(defn clear-token
    []
    (save-token nil))