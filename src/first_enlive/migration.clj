(ns first-enlive.migration
  (use first-enlive.db)
  (:require [clojure.java.jdbc :as sql]))

(defn create-shouts []
  (sql/with-connection connection
    (sql/create-table :shouts
                      [:id :serial "PRIMARY KEY"]
                      [:body :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print "Migrating database...") (flush)
  (create-shouts)
  (println " done"))

