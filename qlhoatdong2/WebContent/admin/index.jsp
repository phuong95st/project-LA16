<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống quản lý hoạt động</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script
	src='//cdn.jsdelivr.net/jquery.marquee/1.4.0/jquery.marquee.min.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
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
		<jsp:include page="../jsp/header.jsp"></jsp:include>
		<!-- Bắt đầu row 2 -->
		<div class="container text-center">
			<div class="row content" id="content">
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<div class="col-sm-9">
						<p style="margin-top: 10pt;">
							Bạn là <b>quản trị viên!</b>
						</p>
					</div>
					<div class="col-sm-3">
						<script src="http://www.clocklink.com/embed.js"></script>

						<script type="text/javascript">
							obj = new Object;

							obj.clockfile = "0009-magenta.swf";
							obj.TimeZone = "ICT";

							obj.width = "auto";
							obj.height = "auto";

							obj.wmode = "transparent";
							showClock(obj);
						</script>
					</div>

					<div id="wellcome">Chúc bạn 1 ngày làm việc hiệu quả.</div>

				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>