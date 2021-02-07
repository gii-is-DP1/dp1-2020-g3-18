<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="students">
    <h2>
        <c:if test="${student['new']}">New </c:if> Student
    </h2>
    <form:form modelAttribute="student" class="form-horizontal" id="add-student-form">
        <div class="form-group has-feedback">
            <rateacher:inputField label="First Name" name="firstName"/>
            <rateacher:inputField label="Last Name" name="lastName"/>
            <rateacher:inputField label="Name" name="name"/>
            <rateacher:inputField label="Email" name="email"/>
            <rateacher:inputField label="Username" name="user.username"/>
            <rateacher:inputField label="Password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${student['new']}">
                        <button class="btn btn-default" type="submit">Add Student</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Student</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</rateacher:layout>
