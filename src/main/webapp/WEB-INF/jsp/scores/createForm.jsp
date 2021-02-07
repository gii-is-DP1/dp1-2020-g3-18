<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>

<rateacher:layout pageName="teachers">
    <jsp:body>
        <h2>
          <c:choose><c:when test="${score['new']}">New </c:when><c:otherwise>Update </c:otherwise></c:choose>Score
        </h2>
         <form:form modelAttribute="score"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${score.id}"/>
            <div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Teacher</label>
                    <div class="col-sm-10">
                        <c:out value="${teacher.firstName} ${teacher.lastName}"/>
                    </div>
                </div>
                <rateacher:inputField label="Value" name="valu"/>
                <rateacher:inputField label="Comment" name="comment"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${score['new']}">
                            <button class="btn btn-default" type="submit">Add Score</button>
                            <h2>
                            *You must keep in mind that if you already have a score on this teacher, the new score won't be saved.
                            </h2>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Score</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
	</jsp:body>
</rateacher:layout>