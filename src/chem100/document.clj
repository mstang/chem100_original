(ns chem100.document
  (import org.apache.chemistry.opencmis.client.api.Folder)
  (import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships)
  (:require [chem100.session :as session]))
      
;cmis:isImmutable - Boolean
;cmis:isLatestVersion - Boolean
;cmis:MajorVersion - Boolean
;cmis:versionLabel - String
;cmis:versionSeriesId - ID
;cmis:isVersionSeriesCheckedOut - Boolean
;cmis:versionSeriesCheckedOutBy - String
;cmis:versionSeriesCheckedOutId - ID
;cmis:checkInComment - String
;cmis:contentStreamLength - Integer
;cmis:contentStreamMimeType - String
;cmis:contentStreamFileName - String
;cmis:contentStreamId - ID

; 2.4.3 Listing a Documents Properties - Listing 2.3

(defn create-doc-props [folder-name]
  (doto (new java.util.HashMap)
    (.put "cmis:objectTypeId" "cmis:document")
    (.put "cmis:name" folder-name)))

(defn create-document 
  ([folder doc-name]
     (. folder createDocument (create-doc-props doc-name) nil nil))
  ([folder doc-name content-stream]
     (. folder createDocument (create-doc-props doc-name) content-stream nil)))

(defn create-file [path]
   (new java.io.File path))

(defn create-file-input-stream [file]
  (new java.io.FileInputStream file))

(defn content? [document]
  (>= (. document getContentStreamLength) 0))

;; this should be a let
(defn get-content [document]
  (list 
   (doto (. document getContentStream)
     (.getStream)
     (.getFileName)
     (.getMimeType)
     (.getLength))))

(defn create-document-context-content [session]
  (doto (session/create-operation-context session)
    (.setFilterString "cmis:objectId, cmis:baseTypeId, cmis:name, cmis:contentStreamLength, cmis:contentStreamMimeType")
    (.setIncludeAcls false)
    (.setIncludeAllowableActions true)
    (.setIncludePolicies false)
    (.setIncludeRelationships IncludeRelationships/NONE)
    (.setRenditionFilterString "cmis:none")
    (.setIncludePathSegments false)
    (.setOrderBy "cmis:name")
    (.setCacheEnabled false)
    (.setMaxItemsPerPage 10)))

(defn create-document-context-thumbnail [session]
  (doto (session/create-operation-context session)
    (.setFilterString "*")
    (.setIncludeAcls false)
    (.setIncludeAllowableActions true)
    (.setIncludePolicies false)
    (.setIncludeRelationships IncludeRelationships/NONE)
    (.setRenditionFilterString "cmis:thumbnail")
    (.setIncludePathSegments false)
    (.setOrderBy nil)
    (.setCacheEnabled true)))

