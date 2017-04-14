<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%= request.getContextPath() %>" var="url"></c:set>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lịch giảng dạy</title>
<jsp:include page="head.jsp"></jsp:include>
<script>
$(document).ready(function() {
	$("select[name='hocKy']").change(function(){
		$("#load4").removeClass("hidden");
		var hocKy = $("select[name='hocKy']").find(":selected").val();
		$.ajax({
			type : "POST",
			url : "viewTeach.htm",
			data : {
				"hocKy" : hocKy
			},
			success : function(data) {
				$("#table").html(data);
				$("#load4").addClass("hidden");
			},
			error : function() {
				alert("Không thể gửi dữ liệu.");
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
					<h3>Lich dạy</h3>
					<hr>
					<div>
						Học kỳ <select class="selectpicker" data-width="fit" name="hocKy">
							<c:forEach items="${listHocKy }" var="hocKy">
								<option value="${hocKy.name }">${hocKy.name }</option>
							</c:forEach>
						</select>
						<span class="hidden small" id="load4"><img alt="loading..." src="${url }/images/loader.gif"> Loading...</span>
					</div>
					<br>
					<div id="table">
						<jsp:include page="load/viewTeachLoad.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>