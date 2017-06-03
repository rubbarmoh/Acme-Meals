<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasAnyRole('MANAGER')">
<table id="row" class="table" >
	
	<tbody>
		<tr>
			<th>
				<spring:message code = "monthlyBill.cost"/>
			</th>
			<td>
				<jstl:out value="${monthlyBill.cost }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.cc"/>
			</th>
			<td>
				<spring:message code = "monthlyBill.ccc"/> <jstl:out value="${aux}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.moment"/>
			</th>
			<td>
				<fmt:formatDate value="${monthlyBill.moment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.paidMoment"/>
			</th>
			<td>
				<fmt:formatDate value="${monthlyBill.paidMoment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.pay"/>
			</th>
			<jstl:choose>
				<jstl:when test="${monthlyBill.paidMoment eq null }">
					<td>
					<input type="button" name="pay"
					value="<spring:message code="monthlyBill.payB" />"
					onclick="javascript: window.location.replace('managerActor/monthlyBill/pay.do?monthlyBillId=${monthlyBill.id}')" />
					</td>
				</jstl:when>
				<jstl:otherwise>
					<td>
					<spring:message code = "monthlyBill.apay"/>
					</td>
				</jstl:otherwise>
			</jstl:choose>
		</tr>
</table>
</security:authorize>
<security:authorize access="hasAnyRole('ADMIN')">
<table id="row" class="table">
	
	<tbody>
		<tr>
			<th>
				<spring:message code = "monthlyBill.cost"/>
			</th>
			<td>
				<jstl:out value="${monthlyBill.cost }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.moment"/>
			</th>
			<td>
				<fmt:formatDate value="${monthlyBill.moment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "monthlyBill.paidMoment"/>
			</th>
			<td>
				<fmt:formatDate value="${monthlyBill.paidMoment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
</table>
</security:authorize>