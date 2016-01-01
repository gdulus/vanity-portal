(ns social.base.views
    (:require [re-frame.core :as re-frame]
              [reagent-modals.modals :as reagent-modals]))

(defn main-panel []
    (let [active-panel (re-frame/subscribe [:active-panel])]
        (fn []
            [reagent-modals/modal-window (reagent-modals/modal! @active-panel {:size :lg})])))
