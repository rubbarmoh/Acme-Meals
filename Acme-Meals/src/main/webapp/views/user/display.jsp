<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<table id="row" class="table">
	
	<tbody>
		<tr>
			<th>
				<spring:message code = "user.name"/>
			</th>
			<td>
				<jstl:out value="${user.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.surname" />
			</th>
			<td>
				<jstl:out value="${user.surname }" />
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="user.email" />
			</th>
			<td>
				<jstl:out value="${user.email }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.phone" />
			</th>
			<td>
				<jstl:out value="${user.phone}" />
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="user.address" />
			</th>
			<td>
				<jstl:out value="${user.address}" />
			</td>
		</tr>
		<jstl:if test="${user.userAccount.username eq pageContext.request.remoteUser}">
		<tr>
			<th>
				<spring:message code="user.creditCard.holderName" />
			</th>
			<td>
				<jstl:out value="${user.creditCard.holderName}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.creditCard.brandName" />
			</th>
			<td>
				<fmt:formatDate value="${user.creditCard.brandName }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.creditCard.number" />
			</th>
			<td>
				<fmt:formatDate value="${user.creditCard.number }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.creditCard.expirationMonth" />
			</th>
			<td>
				<fmt:formatDate value="${user.creditCard.expirationMonth }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="user.creditCard.expirationYear" />
			</th>
			<td>
				<fmt:formatDate value="${user.creditCard.expirationYear }"/>
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="user.creditCard.cvv" />
			</th>
			<td>
				<fmt:formatDate value="${user.creditCard.cvv }"/>
			</td>
		</tr>
		</jstl:if>
	</tbody>
	
</table>