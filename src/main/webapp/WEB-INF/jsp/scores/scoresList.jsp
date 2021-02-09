<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<rateacher:layout pageName="scores">
    <h2>Scores</h2>
    <table id="scoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Value</th>
            <th>Comment</th>
            <sec:authorize access="hasAuthority('admin')">
            <th>Actions</th>
            </sec:authorize> 
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${scores}" var="score"> 
            <tr>
                <td>
                    <spring:url value="/teachers/{teacherId}/scores/{scoreId}/edit" var="editScoreUrl">
                    <spring:param name="teacherId" value="${score.teacher.id}"/>
                    <spring:param name="scoreId" value="${score.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(editScoreUrl)}"><c:out value="${score.valu}"/></a>
                </td>
                
                <td>
                    <c:out value=" ${score.comment}"/>
                </td>
                <sec:authorize access="hasAuthority('admin')">
                <td>
                	<spring:url value="/teachers/{teacherId}/scores/delete/{scoreId}" var="scoreUrl">
                		<spring:param name="teacherId" value ="${score.teacher.id}"/>
                		<spring:param name="scoreId" value ="${score.id}"/>
                	</spring:url>
                    <a href="${fn:escapeXml(scoreUrl)}">Delete</a>
                </td>
                </sec:authorize>                 
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h5 style="color:green">Press on the number to edit the score, do not try to edit scores made by other students</h5>
        <h1></h1>
        <h2>Comments</h2> 

	    <table class="table table-striped">
	
		<thead>
        <tr>
            <th>Comment</th>
            <th>Student who made it</th>
            <sec:authorize access="hasAuthority('teacher')">
            <th>Report comment</th>
           </sec:authorize>
        </tr>
        </thead>
        <tbody>
      		<c:forEach items="${scores}" var="scor"> <!-- -->
				<tr>
					<td><b><c:out value="${scor.comment}"/></b></td>
					<td><b><c:out value="${scor.student.firstName} ${scor.student.lastName}"/></b></td>
					<td>
                    <spring:url value="/reports/new/{scoreId}" var="reportUrl">
                    <spring:param name="scoreId" value="${scor.id}"/>
                    </spring:url>
                    <sec:authorize access="hasAuthority('teacher')">
                    <a href="${fn:escapeXml(reportUrl)}"><c:out value="Report"/></a>
                	  </sec:authorize>
                	</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>

</rateacher:layout>
