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


<security:authorize access="hasRole('CHORBI')">

<jstl:choose>
	<jstl:when test="${validatorCreditCard == true }">

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
			
			<display:column>
				<a href="chorbi/displayById.do?chorbiId=${row.id}"><spring:message code="chorbi.view.profile" /></a>
			</display:column>
			
		
		</display:table>
		
	</jstl:when>
	<jstl:otherwise>
		<h2><spring:message code="chorbi.creditCard.invalid"/></h2>
	</jstl:otherwise>
</jstl:choose>

</security:authorize>
