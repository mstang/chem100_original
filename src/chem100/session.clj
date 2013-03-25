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


;Acl	applyAcl(ObjectId objectId, List<Ace> addAces, List<Ace> removeAces, AclPropagation aclPropagation)
;Applies ACL changes to an object and potentially dependent objects.
;void	applyPolicy(ObjectId objectId, ObjectId... policyIds)
;Applies a set of policies to an object.
;void	clear()
;Clears all cached data.
;ObjectId	createDocument(Map<String,?> properties, ObjectId folderId, ContentStream contentStream, VersioningState versioningState)
;Creates a new document.
;ObjectId	createDocument(Map<String,?> properties, ObjectId folderId, ContentStream contentStream, VersioningState versioningState, List<Policy> policies, List<Ace> addAces, List<Ace> removeAces)
;Creates a new document.
;ObjectId	createDocumentFromSource(ObjectId source, Map<String,?> properties, ObjectId folderId, VersioningState versioningState)
;Creates a new document from a source document.
;ObjectId	createDocumentFromSource(ObjectId source, Map<String,?> properties, ObjectId folderId, VersioningState versioningState, List<Policy> policies, List<Ace> addAces, List<Ace> removeAces)
;Creates a new document from a source document.
;ObjectId	createFolder(Map<String,?> properties, ObjectId folderId)
;Creates a new folder.
;ObjectId	createFolder(Map<String,?> properties, ObjectId folderId, List<Policy> policies, List<Ace> addAces, List<Ace> removeAces)
;Creates a new folder.
;ObjectId	createObjectId(String id)
;Creates an object id from a String.
;OperationContext	createOperationContext()
;Creates a new operation context object.
;OperationContext	createOperationContext(Set<String> filter, boolean includeAcls, boolean includeAllowableActions, boolean includePolicies, IncludeRelationships includeRelationships, Set<String> renditionFilter, boolean includePathSegments, String orderBy, boolean cacheEnabled, int maxItemsPerPage)
;Creates a new operation context object with the given properties.
;ObjectId	createPolicy(Map<String,?> properties, ObjectId folderId)
;Creates a new policy.
;ObjectId	createPolicy(Map<String,?> properties, ObjectId folderId, List<Policy> policies, List<Ace> addAces, List<Ace> removeAces)
;Creates a new policy.
;QueryStatement	createQueryStatement(String statement)
;Creates a query statement.
;ObjectId	createRelationship(Map<String,?> properties)
;Creates a new relationship.
;ObjectId	createRelationship(Map<String,?> properties, List<Policy> policies, List<Ace> addAces, List<Ace> removeAces)
;Creates a new relationship.
;void	delete(ObjectId objectId)
;Deletes an object and, if it is a document, all versions in the version series.
;void	delete(ObjectId objectId, boolean allVersions)
;Deletes an object.
;Acl	getAcl(ObjectId objectId, boolean onlyBasicPermissions)
;Fetches the ACL of an object from the repository.
;CmisBinding	getBinding()
;Returns the underlying binding object.
;ItemIterable<Document>	getCheckedOutDocs()
;Returns all checked out documents.
;ItemIterable<Document>	getCheckedOutDocs(OperationContext context)
;Returns all checked out documents with the given OperationContext .
;ChangeEvents	getContentChanges(String changeLogToken, boolean includeProperties, long maxNumItems)
;Returns the content changes.
;ChangeEvents	getContentChanges(String changeLogToken, boolean includeProperties, long maxNumItems, OperationContext context)
;Returns the content changes.
;ContentStream	getContentStream(ObjectId docId)
;Retrieves the main content stream of a document
;ContentStream	getContentStream(ObjectId docId, String streamId, BigInteger offset, BigInteger length)
;Retrieves the content stream of a document
;OperationContext	getDefaultContext()
;Returns the current default operation parameters for filtering, paging and caching.
;Locale	getLocale()
;Get the current locale to be used for this session.
;CmisObject	getObject(ObjectId objectId)
;Returns a CMIS object from the session cache.
;CmisObject	getObject(ObjectId objectId, OperationContext context)
;Returns a CMIS object from the session cache.
;CmisObject	getObject(String objectId)
;Returns a CMIS object from the session cache.
;CmisObject	getObject(String objectId, OperationContext context)
;Returns a CMIS object from the session cache.
;CmisObject	getObjectByPath(String path)
;Returns a CMIS object from the session cache.
;CmisObject	getObjectByPath(String path, OperationContext context)
;Returns a CMIS object from the session cache.
;ObjectFactory	getObjectFactory()
;Gets a factory object that provides methods to create the objects used by this API.
;ItemIterable<Relationship>	getRelationships(ObjectId objectId, boolean includeSubRelationshipTypes, RelationshipDirection relationshipDirection, ObjectType type, OperationContext context)
;Fetches the relationships from or to an object from the repository.
;RepositoryInfo	getRepositoryInfo()
;Returns the repository info of the repository associated with this session.
;Folder	getRootFolder()
;Gets the root folder of the repository.
;Folder	getRootFolder(OperationContext context)
;Gets the root folder of the repository with the given OperationContext.
;ItemIterable<ObjectType>	getTypeChildren(String typeId, boolean includePropertyDefinitions)
;Returns the type children of the given type id.
;ObjectType	getTypeDefinition(String typeId)
;Returns the type definition of the given type id.
;List<Tree<ObjectType>>	getTypeDescendants(String typeId, int depth, boolean includePropertyDefinitions)
;Returns the type descendants of the given type id.
;ItemIterable<QueryResult>	query(String statement, boolean searchAllVersions)
;Sends a query to the repository.
;ItemIterable<QueryResult>	query(String statement, boolean searchAllVersions, OperationContext context)
;Sends a query to the repository using the given OperationContext.
;ItemIterable<CmisObject>	queryObjects(String typeId, String where, boolean searchAllVersions, OperationContext context) 
;void	removeObjectFromCache(ObjectId objectId)
;Removes the given object from the cache.
;void	removeObjectFromCache(String objectId)
;Removes the given object from the cache.
;void	removePolicy(ObjectId objectId, ObjectId... policyIds)
;Removes a set of policies from an object.
;Acl	setAcl(ObjectId objectId, List<Ace> aces)
;Removes the direct ACEs of an object and sets the provided ACEs.
;void	setDefaultContext(OperationContext context)
;Sets the current session parameters for filtering, paging and caching.

