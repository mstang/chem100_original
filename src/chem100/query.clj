(ns chem100.query
  (import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:require [chem100.session :as session]
            [chem100.repository :as repo]
            [chem100.folder :as folder]
            [chem100.document :as doc]
            [chem100.cmisobject :as co]))
      
(use '[clojure.string :only (join split)])

(def in-mem-session 
  (session/create-session "http://localhost:8081/inmemory/atom/" "test" "test" "A1"))

(defn query [session search-all-versions query]
  (. session query query search-all-versions))

(defn get-property-by-query-name [result]
  (. (. result getPropertyByQueryName "cmis:name") getFirstValue))

(let [qr (query in-mem-session false "select cmisbook:author from cmisbook:lyrics")]
  (map get-property-by-query-name (take 5 qr)))

(let [qr (query in-mem-session false "select cmis:name from cmisbook:lyrics")]
  (map get-property-by-query-name qr))
