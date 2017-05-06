<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page error</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<div class="h3 text-danger" align="center">
			${error }<br><br>
			<a class="btn btn-primary" href="index.ad"><span class="glyphicon glyphicon-home"></span> Back to home</a>
		</div>
		
	</div>
</body>
</html>