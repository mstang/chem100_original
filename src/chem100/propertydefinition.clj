(ns chem100.propertydefinition
  "Used to access properties"
  (import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:require [chem100.session :as session]
            [chem100.repository :as repo]
            [chem100.folder :as folder]
            [chem100.document :as doc]
            [chem100.cmisobject :as co]))

;Cardinality	getCardinality()
;Returns the cardinality.
;List<Choice<T>>	getChoices()
;Returns the choices for this property.
;List<T>	getDefaultValue()
;Returns the default value.
;String	getDescription()
;Returns the property description.
;String	getDisplayName()
;Returns the display name.
;String	getId()
;Returns the property definition id.
;String	getLocalName()
;Returns the local name.
;String	getLocalNamespace()
;Returns the local namespace.
;PropertyType	getPropertyType()
;Returns the property type.
;String	getQueryName()
;Returns the query name
;Updatability	getUpdatability()
;Returns the updatability.
;Boolean	isInherited()
;Returns if the property is inherited by a parent type.
;Boolean	isOpenChoice()
;Returns if the property supports open choice.
;Boolean	isOrderable()
;Returns if the property is Orderable.
;Boolean	isQueryable()
;Returns if the property is queryable.
;Boolean	isRequired()
;Returns if the property is required.

(defn get-display-name [prop]
  (. prop getDisplayName))

(defn get-id [prop]
  (. prop getId))

;;#(Foo. %)
;; #(. % getDisplayName)

(let [root-folder (folder/get-root-folder in-mem-session)
      ]
  (sort (map #(. % getDisplayName) (. root-folder getProperties))))
