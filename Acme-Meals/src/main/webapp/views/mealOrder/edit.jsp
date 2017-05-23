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
		
		<acme:checkbox code="mealOrder.pickUp" path="pickUp" />
		<br/>
		<acme:textbox code="mealOrder.deliveryAdress" path="deliveryAdress"/>
		<acme:submit name="save" code="mealOrder.save"/>
		<acme:cancel code="mealOrder.cancel" url="user/mealOrder/morder.do?restaurantId=${restaurantId}"/>
		
		
	</form:form>

</security:authorize>