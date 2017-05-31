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

	<display:table name="categories" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >
	
		<spring:message code="category.name" var="categoryHeader" />
		<display:column property="name" title="${categoryHeader}"/>
		
		<display:column>
			<a href="managerActor/category/edit.do?categoryId=${row.id}"><spring:message code="category.edit" /></a>
		</display:column>
	
	</display:table>

</security:authorize>
