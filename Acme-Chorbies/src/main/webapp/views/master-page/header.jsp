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
	<img src="images/logo.png" alt="Acme-Chorbies Co., Inc." />
</div>

<div>
	<ul id="jMenu">
	
		<li><a class = "fNiv"><spring:message code="master.page.event"/></a>
			<ul>
				<li class="arrow"></li>
				<li><a href="event/browse.do"><spring:message code="master.page.event.browse" /></a></li>
				<li><a href="event/browseAvailable.do"><spring:message code="master.page.event.browseAvailable" /></a></li>
			</ul>
		</li>
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href="administrator/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.banner" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/banner/list.do"><spring:message code="master.page.banner.list" /></a></li>
					<li><a href="administrator/banner/create.do"><spring:message code="master.page.banner.create" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="admin/cacheTime/edit.do"><spring:message code="master.page.cacheTime" /></a></li>
			<li><a class="fNiv" href="administrator/fee/edit.do"><spring:message code="master.page.fee" /></a></li>
			<li><a class="fNiv" href="administrator/fee/updateChorbiFee.do"><spring:message code="master.page.fee.update" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.genre" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/genre/list.do"><spring:message code="master.page.genre.list" /></a></li>
					<li><a href="administrator/genre/create.do"><spring:message code="master.page.genre.create" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.administrator.kindRelationship" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/kindRelationship/list.do"><spring:message code="master.page.kindRelationship.list" /></a></li>
					<li><a href="administrator/kindRelationship/create.do"><spring:message code="master.page.kindRelationship.create" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CHORBI')">
			<li><a class="fNiv" href="event/browseRegistered.do"><spring:message code="master.page.event.browseRegistered" /></a></li>
			<li><a class="fNiv" href="chorbi/browseLike.do"><spring:message code="master.page.chorbi.browseLike" /></a></li>
			<li><a class="fNiv" href="chorbi/searchTemplate/display.do"><spring:message code="master.page.searchTemplate" /></a></li>
			<li><a class = "fNiv"><spring:message code="master.page.chirp"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chirp/write.do" ><spring:message code="master.page.chirp.write" /></a></li>
					<li><a href="chirp/sent.do" ><spring:message code="master.page.chirp.sent" /></a></li>
					<li><a href="chirp/received.do" ><spring:message code="master.page.chirp.received" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv" href="managerActor/event/list.do"><spring:message code="master.page.manager.list" /></a></li>
			<li><a class="fNiv" href="managerActor/event/create.do"><spring:message code="master.page.manager.create" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class = "fNiv"><spring:message code="master.page.register"/></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chorbi/register.do"><spring:message code="master.page.register.chorbi" /></a></li>
					<li><a href="managerActor/register.do"><spring:message code="master.page.register.manager" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<security:authorize access="hasRole('CHORBI')||hasRole('ADMIN')">
		<li><a class="fNiv" href="chorbi/browse.do"><spring:message code="master.page.chorbi.browse" /></a></li>
			</security:authorize>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<security:authorize access="hasRole('CHORBI')">
				<ul>
					<li class="arrow"></li>		
					<li><a href="chorbi/display.do"><spring:message code="master.page.display" /> </a></li>	
					<li><a href="chorbi/edit.do"><spring:message code="master.page.edit.profile" /> </a></li>	
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
				</security:authorize>
				<security:authorize access="hasRole('MANAGER')">
				<ul>
					<li class="arrow"></li>			
					<li><a href="managerActor/edit.do"><spring:message code="master.page.edit.profile" /> </a></li>	
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

