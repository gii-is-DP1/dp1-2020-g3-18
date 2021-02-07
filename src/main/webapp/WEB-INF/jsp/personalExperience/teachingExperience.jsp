<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>


 <rateacher:layout pageName="teachingExperience">
    <h2><c:if test="${teachingExperience['new']}">New </c:if> Teaching Experience	</h2>
    <form:form modelAttribute="teachingExperience" class="form-horizontal" id="add-teachingExperience-form" >
        <div class="form-group has-feedback">
            <rateacher:inputField label="Titulation" name="titulation"/>
            <rateacher:inputField label="Comment" name="comment"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    		<button class="btn btn-default" type="submit">Save Teaching Experience</button>
    				
            </div>
        </div>
    </form:form>
</rateacher:layout> 