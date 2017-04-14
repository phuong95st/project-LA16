<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tìm kiếm</title>
<jsp:include page="head.jsp"></jsp:include>
<script>
	$(document).ready(
			function() {
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
				$("button[name='search']").click(
						function() {
							$("#load8").removeClass("hidden");
							$("#mess").addClass("hidden");

							var timeStart = $('.timepicker[name="timeStart"]')
									.val();
							var timeEnd = $('.timepicker[name="timeEnd"]')
									.val();
							var date = $(".datepicker").val();

							if (date == "") {
								$.bootstrapGrowl(
										"Hãy nhập thông tin tìm kiếm!", {
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
								$("#load8").addClass("hidden");
								$("#mess").removeClass("hidden");
							} else {
								var inputDate = new Date(formatDate(date))
										.getTime();
								var now = new Date();
								var now2 = new Date(now.getFullYear(), now
										.getMonth(), now.getDate()).getTime();
								if (inputDate >= now2) {
									$.bootstrapGrowl(
											"Hãy nhập thời gian trong quá khứ",
											{
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
									$("#load8").addClass("hidden");
									$("#mess").removeClass("hidden");
								} else if (timeStart == "" && timeEnd != "") {
									$.bootstrapGrowl(
											"Nhập chính xác thời gian!", {
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
									$("#load8").addClass("hidden");
									$("#mess").removeClass("hidden");
								} else if (timeStart != "" && timeEnd == "") {
									$.bootstrapGrowl(
											"Nhập chính xác thời gian!", {
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
									$("#load8").addClass("hidden");
									$("#mess").removeClass("hidden");
								} else {
									$.ajax({
										type : "POST",
										url : "search.htm",
										data : {
											"timeStart" : timeStart,
											"timeEnd" : timeEnd,
											"date" : date
										},
										success : function(data) {
											$("#resultSearch").html(data);
											$("#load8").addClass("hidden");
										},
										error : function() {
											alert("Không thể gửi dữ liệu!");
										}
									});
								}
							}
						});
				function formatDate(date) {
					var datearray = date.split("/");
					return datearray[1] + '/' + datearray[0] + '/'
							+ datearray[2];
				}
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
					<h3>Tìm kiếm</h3>
					<hr>
					<div>
						<p>Thông tin tìm kiếm</p>
						<form class="form-inline" id="formID">
							<div class="form-group">
								<div class="form-group">
									<label for="pwd">Thời gian: </label> <input type="text"
										name="timeStart" class="timepicker" value=""> đến <input
										type="text" name="timeEnd" class="timepicker" value="">,
								</div>
								<div class="form-group">
									<label for="pwd">Ngày <sup class="red">*</sup>:
									</label> <input type="text" name="date" class="datepicker"
										value="${my:getDay(now) }">
								</div>
							</div>
							<button type="button" class="btn btn-success" name="search">Tìm
								kiếm</button>
						</form>
					</div>
					<hr>
					<p>Kết quả tìm kiếm</p>
					<span class="small hidden" id="load8"><img alt="Loading..."
						src="${url }/images/loader.gif"> Loading...</span>
					<div id="resultSearch">
						<span class="text-danger small" id="mess">Vui lòng nhập
							điều kiện tiềm kiếm!</span>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>