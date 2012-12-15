(defproject first-enlive "0.1.0-SNAPSHOT"
  :description "a trivial clojure web app, base on compojure, enlive and postgresql"
  :url "https://github.com/tzach/first-enlive"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3" :exclusions [ring/ring-core]]
                 [enlive "1.0.1"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [ring/ring-devel "1.1.6"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql/postgresql "9.1-901.jdbc4"]]
  :min-lein-version "2.0.0"
  )