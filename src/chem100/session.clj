(ns chem100.session
  (:require [chem100.document :as doc]
            [chem100.cmisobject :as co]))

  (import '(org.apache.chemistry.opencmis.client.api Session SessionFactory))
  (import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl)
  (import org.apache.chemistry.opencmis.commons.SessionParameter)
  (import org.apache.chemistry.opencmis.commons.enums.BindingType)

(defmacro with-session [& body]
  `(sql/with-connection {:classname "org.h2.Driver"
                         :subprotocol "h2"
                         :subname "mem:"}
     ~@body))

(defn create-parameter [url userid password] 
  "Sample URLs \"http://<host>:<port>/cmis/atom\" \"http://repo.opencmis.org/inmemory/atom/\"
   \"http://localhost:8081/inmemory/atom/\")"
  (doto (new java.util.HashMap) 
    (.put SessionParameter/ATOMPUB_URL url)
    (.put SessionParameter/USER, userid)
    (.put SessionParameter/PASSWORD, password)
    (.put SessionParameter/BINDING_TYPE, (. BindingType/ATOMPUB value))))

(defn session-factory []
  (. SessionFactoryImpl newInstance))

(defn create-session [url userid password repository-id]
  (let [param (create-parameter url userid password)]
  (. param put SessionParameter/REPOSITORY_ID repository-id)
  (. (. SessionFactoryImpl newInstance) createSession param)))

(defn get-object-by-path [session path]
  (. session getObjectByPath path))

(defn get-object-factory [session]
  (. session getObjectFactory))

(defn create-content-stream [session filename mimetype]
  (let [file (doc/create-file filename)
    fis (doc/create-file-input-stream file)]
    (. (get-object-factory session) createContentStream (. file getName) (. file length) mimetype fis)))
