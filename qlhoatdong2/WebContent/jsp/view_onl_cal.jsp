<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lịch trực</title>
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
					<h3>Lịch trực của bạn</h3>
					<hr>
					<div class="col-sm-7"></div>
					<div class="col-sm-5">
						<form class="form-inline">
							<div class="form-group">
								<label for="email">Tháng:</label> <select class="selectpicker"
									data-width="fit">
									<option>6</option>
									<option>7</option>
									<option>8</option>
								</select>
							</div>
							<div class="form-group">
								<label for="pwd">Năm:</label> <select class="selectpicker"
									data-width="fit">
									<option>2016</option>
									<option>2017</option>
								</select>
							</div>

							<button type="submit" class="btn btn-success">Tra cứu</button>
						</form>
						<br>
					</div>
					<div class="table-responsive" style="clear: both">
						<table class="table table-condensed table-bordered">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Ca trực</th>
							</tr>
							<tr>
								<td>1</td>
								<td>07:15 - 10:15</td>
								<td>Buổi sáng</td>
							</tr>
							<tr>
								<td>2</td>
								<td>07:15 - 10:15</td>
								<td>Buổi sáng</td>
							</tr>
							<tr>
								<td>3</td>
								<td>07:15 - 10:15</td>
								<td>Buổi chiều</td>
							</tr>
							<tr>
								<td>4</td>
								<td>14:15 - 16:00</td>
								<td>Buổi chiều</td>
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