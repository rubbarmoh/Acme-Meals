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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>



<security:authorize access="hasRole('USER')">

<table id="row" class="table">
	
	<tbody>
		<tr>
			<td rowspan="10">
				<img src="${mealOrder.restaurant.picture}" width="200" height="200" >
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "mealOrder.restaurant"/>
			</th>
			<td>
				<jstl:out value="${mealOrder.restaurant.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "mealOrder.amount"/>
			</th>
			<td>
				<jstl:out value="${mealOrder.amount }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "mealOrder.moment"/>
			</th>
			<td>
				<fmt:formatDate value="${mealOrder.moment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "mealOrder.status"/>
			</th>
			<td>
				<jstl:out value="${mealOrder.status }" />
			</td>
		</tr>
</table>
<display:table name="meals"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<spring:message code="meal.category.name" var="categoryHeader" />
	<display:column property="category.name" title="${categoryHeader}" sortable="true"/>
	
	<spring:message code="meal.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"/>
	
	<spring:message code="meal.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"/>
	
	<spring:message code="meal.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}"/>

	
	<display:column>
			<input type="button" name="add"
						value="<spring:message code="meal.add" />"
						onclick="javascript: window.location.replace('user/quantity/add.do?mealOrderId=${mealOrder.id }&mealId=${row.id }&restaurantId=${mealOrder.restaurant.id }')"/><br/>
	</display:column>
	
	
</display:table>
<display:table name="quantities" id="row" class="displaytag" pagesize="10" requestURI="${requestURI}">

	<spring:message code="mealOrder.meal" var="titleHeader" />
	<display:column property="meal.title" title="${titleHeader }"/>
	
	<spring:message code="mealOrder.quantity" var="quantityHeader" />
	<display:column property="quantity" title="${quantityHeader }" />
	
	
	<display:column>
	<form:form	action="user/quantity/delete.do?quantityId=${row.id }"> 
			<input type="submit" name="delete"
						value="<spring:message code="mealOrder.deleteMeal" />"
						onclick="return confirm('<spring:message code="mealOrder.confirm.deleteMeal" />')"/><br/>
						</form:form>
	</display:column>
	
</display:table>
<jstl:if test="${mealOrder.status=='DRAFT'}">
<form:form	action="user/mealOrder/delete.do?mealOrderId=${mealOrder.id }"> 
			<input type="submit" name="delete"
						value="<spring:message code="mealOrder.delete" />"
						onclick="return confirm('<spring:message code="mealOrder.confirm.delete" />')"/><br/>
						</form:form>
</jstl:if>
</security:authorize>
