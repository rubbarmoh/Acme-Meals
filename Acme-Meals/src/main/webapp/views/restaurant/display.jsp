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

<table id="row" class="table" style="width: 90%">

	<tbody>
		<tr>
			<td rowspan="10">
				<img src="${restaurant.picture}" width="200" height="200" >
			</td>
		</tr>
		<tr>
			<th><spring:message code = "restaurant.name"/></th>
			<th><spring:message code = "restaurant.manager"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${restaurant.name }" /></td>
			<td><jstl:out value="${restaurant.manager.name }" /></td>
		</tr>
		<tr>
			<th><spring:message code = "restaurant.city"/></th>
			<th><spring:message code = "restaurant.address"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${restaurant.city }" /></td>
			<td><jstl:out value="${restaurant.address }" /></td>
		</tr>
		<tr>
			<th><spring:message code = "restaurant.phone"/></th>
			<th><spring:message code = "restaurant.email"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${restaurant.phone }" /></td>
			<td><jstl:out value="${restaurant.email }" /></td>
		</tr>
		<tr>
			<th><spring:message code = "restaurant.deliveryService"/></th>
			<jstl:if test="${restaurant.deliveryService == true}">
				<th><spring:message code = "restaurant.costDelivery"/></th>
				<th><spring:message code = "restaurant.minimunAmount"/></th>
			</jstl:if>
		</tr>
		<tr>
			<td><jstl:out value="${restaurant.deliveryService }" /></td>
			<jstl:if test="${restaurant.deliveryService == true}">
				<td><spring:message code = "restaurant.costDelivery"/></td>
				<td><spring:message code = "restaurant.minimunAmount"/></td>
			</jstl:if>
		</tr>
		<tr>
			<th><spring:message code = "restaurant.avgStars"/></th>
			<td><jstl:out value="${restaurant.avgStars }" /></td>
		</tr>
</table>
<br/>

<table style="width: 100%; border:none">
	<tr><td width="50%">
	<h2><spring:message code="restaurant.meals" /></h2>
	<display:table name="meals"
		id="row"
		class="displaytag"
		pagesize="5"
		requestURI="${requestURI}" >
		
		<jstl:choose>
		<jstl:when test="${row.erased == false}">
		
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${row.restaurant.manager.userAccount.username == pageContext.request.remoteUser}">
				<display:column style="background-color:none">
					<a href="managerActor/meal/edit.do?mealId=${row.id}"><spring:message code="meal.edit" /></a>
				</display:column>
			</jstl:if>
		</security:authorize>
	
		<spring:message code="meal.category.name" var="categoryHeader" />
		<display:column property="category.name" title="${categoryHeader}" sortable="true" style="background-color:none"/>
		
		<spring:message code="meal.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" style="background-color:none"/>
		
		<spring:message code="meal.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" style="background-color:none"/>
		
		<spring:message code="meal.price" var="priceHeader" />
		<display:column property="price" title="${priceHeader}" style="background-color:none"/>
		
		<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.restaurant.manager.userAccount.username == pageContext.request.remoteUser}">
	
			<spring:message code="restaurant.erased" var="erasedHeader" />
			<display:column property="erased" title="${erasedHeader}" style="background-color:none" sortable="true"/>
			
				<display:column style="background-color:none">
				<jstl:choose>
					<jstl:when test="${row.erased == false}">
						<input type="button" name="delete"
								value="<spring:message code="restaurant.disable" />"
								onclick="javascript: window.location.replace('managerActor/meal/delete.do?mealId=${row.id}')"/>
					</jstl:when>
					<jstl:otherwise>
						<input type="button" name="delete"
								value="<spring:message code="restaurant.enabled" />"
								onclick="javascript: window.location.replace('managerActor/meal/delete.do?mealId=${row.id}')"/>
					</jstl:otherwise>
				</jstl:choose>
				</display:column>
			</jstl:if>
		</security:authorize>
	</jstl:when>
	
	<jstl:otherwise>
			<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${row.restaurant.manager.userAccount.username == pageContext.request.remoteUser}">
				<display:column  style="background-color:#C8C4C4">
					<a href="managerActor/meal/edit.do?mealId=${row.id}"><spring:message code="meal.edit" /></a>
				</display:column>
			</jstl:if>
		</security:authorize>
	
		<spring:message code="meal.category.name" var="categoryHeader" />
		<display:column property="category.name" title="${categoryHeader}" sortable="true" style="background-color:#C8C4C4"/>
		
		<spring:message code="meal.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" style="background-color:#C8C4C4"/>
		
		<spring:message code="meal.description" var="descriptionHeader" />
		<display:column property="description" title="${descriptionHeader}" style="background-color:#C8C4C4"/>
		
		<spring:message code="meal.price" var="priceHeader" />
		<display:column property="price" title="${priceHeader}" style="background-color:#C8C4C4"/>
		
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${row.restaurant.manager.userAccount.username == pageContext.request.remoteUser}">
			<spring:message code="restaurant.erased" var="erasedHeader" />
			<display:column property="erased" title="${erasedHeader}" style="background-color:#C8C4C4" sortable="true"/>
			
			
				<display:column style="background-color:#C8C4C4">
				<jstl:choose>
					<jstl:when test="${row.erased == false}">
						<input type="button" name="delete"
								value="<spring:message code="restaurant.disabled" />"
								onclick="javascript: window.location.replace('managerActor/meal/delete.do?mealId=${row.id}')"/>
					</jstl:when>
					<jstl:otherwise>
						<input type="button" name="delete"
								value="<spring:message code="restaurant.enabled" />"
								onclick="javascript: window.location.replace('managerActor/meal/delete.do?mealId=${row.id}')"/>
					</jstl:otherwise>
				</jstl:choose>
				</display:column>
			</jstl:if>
		</security:authorize>
	
	</jstl:otherwise>
	</jstl:choose>
	
	
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${restaurant.manager.userAccount.username == pageContext.request.remoteUser}">
			<input type="button" name="create"
							value="<spring:message code="meal.create" />"
							onclick="javascript: window.location.replace('managerActor/meal/create.do?restaurantId=${restaurant.id}')"/><br/>
		</jstl:if>
	</security:authorize>

	</td>
	
	<td width="50%">
	<h2><spring:message code="restaurant.comments" /></h2>
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
	
	
	</td>

</table>

<br/>
<security:authorize access="hasRole('USER')">
<input type="button" name="mealOrder"
					value="<spring:message code="restaurant.mealOrder" />"
					onclick="javascript: window.location.replace('user/mealOrder/morder.do?restaurantId=${restaurant.id}')"/> 
<br/>			
</security:authorize>				


<security:authorize access="hasAnyRole('CRITIC')">
	
	<jstl:set var="contains" value="false" />
			<jstl:forEach items="${restaurant.reviews}" var="review">
				<jstl:if test="${review.critic.userAccount.username==pageContext.request.remoteUser}">
						<jstl:set var="contains" value="true" />
						<br/>
						<p><spring:message code="restaurant.makeReviewDone" /></p>
			<br/>
				</jstl:if>
			</jstl:forEach>
			<br/>
			<jstl:if test="${contains==false && review.critic.userAccount.username!=pageContext.request.remoteUser}">
			<p><spring:message code="restaurant.makeReviewNotDone" /></p>
					<input type="button" name="makeReview"
			value="<spring:message code="restaurant.makeReview" />"
			onclick="javascript: window.location.replace('critic/review/create.do?restaurantId=${restaurant.id}')" />
			<br/>
								
			</jstl:if>
	
	</security:authorize>
					