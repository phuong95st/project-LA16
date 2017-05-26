<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<jsp:include page="head.jsp"></jsp:include>
<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Roboto:400);

body {
	background-color: #fff;
	-webkit-font-smoothing: antialiased;
	font: normal 14px Roboto, arial, sans-serif;
}

.container {
	padding: 25px;
	position: fixed;
}

.form-login {
	background-color: #EDEDED;
	padding-top: 10px;
	padding-bottom: 20px;
	padding-left: 20px;
	padding-right: 20px;
	border-radius: 15px;
	border-color: #d2d2d2;
	border-width: 5px;
	box-shadow: 0 1px 0 #cfcfcf;
}

h4 {
	border: 0 solid #fff;
	border-bottom-width: 1px;
	padding-bottom: 10px;
}

.form-control {
	border-radius: 10px;
}

.wrapper {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-offset-5 col-sm-4">
				<div class="form-login">
					<h4>
						<img class="img-thumbnail" alt="Logo" src="${url }/images/logo.jpg" width="70" height="150"> 
						Đăng nhập hệ thống!
					</h4>
					<c:forEach items="${requestScope.listError }" var="error">
						<p style="color: red; font-size: small;">${error }</p>
					</c:forEach>
					<form action="login.do" method="post">
						<input type="text" id="userName" name="email" class="form-control input-sm chat-input" placeholder="Email" />
						<br> 
						<input type="password" id="userPassword" name="pass" class="form-control input-sm chat-input" placeholder="Password" />
						<br>
						<div class="wrapper">
							<span class="group-btn"> <button type="submit" class="btn btn-primary btn-md">Đăng nhập <i class="fa fa-sign-in"></i></button>
							</span>
						</div>
					</form>

				</div>

			</div>
		</div>
	</div>
</body>
</html>