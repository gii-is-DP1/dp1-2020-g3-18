<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="teachersComment">
    <h2>Comments</h2>

    <table id="commentsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Comment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="comment">
            <tr>
                <td>
                    <c:out value=" ${comment}"/>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</rateacher:layout>