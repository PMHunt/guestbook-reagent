(ns guestbook-reagent.test.db.core
  (:require
   [guestbook-reagent.db.core :refer [*db*] :as db]
   [java-time.pre-java8]
   [luminus-migrations.core :as migrations]
   [clojure.test :refer :all]
   [clojure.java.jdbc :as jdbc]
   [guestbook-reagent.config :refer [env]]
   [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'guestbook-reagent.config/env
     #'guestbook-reagent.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-messages
  (jdbc/with-db-transaction [t-conn *db*] ; open a trans. connection
    (jdbc/db-set-rollback-only! t-conn) ; rollback when tests are done
    (testing "Save a message, then get it from db"
      (is (= 1 (db/save-message!
                t-conn
                {:name "Bob" :message "Hello World"}
                {:connection t-conn})))
      (is (= {:name "Bob"
              :message "Hello World"}
             (-> (db/get-messages t-conn {})
                 (first)
                 (select-keys [:name :message])))))))
