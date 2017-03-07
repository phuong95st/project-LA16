<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng ký ngày nghỉ</title>
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
					<h3>Đăng ký nghỉ phép</h3>
					<hr>

						<table class="table table-bordered" style="background-color: #F1F5F8;" id="table_register">
							<tr >
								<th >Ngày đăng ký nghỉ phép</th>
								<th>Loại nghỉ</th>
								<th>Lý do nghỉ</th>
								<th></th>
							</tr>
							<tr>
								<td >Từ <input type="text" width="20px"> đến <input
									type="text" width="20px">
								</td>
								<td>
								<select class="selectpicker" data-width="fit">
										<option>Buổi sáng</option>
										<option>Buổi chiều</option>
										<option>Cả ngày</option>
								</select>
								</td>
								<td><input type="text" class="form-control"></td>
								<td>[<a href="">Add</a>]</td>
							</tr>
							<tr>
								<td>12/3/2017 - 12/3/2017</td>
								<td>Buổi sáng</td>
								<td>Nhà có việc</td>
								<td>[<a href="">Delete</a>]</td>
							</tr>
							<tr>
								<td>12/3/2017 - 12/3/2017</td>
								<td>Cả ngày</td>
								<td>Ốm</td>
								<td>[<a href="">Delete</a>]</td>
							</tr>
						</table>

					<button class="btn btn-success">Register</button>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>