
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="teachersByDepartment">
    <h2>Teachers</h2>

   <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers.teachersList}" var="teacher">
             <tr>
                <td>
                    <spring:url value="/teachers/{teacherId}" var="teacherUrl">
                    <spring:param name="teacherId" value="${teacher.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(teacherUrl)}"><c:out value="${teacher.firstName}"/></a>
                </td>
                <td>
                    <c:out value=" ${teacher.lastName}"/>
                </td>
               
            </tr> 
        </c:forEach>
        </tbody>
    </table>

</rateacher:layout>

