<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<rateacher:layout pageName="colleges">
    <h2>Add teacher to college</h2>
<body>
	<form:form modelAttribute="teacher" class="form-horizontal">
    	<input type="hidden" name="id" value="${teacher.id}"/>
     	<div>
     		<label>Are you sure you want to add teacher: </label>
     		<c:out value="${teacher.firstName} ${teacher.lastName} "></c:out>
     		<label>to college?</label>
     		
     		<button class="btn btn-default" type="submit">yes</button>
     		
     	</div>
     	
   	
        
        <div class="goBack">
        
        	<spring:url value="/deans/colleges/{collegeId}/teachers" var="goBackToView">
				<spring:param name="collegeId" value="${collegeId}"/>        
        
        	</spring:url>
        		Otherwise, go <a href="${fn:escapeXml(goBackToView)}">back</a></label>
        </div>
        	 
           			            			 
	</form:form>

</body>
</rateacher:layout>