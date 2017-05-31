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

	<form:form	action="user/comment/edit.do" modelAttribute="commentForm"> 
		
		<form:hidden path="restaurantId"/>
		
		
		<acme:textbox code="comment.title" path="title"/>
		<acme:textarea code="comment.text" path="text"/>
		<form:label path="stars">
			<spring:message code="comment.stars" />
		</form:label>	
		<form:select path="stars">
			<form:options items="${stars}" />
		</form:select>
		<form:errors cssClass="error" path="stars" />
		
		
		<acme:submit name="save" code="comment.save"/>
		<acme:cancel code="comment.cancel" url="restaurant/browse.do"/>
		
		
	</form:form>

</security:authorize>