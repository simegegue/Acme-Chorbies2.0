<%--
 * display.jsp
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

<display:table name="searchTemplate"
	id="row"
	class="displaytag"
	pagesize="5">
	
	<security:authorize access="hasRole('CHORBI')">
	
	<display:column>
		<a href="chorbi/searchTemplate/edit.do?searchTemplateId=${row.id}"><spring:message code="searchTemplate.edit" /></a>
	</display:column>			
	
					
	<spring:message code="searchTemplate.age" var="ageHeader" />
	<display:column property="age" title="${ageHeader}" sortable="true"/>
	
	<spring:message code="searchTemplate.keyword" var="keywordHeader"/>
	<display:column property="keyword" title="${keywordHeader}" sortable="false"/>
	
	</security:authorize>

</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="chorbies" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->
	
	<spring:message code="searchTemplate.chorbi.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}" sortable="false" />

	<spring:message code="searchTemplate.chorbi.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />

	<spring:message code="searchTemplate.chorbi.birthDate" var="birthDateHeader" />
	<display:column title="${birthDateHeader}"	sortable="true"><fmt:formatDate value="${row.birthDate }" pattern="dd/MM/yyyy" /></display:column>
	
	<display:column>
		<a href="chorbi/display.do?chorbiId=${row.id}"><spring:message code="searchTemplate.chorbi.display" /></a>
	</display:column>
	
</display:table>