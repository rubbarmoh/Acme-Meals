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

<security:authorize access="hasAnyRole('USER','MANAGER')">

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
			<th><spring:message code = "restaurant.deliveryService"/></th>
			<jstl:if test="${mealOrder.restaurant.deliveryService == true}">
				<th><spring:message code = "restaurant.costDelivery"/></th>
				<th><spring:message code = "restaurant.minimunAmount"/></th>
			</jstl:if>
		</tr>
		<tr>
			<jstl:choose>
				<jstl:when test="${mealOrder.restaurant.deliveryService == true}">
						<td><spring:message code = "restaurant.yes"/></td>
						<td><jstl:out value="${mealOrder.restaurant.costDelivery }"/></td>
						<td><jstl:out value="${mealOrder.restaurant.minimunAmount }"/></td>
				</jstl:when>
				<jstl:otherwise>
						<td><spring:message code = "restaurant.no"/></td>
				</jstl:otherwise>
			</jstl:choose>
			
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
			<jstl:choose>
				<jstl:when test="${mealOrder.pickUp == true}">
						<th><spring:message code = "mealOrder.pickUp"/></th>
						<td><spring:message code = "restaurant.yes"/></td>
				</jstl:when>
				<jstl:otherwise>
						<th><spring:message code = "mealOrder.deliveryAddress"/></th>
						<td><jstl:out value="${mealOrder.deliveryAddress}"/></td>
				</jstl:otherwise>
			</jstl:choose>
			
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
</security:authorize>

<security:authorize access="hasRole('USER')">

<acme:cancel url="mealOrder/browseByUser.do" code="mealOrder.cancel"/>
</security:authorize>
	