<%--
 * register.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="chorbiForm">
	<jstl:if test="${chorbiForm.id==0 || chorbiForm.username == pageContext.request.remoteUser}">
		<form:hidden path="id"/>
	
		<jstl:choose>
			<jstl:when test="${tipe eq 'edit' }">
				<form:hidden path="username"/>
				<form:hidden path="password"/>
				<form:hidden path="password2"/>
				<form:hidden path="agreed"/>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.personal.info"/></legend>
					<br/>
					<acme:textbox code="chorbi.name" path="name" />
					<br/>
					<acme:textbox code="chorbi.surname" path="surname"/>
					<br/>
					<acme:textbox code="chorbi.email" path="email"/>
					<br/>
					<acme:textbox code="chorbi.phone" path="phone"/>
					<br/>
					<acme:textbox code="chorbi.picture" path="picture"/>
					<br/>
					<acme:textarea code="chorbi.description" path="description"/>
					<br/>
					<acme:textbox code="chorbi.birthDate" path="birthDate"/>
					<br/>
					<acme:select items="${genres}" itemLabel="value" code="chorbi.genre" path="genre"/>
					<br/>
					<acme:select items="${kindRelationships}" itemLabel="value" code="chorbi.kindRelationship" path="kindRelationship"/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.coordinate.info" /></legend>
					<br/>
					<acme:textbox code="chorbi.coordinate.country" path="coordinate.country"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.state" path="coordinate.state"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.province" path="coordinate.province"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.city" path="coordinate.city"/>
				</fieldset>
				
				<h3><spring:message code="chorbi.creditCard.optional"/></h3>
				
				<fieldset>
					<legend align="left"><spring:message code="chorbi.creditCard.info"/></legend>
					<acme:textbox code="chorbi.creditCard.holderName" path="creditCard.holderName"/>
					<br/>
					<br/><spring:message code="chorbi.creditCard.brandName.check" /><br/>			
					<acme:textbox code="chorbi.creditCard.brandName" path="creditCard.brandName"/>
					
					<br/>		
					<acme:textbox code="chorbi.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="chorbi.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="chorbi.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="chorbi.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
			</jstl:when>
			<jstl:otherwise>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.account.info"/></legend>
					<acme:textbox code="chorbi.username" path="username" />
					<br/>
					<acme:password code="chorbi.password" path="password"/>
					<br/>
					<acme:password code="chorbi.password2" path="password2"/>
						
					<br/>
					<form:checkbox path="agreed"/>
					<form:label path="agreed">
						<spring:message code="chorbi.register.agree" />
						<a href="misc/lopd.do"><spring:message code="chorbi.register.agree.2"/></a>
					</form:label>
					<form:errors path="agreed" cssClass="error" />
					<br/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.personal.info"/></legend>
					<br/>
					<acme:textbox code="chorbi.name" path="name" />
					<br/>
					<acme:textbox code="chorbi.surname" path="surname"/>
					<br/>
					<acme:textbox code="chorbi.email" path="email"/>
					<br/>
					<acme:textbox code="chorbi.phone" path="phone"/>
					<br/>
					<acme:textbox code="chorbi.picture" path="picture"/>
					<br/>
					<acme:textarea code="chorbi.description" path="description"/>
					<br/>
					<acme:textbox code="chorbi.birthDate" path="birthDate"/>
					<br/>
					<acme:select items="${genres}" itemLabel="value" code="chorbi.genre" path="genre"/>
					<br/>
					<acme:select items="${kindRelationships}" itemLabel="value" code="chorbi.kindRelationship" path="kindRelationship"/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.coordinate.info" /></legend>
					<br/>
					<acme:textbox code="chorbi.coordinate.country" path="coordinate.country"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.state" path="coordinate.state"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.province" path="coordinate.province"/>
					<br/>
					<acme:textbox code="chorbi.coordinate.city" path="coordinate.city"/>
				</fieldset>
				<h3><spring:message code="chorbi.creditCard.optional"/></h3>
				<fieldset>
					<legend align="left"><spring:message code="chorbi.creditCard.info"/></legend>
					<acme:textbox code="chorbi.creditCard.holderName" path="creditCard.holderName"/>
					<br/>			
					<acme:textbox code="chorbi.creditCard.brandName" path="creditCard.brandName"/><spring:message code="chorbi.creditCard.brandName.check" />
					<br/>		
					<acme:textbox code="chorbi.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="chorbi.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="chorbi.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="chorbi.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
			</jstl:otherwise>
		</jstl:choose>
		
		<br/>
		<acme:submit name="save" code="chorbi.save"/>
		<acme:cancel code="chorbi.cancel" url="welcome/index.do" />
</jstl:if>

</form:form>