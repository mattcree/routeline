<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Team1">

    <title>RouteLine Sign in page for administrators</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
  </head>

  <body>
	<img src="RouteLineLogo2.PNG" alt="RouteLine Logo" class="img-responsive" height="80" width=100% />
	<!-- Form for taking in the ID and password for login. Performs basic pattern check for ID.  -->
    <div class="container">
      <form class="form-signin" action="SignInServLet" method="get">
        <h2 class="form-signin-heading">Admin Sign in</h2>
        <label for="inputNumber" class="sr-only">Employee ID</label>
        <input type="text" pattern="[a-b][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" id="inputNumber" name="empID" class="form-control" placeholder="Employee ID: b1234567" required autofocus/>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
          <!-- Not yet functional -->
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>