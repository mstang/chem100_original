(ns chem100.folder
  (import org.apache.chemistry.opencmis.client.api.Folder)
  (:require [chem100.session :as session]))

(import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships)

; need to walk the tree of descendants recursively.
; getChildren() - gets just the direct containees in a folder
; getDescentants() - gets the containees of a folder and all their children to a specified depth
; getDescendants() - 
; getFolderTree() - gets the set of descendant folder objects contained in a specified folder
; getFolderParent() - gets the parent folde object for the specified folder
; getObjectParents() - gets the parent folder(s) for the specified non-folder object

(defn create-folder-props [folder-name]
  (doto (new java.util.HashMap)
    (.put "cmis:objectTypeId" "cmis:folder")
    (.put "cmis:name" folder-name)))

(defn get-root-folder [session] 
  (. session getRootFolder))

(defn create-folder [parent folder-name]
  (. parent createFolder (create-folder-props folder-name)))

(defn create-folder-context [session]
  (doto (session/create-operation-context session)
    (.setFilterString "cmis:name, cmis:path")
    (.setIncludeAcls false)
    (.setIncludeAllowableActions false)
    (.setIncludePolicies false)
    (.setIncludeRelationships IncludeRelationships/NONE)
    (.setRenditionFilterString "cmis:none")
    (.setIncludePathSegments false)
    (.setOrderBy nil)
    (.setCacheEnabled true)))

(defn get-children 
  ([folder]
     (. folder getChildren))
  ([folder context]
     (. folder getChildren context)))

(defn get-page [children skip size]
  (. (. children skipTo skip) getPage size ) )

;(map co/get-object-id (. (folder/get-root-folder in-mem-session) getChildren))
;(map co/get-properties (. (folder/get-root-folder in-mem-session) getChildren))

;(map find-class-name (supers org.apache.chemistry.opencmis.client.runtime.FolderImpl))

