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

<display:table name="reviews"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >

	<spring:message code="review.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"/>
	
	<display:column>
		<a href="review/display.do?reviewId=${row.id}"><spring:message code="review.display" /></a>
	</display:column>
	
	<display:column>
		<a href="critic/display.do?criticId=${row.id}"><spring:message code="review.critic.display" /></a>
	</display:column>
	
	
	
</display:table>


