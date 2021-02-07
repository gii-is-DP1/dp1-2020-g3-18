
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="reports">
    <h2>Reports</h2>
 
   <table id="reportsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Reported Comment</th>
            <th>Reason for the report</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reports}" var="report">
             <tr>
                <td>
                    <c:out value=" ${report.score.comment}"/>
                </td>
                <td>
                    <c:out value=" ${report.reason}"/>
                </td>
            </tr> 
        </c:forEach>                   
        
        </tbody>
    </table>
  
</rateacher:layout>

