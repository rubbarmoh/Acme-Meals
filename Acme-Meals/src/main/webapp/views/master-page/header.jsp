<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.manageValue" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/fee/edit.do"><spring:message code="master.page.administrator.fee" /></a></li>
					<li><a href="administrator/vatNumber/edit.do"><spring:message code="master.page.administrator.vatNumber" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message	code="master.page.manager.restaurant" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="managerActor/restaurant/create.do"><spring:message code="master.page.manager.restaurant.create" /></a></li>
					<li><a href="managerActor/restaurant/list.do"><spring:message code="master.page.manager.restaurant.list" /></a></li>					
					<li><a href="mealOrder/browseByManager.do"><spring:message code="master.page.manager.mealOrder.browseByManager" /></a></li>					
					<li><a href="mealOrder/browseCurrentlyByManager.do"><spring:message code="master.page.manager.mealOrder.browseCurrentlyByManager" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="managerActor/monthlyBill/list.do"><spring:message code="master.page.monthlyBill.list" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.manager.categories" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="managerActor/category/create.do"><spring:message code="master.page.manager.category.create" /></a></li>
					<li><a href="managerActor/category/list.do"><spring:message code="master.page.manager.category.list" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="managerActor/register.do"><spring:message code="master.page.register.manager" /></a></li>
					<li><a href="user/register.do"><spring:message code="master.page.register.user" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv" href="review/browse.do"><spring:message code="master.page.review.browse" /></a></li>
			<li><a class="fNiv" href="restaurant/browse.do"><spring:message code="master.page.restaurant.browse" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv" href="review/browse.do"><spring:message code="master.page.review.browse" /></a></li>
			<li><a class="fNiv" href="restaurant/browse.do"><spring:message code="master.page.restaurant.browse" /></a></li>
			
			<security:authorize access="hasRole('USER')">
				<li><a class="fNiv" href="mealOrder/browseByUser.do"><spring:message code="master.page.user.mealOrder" /></a></li>
				<li><a class="fNiv" href="invoice/list.do"><spring:message code="master.page.user.invoice" /></a></li>
			</security:authorize>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<security:authorize access="hasRole('MANAGER')">
				<ul>
					<li class="arrow"></li>
					<li><a href="managerActor/edit.do"><spring:message code="master.page.edit.manager" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('USER')">
				<ul>
					<li class="arrow"></li>
					<li><a href="user/edit.do"><spring:message code="master.page.edit.user" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('CRITIC')">
				<ul>
					<li class="arrow"></li>		
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
				<ul>
					<li class="arrow"></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
				</security:authorize>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

