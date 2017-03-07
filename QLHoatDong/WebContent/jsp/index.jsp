<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<jsp:include page="head.jsp"></jsp:include>
<script src='//cdn.jsdelivr.net/jquery.marquee/1.4.0/jquery.marquee.min.js'></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#wellcome').marquee({
			//speed in milliseconds of the marquee
			duration : 9000,
			//gap in pixels between the tickers
			gap : 50,
			//time in milliseconds before the marquee will start animating
			delayBeforeStart : 0,
			//'left' or 'right'
			direction : 'left',
			//true or false - should the marquee be duplicated to show an effect of continues flow
			duplicated : false
		});
	});
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Bắt đầu row 2 -->
		<div class="container text-center">
			<div class="row content" id="content">
				<jsp:include page="slidebar.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
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
								<td>07:15 - 10:15</td>
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
								<td>07:15 - 10:15</td>
								<td>Đồ án</td>
							</tr>
						</table>
					</div>
					
					<div id="teach" class="col-sm-12">
						Lịch giảng dạy hôm nay của bạn
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Tuần</th>
								<th>Mã lớp</th>
								<th>Mã môn học</th>
								<th>Tên môn học</th>
							</tr>
							<tr>
								<td>1</td>
								<td>07:15 - 10:15</td>
								<td>12</td>
								<td>143563</td>
								<td>IT4954</td>
								<td>Thiết kế và xây dựng phần mềm</td>
							</tr>
							<tr>
								<td>2</td>
								<td>13:15 - 15:00</td>
								<td>12</td>
								<td>53467</td>
								<td>IT1234</td>
								<td>Tương tác người máy</td>
							</tr>
						</table>
					</div>

					<div id="holDay" class="col-sm-12">
						<button type="button" class="btn btn-primary">Đăng ký nghỉ phép</button>
						
						<p id="txt-hol"><span class="glyphicon glyphicon-group"></span> Đơn xin nghỉ phép trong năm</p>
						<table class="table">
							<tr>
								<th>STT</th>
								<th>Thời gian</th>
								<th>Loại</th>
								<th>Lý do</th>
								<th>Trạng thái</th>
								<th>Action</th>
							</tr>
							<tr>
								<td>1</td>
								<td>15/3/2017 - 15/3/2017</td>
								<td>Buổi sáng</td>
								<td>Việc riêng</td>
								<td>NA</td>
								<td><button type="button" class="btn btn-success btn-xs">Cencle</button></td>
							</tr>
							<tr>
								<td>2</td>
								<td>18/3/2017 - 18/3/2017</td>
								<td>Cả ngày</td>
								<td>Đi công tác</td>
								<td>OK</td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>