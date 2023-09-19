<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application</title>
<script type="text/javascript">

	function validateForm() {
		const zipcode = document.getElementById("zipcode").value;
		if (zipcode != "") {
			if (zipcode < 100000 || zipcode > 999999) {
				alert("Invalid zipcode. Zipcode must be a six-digit number only.");
				return false;
			}
		}
		
		const phone = document.getElementById("phone").value;
		if (phone != "") {
			if (phone < 1000000000 || phone > 9999999999) {
				alert("Invalid phone number. Phone number must be a ten-digit number only.");
				return false;
			}
		}
		
		const pass = document.getElementById("password").value;
		const confirm_pass = document.getElementById("confirm").value;
		if (confirm_pass != pass) {
			alert("Password and confirm password don't match. Both password values must be same.");
			return false;
		} 
		else if (pass.length < 8) {
			alert("Minimum length of password is 8 characters. Please re-enter.");
			return false;
		}
	}
	
</script>
</head>

<body>
	<section id="intro">
		<h1>Welcome to Application!</h1>
		<h4>Create, Search, Update, Delete</h4>
	</section>
	
	<section id="signup">
		<h3>Enter Details</h3>
	
		<form action="signup" method="post">
			<label for="fname">First Name * </label>
			<input type="text" id="fname" name="fname" required>
			
			<br><br>
			<label for="lname">Last Name * </label>
			<input type="text" id="lname" name="lname" required>
			
			<br><br>
			<label for="address">Address </label>
			<input type="text" id="address" name="address">
			
			<br><br>
			<label for="city">City </label>
			<input type="text" id="city" name="city">
			
			<br><br>
			<label for="state">State </label>
			<input type="text" id="state" name="state">
			
			<br><br>
			<label for="zipcode">Zip Code </label>
			<input type="text" id="zipcode" name="zipcode">
			
			<br><br>
			<label for="phone">Phone Number </label>
			<input type="text" id="phone" name="phone">
			
			<br><br>
			<label for="email">Email * </label>
			<input type="email" id="email" name="email" required>
			
			<br><br>
			<label for="password">Password * </label>
			<input type="password" id="password" name="password" required>
			
			<br><br>
			<label for="confirm">Confirm Password * </label>		
			<input type="password" id="confirm" name="confirm" required>

			<br><br>
			<button type="submit" onclick="validateForm()">Sign Up</button>
		</form>
		
	</section>
</body>
</html>