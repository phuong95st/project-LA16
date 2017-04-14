<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="../jsp/header.jsp"></jsp:include>
		<!-- Bắt đầu row 2 -->
		<div class="container text-center">
			<div class="row content" id="content">
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<div>
						<p style="margin-top: 10pt;">
							Bạn là <b>quản trị viên!</b>
						</p>
					</div>
					<hr>
					<div></div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>