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
<br/>
<input type="button" name="meals"
					value="<spring:message code="restaurant.meals" />"
					onclick="javascript: window.location.replace('meal/browse.do?restaurantId=${restaurant.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
					
<display:table pagesize="5" class="displaytag" keepStatus="true" name="comments" requestURI="${requestURI}" id="row">

	<spring:message code="restaurant.comment.user" var="author"/>
	<display:column title="${author }" property="user.name"/>
	
	<spring:message code="restaurant.comment.title" var="title"/>
	<display:column title="${title }" property="title"/>
	
	<spring:message code="restaurant.comment.text" var="text"/>
	<display:column title="${text }" property="text"/>
	
	<spring:message code="restaurant.comment.stars" var="stars"/>
	<display:column title="${stars }" property="stars"/>
	
	<spring:message code="restaurant.comment.moment" var="moment"/>
	<display:column title="${moment}" sortable="true"><fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<display:column>
		<a href="user/displayById.do?userId=${row.user.id}"><spring:message code="restaurant.user.display" /></a>
	</display:column>
	<security:authorize access="hasAnyRole('USER','MANAGER')">
	<display:column>
	<jstl:set var="contains" value="false" />
			<jstl:forEach items="${row.reports}" var="report">
				<jstl:if test="${report.reporter.userAccount.username==pageContext.request.remoteUser}">
						<jstl:set var="contains" value="true" />
				</jstl:if>
			</jstl:forEach>
			<jstl:if test="${contains==false && row.user.userAccount.username!=pageContext.request.remoteUser}">
					<a href="report/edit.do?commentId=${row.id}"><spring:message code="restaurant.comment.report" /></a>
								
			</jstl:if>
	</display:column>
	</security:authorize>
	
<br/>
	
</display:table>
<security:authorize access="hasRole('USER')">
	<input type="button" name="addComment"
			value="<spring:message code="restaurant.addComment" />"
			onclick="javascript: window.location.replace('user/comment/create.do?restaurantId=${restaurant.id}')" />
<br/>
</security:authorize>
<security:authorize access="hasRole('CRITIC')">
	<input type="button" name="makeReview"
			value="<spring:message code="restaurant.makeReview" />"
			onclick="javascript: window.location.replace('critic/review/create.do?restaurantId=${restaurant.id}')" />
<br/>
</security:authorize>
					