<%--
 * footer.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:useBean id="date" class="java.util.Date" />

<hr />

<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme-Chorbies Co., Inc.</b>

<p style="text-align:center"><a href="misc/lopd.do"><spring:message code="master.page.lopd" /></a></p>

<spring:message code='master.page.cookies' var="text" javaScriptEscape="true" />

<script type="text/javascript">
	function closeCookiesWarning(){
		localStorage.setItem("cw_Enabled", 'falso');
		window.location.reload();
	}
	
	var value=localStorage.getItem("cw_Enabled");
	
	if(value!='falso'){
		document.write("<div class='cookiePopUp'><div class='cookies_close'><a href='javascript:closeCookiesWarning()'><img src='images/cpu_Close.png' alt='Close' border='0' /></a></div><p class='pCookiesText'>${text}</p></div>");
	}
</script>