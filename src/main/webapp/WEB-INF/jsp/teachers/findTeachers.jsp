<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<rateacher:layout pageName="teachers">

    <h2>Find Teachers</h2>

    <form:form modelAttribute="teachers" action="/teachersFound" method="get" class="form-horizontal"
               id="search-teacher-form">
        <div class="form-group">
            <div class="control-group" id="firstName">
                <label>First name </label>
                <div>
                     <form:input class="form-control" path="firstName" size="30" maxlength="80"/>
                    <span class="help-inline"><form:errors path="*"/></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div>
                <button type="submit">Find Teacher</button>
            </div>
        </div>

    </form:form>

    <br/> 

	
</rateacher:layout>
