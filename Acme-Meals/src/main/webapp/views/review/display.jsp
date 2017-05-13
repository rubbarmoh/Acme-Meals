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
				<spring:message code = "review.title"/>
			</th>
			<td>
				<jstl:out value="${review.title }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "review.text"/>
			</th>
			<td>
				<jstl:out value="${review.text }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "review.rate"/>
			</th>
			<td>
				<jstl:out value="${review.rate }" />
			</td>
		</tr>
		
		<tr>
			<td>
				<a href="critic/display.do?criticId=${row.id}"><spring:message code="review.critic.display" /></a>
			</td>
		</tr>
</table>