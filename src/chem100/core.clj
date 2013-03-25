(ns chem100.core
  (import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:require [chem100.session :as session]
            [chem100.repository :as repo]
            [chem100.folder :as folder]
            [chem100.document :as doc]
            [chem100.cmisobject :as co]))
      
(use '[clojure.string :only (join split)])

; ToDo - CMIS Scripts as examples and CMIS.groovy

; Navigation Services
; Multi-filing Services
; Discovery Services
; Versioning Services
; Relationship Services
; Policy Services

; ACL (Access Control List) Services

(def in-mem-session 
  (session/create-session "http://localhost:8081/inmemory/atom/" "test" "test" "A1"))

(map co/get-object-id (. (folder/get-root-folder in-mem-session) getChildren))
(co/get-object-id (repo/get-repo-info in-mem-session))

(. (repo/get-repo-info in-mem-session) toString)

(. (repo/get-repo-caps in-mem-session) getQueryCapability)
(. (repo/get-repo-caps in-mem-session) isGetDescendantsSupported)
(. (repo/get-repo-caps in-mem-session) isGetFolderTreeSupported)

(repo/get-cmis-version-supported in-mem-session)
;        // Get everything in the root folder and print the names of the objects
;        Folder root = session.getRootFolder();
;        ItemIterable<CmisObject> children = root.getChildren();
;        System.out.println("Found the following objects in the root folder:-");
;        for (CmisObject o : children) {
;            System.out.println(o.getName());
;        }
(bean (repo/get-repo-info in-mem-session))
(:capabilities (bean (repo/get-repo-info in-mem-session)))
(bean (repo/get-repo-caps in-mem-session))

; we need to be able to write a recursive descent for processing folders

(map co/get-properties (. (folder/get-root-folder in-mem-session) getChildren))

(map println (co/get-properties (last (. (folder/get-root-folder in-mem-session) getChildren))))

(count (co/get-properties (last (. (folder/get-root-folder in-mem-session) getChildren))))
(bean (last (. (folder/get-root-folder in-mem-session) getChildren)))

(count (last (split "org.apache.chemistry.opencmis.client.runtime.DocumentImpl" #"\.")))

(split "root:*:0:0:admin:/var/root:/bin/sh" #"\.")

(defn find-class-name [package]
  (last (split (str package) #"\.")))

(map find-class-name (supers org.apache.chemistry.opencmis.client.runtime.FolderImpl))

(map co/get-object-id (. (folder/get-root-folder in-mem-session) getChildren))
(map co/get-object-id (. (folder/get-root-folder in-mem-session) getChildren))
;(folder/create-folder (folder/get-root-folder in-mem-session) "My Test Folder")
;(doc/create-document (folder/get-root-folder in-mem-session) "My Test Document")
;(session/get-object-by-path in-mem-session "/My Test Folder")

;(. (new java.io.File "./purchase_order1.pdf") getName)

;(co/get-base-type-id
; (session/get-object-by-path in-mem-session "/My Test Folder/purchase_order1"))

;(doc/create-document 
; (session/get-object-by-path in-mem-session "/My Test Folder")
; "purchase_order1"
; (session/create-content-stream in-mem-session "./purchase_order1.pdf" "application/pdf")))



