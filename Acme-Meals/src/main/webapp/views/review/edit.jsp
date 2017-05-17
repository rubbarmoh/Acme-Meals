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

<security:authorize access="hasRole('CRITIC')">

	<form:form action="critic/review/edit.do"	modelAttribute="reviewForm">
	
		<form:hidden path="id"/>
		<form:hidden path="restId"/>
	
		<acme:textbox code="review.title" path="title" />
		<br/>
		<acme:textarea code="review.text" path="text" />
		<br/>
		<form:label path="rate">
			<spring:message code="review.rate" />
		</form:label>	
		<form:select path="rate">
			<form:options items="${stars}" />
		</form:select>
		<form:errors cssClass="error" path="rate" />
		
		<acme:submit name="save" code="review.save"/>
		
		<acme:cancel code="review.cancel" url="restaurant/browse.do" />
	
	</form:form>

</security:authorize>