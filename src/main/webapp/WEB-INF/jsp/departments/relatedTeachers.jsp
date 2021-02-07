
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="departments">
    <h2><c:out value = "Teachers related to ${department.name}"></c:out></h2>

    <table id="tttTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
             <tr>
                <td>
                    <c:out value="${teacher.firstName}"/> <c:out value=" "/> <c:out value="${teacher.lastName}"/>   
                </td>
            </tr> 
        </c:forEach>
        </tbody>
    </table> 
</rateacher:layout>