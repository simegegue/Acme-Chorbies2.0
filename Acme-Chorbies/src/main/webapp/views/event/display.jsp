<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

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