<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Line Maintenance</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for the standard template and tables -->
<link href="admin.css" rel="stylesheet">
</head>
<body>
	<!-- This section checks if the session attribute ID is null and redirects to the Login page if that's the case. If not it it terminates the session after 5 minutes. -->
	<%
		if (session.getAttribute("ID") == null) {
			response.sendRedirect("AdminSignIn.jsp");
		}
		session.setMaxInactiveInterval(300);
	%>
	<img src="RouteLineLogo2.PNG" alt="RouteLine Logo"
		class="img-responsive" height="80" width=100% />

</body>
</html>