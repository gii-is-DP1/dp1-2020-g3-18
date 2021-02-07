<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="colleges">
    <h2>Colleges</h2>

   <table id="collegesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>City</th>
            <th>Teachers</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${colleges}" var="college">
             <tr>
                <td><c:out value="${college.name}"/>
                <td><c:out value=" ${college.city}"/>
                <td>
                <c:forEach items="${college.teachers}" var="teacher">
                <c:out value= "${teacher.firstName} "/> <c:out value= "${teacher.lastName}, "/>
                </c:forEach>  
                </td>
                <td><spring:url value="/deans/colleges/{collegeId}/teachers" var="showTeachersUrl">
      				<spring:param name="collegeId" value="${college.id}"/>
  					</spring:url>
   					<a href="${fn:escapeXml(showTeachersUrl)}" class="btn btn-default">Add Teacher</a>
    			</td>
            </tr> 
        </c:forEach>                   
        
        </tbody>
    </table>
</rateacher:layout>
