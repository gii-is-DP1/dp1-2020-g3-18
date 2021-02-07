<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<rateacher:layout pageName="colleges">
    <h2>Colleges</h2>
<body>
	 <form:form modelAttribute="teacher" class="form-horizontal">
           			 <input type="hidden" name="id" value="${teacher.id}"/>
            			<div class="form-group has-feedback">
               			 <div class="form-group">
                    		<label class="col-sm-2 control-label">Are you sure you want to add teacher:</label>
                   			 <div class="col-sm-10">
    							
                       			<c:out value="${teacher.firstName} ${teacher.lastName}"/>
                       			 <div class="col-sm-11">
                       			<label class="col-sm-4 control-label">to subject?</label>
                       			</div>
                       			                	<div class="col-sm-offset-5 col-sm-10">
                            <button class="btn btn-default" type="submit">yes</button>        
                    </div>
                   			 </div>
                		</div>
          			    </div>
          		  <div class="form-group">
                    <label class="col-sm-2 control-label">Otherwise you must go back</label>   
            </div>
        </form:form>

</body>
</rateacher:layout>