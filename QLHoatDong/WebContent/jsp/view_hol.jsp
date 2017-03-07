<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ngày nghỉ</title>
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
					<h4>Danh sách những ngày đi muộn</h4>
					<hr>

					<div class="subContent" style="clear:both">
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thứ</th>
								<th>Thời gian login</th>
								<th>Thời lượng</th>
								<th>Lý do</th>
							</tr>
							<tr>
								<td>1</td>
								<td>Tue</td>
								<td>15-3-2017 09:05</td>
								<td>60 phút</td>
								<td>Việc riêng đột xuất</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Thu</td>
								<td>15-3-2017 09:05</td>
								<td>60 phút</td>
								<td>Dạy lớp XXX</td>
							</tr>
							<tr>
								<td>3</td>
								<td>Tue</td>
								<td>15-3-2017 09:05</td>
								<td>60 phút</td>
								<td>Dạy lớp XXX</td>
							</tr>
							<tr>
								<td>4</td>
								<td>Tue</td>
								<td>15-3-2017 09:05</td>
								<td>60 phút</td>
								<td>Họp</td>
							</tr>
						</table>
					</div>
					<br>
					<h4>Danh sách những ngày nghỉ trong năm</h4>
					<hr>
					<div class="subContent" style="clear:both">
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thứ</th>
								<th>Ngày nghỉ</th>
								<th>Loại nghỉ</th>
								<th>Lý do</th>
							</tr>
							<tr>
								<td>1</td>
								<td>Tue</td>
								<td>15-3-2017</td>
								<td>Buổi sáng</td>
								<td>Việc riêng đột xuất</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Thu</td>
								<td>15-3-2017</td>
								<td>Buổi chiều</td>
								<td>Dạy lớp XXX</td>
							</tr>
							
							<tr>
								<td>3</td>
								<td>Tue</td>
								<td>15-3-2017 09:05</td>
								<td>Cả ngày</td>
								<td>Họp</td>
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