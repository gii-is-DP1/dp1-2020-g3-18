<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<rateacher:layout pageName="error">

	<%-- <c:if test="${exception.message == 'Expected: controller used to showcase what happens when an access is not permited'}" >  --%>  
	<!-- Quiero hacer un if por si hay un acceso denegado me aparezca esto, proveniente del mapping /forbiddenAccess -->
	<spring:url value="/resources/images/accessDenied.png" var="accessDeniedImage"/>
    <img src="${accessDeniedImage}"/>
    <h2>Sorry, but you are not allowed to do this</h2>
	<%-- </c:if> --%>
	
	<!-- Y despues dejar esto en el else, que es cualquier otro error que me salga (Como lo tenia antes) -->
    <spring:url value="/resources/images/error.png" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>It seems you cannot do this or we are having technical problems...</h2>
    <h2>We are working hard to prevent unexpected errors    :D</h2>
    
    <p>${exception.message}</p>

</rateacher:layout>
