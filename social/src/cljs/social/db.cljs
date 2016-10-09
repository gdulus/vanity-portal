(ns social.db
    (:require [alandipert.storage-atom :refer [local-storage]]))

;; ----------------------------------------------------------------------------------------------

(def storage (local-storage (atom {}) :token))

(def default-db {:vip {:name (.text (js/$ "#vip-name"))}})

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