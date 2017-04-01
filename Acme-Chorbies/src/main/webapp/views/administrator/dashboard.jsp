<%--
 * dashboard.jsp
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

<security:authorize access="hasRole('ADMIN')">
<!-- C -->
<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.numberOfChorbiesByCountry" /></legend>
		<table id="numberOfChorbiesByCountry" class="table">
			<tr>
				<jstl:if test="${not empty numberOfChorbiesByCountry }">
					<td><jstl:out value="${numberOfChorbiesByCountry}" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.numberOfChorbiesByCity" /></legend>
		<table id="numberOfChorbiesByCity" class="table">
			<tr>
				<jstl:if test="${not empty numberOfChorbiesByCity }">
					<td><jstl:out value="${numberOfChorbiesByCity}" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgChorbiYear" /></legend>
		<table id="minMaxAvgChorbiYear" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgChorbiYear }">
					<td><jstl:out value="${minMaxAvgChorbiYear.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgChorbiYear }">
					<td><jstl:out value="${minMaxAvgChorbiYear.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgChorbiYear }">
					<td><jstl:out value="${minMaxAvgChorbiYear.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.ratioChorbiesNullCreditCard" /></legend>
		<table id="ratioChorbiesNullCreditCard" class="table">
			<tr>
				<jstl:if test="${not empty ratioChorbiesNullCreditCard }">
					<td><jstl:out value="${ratioChorbiesNullCreditCard}" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.actFriLoveRatioRelationChorbi" /></legend>
		<table id="actFriLoveRatioRelationChorbi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.activities"/></th>
				<jstl:if test="${not empty actFriLoveRatioRelationChorbi }">
					<td><jstl:out value="${actFriLoveRatioRelationChorbi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.friendship"/></th>
				<jstl:if test="${not empty actFriLoveRatioRelationChorbi }">
					<td><jstl:out value="${actFriLoveRatioRelationChorbi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.love"/></th>
				<jstl:if test="${not empty actFriLoveRatioRelationChorbi }">
					<td><jstl:out value="${actFriLoveRatioRelationChorbi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<!-- B -->



<!-- A -->



</security:authorize>
