<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>


<rateacher:layout pageName="subjects">
    <jsp:body>
		<h2>
    		New Subject
    	</h2>
       	<form:form modelAttribute="subjects"
                   class="form-horizontal">
        <input type="hidden" name="id" value="${subjects.id}"/>
        <div class="form-group has-feedback">
            <rateacher:inputField label="Name" name="name"/>
            <rateacher:inputField label="Curso" name="curso"/>
        </div>
        <div class="form-group">
           		<div class="col-sm-offset-2 col-sm-10">
            		<button class="btn btn-default" type="submit">Save Subject</button>                  
            	</div>
        	</div>
    	</form:form>
    </jsp:body>
</rateacher:layout>
