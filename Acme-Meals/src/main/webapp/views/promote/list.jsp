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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('MANAGER')">

<display:table name="promotes"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
			
	<spring:message code="promote.beginning" var="momentHeader" />
	<display:column title="${momentHeader}" sortable="false"><fmt:formatDate value="${row.beginning }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="promote.ending" var="momentendingHeader" />
	<display:column title="${momentendingHeader}" sortable="false"><fmt:formatDate value="${row.ending }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="promote.timesDisplayed" var="timesDisplayedHeader" />
	<display:column property="timesDisplayed" title="${timesDisplayedHeader}" sortable="true"/>
	
	<display:column>
		<a href="restaurant/display.do?restaurantId=${row.restaurant.id}"><spring:message code="promote.display.restaurant" /></a>
	</display:column>
	
</display:table>
	
	<input type="button" name="create"
						value="<spring:message code="promote.generate" />"
						onclick="javascript: window.location.replace('managerActor/promote/generate.do')"/><br/>

</security:authorize>
