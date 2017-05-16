<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
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

<display:table name="quantities" id="row" class="displaytag" pagesize="10" requestURI="${requestURI}">

	<spring:message code="mealOrder.meal" var="titleHeader" />
	<display:column property="meal.title" title="${titleHeader }"/>
	
	<spring:message code="mealOrder.quantity" var="quantityHeader" />
	<display:column property="quantity" title="${quantityHeader }" />
	
</display:table>

<acme:cancel url="mealOrder/browseByUser.do" code="mealOrder.cancel"/>
</security:authorize>
	