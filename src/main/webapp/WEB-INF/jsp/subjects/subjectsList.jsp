<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<rateacher:layout pageName="subjects">
    <h2>Subjects</h2>

    <table id="subjectsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Curso</th>
            <th>Teaching Plans</th>
            <sec:authorize access="hasAuthority('admin')">
            <th>Delete</th>
            </sec:authorize> 
            <c:if test="${esDean}"> 
            	<th>Add Teacher</th>
            </c:if> 
        </tr>
        </thead>
        <tbody>
         <c:forEach items="${subjects}" var="subject">
             <tr>
                <td>
                    <spring:url value="/subjects/{subjectId}" var="subjectUrl">
                    <spring:param name="subjectId" value="${subject.id}"/>
                    </spring:url>
                    <c:out value="${subject.name}"/>
                </td>
                <td>
                    <c:out value=" ${subject.curso}"/>
                </td>

                <td><c:out value=" ${subject.teachingPlan.name}"/>
                <c:if test="${subject.teachingPlan  != null}">
                <sec:authorize access="hasAuthority('teacher')">
   					   <spring:url value="/subjects/deleteTeachingPlan/{subjectId}" var="subjectUrl">
                		<spring:param name="subjectId" value ="${subject.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(subjectUrl)}">((  Delete  ))</a>
                	</sec:authorize>
				</c:if>
				<c:if test="${subject.teachingPlan  == null}">
				<sec:authorize access="hasAuthority('teacher')">
   					   <spring:url value="/subjects/{subjectId}/newTeachingPlan" var="subjectUrl">
                		<spring:param name="subjectId" value ="${subject.id}"/>
                	</spring:url>
                	<a href="${fn:escapeXml(subjectUrl)}">Add Teaching Plan</a>
				</sec:authorize>
				</c:if>
                
                </td>

                <sec:authorize access="hasAuthority('admin')">
                <td>
                	<spring:url value="/subjects/delete/{subjectId}" var="subjectUrl">
                		<spring:param name="subjectId" value ="${subject.id}"/>
                	</spring:url>
                    <a href="${fn:escapeXml(subjectUrl)}">Delete</a>
                </td>
                </sec:authorize> 
                  <c:if test="${esDean}">
                <td>
                    <spring:url value="/subjects/{subjectId}/teachers" var="showTeachersUrl">
      				<spring:param name="subjectId" value="${subject.id}"/>
  					</spring:url>
   					<a href="${fn:escapeXml(showTeachersUrl)}" class="btn btn-default">Add Teacher</a>
                </td>
                 </c:if>  

               
             </tr> 
        </c:forEach> 
        
             
        </tbody>
    </table>
    <spring:url value="/subjects/new" var="addUrl">
    </spring:url>
   		<sec:authorize access="hasAuthority('admin')">
    	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add Subject</a> 
    	</sec:authorize>
    	
    <spring:url value="/subjects/mySubjects/{studentId}" var="mySubjectsUrl">
        <spring:param name="studentId" value="${student.id}"/>
        </spring:url>
        <c:if test="${condition}">
        	<a href="${fn:escapeXml(mySubjectsUrl)}" class="btn btn-default"><c:out value="My Subjects"/></a>
        </c:if>
        <%-- <c:if test="${esDean}">
        	<a href="${fn:escapeXml(mySubjectsUrl)}" class="btn btn-default"><c:out value="My Subjects"/></a>
        </c:if> --%>
    
                    
    
</rateacher:layout>


