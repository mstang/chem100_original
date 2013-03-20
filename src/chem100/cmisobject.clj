(ns chem100.cmisobject
  "Object Services"
  (import org.apache.chemistry.opencmis.client.api.CmisObject))

(defn get-name [cmis-object] 
  (. cmis-object getName))

(defn get-object-id [cmis-object]
  (. cmis-object getId))

(defn get-base-type-id [cmis-object]
  (. cmis-object getBaseTypeId))

(defn get-object-type-id [cmis-object]
  (. cmis-object getObjectTypeId))

(defn get-created-by [cmis-object]
  (. cmis-object getCreatedBy))

; cmis:createdBy
; cmis:creationDate
; cmis:lastModifiedBy
; cmis:lastModificationDate
; cmis:changeToken (String)

; this returns an #<UnmodifiableRandomAccessList [Property
; need to be able to parse those
; using bean only works for document, I think we can "parse" the list
(defn get-properties [cmis-object]
  (. cmis-object getProperties))

