(ns chem100.core)

(import '(org.apache.chemistry.opencmis.client.api CmisObject Folder ItemIterable Repository Session SessionFactory))
(import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl)
(import org.apache.chemistry.opencmis.commons.SessionParameter)
(import org.apache.chemistry.opencmis.commons.enums.BindingType)

; Repository Services
; Navigation Services
; Object Services
; Multi-filing Services
; Discovery Services
; Versioning Services
; Relationship Services
; Policy Services
; ACL (Access Control List) Services

(defn create-parameter [url] 
  (let [param (java.util.HashMap.)]
    (. param put SessionParameter/ATOMPUB_URL url)
    (. param put SessionParameter/BINDING_TYPE (. BindingType/ATOMPUB value))
        param))

(defn session-factory []
  (. SessionFactoryImpl newInstance))

(defn repositories [parameter]
  (. (session-factory) getRepositories parameter))

(defn repository-id [which parameter]
  (. (which (repositories parameter)) getId))

(defmacro with-tmp-db [& body]
  `(sql/with-connection {:classname "org.h2.Driver"
                         :subprotocol "h2"
                         :subname "mem:"}
     ~@body))

;(. parameter put SessionParameter/ATOMPUB_URL "http://repo.opencmis.org/inmemory/atom/")
;(. parameter put SessionParameter/ATOMPUB_URL "http://localhost:8081/inmemory/atom/")
(defn create-session [url]
  (let [param (create-parameter url)]
    [sessionFactory (session-factory)]
  (. param put SessionParameter/REPOSITORY_ID (repository-id first param))
  (. sessionFactory createSession param)))

(def local-host-session (create-session "http://localhost:8081/inmemory/atom/"))

(defn get-root-folder [session] 
  (. session getRootFolder))

(defn get-name [cmis-object] 
  (. cmis-object getName))

(defn get-object-id [cmis-object]
  (. cmis-object getId))

(defn get-repo-info [session]
  (. session getRepositoryInfo))

(defn get-repo-caps [session]
  (. (get-repo-info session) getCapabilities))

; can we do this with a macro
(defn get-product-name [session] 
  (. (get-repository-info session) getProductName))

(defn get-product-version [session] 
  (. (get-repository-info session) getProductVersion))

(map get-object-id (. (get-root-folder local-host-session) getChildren))
(get-object-id (get-repository-info local-host-session))

(defn get-cmis-version-supported [session] 
  (. (get-repository-info local-host-session) getCmisVersionSupported))


(. (get-repository-info local-host-session) toString)

(. (get-repo-caps local-host-session) getQueryCapability)
(. (get-repo-caps local-host-session) isGetDescendantsSupported)
(. (get-repo-caps local-host-session) isGetFolderTreeSupported)

(get-cmis-version-supported local-host-session)

;        Repository repository = repositories.get(0);
;        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());
;        Session session = sessionFactory.createSession(parameter)
;        System.out.println("Got a connection to repository: " + repository.getName() + ", with id: " + repository.getId());
;        // Get everything in the root folder and print the names of the objects
;        Folder root = session.getRootFolder();
;        ItemIterable<CmisObject> children = root.getChildren();
;        System.out.println("Found the following objects in the root folder:-");
;        for (CmisObject o : children) {
;            System.out.println(o.getName());
;        }

