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

<display:table name="mealOrders" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >

	<spring:message code="mealOrder.restaurant" var="restaurantHeader" />
	<display:column property="restaurant.name" title="${restaurantHeader}"/>
	
	<spring:message code="mealOrder.moment" var="momentHeader" />
	<display:column title="${momentHeader}" sortable="false"><fmt:formatDate value="${row.moment }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="mealOrder.amount" var="amountHeader" />
	<display:column property="amount" title="${amountHeader}"/>

	<spring:message code="mealOrder.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}"/>
	
	<display:column>
		<a href="mealOrder/display.do?mealOrderId=${row.id}"><spring:message code="mealOrder.display" /></a>
	</display:column>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.manager.userAccount.username == pageContext.request.remoteUser &&
			(row.status=='PENDING' || row.status=='INPROGRESS')}">
			<display:column>
				<input type="button" name="step" value="<spring:message code="mealOrder.step" />"
						onclick="javascript: window.location.replace('mealOrder/step.do?mealOrderId=${row.id}')" />
			</display:column>
		</jstl:if>
	</security:authorize>
	
	
	
	
</display:table>


