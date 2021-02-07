<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<rateacher:layout pageName="personalExperience">

    <h2>Personal Experience</h2>
	<c:out value="${personalExperience.name}"/>
            <br/>
            
		    <br/>
		    <h2>Research Experience</h2>
		<c:if test="${personalExperience.researchExperience  == null}">
		<sec:authorize access="hasAuthority('teacher')">
   		<spring:url value="/personalExperience/{personalExperienceId}/newResearchExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Research Experience</a>
		</sec:authorize>
		</c:if>
		
		<c:if test="${personalExperience.researchExperience  != null}">
   			<c:out value="${personalExperience.researchExperience.research}"/>
		</c:if>

            
            <br/>
		    <br/>
		    <h2>Teaching Experience</h2>
		<c:if test="${personalExperience.teachingExperience  == null}">
		<sec:authorize access="hasAuthority('teacher')">
   		<spring:url value="/personalExperience/{personalExperienceId}/newTeachingExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Teaching Experience</a>
		</sec:authorize>
		</c:if>
		
		<c:if test="${personalExperience.teachingExperience  != null}">
   			<c:out value="${personalExperience.teachingExperience.titulation}"/>
   			<br/>
   			<c:out value="${personalExperience.teachingExperience.comment}"/>
		</c:if>
		
			<br/>
		    <br/>
		    <h2>Professional Experience</h2>
		<c:if test="${personalExperience.professionalExperience  == null}">
		<sec:authorize access="hasAuthority('teacher')">
   		<spring:url value="/personalExperience/{personalExperienceId}/newProfessionalExperience" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add Professional Experience</a>
		</sec:authorize>
		</c:if>
		
		<c:if test="${personalExperience.professionalExperience  != null}">
   			<c:out value="${personalExperience.professionalExperience.university}"/>
   			<br/>
   			<c:out value="${personalExperience.professionalExperience.comment}"/>
		</c:if>
		
			<br/>
		    <br/>
		    <h2>External Evaluation</h2>
		<c:if test="${personalExperience.externalEvaluation  == null}">
		<sec:authorize access="hasAuthority('teacher')">
   		<spring:url value="/personalExperience/{personalExperienceId}/newExternalEvaluation" var="personalExperienceUrl">
                <spring:param name="personalExperienceId" value ="${personalExperience.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(personalExperienceUrl)}">Add External Experience</a>
		</sec:authorize>
		</c:if>
		
		<c:if test="${personalExperience.externalEvaluation  != null}">
   			<c:out value="${personalExperience.externalEvaluation.note}"/>
   			<br/>
   			<c:out value="${personalExperience.externalEvaluation.evaluationReport}"/>
   			<br/>
   			<c:out value="${personalExperience.externalEvaluation.comment}"/>
   			
   			
		</c:if>

</rateacher:layout>