(ns chem100.operation-context)

;String	getCacheKey()
;Returns a key for this OperationContext object that is used for caching.
;Set<String>	getFilter()
;Returns the current filter.
;String	getFilterString()
;Returns the filter extended by cmis:objectId, cmis:objectTypeId and cmis:baseTypeId.
;;IncludeRelationships	getIncludeRelationships()
;Returns which relationships should be returned.
;int	getMaxItemsPerPage()
;Returns the current max number of items per page.
;String	getOrderBy()
;Returns the order by rule for operations that return lists.
;Set<String>	getRenditionFilter()
;Returns the current rendition filter.
;String	getRenditionFilterString()
;Returns the current rendition filter.
;boolean	isCacheEnabled()
;Return if caching is enabled.
;boolean	isIncludeAcls()
;Returns if ACLs should returned.
;boolean	isIncludeAllowableActions()
;Returns if allowable actions should returned.
;boolean	isIncludePathSegments()
;Returns if path segments should returned.
;boolean	isIncludePolicies()
;Returns if policies should returned.
;void	setCacheEnabled(boolean cacheEnabled)
;Enables or disables the cache.
;void	setFilter(Set<String> propertyFilter)
;Sets the current filter.
;void	setFilterString(String propertyFilter)
;Sets the current filter.
;;void	setIncludeAcls(boolean include)
;Sets if ACLs should returned.
;;;void	setIncludeAllowableActions(boolean include)
;Sets if allowable actions should returned.
;void	setIncludePathSegments(boolean include)
;Sets if path segments should returned.
;void	setIncludePolicies(boolean include)
;Sets if policies should returned.
;void	setIncludeRelationships(IncludeRelationships include)
;Sets which relationships should be returned.
;void	setMaxItemsPerPage(int maxItemsPerPage)
;Set the max number of items per page for operations that return lists.
;void	setOrderBy(String orderBy)
;Sets the order by rule for operations that return lists.
;void	setRenditionFilter(Set<String> renditionFilter)
;Sets the current rendition filter.
;void	setRenditionFilterString(String renditionFilter)
;Sets the current rendition filter.

