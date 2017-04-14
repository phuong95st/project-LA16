<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thống kê</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<h4>Thống kê giờ dạy</h4>
					<hr>
					<div>
						<form class="form-inline">
							<div class="form-group">
								<label for="email">Học kỳ</label> <select class="selectpicker"
									data-width="fit">
									<option>20161</option>
									<option>20162</option>
									<option>20171</option>
									<option>20172</option>
								</select>
								<label for="email">Tuần</label> <select
									class="selectpicker" data-width="fit">
									<option>1: 11-7 đến 23-4</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
								</select> 
							</div>
							<button type="submit" class="btn btn-success">Thống kê</button>
						</form>
						<hr>
						<b>Kết quả</b>
						<br><br>
						<table class="table table-condensed table-bordered">
							<tr>
								<td>Tổng số giờ dạy dự kiến:</td>
								<td>345</td>
							</tr>
							<tr>
								<td>Tổng số giờ đã dạy:</td>
								<td>300</td>
							</tr>
							<tr>
								<td>Tỉ lệ dạy:</td>
								<td>90%</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>