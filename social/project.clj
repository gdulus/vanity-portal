(defproject social "0.1.0-SNAPSHOT"
    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/clojurescript "1.7.170"]
                   [reagent "0.5.1"]
                   [re-frame "0.7.0"]
                   [secretary "1.2.3"]
                   [org.clojars.frozenlock/reagent-modals "0.2.3"]
                   [bouncer "1.0.0"]
                   [alandipert/storage-atom "1.2.4"]
                   [com.andrewmcveigh/cljs-time "0.4.0"]]

    :jvm-opts []

    :min-lein-version "2.5.3"

    :source-paths ["src/clj"]

    :plugins [[lein-cljsbuild "1.1.1"]
              [lein-figwheel "0.5.0-2"]]

    :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

    :figwheel {:css-dirs ["resources/public/css"]}

    :cljsbuild {:builds [{:id           "dev"
                          :source-paths ["src/cljs"]
                          :figwheel     {:on-jsload "social.core/mount-root"}
                          :compiler     {:main                 social.core
                                         :output-to            "../grails-app/assets/javascripts/social/compiled/app.js"
                                         :output-dir           "../grails-app/assets/javascripts/social/compiled/out"
                                         :asset-path           "/assets/social/compiled/out"
                                         :source-map-timestamp true}}

                         {:id           "min"
                          :source-paths ["src/cljs"]
                          :compiler     {:main            social.core
                                         :output-to       "../grails-app/assets/javascripts/social/compiled/app.js"
                                         :optimizations   :advanced
                                         :closure-defines {goog.DEBUG false}
                                         :pretty-print    false
                                         :externs         ["../grails-app/assets/javascripts/libs/bootstrap.min.js"
                                                           "../grails-app/assets/javascripts/libs/jquery-1.12.0.js"
                                                           "../grails-app/assets/javascripts/libs/slick.js"]}}]})
