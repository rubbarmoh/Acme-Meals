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

<table id="row" class="table">
	
	<tbody>
		<tr>
			<th>
				<spring:message code = "invoice.name"/>
			</th>
			<td>
				<jstl:out value="${invoice.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "invoice.surname"/>
			</th>
			<td>
				<jstl:out value="${invoice.surname }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "invoice.vatNumber"/>
			</th>
			<td>
				<jstl:out value="${invoice.vatNumber }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "invoice.description"/>
			</th>
			<td>
				<jstl:out value="${invoice.description }" />
			</td>
			</tr>
		<tr>
			<th>
				<spring:message code = "invoice.moment"/>
			</th>
			<td>
				<fmt:formatDate value="${invoice.moment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
</table>