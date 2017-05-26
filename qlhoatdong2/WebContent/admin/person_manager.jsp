<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hồ sơ nhân viên</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script>
	$(document).ready(function(){
		$("select[name='user']").change(function(){
			$("#loader12").removeClass("hidden");
			var userId = $(this).find(":selected").val();
			
			$.ajax({
				type: "POST",
				url: "info.ad",
				data:{
					"action" : "load_info",
					"userId" : userId
				},
				success: function(data){
					$("#load_info").html(data);
					$("#loader12").addClass("hidden");
				},
				error: function(){
					alert("System Error!");
					$("#loader12").addClass("hidden");
				}
			});
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
					<div>
						<p style="margin-top: 10pt;">
							Bạn là <b>quản trị viên!</b>
						</p>
					</div>
					<hr>
					<div>
						<span>Chọn nhân viên: </span> <select class="selectpicker"
							data-width="fit" name="user">
							<option value="none">--choose--</option>
							<c:forEach items="${lisUser }" var="user">
								<option value="${user.userId }">${user.name }</option>
							</c:forEach>
						</select>
					</div>
					<hr>
					<div id="load_info">
						<jsp:include page="load/load_info.jsp"></jsp:include>	
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>