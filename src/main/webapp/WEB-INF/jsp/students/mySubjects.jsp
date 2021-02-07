
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="subjects">
    <!-- <h2>Subjects related</h2> -->
    <h2><c:out value = "I am ${student.firstName} and this are my subjects"></c:out></h2>

    <table id="mySubjectsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Course</th>
            <th>List of teachers who teach this subject</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${subjects}" var="subject">
             <tr>
                <td>
                    <c:out value="${subject.name}"/>
                </td>
                <td>
                    <c:out value="${subject.curso}"/>
                </td>
                 <td>
                     <spring:url value="/teachers/{subjectId}/subjectsTeached" var="subjectUrl">
                     <spring:param name="subjectId" value="${subject.id}"/>
                     </spring:url>
                    <a href="${fn:escapeXml(subjectUrl)}"><c:out value="Teachers"/></a>
                </td>

               
            </tr> 
        </c:forEach>
        </tbody>
    </table> 
    
</rateacher:layout>