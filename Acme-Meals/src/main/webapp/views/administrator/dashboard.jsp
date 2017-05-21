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

<security:authorize access="hasRole('ADMIN')">

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

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.restaurantMoreOrders" /></legend>
		<table id="restaurantMoreOrders" class="table">
			<tr>
				<jstl:if test="${not empty restaurantMoreOrders }">
					<jstl:forEach var="X" items="${restaurantMoreOrders}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.mealOrders.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.restaurantLessOrders" /></legend>
		<table id="restaurantLessOrders" class="table">
			<tr>
				<jstl:if test="${not empty restaurantLessOrders }">
					<jstl:forEach var="X" items="${restaurantLessOrders}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.mealOrders.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.ratioRestaurantWithSocialIdentity" /></legend>
		<table id="ratioRestaurantWithSocialIdentity" class="table">
			<tr>
				<jstl:if test="${not empty ratioRestaurantWithSocialIdentity }">
					
						<td>	<jstl:out value="${ratioRestaurantWithSocialIdentity} "/><td>
					
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgReviewsPerCritic" /></legend>
		<table id="minMaxAvgReviewsPerCritic" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgReviewsPerCritic }">
					<td><jstl:out value="${minMaxAvgReviewsPerCritic.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgReviewsPerCritic }">
					<td><jstl:out value="${minMaxAvgReviewsPerCritic.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgReviewsPerCritic }">
					<td><jstl:out value="${minMaxAvgReviewsPerCritic.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.restaurantWithMoreReviews" /></legend>
		<table id="restaurantWithMoreReviews" class="table">
			<tr>
				<jstl:if test="${not empty restaurantWithMoreReviews }">
					<jstl:forEach var="X" items="${restaurantWithMoreReviews}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.reviews.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.restaurantWithLessReviews" /></legend>
		<table id="restaurantWithLessReviews" class="table">
			<tr>
				<jstl:if test="${not empty restaurantWithLessReviews }">
					<jstl:forEach var="X" items="${restaurantWithLessReviews}">
					<tr>
						<td>	<jstl:out value="${X.name} = ${X.reviews.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.reviewMoreLikes" /></legend>
		<table id="reviewMoreLikes" class="table">
			<tr>
				<jstl:if test="${not empty reviewMoreLikes }">
					<jstl:forEach var="X" items="${reviewMoreLikes}">
					<tr>
						<td>	<jstl:out value="${X.title} = ${X.relationLikes.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgMonthlyBillsPerManager" /></legend>
		<table id="minMaxAvgMonthlyBillsPerManager" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgMonthlyBillsPerManager }">
					<td><jstl:out value="${minMaxAvgMonthlyBillsPerManager.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgMonthlyBillsPerManager }">
					<td><jstl:out value="${minMaxAvgMonthlyBillsPerManager.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgMonthlyBillsPerManager }">
					<td><jstl:out value="${minMaxAvgMonthlyBillsPerManager.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

</security:authorize>