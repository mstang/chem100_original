(ns chem100.session)

(import '(org.apache.chemistry.opencmis.client.api Session SessionFactory))
(import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl)
(import org.apache.chemistry.opencmis.commons.SessionParameter)
(import org.apache.chemistry.opencmis.commons.enums.BindingType)

(defmacro with-tmp-db [& body]
  `(sql/with-connection {:classname "org.h2.Driver"
                         :subprotocol "h2"
                         :subname "mem:"}
     ~@body))

(defn create-parameter [url] 
  (let [param (java.util.HashMap.)]
    (. param put SessionParameter/ATOMPUB_URL url)
    (. param put SessionParameter/BINDING_TYPE (. BindingType/ATOMPUB value))
        param))

(defn session-factory []
  (. SessionFactoryImpl newInstance))

;(. parameter put SessionParameter/ATOMPUB_URL "http://repo.opencmis.org/inmemory/atom/")
;(. parameter put SessionParameter/ATOMPUB_URL "http://localhost:8081/inmemory/atom/")

(defn create-session [url repository-id]
  (let [param (create-parameter url)]
  (. param put SessionParameter/REPOSITORY_ID repository-id)
  (. (. SessionFactoryImpl newInstance) createSession param)))

(def local-host-session (create-session "http://localhost:8081/inmemory/atom/" "A1"))

