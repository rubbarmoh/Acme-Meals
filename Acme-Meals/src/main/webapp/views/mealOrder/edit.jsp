<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('USER')">

	<form:form	action="user/mealOrder/edit.do" modelAttribute="mealOrderForm"> 
		
		<form:hidden path="restaurantId"/>
		<form:hidden path="mealOrderId"/>
		
		<h3><spring:message code="mealOrder.pickOne"/></h3>
		
		
		<acme:checkbox code="mealOrder.pickUp" path="pickUp" />
		
		<jstl:choose>
			<jstl:when test="${minimun && dev}">
				<acme:textbox code="mealOrder.deliveryAddress" path="deliveryAddress"/>
			</jstl:when>
			<jstl:when test="${minimun==false && dev }">
				<acme:textbox code="mealOrder.deliveryAddress" readonly="true" path="deliveryAddress"/>
				<b><spring:message code="mealOrder.noMinimun"/></b>
			</jstl:when>
		</jstl:choose>
		<br><br>
		<acme:submit name="save" code="mealOrder.save"/>
		<acme:cancel code="mealOrder.cancel" url="user/mealOrder/morder.do?restaurantId=${mealOrderForm.restaurantId}"/>
		
	</form:form>

</security:authorize>