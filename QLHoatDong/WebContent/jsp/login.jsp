<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<div class="container " style="margin-top: 15%">
		<div class="col-sm-2"></div>
		<div class="col-sm-2">
			<script src="http://www.clocklink.com/embed.js"></script>

			<script type="text/javascript">
				obj = new Object;

				obj.clockfile = "0009-magenta.swf";
				obj.TimeZone = "ICT";

				obj.width = 200;
				obj.height = 200;

				obj.wmode = "transparent";
				showClock(obj);
			</script>
		</div>
		<div class="col-sm-4" id="login">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-3" for="email">Email:</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="email"
							placeholder="Enter email">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="pwd">Password:</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="pwd"
							placeholder="Enter password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-sm-4"></div>
	</div>
</body>
</html>