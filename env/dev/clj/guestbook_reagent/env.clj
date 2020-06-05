(ns guestbook-reagent.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [guestbook-reagent.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[guestbook-reagent started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[guestbook-reagent has shut down successfully]=-"))
   :middleware wrap-dev})
