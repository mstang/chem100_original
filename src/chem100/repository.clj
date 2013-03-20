(ns chem100.repository
  "Repository Services"
  (import org.apache.chemistry.opencmis.client.api.Repository)
  (:require [chem100.session :as session]))

; ToDo
; We need to get all the repo info and caps info and return as maps
; I think we can use bean

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

;(map (memfn toUpperCase) ["a" "short" "message"])
;The map function applies the function/method toUpperCase to each element in ["a" "short" "message"]

;You can also use the bean function to wrap a Java bean in an immutable Clojure map.

;(bean (new Person "Alexandre" "Martins")) 
;-> {:firstName "Alexandre", :lastName "Martins"} 
;Once converted, you can manipulate the new map using any of Clojure’s map functions, like:

;(:firstName (bean (new Person "Alexandre" "Martins"))) 
;-> Alexandre 
