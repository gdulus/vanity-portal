(ns social.base.db)

(def default-db
    {:name "re-frame"})

(defn get-user-status
    [db]
    (let [user (:user db)]
        (if (nil? user)
            :not-logged-in
            (if (get user "firstLogin")
                :first-time-logged-in
                :logged-in))))