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


<security:authorize access="hasRole('ADMIN')">
<table>
		<tr>
			<th><spring:message code="comment.comment"/></th>
			<th><spring:message code="comment.report"/></th>
		</tr>
		
	<jstl:forEach items="${comments}" var="comment">
	
		<tr>
			<td>
				<jstl:out value="${comment.text}"/>
			</td>
				<jstl:forEach items="${mapa[comment]}" var="report">
							<td>
								<jstl:out value="${report.text}"/>
							</td>
							<td>
							<input type="button" name="delete"
								value="<spring:message code="comment.report.delete" />"
								onclick="javascript: window.location.replace('administrator/banUnban/deleteReport.do?reportId=${report.id}')"/>
							</td>
							<tr><td>
				</jstl:forEach>
		</tr>
	
	</jstl:forEach>
</table>


</security:authorize>
