(ns chem100.property
(import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:require [chem100.session :as session]
            [chem100.repository :as repo]
            [chem100.folder :as folder]
            [chem100.document :as doc]
            [chem100.cmisobject :as co]))
      
(use '[clojure.string :only (join split)])

(def in-mem-session 
  (session/create-session "http://localhost:8081/inmemory/atom/" "test" "test" "A1"))


;PropertyDefinition<T>	getDefinition()
;Returns the property definition.
;PropertyType	getType()
;Returns the property data type.
;<U> U	getValue()
;Returns the property value (single or multiple).
;String	getValueAsString()
;Returns a human readable representation of the property value.
;String	getValuesAsString()
;Returns a human readable representation of the property values.
;boolean	isMultiValued()
;Returns if the property is a multi-value property.

(defn get-definition [prop]
  (. prop getDefinition))

(defn get-value [prop]
  (. prop getValue))

(let [root-folder (folder/get-root-folder in-mem-session)
      ]
  (map get-id (. root-folder getProperties)))

