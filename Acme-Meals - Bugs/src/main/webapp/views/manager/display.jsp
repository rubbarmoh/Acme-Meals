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
				<spring:message code = "manager.name"/>
			</th>
			<td>
				<jstl:out value="${manager.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.surname" />
			</th>
			<td>
				<jstl:out value="${manager.surname }" />
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="manager.email" />
			</th>
			<td>
				<jstl:out value="${manager.email }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.phone" />
			</th>
			<td>
				<jstl:out value="${manager.phone}" />
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="manager.address" />
			</th>
			<td>
				<jstl:out value="${manager.address}" />
			</td>
		</tr>
		<jstl:if test="${manager.userAccount.username eq pageContext.request.remoteUser}">
		<tr>
			<th>
				<spring:message code="manager.creditCard.holderName" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.holderName}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.creditCard.brandName" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.brandName }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.creditCard.number" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.number }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.creditCard.expirationMonth" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.expirationMonth }"/>
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="manager.creditCard.expirationYear" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.expirationYear }"/>
			</td>
		</tr>
		
		<tr>
			<th>
				<spring:message code="manager.creditCard.cvv" />
			</th>
			<td>
				<jstl:out value="${manager.creditCard.cvv }"/>
			</td>
		</tr>
		</jstl:if>
	</tbody>
	
</table>