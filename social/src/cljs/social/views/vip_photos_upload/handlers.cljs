(ns social.views.vip-photos-upload.handlers
    (:require [re-frame.core :as re-frame]
              [bouncer.validators :as v]
              [social.validation :as validation]
              [social.logger :as log]
              [social.ajax :as ajax]))