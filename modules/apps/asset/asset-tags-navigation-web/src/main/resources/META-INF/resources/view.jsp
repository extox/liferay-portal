<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<AssetTag> assetTags = null;

if (showAssetCount && (classNameId > 0)) {
	assetTags = AssetTagServiceUtil.getTags(scopeGroupId, classNameId, null, 0, maxAssetTags, new AssetTagCountComparator());
}
else {
	assetTags = AssetTagServiceUtil.getGroupTags(scopeGroupId, 0, maxAssetTags, new AssetTagCountComparator());
}

assetTags = ListUtil.sort(assetTags);
%>

<liferay-ddm:template-renderer
	className="<%= AssetTag.class.getName() %>"
	contextObjects='<%=
		HashMapBuilder.<String, Object>put(
			"scopeGroupId", Long.valueOf(scopeGroupId)
		).build()
	%>'
	displayStyle="<%= displayStyle %>"
	displayStyleGroupId="<%= displayStyleGroupId %>"
	entries="<%= assetTags %>"
>
	<liferay-asset:asset-tags-navigation
		classNameId="<%= classNameId %>"
		displayStyle="<%= displayStyle %>"
		hidePortletWhenEmpty="<%= true %>"
		maxAssetTags="<%= maxAssetTags %>"
		showAssetCount="<%= showAssetCount %>"
		showZeroAssetCount="<%= showZeroAssetCount %>"
	/>
</liferay-ddm:template-renderer>