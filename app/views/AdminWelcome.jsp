<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Welcome Page</title>
<!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
</head>
<body>
	<!-- This section checks if the session attribute ID is null and redirects to the Login page if that's the case. If not it it terminates the session after 5 minutes. -->
	<%
		if(session.getAttribute("ID")==null)
		{
			response.sendRedirect("AdminSignIn.jsp");
		}
	 session.setMaxInactiveInterval(300);
	
	%>
	<img src="RouteLineLogo2.PNG" alt="RouteLine Logo" class="img-responsive" height="80" width=100% />
	
	Welcome to the admin section. You are logged in as ${ID}.<br><br>
	
	<!-- This is to test the logout and accesses the Logout servlet -->
	
	
	
	<!-- Creates two bootstrap cards.  -->
	<div class="container-fluid">
		<div class="row">
	
	<div class="col-md-3">
	<div class="card" style="max-width: 289px;">
	
	<!-- Image -->
	<!--<a href="MaintainStations.jsp" class="card-link">-->
	<img class="card-img-top card-img-responsive" src="Station50.jpg" alt="Station Record Maintenance">
	<!-- </a> -->
	<!-- Text Content -->
	<div class="card-block card-primary card-inverse">
	<p class="card-text text-center"><a href="MaintainStations.jsp" class="card-link">Maintain Station Record</a></p>
	</div>
	
	</div></div>
	
	<div class="col-md-3">
	<div class="card" style="max-width: 289px;">
	
	<!-- Image - Admin Maintenance -->
	
	<img class="card-img-top card-img-responsive" src="Network289.jpg" alt="Line Maintenance">

	
	<!-- Text Content -->
	
	<div class="card-block card-primary card-inverse">
	<p class="card-text text-center"><a href="MaintainLines.jsp" class="card-link">Maintain Line Record</a></p>
	</div>
	</div></div>
	
	<div class="col-md-3">
	<div class="card" style="max-width: 289px;">
	
	<!-- Image -->
	
	<img class="card-img-top card-img-responsive" src="AdminMaintenance5.JPG" alt="Admin Maintenance">

	
	<!-- Text Content -->
	
	<div class="card-block card-primary card-inverse">
	<p class="card-text text-center"><a href="MaintainAdmins.jsp" class="card-link">Maintain Admin Logins</a></p>
	</div>
	</div></div>
	
	</div></div>
	<br><br>
	<div class="text-center">
	<form action="Logout">
		<button class="btn btn-md btn-primary" type="submit">Logout</button>
	</form></div>
	
</body>
</html>