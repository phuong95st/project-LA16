<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sự kiện</title>
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
					<h4>Sự kiện sắp diễn ra</h4>
					<hr>

					<div class="subContent" style="clear:both">
						<table class="table">
								<tr>
									<th>Tên</th>
									<th>Thời gian</th>
									<th>Địa điểm</th>
									<th>Loại</th>
									<th>Nội dung</th>
								</tr>
								<tr>
									<td>Du xuân đầu năm 2017</td>
									<td>30/3/2017 07:15</td>
									<td>Nhà B1 ĐHBK HN</td>
									<td>Du lịch</td>
									<td>Tham quan chùa Bái Đính</td>
								</tr>
							</table>
					</div>
					<br>
					<h4>Sự kiện đã diễn ra</h4>
					<hr>
					<div class="subContent" style="clear:both">
						<table class="table">
								<tr>
									<th>Tên</th>
									<th>Thời gian</th>
									<th>Địa điểm</th>
									<th>Loại</th>
									<th>Nội dung</th>
								</tr>
								<tr>
									<td>Du xuân đầu năm 2017</td>
									<td>30/3/2017 07:15</td>
									<td>Nhà B1 ĐHBK HN</td>
									<td>Du lịch</td>
									<td>Tham quan chùa Bái Đính</td>
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