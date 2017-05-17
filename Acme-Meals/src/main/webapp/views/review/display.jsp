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
			<th>
				<spring:message code = "review.likes"/>
			</th>
			<td>
				<jstl:out value="${like }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "review.dislikes"/>
			</th>
			<td>
				<jstl:out value="${dislike }" />
			</td>
		</tr>
		
		<tr>
			<td>
				<a href="critic/display.do?criticId=${review.critic.id}"><spring:message code="review.critic.display" /></a>
			</td>
		</tr>
		
		<tr>
			<td>
				<a href="restaurant/display.do?restaurantId=${review.restaurant.id}"><spring:message code="review.restaurant.display" /></a>
			</td>
		</tr>
			
</table>

<security:authorize access="hasAnyRole('USER')">
	<div  style="width:980px;height:40px">	
	
		<jstl:set var="containsD" value="false" />
		<jstl:forEach var="item" items="${review.relationDislikes}">
	  		<jstl:if test="${item.user.userAccount.username eq pageContext.request.remoteUser}">
	    		<jstl:set var="containsD" value="true" />
	  		</jstl:if>
		</jstl:forEach>
	
	
		<jstl:choose>
			<jstl:when test="${containsD == true }">
				<input type="button" name="unDislike"
					value="<spring:message code="review.undislike" />"
					onclick="javascript: window.location.replace('user/review/dislike/delete.do?reviewId=${review.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			</jstl:when>
			<jstl:otherwise>
				<input type="button" name="dislike"
					value="<spring:message code="review.dislike" />"
					onclick="javascript: window.location.replace('user/review/dislike/create.do?reviewId=${review.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			
			</jstl:otherwise>
		</jstl:choose>
		
		<jstl:set var="containsL" value="false" />
		<jstl:forEach var="itemL" items="${review.relationLikes}">
	  		<jstl:if test="${itemL.user.userAccount.username eq pageContext.request.remoteUser}">
	    		<jstl:set var="containsL" value="true" />
	  		</jstl:if>
		</jstl:forEach>
		<jstl:choose>
		
			<jstl:when test="${containsL == true }">
				<input type="button" name="unLike"
					value="<spring:message code="review.unlike" />"
					onclick="javascript: window.location.replace('user/review/like/delete.do?reviewId=${review.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			</jstl:when>
			<jstl:otherwise>
				<input type="button" name="like"
					value="<spring:message code="review.like" />"
					onclick="javascript: window.location.replace('user/review/like/create.do?reviewId=${review.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			
			</jstl:otherwise>
		</jstl:choose>
		</div>
</security:authorize>