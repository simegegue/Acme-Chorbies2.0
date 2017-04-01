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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize
	access="hasRole('ADMIN')">

	<form:form	action="genre/edit.do"	modelAttribute="genreForm"> 
	
		<form:hidden path="id"/>
		<acme:textbox code="genre.value" path="value"/>
		
		
		<acme:submit name="save" code="genre.save"/>
		<jstl:if test="${genreForm.id != 0}">
			<input type="submit" name="delete" value="<spring:message code="genre.delete" />" onclick="return confirm('<spring:message code="genre.confirm.delete" />')" />
		</jstl:if>
		<acme:cancel code="genre.cancel" url="genre/list.do"/>
		
	</form:form>

</security:authorize>