(ns chem100.folder)

(import '(org.apache.chemistry.opencmis.client.api CmisObject Folder ItemIterable))

(defn create-folder-props [folder-name]
  (let [param (java.util.HashMap.)]
    (. param put "cmis:objectTypeId" "cmis:folder")
    (. param put "cmis:name" folder-name)
    param))

(defn get-root-folder [session] 
  (. session getRootFolder))

;(map get-object-id (. (get-root-folder local-host-session) getChildren))

(defn create-folder [parent folder-name]
  (. parent createFolder (create-folder-props folder-name)))

; need to walk the tree of descendants recursively.
; getDescendants() -
; getDescentants() -
; getChildren() -
; getFolderTree()
; getFolderParent()
; getObjectParents()

;        // Get everything in the root folder and print the names of the objects
;        Folder root = session.getRootFolder();
;        ItemIterable<CmisObject> children = root.getChildren();
;        System.out.println("Found the following objects in the root folder:-");
;        for (CmisObject o : children) {
;            System.out.println(o.getName());
;        }

