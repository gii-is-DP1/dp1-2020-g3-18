<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<rateacher:layout pageName="teachers">
    <h2>Teachers</h2>
 
   <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Subjects</th>
            <th>Press to add</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
             <tr>
                <td>
                    <c:out value="${teacher.firstName}"/>
                </td>
                <td>
                    <c:out value=" ${teacher.lastName}"/>
                </td>

                <td>
                	<c:forEach items="${teacher.subjects}" var="subject">
	                    <c:out value="${subject.name} "/>
	                </c:forEach>
                </td>
				<td>
				 <spring:url value="teachers/{teacherId}/add" var="addTeacherToScoreUrl">
                  	<spring:param name="subjectId" value="${subjectId}"/>
                  	<spring:param name="teacherId" value="${teacher.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(addTeacherToScoreUrl)}"><c:out value="add to subject"/></a> -->
				</td>
               
            </tr> 
        </c:forEach>                   
        
        </tbody>
    </table>
     <h4 style="color:red;"><c:out value="${nono}"></c:out></h4>
</rateacher:layout>