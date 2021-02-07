
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<rateacher:layout pageName="teachers">
    <h2>Teachers</h2>
 
   <table id="teachersTable" class="table table-striped">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Subjects</th>

        </tr>
        </thead>
        <tbody>
         <c:forEach items="${teachers}" var="teacher">
             <tr>
                <td>
                    <spring:url value="/teachers/{teacherId}" var="teacherUrl">
                    <spring:param name="teacherId" value="${teacher.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(teacherUrl)}"><c:out value="${teacher.firstName}"/></a>
                </td>
                <td>
                    <c:out value=" ${teacher.lastName}"/>
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
        
	
    <spring:url value="/teachersWithScore" var="addUrl">
    </spring:url>
    <sec:authorize access="hasAuthority('admin')">
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Teachers With Score</a>
    </sec:authorize>
  	<sec:authorize access="hasAuthority('student')">
    <a class="btn btn-default" href="<c:url value="/students/${student.id}/showRatedTeachers" />">My rated teachers</a>
     </sec:authorize>	
    <sec:authorize access="hasAuthority('student')">
    <a class="btn btn-default"  href="<c:url value="/students/${student.id}/teacherToRate"  />">My teachers to Rate</a>
    </sec:authorize>	
    <sec:authorize access="hasAuthority('teacher')">
     <spring:url value="/teachers/{teacherId}/createPersonalExperienceForm" var="mySubjectsUrl">
                     <spring:param name="teacherId" value="${teacherr.id}"/>
                     </spring:url>
                     	<a class="btn btn-default" href="${fn:escapeXml(mySubjectsUrl)}"><c:out value="Create My Personal Experience"/></a>
      </sec:authorize>
      
      <sec:authorize access="hasAuthority('dean')">
     <spring:url value="/teachers/new" var="teacherUrl">
            </spring:url>
            <a class="btn btn-default" href="${fn:escapeXml(teacherUrl)}"><c:out value="Create Teacher"/></a>
      </sec:authorize>


</rateacher:layout>
