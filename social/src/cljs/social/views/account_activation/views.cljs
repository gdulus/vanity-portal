(ns social.views.account-activation.views
    (:require [social.views.account-activation.subs]
              [social.views.account-activation.handlers]
              [re-frame.core :as re-frame]
              [social.i18n :as i18n]
              [reagent.core :as r]
              [social.logger :as log]))

;; ----------------------------------------------------------------------------------------------

(defn- component-did-mount-handler
    []
    (let [query-params (re-frame/subscribe [:query-params :account-activation])
          token (get @query-params :token)]
        (log/info "Triggering account activation" (get @query-params :token))
        (re-frame/dispatch-sync [:start-activation token])))

(defn- main-panel-renderer
    []
    (fn []
        [:div.row {:id "account-activation"}
         [:div.col-md-12
          [:h1.text-center (i18n/message "social.account-activation.header")]
          [:div.text-center (i18n/message "social.account-activation.explanation")]
          [:div.loader]]]))

(defn main-panel
    []
    (r/create-class {:reagent-render      main-panel-renderer
                     :component-did-mount component-did-mount-handler}))


