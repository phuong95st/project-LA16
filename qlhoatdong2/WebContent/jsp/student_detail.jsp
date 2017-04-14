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
<script type="text/javascript">
	function deleteScheStu(scheStuId){
		if(confirm("Việc này sẽ làm mất một số dữ liệu.\nBạn có chắc chăn xóa?")){
			$("#load7").removeClass("hidden");
			$.ajax({
				type: "POST",
				url: "student_manager.htm",
				data: {
					"scheStuId" : scheStuId,
					"action" : "del"
				},
				header : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				success: function(data){
					result = $.parseJSON(data);
					if(result['status']){
						alert(result['data']);
						window.location.href = 'student_manager.htm';
					}else{
						$("#load7").addClass("hidden");
						alert(result['data']);
					}
				},
				error: function(){
					alert("Không thể gửi dữ liệu")
				}
			});
		}
	}
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
					<div class="table-responsive">
						<p>Thông tin sinh viên </p>
						<table class="table table-condensed table-bordered">
							<tr>
								<th>Tên sinh viên</th>
								<td>${scheStu.studentName }</td>
							</tr>
							<tr>
								<th>loại sinh viên</th>
								<td>${my:mappingTypeStudent(scheStu.type) }</td>
							</tr>
							<tr>
								<th>Đề tài</th>
								<td>${scheStu.topic }</td>
							</tr>
							<tr>
								<th>Thời gian gặp</th>
								<td>${my:mapDayOfWeek(scheStu.dateOfWeek) }, ${my:getHour(scheStu.start) } - ${my:getHour(scheStu.end) }</td>
							</tr>
							<tr>
								<th>Tuần gặp</th>
								<td>${scheStu.wStart.weekCount } - ${scheStu.wEnd.weekCount }</td>
							</tr>
							<tr>
								<th>Học kỳ</th>
								<td>${scheStu.wEnd.hocKy.name }</td>
							</tr>
							<tr>
								<th>Phòng</th>
								<td>${scheStu.phong.key }</td>
							</tr>
						</table>
						<p class="hidden small" id="load7"><img alt="Loading..." src="${url }/images/loader.gif"> Loading...</p>
						<a type="button" class="btn btn-info btn-sm" href="student_manager.htm?action=edit&scheStuId=${scheStu.id }">Edit</a>
						<button type="button" class="btn btn-warning btn-sm" name="delete" onclick="deleteScheStu(${scheStu.id});">Delete</button>
					</div>
					<hr>
					<div class="table-responsive">
						<p>Danh sách tiến độ</p>
						<table class="table table-condensed table-bordered">
							<tr>
								<th>Tuần</th>
								<th>Ngày bắt đầu tuần</th>
								<th>Điểm danh</th>
								<th>Tiến độ</th>
								<th>Ghi chú</th>
							</tr>
							<c:forEach items="${listCheck }" var="check">
								<tr>
									<td><a href="student_detail.htm?action=edit&checkId=${check.id }">${check.week.weekCount }</a></td>
									<td>${my:getDay(check.week.startDate) }</td>
									<td>
										<c:choose>
											<c:when test="${check.status }">
												<c:out value="Có"></c:out>
											</c:when>
											<c:otherwise>
												<c:out value="Không"></c:out>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${check.content }</td>
									<td>${check.other }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>