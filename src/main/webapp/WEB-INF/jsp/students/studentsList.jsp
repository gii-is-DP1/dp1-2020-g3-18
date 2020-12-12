
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="students">
    <h2>Students</h2>

    <table id="studentsTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students.studentList}" var="student">
             <tr>
                <td>
                    <spring:url value="/students/{studentId}" var="studentUrl">
                    <spring:param name="studentId" value="${student.id}"/>
                    </spring:url>
                    <c:out value="${student.firstName}"/>
                </td>
                <td>
                    <c:out value=" ${student.lastName}"/>
                </td>

               
            </tr> 
        </c:forEach>
        </tbody>
    </table> 
    
  <!-- <spring:url value="/studentsWithScore" var="addUrl">
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">students With Score</a>
      -->
    <%-- <spring:url value="/students/new" var="addUrl">
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Student</a> --%>
</petclinic:layout>

