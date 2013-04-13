(ns chem100.core
  (import org.apache.chemistry.opencmis.client.api.ItemIterable)
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic]) 
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

; // Get everything in the root folder and print the names of the objects
;        Folder root = session.getRootFolder();
;        ItemIterable<CmisObject> children = root.getChildren();
;        System.out.println("Found the following objects in the root folder:-");
;        for (CmisObject o : children) {
;            System.out.println(o.getName());
;        }
; we need to be able to write a recursive descent for processing folders

(map println (co/get-properties (last (. (folder/get-root-folder in-mem-session) getChildren))))

(count (co/get-properties (last (. (folder/get-root-folder in-mem-session) getChildren))))
(bean (last (. (folder/get-root-folder in-mem-session) getChildren)))

(count (last (split "org.apache.chemistry.opencmis.client.runtime.DocumentImpl" #"\.")))

(map co/get-object-id (. (folder/get-root-folder in-mem-session) getChildren))

(. (folder/get-root-folder in-mem-session) getChildren)

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

(defn looprecur_fibonacci [n]
  (loop [first 0 second 1 iter 0]
    (if (= iter n)
      first
      (recur second (+ first second) (inc iter)))))

(run* [q]
      (== q 3))

(folder/get-children
(session/get-object in-mem-session "101" (folder/create-folder-context in-mem-session)))

(session/set-max-items-per-page! 
 (session/set-order-by! (session/create-operation-context in-mem-session) "cmis:name")
 10)


;(folder/create-folder-context in-mem-session)


