(ns chem100.folder
  (import org.apache.chemistry.opencmis.client.api.Folder))

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

