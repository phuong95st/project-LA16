<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<div class="container" id="banner">
			<div class="col-sm-2">
				<img class="img-thumbnail" alt="Logo" src="${url }/images/logo.jpg"
					width="100" height="200">
			</div>
			<div class="col-sm-10">
				<p id="title">Hệ thống quản lý hoạt động</p>
				<p id="title2">Bộ môn Kỹ thuật máy tính</p>
				<p style="color: #FFF; text-align: right">
					Xin chào <b>Nguyễn Văn A</b> [<a href="" style="color: #FFF">Logout</a>]
				</p>
			</div>
		</div>

		<!-- Bắt đầu row 2 -->
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- Slide bar -->
				<div class="col-sm-2 sidenav">
					<p>
						<a href="#">Link</a>
					</p>
					<p>
						<a href="#">Link</a>
					</p>
					<p>
						<a href="#">Link</a>
					</p>
				</div>
				<!-- Content -->
				<div class="col-sm-10 text-left"
					style="border-right: solid 1px gray; border-left: solid 1px gray;">

					<div class="col-sm-9">
						<div id="wellcome">Chúc bạn 1 ngày làm việc hiệu quả.</div>
						<div id="envent">
							Các sự kiện sẽ diễn ra trong tuần
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
					<div class="col-sm-3">
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
					<div id="OnlCal" class="col-sm-12">
						Lịch trực bộ môn ngày hôm nay
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Ca trực</th>
							</tr>
							<tr>
								<td>1</td>
								<td>30/3/2017 07:15 - 30/3/2017 10:15</td>
								<td>Buổi sáng</td>
							</tr>
						</table>
					</div>

					<div id="stuCal" class="col-sm-12">
						Lịch gặp sinh viên hôm nay
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Loại sinh viên</th>
							</tr>
							<tr>
								<td>1</td>
								<td>30/3/2017 07:15 - 30/3/2017 10:15</td>
								<td>Đồ án</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<footer class="container text-center">
			<p>Footer Text</p>
		</footer>
	</div>
</body>
</html>