<%--
 * edit.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize
	access="hasRole('CHORBI')">

	<form:form	action="chorbi/searchTemplate/edit.do"	modelAttribute="searchTemplate"> 

			<acme:textbox code="searchTemplate.age" path="age"/>
			<acme:textbox code="searchTemplate.keyword" path="keyword"/>
			
			<fieldset>
					<legend align="left"><spring:message code="searchTemplate.coordinate.info"/></legend>
					<acme:textbox code="searchTemplate.coordinate.country" path="coordinate.country"/>
					<br/>			
					<acme:textbox code="searchTemplate.coordinate.state" path="coordinate.state"/>
					<br/>		
					<acme:textbox code="searchTemplate.coordinate.province" path="coordinate.province"/>
					<br/>			
					<acme:textbox code="searchTemplate.coordinate.city" path="coordinate.city"/>
			</fieldset>
			
			<acme:textbox code="searchTemplate.genre.value" path="genre.value"/>
			<acme:textbox code="searchTemplate.kindRelationship.value" path="kindRelationship.value"/>
			
			<acme:submit name="save" code="searchTemplate.save"/>
			<acme:cancel code="searchTemplate.cancel" url="chorbi/searchTemplate/display.do"/>
		
	</form:form>

</security:authorize>
