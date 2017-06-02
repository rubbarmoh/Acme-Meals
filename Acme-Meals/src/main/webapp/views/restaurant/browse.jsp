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

<!-- Search for a restaurant -->

<input type="text" value="" id="textSearch" />
<input type="button" id="buttonSearch"
	value="<spring:message code="restaurant.search"/>" />
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#buttonSearch").click(function(){
			window.location.replace('restaurant/search.do?key=' + $("#textSearch").val());
		});
		
		$("#buttonSearch").onsubmit(function(){
			window.location.replace('restaurant/search.do?key=' + $("#textSearch").val());
		});
	});
</script>

<display:table name="restaurants"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >

	<spring:message code="restaurant.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}">
		<img src="${row.picture}" width="100" height="100">
	</display:column>
	
	<spring:message code="restaurant.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="restaurant.city" var="cityHeader" />
	<display:column property="city" title="${cityHeader}"/>

	<spring:message code="restaurant.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}"/>
	
	<spring:message code="restaurant.avgStars" var="avgStarsHeader" />
	<display:column property="avgStars" title="${avgStarsHeader}"/>
	
	<spring:message code="restaurant.deliveryService" var="deliveryServiceHeader" />
	<display:column title="${deliveryServiceHeader}">
	<jstl:choose>
		<jstl:when test="${row.deliveryService == true}">
			<spring:message code = "restaurant.yes"/>
		</jstl:when>
		<jstl:otherwise>
			<spring:message code = "restaurant.no"/>
		</jstl:otherwise>
	</jstl:choose>
	</display:column>
	
	<spring:message code="restaurant.costDelivery" var="costDeliveryHeader" />
	<display:column property="costDelivery" title="${costDeliveryHeader}"/>
	
	<spring:message code="restaurant.minimunAmount" var="minimunAmountHeader" />
	<display:column property="minimunAmount" title="${minimunAmountHeader}"/>

		
	<display:column>
		<a href="restaurant/display.do?restaurantId=${row.id}"><spring:message code="restaurant.display" /></a>
	</display:column>
	
	
</display:table>


