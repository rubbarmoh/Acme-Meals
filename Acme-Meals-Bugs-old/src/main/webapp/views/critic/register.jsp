<%--
 * register.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('ADMIN')">
<form:form action="${requestURI}" modelAttribute="criticForm">
	<jstl:if test="${criticForm.id==0 || criticForm.username == pageContext.request.remoteUser}">
		<form:hidden path="id"/>
		<jstl:choose>
			<jstl:when test="${criticForm.id!=0}">
				<form:hidden path="username"/>
				<form:hidden path="password"/>
				<form:hidden path="password2"/>
				<fieldset>
					<legend align="left"><spring:message code="critic.personal.info"/></legend>
					<br/>
					<acme:textbox code="critic.name" path="name" />
					<br/>
					<acme:textbox code="critic.surname" path="surname"/>
					<br/>
					<acme:textbox code="critic.email" path="email"/>
					<br/>
					<acme:textbox code="critic.phone" path="phone"/>
					<br/>
					<acme:textbox code="critic.address" path="address"/>
					<br/>
					<acme:textbox code="critic.companyName" path="companyName"/>
					<br/>
				</fieldset>
				
			</jstl:when>
			<jstl:otherwise>
				<fieldset>
					<legend align="left"><spring:message code="critic.account.info"/></legend>
					<acme:textbox code="critic.username" path="username" />
					<br/>
					<acme:password code="critic.password" path="password"/>
					<br/>
					<acme:password code="critic.password2" path="password2"/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="critic.personal.info"/></legend>
					<br/>
					<acme:textbox code="critic.name" path="name" />
					<br/>
					<acme:textbox code="critic.surname" path="surname"/>
					<br/>
					<acme:textbox code="critic.email" path="email"/>
					<br/>
					<acme:textbox code="critic.phone" path="phone"/>
					<br/>
					<acme:textbox code="critic.address" path="address"/>
					<br/>
					<acme:textbox code="critic.companyName" path="companyName"/>
					<br/>
				</fieldset>
			</jstl:otherwise>
		</jstl:choose>
		
		<br/>
		<acme:submit name="save" code="critic.save"/>
		<acme:cancel code="critic.cancel" url="welcome/index.do" />
</jstl:if>

</form:form>
</security:authorize>