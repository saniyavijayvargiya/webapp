<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Application</title>
</head>

<body>
	
	<section id="intro">
		<h1>Welcome to Application!</h1>
		<h5>Create, Search, Update, Delete</h5>
		<img src="images/home.png">
	</section>
	
	<section id="login">
		<h3>Enter Login Details</h3>
	
		<form action="signin" method="post">
			<label for="email" name="email">Email </label>
			<br>
			<input type="email" name="email" placeholder="name@example.com" required>
			
			<br><br>
			<label for="password" name="password">Password </label>
			<br>
			<input type="password" name="password" required>

			<br><br>
			<button type="submit">Sign In</button>
		</form>
		
		If you are not an existing user, sign up <a href="signup.jsp">here</a>.
	</section>
	
</body>
</html>