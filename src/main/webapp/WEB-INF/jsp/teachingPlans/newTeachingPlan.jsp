<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="teachingPlans">
    <h2><c:if test="${teachingPlan['new']}">New </c:if> TeachingPlan	</h2>
    <form:form modelAttribute="teachingPlans" class="form-horizontal" id="add-teachingPlan-form" action ="newTeachingPlan">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Save TeachingPlan</button>
    				<spring:url value="/subjects/{subjectId}/newTeachingPlan" var="mySubjectsUrl">
       				 <spring:param name="subjectId" value="${subject.id}"/>
        			 </spring:url>
        			  <a href="${fn:escapeXml(mySubjectsUrl)}" class="btn btn-default"><c:out value="My Subjects"/></a>
            </div>
        </div>
    </form:form>
</petclinic:layout>
