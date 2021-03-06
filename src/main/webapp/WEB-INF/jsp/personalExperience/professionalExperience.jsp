<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>


 <rateacher:layout pageName="professionalExperience">
    <h2><c:if test="${professionalExperience['new']}">New </c:if> Professional Experience	</h2>
    <form:form modelAttribute="professionalExperience" class="form-horizontal" id="add-professionalExperience-form" >
        <div class="form-group has-feedback">
            <rateacher:inputField label="University" name="university"/>
            <rateacher:inputField label="Comment" name="comment"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    		<button class="btn btn-default" type="submit">Save Professional Experience</button>
    				
            </div>
        </div>
    </form:form>
</rateacher:layout> 