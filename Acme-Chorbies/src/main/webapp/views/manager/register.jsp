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

<form:form action="${requestURI}" modelAttribute="managerForm">
	<jstl:if test="${managerForm.id==0 || managerForm.username == pageContext.request.remoteUser}">
		<form:hidden path="id"/>
		<jstl:choose>
			<jstl:when test="${tipe eq 'edit' }">
				<form:hidden path="username"/>
				<form:hidden path="password"/>
				<form:hidden path="password2"/>
				<form:hidden path="agreed"/>
				<fieldset>
					<legend align="left"><spring:message code="manager.personal.info"/></legend>
					<br/>
					<acme:textbox code="manager.name" path="name" />
					<br/>
					<acme:textbox code="manager.surname" path="surname"/>
					<br/>
					<acme:textbox code="manager.email" path="email"/>
					<br/>
					<acme:textbox code="manager.phone" path="phone"/>
					<br/>
					<acme:textbox code="manager.company" path="company"/>
					<br/>
					<acme:textbox code="manager.vat" path="vat"/>
					<br/>
				</fieldset>
				
				<h3><spring:message code="manager.creditCard.optional"/></h3>
				
				<fieldset>
					<legend align="left"><spring:message code="manager.creditCard.info"/></legend>
					<acme:textbox code="manager.creditCard.holderName" path="creditCard.holderName"/>
					<br/>
					<br/><spring:message code="manager.creditCard.brandName.check" /><br/>			
					<acme:textbox code="manager.creditCard.brandName" path="creditCard.brandName"/>
					
					<br/>		
					<acme:textbox code="manager.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="manager.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="manager.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="manager.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
			</jstl:when>
			<jstl:otherwise>
				<fieldset>
					<legend align="left"><spring:message code="manager.account.info"/></legend>
					<acme:textbox code="manager.username" path="username" />
					<br/>
					<acme:password code="manager.password" path="password"/>
					<br/>
					<acme:password code="manager.password2" path="password2"/>
						
					<br/>
					<form:checkbox path="agreed"/>
					<form:label path="agreed">
						<spring:message code="manager.register.agree" />
						<a href="misc/lopd.do"><spring:message code="manager.register.agree.2"/></a>
					</form:label>
					<form:errors path="agreed" cssClass="error" />
					<br/>
				</fieldset>
				<fieldset>
					<legend align="left"><spring:message code="manager.personal.info"/></legend>
					<br/>
					<acme:textbox code="manager.name" path="name" />
					<br/>
					<acme:textbox code="manager.surname" path="surname"/>
					<br/>
					<acme:textbox code="manager.email" path="email"/>
					<br/>
					<acme:textbox code="manager.phone" path="phone"/>
					<br/>
					<acme:textbox code="manager.company" path="company"/>
					<br/>
					<acme:textbox code="manager.vat" path="vat"/>
					<br/>
				</fieldset>
				<h3><spring:message code="chorbi.creditCard.optional"/></h3>
				<fieldset>
					<legend align="left"><spring:message code="manager.creditCard.info"/></legend>
					<acme:textbox code="manager.creditCard.holderName" path="creditCard.holderName"/>
					<br/>			
					<acme:textbox code="manager.creditCard.brandName" path="creditCard.brandName"/><spring:message code="manager.creditCard.brandName.check" />
					<br/>		
					<acme:textbox code="manager.creditCard.number" path="creditCard.number"/>
					<br/>			
					<acme:textbox code="manager.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					<br/>			
					<acme:textbox code="manager.creditCard.expirationYear" path="creditCard.expirationYear"/>
					<br/>	
					<acme:textbox code="manager.creditCard.cvv" path="creditCard.cvv"/>
				</fieldset>
			</jstl:otherwise>
		</jstl:choose>
		
		<br/>
		<acme:submit name="save" code="manager.save"/>
		<acme:cancel code="manager.cancel" url="welcome/index.do" />
</jstl:if>

</form:form>