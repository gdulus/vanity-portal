(ns social.views.base.views
    (:require [reagent-modals.modals :as reagent-modals]))


(defn main-panel []
    (fn []
        [reagent-modals/modal-window]))
