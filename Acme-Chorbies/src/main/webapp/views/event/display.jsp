<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<table id="row" class="table">
	
	<tbody>
		<tr>
			<td rowspan="10">
				<img src="${event.picture}" width="200" height="200" >
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "event.title"/>
			</th>
			<td>
				<jstl:out value="${event.title }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="event.moment" />
			</th>
			<td>
				<fmt:formatDate value="${event.moment }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="event.description" />
			</th>
			<td>
				<jstl:out value="${event.description}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="event.seatsOffered" />
			</th>
			<td>
				<jstl:out value="${event.seatsOffered}" />
			</td>
		</tr>
</table>
<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${creator}">
		<a href="chirp/broadcast.do?eventId=${event.id }"><spring:message code = "event.broadcast" /></a>
	</jstl:if>
</security:authorize>
<jstl:if test="${not past and full}">
	<security:authorize access="hasRole('CHORBI')">
		<jstl:choose>
			<jstl:when test="${ register==1}">
				<input type="button" name="unRegister"
						value="<spring:message code="event.unRegister" />"
						onclick="javascript: window.location.replace('event/unRegister.do?eventId=${event.id }')" 
						style="float: left;padding: 5px 15px; margin: 0 3px 0 3px;" /><br/>
			</jstl:when>
			<jstl:otherwise>
				<input type="button" name="register"
						value="<spring:message code="event.register" />"
						onclick="javascript: window.location.replace('event/register.do?eventId=${event.id }')" 
						style="float: left;padding: 5px 15px; margin: 0 3px 0 3px;" /><br/>
			</jstl:otherwise>
		</jstl:choose>
		<br>
	</security:authorize>
</jstl:if>
