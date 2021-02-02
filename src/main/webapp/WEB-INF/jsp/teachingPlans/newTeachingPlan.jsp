<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


 <petclinic:layout pageName="teachingPlan">
    <h2><c:if test="${teachingPlan['new']}">New </c:if> TeachingPlan	</h2>
    <form:form modelAttribute="teachingPlan" class="form-horizontal" id="add-teachingPlan-form" action ="subjects/{subjectId}/newTeachingPlan/saveee">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
    				        			  <button class="btn btn-default" type="submit">Save Teaching Plan</button>
    				
    				<%-- <spring:url value="subjects/{subjectId}/newTeachingPlan/saveee" var="creatingTeachingPlanUrl">
       				 <spring:param name="subjectId" value="${subject.id}"/>
        			  <a href="${fn:escapeXml(creatingTeachingPlanUrl)}" class="btn btn-default"><c:out value="New Teaching plan"/></a>
        			  </spring:url> --%>
            </div>
        </div>
    </form:form>
</petclinic:layout> 


<%-- <petclinic:layout pageName="teachingPlan">
    <h2><c:if test="${teachingPlan['new']}">New </c:if> Teaching Plan</h2>
    <spring:param name="subjectId" value ="${subject.id}"/>
    <form:form modelAttribute="subjects" class="form-horizontal" id="add-teachingPlan-form" action ="/subjects/${subject.id}/newTeachingPlan/save">
    	  
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Save Teaching Plan</button>
                  
            </div>
        </div>
    </form:form>
</petclinic:layout> --%>