<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<rateacher:layout pageName="teachers">

    <h2>Teacher Details</h2>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${teacher.firstName} ${teacher.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Username</th>
            <td><b><c:out value="${teacher.user.username} "/></b></td>
        </tr>

    </table>

    <spring:url value="{teacherId}/scores/new" var="addUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
   	<c:if test="${studentAuth}">
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Score</a>
	</c:if>
	<br/>
    <br/>
    <h2>Scores</h2>


    <spring:url value="{teacherId}/scores" var="showScoresUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(showScoresUrl)}" class="btn btn-default">Show Score and Comment List</a>
    
    <spring:url value="{teacherId}/studentsRated" var="studentsRatedUrl">
        <spring:param name="teacherId" value="${teacher.id}"/>
    </spring:url>
    <sec:authorize access="hasAuthority('admin')">
    <a href="${fn:escapeXml(studentsRatedUrl)}" class="btn btn-default">Students Rated</a>
    </sec:authorize>

	<br/>
    <br/>
    <h2>Personal Experience</h2>
    <br/>    
    
    <c:if test="${teacher.personalExperience  != null}">
		<spring:url value="/personalExperience/{personalExperienceId}" var="personalExperienceUrl">
               <spring:param name="personalExperienceId" value="${personalExperience.id}"/>
               </spring:url>
               <a href="${fn:escapeXml(personalExperienceUrl)}"><c:out value="${teacher.personalExperience.name}"/></a>
                    
	</c:if>
	
	<sec:authorize access="hasAuthority('teacher')">
	<c:if test="${teacher.personalExperience  == null}">
   		<spring:url value="/teachers/{teacherId}/newPersonalExperience" var="teacherUrl">
                <spring:param name="teacherId" value ="${teacher.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(teacherUrl)}">Add Personal Experience</a>
	</c:if></sec:authorize>

</rateacher:layout>