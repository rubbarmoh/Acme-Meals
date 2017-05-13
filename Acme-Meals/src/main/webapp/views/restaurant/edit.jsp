<%--
 * edit.jsp
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

<security:authorize access="hasRole('MANAGER')">

	<form:form action="managerActor/restaurant/edit.do"	modelAttribute="restaurantForm">
	
		<form:hidden path="id"/>
	
		<acme:textbox code="restaurant.name" path="name" />
		<br/>
		<acme:textbox code="restaurant.phone" path="phone" />
		<br/>
		<acme:textbox code="restaurant.city" path="city" />
		<br/>
		<acme:textbox code="restaurant.address" path="address" />
		<br/>
		<acme:textbox code="restaurant.email" path="email" />
		<br/>
		<acme:textbox code="restaurant.picture" path="picture" />
		<br/>
		<acme:textbox code="restaurant.deliveryService" path="deliveryService" />
		<br/>
		<acme:textbox code="restaurant.costDelivery" path="costDelivery" />
		<br/>
		<acme:textbox code="restaurant.minimunAmount" path="minimunAmount" />
		<br/>
		<acme:submit name="save" code="restaurant.save"/>
		<jstl:if test="${restaurantForm.id != 0}">
			<input type="submit" name="delete" value="<spring:message code="restaurant.delete" />" onclick="return confirm('<spring:message code="restaurant.confirm.delete" />')" />
		</jstl:if>
		<acme:cancel code="restaurant.cancel" url="managerActor/restaurant/list.do" />
	
	</form:form>

</security:authorize>