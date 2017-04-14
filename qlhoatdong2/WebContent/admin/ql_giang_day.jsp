<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QL giảng dạy</title>
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
					<div>
						<div class="col-sm-5"></div>
						<div class="col-sm-7">
							<form class="form-inline">
								<div class="form-group">
									<label for="email">Chọn nhân viên:</label> <select
										class="selectpicker" data-width="fit" name="user">
										<option value="1">Nguyễn Văn A</option>
										<option value="2">Nguyễn Văn B</option>
										<option value="3">Nguyễn Văn C</option>
									</select>
								</div>
								<div class="form-group">
									<label for="pwd">Học kỳ:</label> <select class="selectpicker"
										data-width="fit" name="hocKy">
										<option value="20161">20161</option>
										<option value="20162">20162</option>
									</select>
								</div>

								<button type="submit" class="btn btn-success">Tra cứu</button>
							</form>
							<br>
						</div>
						<div style="clear: both">
							<form id="formAddTeach" onsubmit="javascript: addTeach()"
								action="teach.htm" method="post">
								<input type="hidden" value="1" name="user"> <input
									type="hidden" value="20161" name="hocKy"> <input
									type="hidden" value="add" name="action">
								<button class="btn btn-info" onclick="submit()">Add</button>
							</form>
							<br>
							<div class="table-responsive">
								<table class="table table-condensed table-bordered">
									<tr>
										<th>No.</th>
										<th>Thứ</th>
										<th>Thời gian</th>
										<th>Phòng học</th>
										<th>tuần học</th>
										<th>Mã lớp</th>
										<th>Mã môn</th>
										<th>Tên môn học</th>
										<th>Action</th>
									</tr>
									<tr>
										<td><a href="#">1</a></td>
										<td>Mon</td>
										<td>6:45 - 9:00</td>
										<td>TC-403</td>
										<td>16 - 30</td>
										<td>86778</td>
										<td>IT8457</td>
										<td>Lý thuyết thông tin</td>
										<td><a href="#">Del</a></td>
									</tr>
									<tr>
										<td><a href="#">2</a></td>
										<td>Mon</td>
										<td>6:45 - 9:00</td>
										<td>TC-403</td>
										<td>16 - 30</td>
										<td>86778</td>
										<td>IT8457</td>
										<td>Lý thuyết thông tin</td>
										<td><a href="#">Del</a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>