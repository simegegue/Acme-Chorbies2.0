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

<!-- C2 -->
<p>Dashboard 2.0</p>
<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.managersByEvents" /></legend>
		<table id="managersByEvents" class="table">
			<tr>
				<jstl:if test="${not empty managersByEvents }">
					<jstl:forEach var="X" items="${managersByEvents}">
					<tr>
						<td>	<jstl:out value="${X.name} (${X.userAccount.username}) = ${X.events.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.managersFee" /></legend>
		<table id="mapManager" class="table">
			<tr>
				<jstl:if test="${not empty mapManager }">
					<jstl:forEach var="X" items="${mapManager}">
					<tr>
						<td>	<jstl:out value="${X.name} (${X.userAccount.username}) = ${mapM.get(X)} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.chorbiesByEvents" /></legend>
		<table id="chorbiesByEvents" class="table">
			<tr>
				<jstl:if test="${not empty chorbiesByEvents }">
					<jstl:forEach var="X" items="${chorbiesByEvents}">
					<tr>
						<td>	<jstl:out value="${X.name} (${X.userAccount.username}) = ${X.relationEvents.size()} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mapChorbies" /></legend>
		<table id="mapChorbies" class="table">
			<tr>
				<jstl:if test="${not empty mapChorbies }">
					<jstl:forEach var="X" items="${mapChorbies}">
					<tr>
						<td>	<jstl:out value="${X.name} (${X.userAccount.username}) = ${mapC.get(X)} "/><td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<!-- C -->
<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.numberOfChorbiesByCountry" /></legend>
		<table id="numberOfChorbiesByCountry" class="table">
			<tr>
				<td><jstl:if test="${not empty auxCountry }">
					<jstl:forEach var="Y" items="${auxCountry}">
						<td>	<jstl:out value="${Y}"/> <td>
					</jstl:forEach>	
				</jstl:if></td>
			</tr>
			<tr>
				<td><jstl:if test="${not empty numberOfChorbiesByCountry }">
					<jstl:forEach var="X" items="${numberOfChorbiesByCountry}">
						<td>	<jstl:out value="${X}"/><td>
					</jstl:forEach>	
				</jstl:if></td>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.numberOfChorbiesByCountry" /></legend>
		<table id="numberOfChorbiesByCountry" class="table">
			<tr>
				<td><jstl:if test="${not empty auxCity }">
					<jstl:forEach var="Y" items="${auxCity}">
						<td>	<jstl:out value="${Y}"/> <td>
					</jstl:forEach>	
				</jstl:if></td>
			</tr>
			<tr>
				<td><jstl:if test="${not empty numberOfChorbiesByCity }">
					<jstl:forEach var="X" items="${numberOfChorbiesByCity}">
						<td>	<jstl:out value="${X}"/><td>
					</jstl:forEach>	
				</jstl:if></td>
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

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.listChorbiesbyLikes" /></legend>
		<table id="listChorbiesbyLikes" class="table">
			<tr>
				<jstl:if test="${not empty listChorbiesbyLikes }">
					<jstl:forEach var="X" items="${listChorbiesbyLikes}">
					<tr>
						<td><jstl:out value="${X.name} (${X.userAccount.username}) (${X.likesReceived.size()})"/></td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgReceivedLikeChorbi" /></legend>
		<table id="minMaxAvgReceivedLikeChorbi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedLikeChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedLikeChorbi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedLikeChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedLikeChorbi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedLikeChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedLikeChorbi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<!-- A -->

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgReceivedChirpChorbi" /></legend>
		<table id="minMaxAvgReceivedChirpChorbi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedChirpChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedChirpChorbi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedChirpChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedChirpChorbi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgReceivedChirpChorbi }">
					<td><jstl:out value="${minMaxAvgReceivedChirpChorbi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.minMaxAvgSentChirpChorbi" /></legend>
		<table id="minMaxAvgSentChirpChorbi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty minMaxAvgSentChirpChorbi }">
					<td><jstl:out value="${minMaxAvgSentChirpChorbi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty minMaxAvgSentChirpChorbi }">
					<td><jstl:out value="${minMaxAvgSentChirpChorbi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty minMaxAvgSentChirpChorbi }">
					<td><jstl:out value="${minMaxAvgSentChirpChorbi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.moreChirpReceivedChorbies" /></legend>
		<table id="moreChirpReceivedChorbies" class="table">
			<tr>
				<jstl:if test="${not empty moreChirpReceivedChorbies }">
					<jstl:forEach var="X" items="${moreChirpReceivedChorbies}">
					<tr>
						<td><jstl:out value="${X.name} (${X.userAccount.username}) (${X.received.size()})"/></td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.moreChirpSentChorbies" /></legend>
		<table id="moreChirpSentChorbies" class="table">
			<tr>
				<jstl:if test="${not empty moreChirpSentChorbies }">
					<jstl:forEach var="X" items="${moreChirpSentChorbies}">
					<tr>
						<td><jstl:out value="${X.name} (${X.userAccount.username}) (${X.sent.size()})"/></td>
					</tr>
					</jstl:forEach>	
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

</security:authorize>
