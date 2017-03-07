<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý lịch giảng</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
						<h3>Quản lý lịch giảng</h3>
						<hr>
						<div class="col-sm-2" >
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#">Nguyễn Văn A</a></li>
								<li><a href="#">Nguyễn Văn B</a></li>
								<li class="active"><a href="#">Nguyễn Văn C</a></li>
								<li><a href="#">Nguyễn Văn D</a></li>
							</ul>
						</div>
						<div class="col-sm-10">
							<button class="btn btn-success" style="margin-bottom: 5pt;">Add</button>
							<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Tuần</th>
								<th>Mã lớp</th>
								<th>Mã môn học</th>
								<th>Tên môn học</th>
								<th>Action</th>
							</tr>
							<tr>
								<td>1</td>
								<td>07:15 - 10:15</td>
								<td>12 - 34</td>
								<td>143563</td>
								<td>IT4954</td>
								<td>Thiết kế và xây dựng phần mềm</td>
								<th><a href="">Edit</a> | <a href="">Del</a></th>
							</tr>
							<tr>
								<td>2</td>
								<td>13:15 - 15:00</td>
								<td>12 - 34</td>
								<td>53467</td>
								<td>IT1234</td>
								<td>Tương tác người máy</td>
								<th><a href="">Edit</a> | <a href="">Del</a></th>
							</tr>
							<tr>
								<td>3</td>
								<td>13:15 - 15:00</td>
								<td>12 - 34</td>
								<td>53467</td>
								<td>IT1234</td>
								<td>Tương tác người máy</td>
								<th><a href="">Edit</a> | <a href="">Del</a></th>
							</tr>
							<tr>
								<td>4</td>
								<td>13:15 - 15:00</td>
								<td>12 - 34</td>
								<td>53467</td>
								<td>IT1234</td>
								<td>Tương tác người máy</td>
								<th><a href="">Edit</a> | <a href="">Del</a></th>
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