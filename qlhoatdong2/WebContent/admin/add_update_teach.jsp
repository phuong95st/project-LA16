<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quản lý lịch giảng</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="../jsp/header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<h3>
						Thêm lịch giảng <small> Nguyễn Văn B</small>
					</h3>
					<hr>
					<div class="col-sm-1"></div>
					<div class="col-sm-7">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-sm-4" for="email">Thời
									gian bắt đầu:</label>
								<div class="col-sm-8">
									<input type="email" class="form-control" id="email"
										placeholder="Thời gian bắt đầu">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="pwd">Thời
									gian kết thúc:</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="pwd"
										placeholder="Thời gian kết thúc">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="pwd">Tuần
									học:</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="pwd"
										placeholder="Tuần học">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="pwd">Mã lớp:</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="pwd"
										placeholder="Mã lớp">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="pwd">Mã môn
									học:</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="pwd"
										placeholder="Mã môn học">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="pwd">Tên môn
									học:</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="pwd"
										placeholder="Tên môn học">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-8">
									<button type="submit" class="btn btn-success">Add</button>
								</div>
							</div>
						</form>
						<div class="col-sm-4"></div>
						<br>
					</div>
					<div class="subContent" style="clear: both">
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Tuần</th>
								<th>Mã lớp</th>
								<th>Mã môn học</th>
								<th>Tên môn học</th>
								<th></th>
							</tr>
							<tr>
								<td>1</td>
								<td>07:15 - 10:15</td>
								<td>12</td>
								<td>143563</td>
								<td>IT4954</td>
								<td>Thiết kế và xây dựng phần mềm</td>
								<td><button class="btn btn-default btn-xs">Remove</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>13:15 - 15:00</td>
								<td>12</td>
								<td>53467</td>
								<td>IT1234</td>
								<td>Tương tác người máy</td>
								<td><button class="btn btn-default btn-xs">Remove</button>
								</td>
							</tr>
						</table>
					</div>
					<button class="btn btn-info"
						style="margin-left: 10pt; margin-bottom: 10pt">Thêm</button>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>