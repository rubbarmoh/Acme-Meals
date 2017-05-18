<%--
 * dashboard.jsp
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

<security:authorize access="hasRole('MANAGER')">

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.ordersPerRestaurant" /></legend>
		<table id="ordersPerRestaurant" class="table">
			<tr>
				<jstl:if test="${not empty restaurants1 }">
					<jstl:forEach var="X" items="${restaurants1}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${ordersPerRestaurant.get(X)} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.restaurantMoreStars" /></legend>
		<table id="restaurantMoreStars" class="table">
			<tr>
				<jstl:if test="${not empty restaurantMoreStars }">
					<jstl:forEach var="X" items="${restaurantMoreStars}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.avgStars} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.restaurantLessStars" /></legend>
		<table id="restaurantLessStars" class="table">
			<tr>
				<jstl:if test="${not empty restaurantLessStars }">
					<jstl:forEach var="X" items="${restaurantLessStars}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.avgStars} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.avgProfitMyRestaurants" /></legend>
		<table id="avgProfitMyRestaurants" class="table">
			<tr>
				<jstl:if test="${not empty avgProfitMyRestaurants }">
					
						<td>	<jstl:out value="${avgProfitMyRestaurants} "/><td>
					
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.profitByRestaurant" /></legend>
		<table id="profitByRestaurant" class="table">
			<tr>
				<jstl:if test="${not empty restaurants }">
					<jstl:forEach var="X" items="${restaurants}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${profitByRestaurant.get(X)} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.restaurantMoreProfit" /></legend>
		<table id="restaurantMoreProfit" class="table">
			<tr>
				<jstl:if test="${not empty restaurantMoreProfit }">
					<jstl:forEach var="X" items="${restaurantMoreProfit}">
					<tr>
						<td>	<jstl:out value="${X.name}"/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.restaurantsWithMore10PercentOrders" /></legend>
		<table id="restaurantsWithMore10PercentOrders" class="table">
			<tr>
				<jstl:if test="${not empty restaurantsWithMore10PercentOrders }">
					<jstl:forEach var="X" items="${restaurantsWithMore10PercentOrders}">
					<tr>
						<td>	<jstl:out value="${X.name}"/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="manager.restaurantsWithLess10PercentOrders" /></legend>
		<table id="restaurantsWithLess10PercentOrders" class="table">
			<tr>
				<jstl:if test="${not empty restaurantsWithLess10PercentOrders }">
					<jstl:forEach var="X" items="${restaurantsWithLess10PercentOrders}">
					<tr>
						<td>	<jstl:out value="${X.name}"/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

</security:authorize>