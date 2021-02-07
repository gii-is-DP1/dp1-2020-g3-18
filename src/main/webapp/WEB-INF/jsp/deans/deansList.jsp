<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="deans">
    <h2>Deans</h2>

   <table id="deansTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>College</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${deans}" var="dean">
             <tr>
                <td><c:out value="${dean.firstName}"/>
                <td><c:out value=" ${dean.lastName}"/>
                <td><c:out value="${dean.college.name} "/>
            </tr> 
        </c:forEach>                   
        
        </tbody>
    </table>
</rateacher:layout>
