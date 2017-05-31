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

<display:table name="users"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<spring:message code="user.name" var="nameHeader" />
	<display:column title="${nameHeader}">
		<a href="user/displayById.do?userId=${row.id}"><jstl:out value="${row.name}"/></a>
	</display:column>
	
	<spring:message code="user.numReports" var="numReportHeader" />
	<display:column title="${numReportHeader}">
		<jstl:out value="${num[row]}"/>
	</display:column>
	
	<spring:message code="user.reports" var="reportHeader" />
	<display:column title="${reportHeader}">
		<a href="administrator/banUnban/commentReported.do?userId=${row.id}"><spring:message code="user.reports"/></a>
	</display:column>
	

	<spring:message code="user.banned" var="bannedHeader" />
	<display:column title="${bannedHeader}">
		<jstl:if test="${row.banned==true}">
			<spring:message code="user.banned.yes" var="yesH" />
			<jstl:out value="${yesH}"/>
		</jstl:if>
		<jstl:if test="${row.banned==false}">
			<spring:message code="user.banned.no" var="noH" />
			<jstl:out value="${noH}"/>
		</jstl:if>
	</display:column>
	
	
	<display:column>
		<jstl:choose>
				<jstl:when test="${row.banned}">
						<input type="button" name="banUnban"
						value="<spring:message code="user.unban" />"
						onclick="javascript: window.location.replace('administrator/banUnban/banUnban.do?userId=${row.id}')"/>
				</jstl:when>
				<jstl:otherwise>
					<input type="button" name="banUnban"
						value="<spring:message code="user.ban" />"
						onclick="javascript: window.location.replace('administrator/banUnban/banUnban.do?userId=${row.id}')"/>
				</jstl:otherwise>
		</jstl:choose>
	</display:column>
</display:table>

</security:authorize>
