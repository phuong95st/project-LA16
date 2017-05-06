<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lịch trực</title>
<jsp:include page="head.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		$("select[name='hocKy']").change(function() {
			$("#loader10").removeClass("hidden");
			var hocKy = $(this).find(":selected").val();

			$.ajax({
				type : "GET",
				url : "viewOnl.htm",
				data : {
					"hocKy" : hocKy
				},
				success : function(data) {
					$("#listWeek").html(data);
					$('.selectpicker').selectpicker("refresh");
					$("#loader10").addClass("hidden");
				},
				error : function() {
					alert("Không thể gửi dữ liệu");
					$("#loader10").addClass("hidden");
				}
			});
		});
		$("#onlClick").click(function(){
			$("#loader10").removeClass("hidden");
			var hocKy = $("select[name='hocKy']").find(":selected").val();
			var weekId = $("select[name='weekId']").find(":selected").val();
			
			$.ajax({
				type: "POST",
				url: "viewOnl.htm",
				data: {
					"weekId" : weekId
				},
				success: function(data){
					$("#resultOnl").html(data);
					$("#loader10").addClass("hidden");
				},
				error : function() {
					alert("Không thể gửi dữ liệu");
					$("#loader10").addClass("hidden");
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
					<h3>Lịch trực của bạn</h3>
					<hr>
					<div class="col-sm-5">
						<span class="hidden" id="loader10"><img alt="Loading..."
							src="${url }/images/loader.gif"> Loading...</span>
					</div>
					<div class="col-sm-7">
						<form class="form-inline">
							<div class="form-group">
								<label for="email">Học kỳ:</label> <select class="selectpicker"
									data-width="fit" name="hocKy">
									<c:forEach items="${listHocKy }" var="hocKy">
										<c:choose>
											<c:when test="${hocKy.name == currentHocKy }">
												<option value="${hocKy.name }" selected="selected">${hocKy.name }</option>
											</c:when>
											<c:otherwise>
												<option value="${hocKy.name }">${hocKy.name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="form-group" id="listWeek">
								<jsp:include page="load/listWeek.jsp"></jsp:include>
							</div>
							<button type="button" class="btn btn-success" id="onlClick"><span class="glyphicon glyphicon-search"></span> Tra cứu</button>
						</form>
					</div>
					<br> <br>
					<hr>
					<p>Kết quả:</p>
					<div class="table-responsive" style="clear: both">
						<div id="resultOnl">
							<jsp:include page="load/resultOnl.jsp"></jsp:include>
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