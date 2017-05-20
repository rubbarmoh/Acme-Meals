<%--
 * dashboard.jsp
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

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAVGOrdersPerUser" /></legend>
		<table id="minMaxAVGOrdersPerUser" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAVGOrdersPerUser }">
					<td><jstl:out value="${minMaxAVGOrdersPerUser.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAVGOrdersPerUser }">
					<td><jstl:out value="${minMaxAVGOrdersPerUser.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAVGOrdersPerUser }">
					<td><jstl:out value="${minMaxAVGOrdersPerUser.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>