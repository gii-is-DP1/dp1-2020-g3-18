<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true" description="Name of the active menu"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="rateacher" tagdir="/WEB-INF/tags" %>


<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<rateacher:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span aria-hidden="true"></span>
					<span>Home</span>
				</rateacher:menuItem>
		
 
				<rateacher:menuItem active="${name eq 'teachers'}" url="/teachers"
					title="teachers">
					<span aria-hidden="true"></span>
					<span>Teachers</span>
				</rateacher:menuItem>

				<rateacher:menuItem active="${name eq 'departments'}" url="/departments"
					title="departments">
					<span aria-hidden="true"></span>
					<span>Departments</span>
				</rateacher:menuItem>

				<rateacher:menuItem active="${name eq 'students'}" url="/students"
					title="students">
					<span aria-hidden="true"></span>
					<span>Students</span>
				</rateacher:menuItem>
				<rateacher:menuItem active="${name eq 'findteachers'}" url="/findTeachers"
					title="teachers">
					<span aria-hidden="true"></span>
					<span>Find Teachers</span>
				</rateacher:menuItem>
			<rateacher:menuItem active="${name eq 'subject'}" url="/subjects"
					title="subjects">
					<span aria-hidden="true"></span>
					<span>Subjects</span>
				</rateacher:menuItem>
				<rateacher:menuItem active="${name eq 'subject'}" url="/deans/colleges"
					title="Colleges">
					<span aria-hidden="true"></span>
					<span>Colleges</span>
				</rateacher:menuItem>
				<rateacher:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</rateacher:menuItem>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> Bienvenido,  
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
