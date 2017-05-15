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
			<td rowspan="10">
				<img src="${restaurant.picture}" width="200" height="200" >
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.name"/>
			</th>
			<td>
				<jstl:out value="${restaurant.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.city"/>
			</th>
			<td>
				<jstl:out value="${restaurant.city }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.address"/>
			</th>
			<td>
				<jstl:out value="${restaurant.address }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.phone"/>
			</th>
			<td>
				<jstl:out value="${restaurant.phone }" />
			</td>
		</tr>
</table>