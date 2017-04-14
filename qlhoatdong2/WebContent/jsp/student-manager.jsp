<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý sinh viên hướng dẫn</title>
<jsp:include page="head.jsp"></jsp:include>
<script >
$(document).ready(function(){
	$('#formID').on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
		});
	$("button[name='tracuu']").click(function(){
		$("#load5").removeClass("hidden");
		$.ajax({
			type: "POST",
			url: "student_manager.htm",
			data: {
				"name" : $("input[name='name']").val(),
				"type" : $("select[name='type']").find(":selected").val(),
				"hocKy" : $("select[name='hocKy']").find(":selected").val(),
				"action" : "search"
			},
			success: function(data){
				$("#studentLoad").html(data);
				$("#load5").addClass("hidden");
			},
			error: function(){
				alert("Không thể gửi dữ liệu");
			}
		});
	});
});
</script>
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
					<h4>Quản lý sinh viên hướng dẫn</h4>
					<hr>
					<div class="col-sm-2">
						<span class="hidden small" id="load5"><img alt="Loading..." src="${url }/images/loader.gif"> Loading...</span>
					</div>
					<div class="col-sm-10">
						<form class="form-inline" id="formID">
							<div class="form-group">
								<div class="form-group">
									<label for="pwd">Tên</label> <input type="text" name="name">
								</div>
								<label for="email">Loại sinh viên</label> <select
									class="selectpicker" data-width="fit" name="type">
									<option value="1">Project 1</option>
									<option value="2">Project 2</option>
									<option value="3">Project 3</option>
									<option value="4">Đồ án tốt nghiệp</option>
								</select> <label for="email">Học kỳ</label> <select class="selectpicker"
									data-width="fit" name="hocKy">
									<c:forEach items="${listHocKy }" var="hocKy">
										<option value="${hocKy.name }">${hocKy.name }</option>
									</c:forEach>
								</select>
							</div>
							<button type="button" class="btn btn-success" name="tracuu">Tra cứu</button>
						</form>
					</div>
					<div style="clear: both">

						<a class="btn btn-info" href="student_manager.htm?action=add">Add</a>

						<br> <br>
						<div id="studentLoad">
							<jsp:include page="load/studentManagerLoad.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>