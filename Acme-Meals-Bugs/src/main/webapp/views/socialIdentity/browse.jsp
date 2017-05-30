<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<security:authorize
	access="hasRole('MANAGER')">
<display:table name="socialIdentities"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<spring:message code="socialIdentity.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}">
		<img src="${row.picture}" width="100" height="100">
	</display:column>
					
	<spring:message code="socialIdentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true"/>
	
	<spring:message code="socialIdentity.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="true"/>
	
	<spring:message code="socialIdentity.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" sortable="false"/>
	
	<display:column>
		<a href="socialIdentity/edit.do?socialIdentityId=${row.id}"><spring:message code="socialIdentity.edit" /></a>
	</display:column>			
		
</display:table>
</security:authorize>