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

<security:authorize
	access="hasRole('MANAGER')">

	<form:form	action="${requestURI}"	modelAttribute="promote"> 
	
		<form:hidden path="id"/>
		<form:hidden path="timesDisplayed"/>
		<form:hidden path="totalDisplayed"/>
		
		<acme:textbox code="promote.beginning" path="beginning"/>
		<acme:textbox code="promote.ending" path="ending"/>
		<form:label path="restaurant">
			<spring:message code="promote.restaurant" />
		</form:label>	
		<form:select path="restaurant">
			<form:options items="${restaurants}" />
		</form:select>
		<form:errors cssClass="error" path="restaurant" />
		
		<acme:submit name="save" code="promote.save"/>
		<acme:cancel code="promote.cancel" url="managerActor/promote/list.do"/>
		
		
	</form:form>

</security:authorize>