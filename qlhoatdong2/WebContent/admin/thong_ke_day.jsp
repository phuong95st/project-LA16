<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thống kê</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		$("select[name='hocKy']").change(function() {
			$("#loader23").removeClass("hidden");
			var hocKy = $(this).find(":selected").val();
			$.ajax({
				type : "GET",
				url : "thong_ke.ad",
				data : {
					"action" : "change_data",
					"hocKy" : hocKy
				},
				success : function(data) {
					$("#weekAdmin").html(data);
					$('.selectpicker').selectpicker("refresh");
					$("#loader23").addClass("hidden");
				},
				error : function() {
					alert("Không thể gửi dữ liệu");
					$("#loader23").addClass("hidden");
				}
			});
		});
		$("#thongkeAdmin").click(function() {
			$("#loader24").removeClass("hidden");
			var hocKy = $("select[name='hocKy']").find(":selected").val();
			var weekId = $("select[name='weekId']").find(":selected").val();
			var userId =  $("select[name='userId']").find(":selected").val();
			
			var flag = false;
			var message = "";
			if (hocKy == "none") {
				flag = true;
				message = "Vui lòng nhập học kỳ";	
			} else if(userId == "none"){
				flag = true;
				message = "Vui lòng chọn 1 nhân viên!";
			}
			
			if(flag){
				$.bootstrapGrowl(message, {
					ele : 'body', // which element to append
					// to
					type : 'danger', // (null, 'info',
					// 'danger', 'success')
					offset : {
						from : 'top',
						amount : 20
					}, // 'top', or 'bottom'
					align : 'center', // ('left', 'right', or
					// 'center')
					width : "auto", // (integer, or 'auto')
					delay : 5000, // Time while the message
					// will be displayed.
					// It's not equivalent to the *demo*
					// timeOut!
					allow_dismiss : true, // If true then will
					// display a cross
					// to close the
					// popup.
					stackup_spacing : 10
				// spacing between consecutively stacked growls.
				});
				$("#loader24").addClass("hidden");
			}else{
				$.ajax({
					type : "POST",
					url : "thong_ke.ad",
					data : {
						"hocKy" : hocKy,
						"weekId" : weekId,
						"userId": userId
					},
					success : function(data) {
						$("#resultChartAdmin").html(data);
						$("#loader24").addClass("hidden");
					},
					error : function() {
						alert("Không thể gửi dữ liệu");
						$("#loader24").addClass("hidden");
					}
				});
			}
		});
	});
</script>

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
					<h4>Thống kê dạy</h4>
					<hr>
					<div>
						<form class="form-inline">
							<div class="form-group">
								<span class="small hidden" id="loader23"><img
									alt="Loading..." src="${url }/images/loader.gif" width="10pt"
									height="10pt"> Loading...</span> <label for="email">Học
									kỳ</label> 
									<select class="selectpicker" data-width="fit" name="hocKy"">
									<option value="none">--choose--</option>
									<c:forEach items="${listHocKy }" var="hocKy">
										<option value="${hocKy.name }">${hocKy.name }</option>
									</c:forEach>
								</select>
								<!-- <label for="email">Tuần</label> <select class="selectpicker"
									data-width="fit">
									<option value="none">--choose--</option>
									<c:forEach items="${listWeek }" var="week">
										<option value="${week.weekId }">${week.weekCount }
											(${my:getDay(week.startDate) })</option>
									</c:forEach>
								</select> -->
							</div>
							<div class="form-group" id="weekAdmin">
								<jsp:include page="load/listWeek_thongke.jsp"></jsp:include>
							</div>
							<div class="form-group">
								<label for="email">Nhân viên</label> 
								<select class="selectpicker" data-width="fit" name="userId">
									<option value="none">--choose--</option>
									<c:forEach items="${listUser }" var="user">
										<option value="${user.userId }">${user.name }</option>
									</c:forEach>
								</select>
							</div>
							<button type="button" class="btn btn-success" id="thongkeAdmin"><span class="glyphicon glyphicon-stats"></span> Thống kê</button>
						</form>
						<hr>
						<b>Kết quả</b> <br>
						<span class="small hidden" id="loader24"><img alt="Loading..." src="${url }/images/loader.gif" width="10pt" height="10pt"> Loading...</span>
						<div id="resultChartAdmin">
							<jsp:include page="load/resultChart.jsp"></jsp:include>
						</div>
					</div>	
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>