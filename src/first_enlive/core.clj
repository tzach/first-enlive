(ns first-enlive.core
  (:use compojure.core
        [net.cgrand.enlive-html])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [first-enlive.db :as db]
            [clojure.string :as str]
            [ring.util.response :as ring-res]
            [ring.adapter.jetty :as ring-adpt]
            ))

(defn create [params]
  (println params)
  (let [shout (:shout params)]
    (when-not (str/blank? shout)
      (db/create shout)))
  (ring-res/redirect "/all"))

(deftemplate all "all.html" []
  [:.header] (content "Shouts")
  [:.item] (clone-for [s (map :body (db/all))] (content s)))

(defroutes app-routes
  (route/files "/" (ring-res/redirect "/index.html"))
  (route/files "/" {:root "./resources"})
  (POST "/shout" {params :params} (create params))
  (GET "/all" [] (ring-res/response (all)))
  (GET "/clear" []
       (do (db/clear)
           (ring-res/redirect "/shout.html")))
  (route/not-found "Not Found??"))

(def app
  (handler/site app-routes))

(defn start [port]
  (ring-adpt/run-jetty #'app {:port (or port 8080) :join? false}))

(defn -main []
  (let [port (Integer. (System/getenv "PORT"))]
    (start port)))

;;;
;; (def s (start 3000))
;; (.stop s)