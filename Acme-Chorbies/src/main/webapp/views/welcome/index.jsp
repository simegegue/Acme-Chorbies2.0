<%--
 * index.jsp
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

<jstl:if test="${banned == true }">
	<script>
		posicion_x=(screen.width/2)-250; 
	 	posicion_y=(screen.height/2)-50; 
	    resultado = window.open('', 'formpopup', 'width=500,height=100,resizeable,scrollbars,left='+posicion_x+",top="+posicion_y+"");
		resultado.document.write("<h2><spring:message code="welcome.chorbi.banned"/></h2>");
		window.location.href="j_spring_security_logout"
	</script>
</jstl:if>

<jstl:if test = "${not emptyBanner }" >
	<img src="${banner.url}" width="400" height="100">
</jstl:if>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 
