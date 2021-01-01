<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="teachers">
   
    <h2>My rated teachers</h2>

    <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Subject</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
             <tr>
                <td>
                    <c:out value="${teacher.firstName}"/>
                </td>
                <td>
                    <c:forEach items="${teacher.subjects}" var="subject">
                
	                    <c:out value="${subject.name} "/>
	                   
	                    
	                    
	                </c:forEach>
                </td> 
                
               
            </tr> 
        </c:forEach>
        </tbody>
    </table> 
    
</petclinic:layout>