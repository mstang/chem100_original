(ns chem100.core)

(import '(org.apache.chemistry.opencmis.client.api CmisObject Folder ItemIterable Repository Session SessionFactory))
(import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl)
(import org.apache.chemistry.opencmis.commons.SessionParameter)
(import org.apache.chemistry.opencmis.commons.enums.BindingType)

;; need to create a session to connect to:
;; http://localhost:8081/inmemory/atom
;; user test, password test

(def sessionFactory (SessionFactoryImpl/newInstance))
(def parameter (new java.util.HashMap))

(. parameter put "hello" "world")
;(. parameter put SessionParameter/ATOMPUB_URL "http://repo.opencmis.org/inmemory/atom/")
(. parameter put SessionParameter/ATOMPUB_URL "http://localhost:8081/inmemory/atom/")
(. parameter put SessionParameter/BINDING_TYPE (. BindingType/ATOMPUB value))

(defn get-repositories [parameter]
  (. sessionFactory getRepositories parameter))

(defn repository-id [parameter]
  (. (first (get-repositories parameter)) getId))

(defn create-session [parameter]
  )
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

