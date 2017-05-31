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

<form:form action="${requestURI}" modelAttribute="userForm">
	<jstl:if test="${userForm.id==0 || userForm.username == pageContext.request.remoteUser}">
		<form:hidden path="id"/>
		<jstl:choose>
			<jstl:when test="${userForm.id!=0}">
				<form:hidden path="username"/>
				<form:hidden path="password"/>
				<form:hidden path="password2"/>
				<form:hidden path="agreed"/>
				<fieldset>
					<legend align="left"><spring:message code="user.personal.info"/></legend>
					<br/>
					<acme:textbox code="user.name" path="name" />
					<br/>
					<acme:textbox code="user.surname" path="surname"/>
					<br/>
					<acme:textbox code="user.email" path="email"/>
					<br/>
					<acme:textbox code="user.phone" path="phone"/>
					<br/>
					<acme:textbox code="user.address" path="address"/>
					<br/>
				</fieldset>
				
				<fieldset>
					<legend align="left"><spring:message code="user.creditCard.info"/></legend>
					<acme:textbox code="user.creditCard.holderName" path="creditCard.holderName"/>
					<br/>
					<br/><spring:message code="user.creditCard.brandName.check" /><br/>			
					<acme:textbox code="user.creditCard.brandName" path="creditCard.brandName"/>
					
					<br/>		
					<acme:textbox code="user.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="user.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="user.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="user.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
						<br/>
				<acme:submit name="save" code="user.save"/>
				<acme:cancel code="user.cancel" url="restaurant/browse.do" />
			</jstl:when>
			<jstl:otherwise>
				<fieldset>
					<legend align="left"><spring:message code="user.account.info"/></legend>
					<acme:textbox code="user.username" path="username" />
					<br/>
					<acme:password code="user.password" path="password"/>
					<br/>
					<acme:password code="user.password2" path="password2"/>
						
					<br/>
					<form:checkbox path="agreed"/>
					<form:label path="agreed">
						<spring:message code="user.register.agree" />
						<a href="misc/lopd.do"><spring:message code="user.register.agree.2"/></a>
					</form:label>
					<form:errors path="agreed" cssClass="error" />
					<br/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="user.personal.info"/></legend>
					<br/>
					<acme:textbox code="user.name" path="name" />
					<br/>
					<acme:textbox code="user.surname" path="surname"/>
					<br/>
					<acme:textbox code="user.email" path="email"/>
					<br/>
					<acme:textbox code="user.phone" path="phone"/>
					<br/>
					<acme:textbox code="user.address" path="address"/>
					<br/>
				</fieldset>
				
				<fieldset>
					<legend align="left"><spring:message code="user.creditCard.info"/></legend>
					<acme:textbox code="user.creditCard.holderName" path="creditCard.holderName"/>
					<br/>			
					<acme:textbox code="user.creditCard.brandName" path="creditCard.brandName"/><spring:message code="user.creditCard.brandName.check" />
					<br/>		
					<acme:textbox code="user.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="user.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="user.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="user.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
						<br/>
				<acme:submit name="save" code="user.save"/>
				<acme:cancel code="user.cancel" url="welcome/index.do" />
			</jstl:otherwise>
		</jstl:choose>
		

</jstl:if>

</form:form>