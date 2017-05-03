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

<display:table name="banners"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}" >
	
	<display:column>
		<a href="administrator/banner/edit.do?bannerId=${row.id}"><spring:message code="banner.edit" /></a>
	</display:column>
	
	<spring:message code="banner.url" var="urlHeader" />
	<display:column title="${urlHeader}">
		<img src="${row.url}" width="400" height="100">
	</display:column>
	
</display:table>

</security:authorize>
