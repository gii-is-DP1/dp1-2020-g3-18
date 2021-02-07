<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<rateacher:layout pageName="home">
    <!--<h2>Welcome to RATE A TEACHER! </h2>-->
    <div class="row">
        <div class="col-md-12">
        
        	
        
            <spring:url value="/resources/images/fotoWelcome.png" var="welcomeImage"/>
    		<img src="${welcomeImage}"/>
        </div>
    </div>
</rateacher:layout>
