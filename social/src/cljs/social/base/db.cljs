(ns social.base.db
    (:require [alandipert.storage-atom :refer [local-storage]]))

(def storage (local-storage (atom {}) :token))

(def default-db {})

(defn get-user-status
    [db]
    (let [user (:user db)]
        (if (nil? user)
            :not-logged-in
            (if (get user "firstLogin")
                :first-time-logged-in
                :logged-in))))