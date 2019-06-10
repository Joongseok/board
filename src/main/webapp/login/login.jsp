<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Login</title>

<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container">

		<form id="frm" class="form-signin"
			  method="post" action="${pageContext.request.contextPath }/login">
			<h2 class="form-signin-heading">Login</h2>
			<label for="userId" class="sr-only">userId</label> 
			<input type="text" name="userId" id="userId" class="form-control" placeholder="userId" required value="${param.userId}">
			 <label	for="password" class="sr-only">password</label> 
			 <input	type="password" name="pass" id="password" class="form-control" value="brown1234" placeholder="password" required>
			<button id="loginBtn" class="btn btn-lg btn-primary btn-block" type="submit">login </button>
		</form>
	</div>
</body>
</html>
