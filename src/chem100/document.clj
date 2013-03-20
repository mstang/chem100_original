(ns chem100.document
  (import org.apache.chemistry.opencmis.client.api.Folder))
      
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

(defn create-document [folder doc-name]
  (. folder createDocument (create-doc-props doc-name) nil nil))


