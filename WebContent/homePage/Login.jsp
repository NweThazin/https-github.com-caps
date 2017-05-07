<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Application Processing System (CAPS)</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/HomeStyle.css">
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/team4Style.css">
</head>
<body>
	<div class="login">
		<div class="login-header">
			<div class="container">
				<img src="${pageContext.request.contextPath}/img/logo-transparent.jpg">
			</div>
		</div>
		<div class="login-form">
			<form action="/Team04CAPS/login" method="post">
				<h3>User ID:</h3>
				<input type="text" placeholder="Username" name="username" required/><br>
				<h3>Password:</h3>
				<input type="password" placeholder="Password" name="password" required/> <br>
				<div class="space">
					<input type="submit" value="Login" class="login-button" />
				</div>
			</form>
		</div>
	</div>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/jquery-ui.min.js"></script>
	<script src="../js/index.js"></script>
</body>
</html>
