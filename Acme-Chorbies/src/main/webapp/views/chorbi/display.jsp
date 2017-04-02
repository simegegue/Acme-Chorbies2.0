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
				<img src="${chorbi.picture}" width="200" height="200" >
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code = "chorbi.name"/>
			</th>
			<td>
				<jstl:out value="${chorbi.name }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.surname" />
			</th>
			<td>
				<jstl:out value="${chorbi.surname }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.email" />
			</th>
			<td>
				<jstl:out value="${chorbi.email }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.phone" />
			</th>
			<td>
				<jstl:out value="${chorbi.phone}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.description" />
			</th>
			<td>
				<jstl:out value="${chorbi.description}" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.birthDate" />
			</th>
			<td>
				<fmt:formatDate value="${chorbi.birthDate }" pattern="dd/MM/yyyy HH:mm" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="chorbi.genre" />
			</th>
			<td>
				<jstl:out value="${chorbi.genre.value}" />
			</td>
		</tr>
		<tr bgcolor="lightgrey">
			<th>
				<spring:message code = "chorbi.likesReceived"/>
			</th>
			<td>
				<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
				<jstl:out value="${fn:length(chorbi.likesReceived)}"/>
			</td>
		</tr>
	</tbody>

</table>

<jstl:if test="${chorbi.userAccount.username != pageContext.request.remoteUser}">
	<security:authorize access="hasRole('CHORBI')">
	<div  style="width:980px;height:40px">	  
		<jstl:set var="containsD" value="false" />
		<jstl:forEach var="item" items="${chorbi.likesReceived}">
	  		<jstl:if test="${item.likeSender.userAccount.username eq pageContext.request.remoteUser}">
	    		<jstl:set var="containsD" value="true" />
	  		</jstl:if>
		</jstl:forEach>
	
		<jstl:choose>
			<jstl:when test="${containsL == true }">
				<input type="button" name="removeLike"
					value="<spring:message code="chorbi.unlike" />"
					onclick="javascript: window.location.replace('chorbi/like/delete.do?chorbiId=${chorbi.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			</jstl:when>
			<jstl:otherwise>
				<input type="button" name="adddisLike"
					value="<spring:message code="chorbi.like" />"
					onclick="javascript: window.location.replace('chorbi/like/create.do?chorbiId=${chorbi.id}')" 
					style="float: right;padding: 5px 15px; margin: 0 3px 0 3px;" />
			
			</jstl:otherwise>
		</jstl:choose>
		
		
	</div>
	</security:authorize>
</jstl:if>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="likesReceived" requestURI="${requestURI}" id="row">

	<spring:message code="chorbi.like.name" var="likeSender"/>
	<display:column title="${likeSender }" property="likeSender.name"/>
	
	<spring:message code="chorbi.like.moment" var="moment"/>
	<display:column title="${moment}" sortable="true"><fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy HH:mm" /></display:column>
		
	<spring:message code="chorbi.comment" var="comment"/>
	<display:column title="${comment }" property="comment"/>

	
	
<br/>
	
</display:table>

