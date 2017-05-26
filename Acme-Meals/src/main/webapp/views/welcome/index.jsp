<%--
 * index.jsp
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

<jstl:if test="${banned == true }">
	<script>alert("<spring:message code="welcome.chorbi.banned"/>")</script>
	<script>window.location.href="j_spring_security_logout"</script>
</jstl:if>
<security:authorize
	access="isAnonymous()">
<table id="row" class="table" style="border:none">
	<tr>
	<td>
	<h2><spring:message code="welcome.restaurant" /></h2>
	<table id="row" class="table" style="width: 600px; height: 200px">
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
				<spring:message code = "restaurant.avgStars"/>
			</th>
			<td>
				<jstl:out value="${restaurant.avgStars }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.display"/>
			</th>
			<td>
				<input type="button" name="display"
						value="<spring:message code="restaurant.display" />"
						onclick="javascript: window.location.replace('restaurant/display.do?restaurantId=${restaurant.id}')"/><br/>
			</td>
		</tr>
		</table>
		</td>
		<td>
		<h2><spring:message code="welcome.review" /></h2>
		<table id="row" class="table" style="width: 600px; height: 200px">
		<tbody>
		<tr>
			<th width=100px>
				<spring:message code = "review.title"/>
			</th>
			<td width=100px>
				<jstl:out value="${review.title }" />
			</td>
		</tr>
		<tr>
			<th width=100px>
				<spring:message code = "review.rate"/>
			</th>
			<td width=100px>
				<jstl:out value="${review.rate }" />
			</td>
		</tr>
		<tr>
			<th width=100px>
				<spring:message code = "review.display"/>
			</th>
			<td width=100px>
				<input type="button" name="display"
						value="<spring:message code="review.display" />"
						onclick="javascript: window.location.replace('review/display.do?reviewId=${review.id}')"/><br/>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</security:authorize>

<security:authorize
	access="hasRole('USER')">
<table id="row" class="table" style="border:none">
	<tr>
	<td>
	<h2><spring:message code="welcome.restaurant" /></h2>
	<table id="row" class="table" style="width: 600px; height: 200px">
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
				<spring:message code = "restaurant.avgStars"/>
			</th>
			<td>
				<jstl:out value="${restaurant.avgStars }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "restaurant.display"/>
			</th>
			<td>
				<input type="button" name="display"
						value="<spring:message code="restaurant.display" />"
						onclick="javascript: window.location.replace('restaurant/display.do?restaurantId=${restaurant.id}')"/><br/>
			</td>
		</tr>
		</table>
		</td>
		<td>
		<h2><spring:message code="welcome.review" /></h2>
		<table id="row" class="table" style="width: 600px; height: 200px">
		<tbody>
		<tr>
			<th width=100px>
				<spring:message code = "review.title"/>
			</th>
			<td width=100px>
				<jstl:out value="${review.title }" />
			</td>
		</tr>
		<tr>
			<th width=100px>
				<spring:message code = "review.rate"/>
			</th>
			<td width=100px>
				<jstl:out value="${review.rate }" />
			</td>
		</tr>
		<tr>
			<th width=100px>
				<spring:message code = "review.display"/>
			</th>
			<td width=100px>
				<input type="button" name="display"
						value="<spring:message code="review.display" />"
						onclick="javascript: window.location.replace('review/display.do?reviewId=${review.id}')"/><br/>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</security:authorize>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 
