(ns chem100.repository
  (import org.apache.chemistry.opencmis.client.api.Repository)
  (:require [chem100.session :as session]))

; Repository Services

(defn repositories [parameter]
  (. (session/session-factory) getRepositories parameter))

(defn repository-id [which parameter]
  (. (which (repositories parameter)) getId))

(defn get-repo-info [session]
  (. session getRepositoryInfo))

(defn get-repo-caps [session]
  (. (get-repo-info session) getCapabilities))

; can we do this with a macro
(defn get-product-name [session] 
  (. (get-repo-info session) getProductName))

(defn get-product-version [session] 
  (. (get-repo-info session) getProductVersion))

;(get-object-id (get-repo-info local-host-session))

(defn get-cmis-version-supported [session] 
  (. (get-repo-info session) getCmisVersionSupported))

;(. (get-repo-info local-host-session) toString)
;(. (get-repo-caps local-host-session) getQueryCapability)
;(. (get-repo-caps local-host-session) isGetDescendantsSupported)
;(. (get-repo-caps local-host-session) isGetFolderTreeSupported)

;(get-cmis-version-supported local-host-session)

;        Repository repository = repositories.get(0);
;        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());
;        Session session = sessionFactory.createSession(parameter)
;        System.out.println("Got a connection to repository: " + repository.getName() + ", with id: " + repository.getId());

