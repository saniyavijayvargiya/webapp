<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Application</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<h1>Search Records</h1>
	<h4>Enter first or last name for search</h4>
	
	<form action="search" method="post">
		<input type="text" id="searchname" name="searchname">
		<button type="submit"><i class="fa fa-search"></i></button>
	</form>
	
</body>
</html>