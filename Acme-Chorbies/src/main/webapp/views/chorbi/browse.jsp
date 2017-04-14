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


<security:authorize access="isAuthenticated()">

<display:table name="chorbies"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<spring:message code="chorbi.picture" var="pictureHeader" />
	<display:column title="${pictureHeader}">
		<img src="${row.picture}" width="100" height="100">
	</display:column>
	<spring:message code="chorbi.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}"/>
	
	<spring:message code="chorbi.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}"/>
	
	<spring:message code="chorbi.birthDate" var="birthDate" />
	<display:column title="${birthDate}"	sortable="false"><fmt:formatDate value="${row.birthDate }" pattern="dd/MM/yyyy" /></display:column>
	
	<spring:message code="chorbi.genre" var="genre" />
	<display:column property="genre.value" title="${genre}"/>
	
	<spring:message code="chorbi.kindRelationship" var="kindRelationship" />
	<display:column property="kindRelationship.value" title="${kindRelationship}"/>
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="chorbi.banned" var="bannedHeader" />
		<display:column title="${bannedHeader}">
			<jstl:if test="${row.banned==true}">
				<spring:message code="chorbi.banned.yes" var="yesH" />
				<jstl:out value="${yesH}"/>
			</jstl:if>
			<jstl:if test="${row.banned==false}">
				<spring:message code="chorbi.banned.no" var="noH" />
				<jstl:out value="${noH}"/>
			</jstl:if>
		</display:column>
	
	<spring:message code="chorbi.banned" var="bannedHeader" />
	<display:column title="${bannedHeader}">
		<jstl:choose>
			<jstl:when test="${row.banned}">
				<a href="chorbi/banUnban.do?chorbiId=${row.id}">
					<spring:message code="chorbi.unban" />
				</a>
			</jstl:when>
			<jstl:otherwise>
				<a href="chorbi/banUnban.do?chorbiId=${row.id}">
					<spring:message code="chorbi.ban" />
				</a>
			</jstl:otherwise>
		</jstl:choose>
	</display:column>
	
	</security:authorize>
	
	<display:column>
		<a href="chorbi/displayById.do?chorbiId=${row.id}"><spring:message code="chorbi.view.profile" /></a>
	</display:column>
	
	<display:column>
		<a href="chorbi/chorbieswholikethem.do?chorbiId=${row.id}"><spring:message code="chorbi.view.likethem" /></a>
	</display:column>
	

</display:table>

</security:authorize>
