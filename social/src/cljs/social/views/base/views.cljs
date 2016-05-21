(ns social.views.base.views
    (:require [reagent-modals.modals :as reagent-modals]
              [re-frame.core :as re-frame]))

;(defn main-panel []
;    (fn []
;        [reagent-modals/modal-window]))

(defn main-panel []
    (let [panel (re-frame/subscribe [:active-panel])]
        (fn []
            (if @panel
                [:div.content @panel]))))
