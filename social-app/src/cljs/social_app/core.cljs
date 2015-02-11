(ns social-app.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [goog.history.EventType :as HistoryEventType]
            [social-app.intro.core :as intro]
            [social-app.register.core :as register]
            [social-app.login.core :as login]
            [reagent-modals.modals :as reagent-modals])
  (:import goog.History))

;; -------------------------
;; Views

;; -------------------------
;; Views

(defn main-conainer []
  [reagent-modals/modal-window])

;(defn about-page []
;  [:div [:h2 "About reagent-app"]
;   [:div [:a {:href "#/result"} "go to the result page!!!"]]])
;
;(defn result-page []
;  [:div [:h2 "DUPA!!"]
;   [:div [:a {:href "#/"} "go to the home page!!!"]]])
;
;(defn current-page []
;  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")
;
;(secretary/defroute "/" []
;                    (session/put! :current-page #'home-page))
;
;(secretary/defroute "/register" []
;                    (session/put! :current-page #'about-page))

(secretary/defroute "/intro" []
                    (reagent-modals/modal! (intro/presenter) {:size :lg}))

(secretary/defroute "/register" []
                    (reagent-modals/modal! [register/presenter] {:size :md}))

(secretary/defroute "/login" []
                    (reagent-modals/modal! (login/presenter) {:size :lg}))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Js hooks

;; TODO move that to event related namespace
(defn- add-listener
  [element-id event-type listener]
  (events/listen
    (.getElementById js/document element-id)
    event-type
    listener))

;; TODO move that rouute related namespace
(defn init-register-button! []
  (add-listener "user-button"
                EventType/CLICK
                #(do (set! (.-location js/window) "#/intro")
                     (secretary/dispatch! "/intro"))))

;; -------------------------
;; Initialize app
(defn init! []
  (init-register-button!)
  (reagent/render-component [main-conainer] (.getElementById js/document "social"))
  (hook-browser-navigation!))
