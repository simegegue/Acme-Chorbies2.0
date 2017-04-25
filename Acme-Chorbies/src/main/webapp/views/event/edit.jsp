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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('MANAGER')">

	<form:form action="manager/event/edit.do"	modelAttribute="event">
	
		<form:hidden path="id"/>
	
		<acme:textbox code="event.title" path="title" />
		<br/>
		<acme:textarea code="event.description" path="description" />
		<br/>
		<acme:textbox code="event.picture" path="picture" />
		<br/>
		<acme:textbox code="event.moment" path="moment" />
		<br/>
		<acme:textbox code="event.seatsOffered" path="seatsOffered" />
		<br/>
		<acme:submit name="save" code="event.save"/>
		<jstl:if test="${event.id != 0}">
			<input type="submit" name="delete" value="<spring:message code="event.delete" />" onclick="return confirm('<spring:message code="event.confirm.delete" />')" />
		</jstl:if>
		<acme:cancel code="event.cancel" url="manager/event/list.do" />
	
	</form:form>

</security:authorize>