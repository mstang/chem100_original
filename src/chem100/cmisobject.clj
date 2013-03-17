(ns chem100.cmisobject)

(import '(org.apache.chemistry.opencmis.client.api CmisObject Folder ItemIterable))

(defn get-name [cmis-object] 
  (. cmis-object getName))

(defn get-object-id [cmis-object]
  (. cmis-object getId))

(defn create-folder [parent folder-name]
  (. parent createFolder (create-folder-props folder-name)))

; cmis:name
; cmis:objectId
; cmis:baseTypeId
; cmis:objectTypeId
; cmis:createdBy
; cmis:creationDate
; cmis:lastModifiedBy
; cmis:lastModificationDate
; cmis:changeToken (String)

