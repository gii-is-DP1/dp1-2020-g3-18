<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<rateacher:layout pageName="error">


	
	<!-- Y despues dejar esto en el else, que es cualquier otro error que me salga (Como lo tenia antes) -->
    <spring:url value="/resources/images/error.png" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>It seems you cannot do this or are not allowed to.</h2>
    <h2>If the problem is caused by our system we will try to fix it as soon as possible</h2>
    
    <p>${exception.message}</p>

</rateacher:layout>
