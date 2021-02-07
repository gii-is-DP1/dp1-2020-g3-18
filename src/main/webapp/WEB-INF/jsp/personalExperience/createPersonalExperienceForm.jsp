<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>


 <rateacher:layout pageName="personalExperiences">
    <h2><c:if test="${personalExperience['new']}">New </c:if> Personal Experience	</h2>
    <form:form modelAttribute="personalExperience" class="form-horizontal" id="add-personalExperience-form" >
        <div class="form-group has-feedback">
            <rateacher:inputField label="Name" name="name"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    		<button class="btn btn-default" type="submit">Save Personal Experience</button>
    				
            </div>
        </div>
    </form:form>
</rateacher:layout> 