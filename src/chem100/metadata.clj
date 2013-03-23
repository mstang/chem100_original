(ns chem100.metadata
  (import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:require [chem100.session :as session]
            [chem100.repository :as repo]
            [chem100.folder :as folder]
            [chem100.document :as doc]
            [chem100.cmisobject :as co]))
      
(use '[clojure.string :only (join split)])

(def in-mem-session 
  (session/create-session "http://localhost:8081/inmemory/atom/" "test" "test" "A1"))

(defn get-definition [prop]
  (. prop getDefinition))

(defn get-display-name [prop]
  (. prop getDisplayName))

(defn get-value [prop]
  (. prop getValue))

(defn get-id [prop]
  (. prop getId))

(let [root-folder (folder/get-root-folder in-mem-session)
      ]
  (map get-id (. root-folder getProperties)))

