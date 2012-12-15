(ns first-enlive.db
  (:require [clojure.java.jdbc :as sql]))

(def connection (or (System/getenv "DATABASE_URL") "postgresql://tzach:tzachdb@localhost:5432/shouter"))

(defn all []
  (sql/with-connection connection
    (sql/with-query-results results
      ["select * from shouts order by id desc"]
      (into [] results))))

(defn create [shout]
  (sql/with-connection connection
    (sql/insert-values :shouts [:body] [shout])))

(defn clear []
  (sql/with-connection connection (sql/do-prepared (format "DELETE from %s" (name :shouts)))))
    
