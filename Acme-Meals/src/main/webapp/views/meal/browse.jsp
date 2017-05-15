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

<display:table name="meals"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<security:authorize access="hasRole('MANAGER')">
		<display:column>
			<a href="managerActor/meal/edit.do?mealId=${row.id}"><spring:message code="meal.edit" /></a>
		</display:column>
	</security:authorize>

	<spring:message code="meal.category.name" var="categoryHeader" />
	<display:column property="category.name" title="${categoryHeader}" sortable="true"/>
	
	<spring:message code="meal.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"/>
	
	<spring:message code="meal.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"/>
	
	<spring:message code="meal.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}"/>

	<security:authorize access="hasRole('USER')">
	<display:column>
			<input type="button" name="add"
						value="<spring:message code="meal.add" />"
						onclick="javascript: window.location.replace('user/mealOrder/add.do?mealId=${meal.id }')"/><br/>
	</display:column>
	</security:authorize>
	
</display:table>


