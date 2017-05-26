<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hoạt động</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$('.timepicker').timepicker({
			showMeridian : false,
			minuteStep : 1,
			defaultTime : false
		});
		$('.timepicker[name="timeStart"]').change(function() {
			$('.timepicker[name="timeEnd"]').val($(this).val());
		});
		$('.datepicker').datepicker({
			format : "dd/mm/yyyy",
			language : "vi"
		});
		$("button[name='search']").click(function() {
			$("#load18").removeClass("hidden");
			$("#messTime").addClass("hidden");

			var timeStart = $('.timepicker[name="timeStart"]').val();
			var timeEnd = $('.timepicker[name="timeEnd"]').val();
			var date = $('input[name="date"]').val();

			var flag = false;
			var message = "";
			if (timeStart == "" || timeEnd == "") {
				flag = true;
				message = "Vui lòng nhập thời gian";
			}else if(date == ""){
				flag = true;
				message = "Vui lòng nhập ngày cần tìm kiếm";
			}

			if (flag) {
				$.bootstrapGrowl(message, {
					ele : 'body', // which element to append to
					type : 'danger', // (null, 'info', 'danger', 'success')
					offset : {
						from : 'top',
						amount : 20
					}, // 'top', or 'bottom'
					align : 'center', // ('left', 'right', or 'center')
					width : "auto", // (integer, or 'auto')
					delay : 5000, // Time while the message will be displayed.
					// It's not equivalent to the *demo* timeOut!
					allow_dismiss : true, // If true then will display a cross to
					// close the popup.
					stackup_spacing : 10
				// spacing between consecutively stacked growls.
				});
				$("#load18").addClass("hidden");
				$("#messTime").removeClass("hidden");
			}else{
				$.ajax({
					type : "POST",
					url : "search.ad",
					data : {
						"action" : "searchTime",
						"timeStart" : timeStart,
						"timeEnd" : timeEnd,
						"date": date
					},
					success : function(data) {
						$("#resultSearchTime").html(data);
						$("#load18").addClass("hidden");
					},
					error : function() {
						alert("Lỗi hệ thống");
						$("#load18").addClass("hidden");
					}
				});
			}
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
					<h3>Tìm kiếm</h3>
					<hr>
					<div>
						<p>Thông tin tìm kiếm</p>
						<form class="form-inline" id="formID">
							<div class="form-group">
								<div class="form-group">
									<label for="pwd">Thời gian<sup class="red">*</sup>: </label> <input type="text"
										name="timeStart" class="timepicker" value=""> đến <input
										type="text" name="timeEnd" class="timepicker" value="">,
								</div>
							</div>
							<div class="form-group">
								<label for="pwd">Ngày<sup class="red">*</sup>: </label> <input type="text" name="date"
									class="datepicker" value="${my:getDay(now) }">
							</div>
							<button type="button" class="btn btn-success" name="search">
								<span class="glyphicon glyphicon-search"></span> Tìm kiếm
							</button>
						</form>
					</div>
					<hr>
					<p><b>Kết quả tìm kiếm</b></p>
					<span class="small hidden" id="load18"><img alt="Loading..."
						src="${url }/images/loader.gif"> Loading...</span>
					<div id="resultSearchTime">
						<span class="text-danger small" id="messTime">Vui lòng nhập
							điều kiện tiềm kiếm!</span>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>