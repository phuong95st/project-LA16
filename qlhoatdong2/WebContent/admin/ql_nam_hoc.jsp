<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hoạt động</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$('.datepicker').datepicker({
			format: "dd/mm/yyyy"
		});
		
		$("#show_hide").hide();
		$("#addNam").click(function(){
			$("#show_hide").slideToggle();
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
						<div>
							<button class="btn btn-success" id="addNam">
								<span class="glyphicon glyphicon-plus"></span> Thêm năm học
							</button>
							
							<div class="" style="margin-left: 15pt" id="show_hide">
								<div class="col-sm-offset-3" id="listErr" >
								
								</div>
								<form class="form-horizontal">
									<div class="form-group">
										<span class="control-label col-sm-3">Năm học <sup class="red">*</sup>:</span>
										<div class="col-sm-4">
											<input type="email" class="form-control" placeholder="Ví dụ: 2016-2017" name="namHoc">
										</div>
									</div>
									<div class="form-group">
										<span class="control-label col-sm-3">Ngày bắt đầu tuần đầu tiên <sup class="red">*</sup>:</span>
										<div class="col-sm-4">
											<input type="text" class="form-control datepicker" name="startDate">
										</div>
									</div>
									<div class="form-group">
										<span class="control-label col-sm-3">Số tuẩn của học kỳ 1: <sup class="red">*</sup>:</span>
										<div class="col-sm-4">
											<input type="number" class="form-control" name="total1">
										</div>
									</div>
									<div class="form-group">
										<span class="control-label col-sm-3">Số tuẩn của học kỳ 2: <sup class="red">*</sup>:</span>
										<div class="col-sm-4">
											<input type="number" class="form-control" name="total2">
										</div>
									</div>
									<div class="form-group">
										<span class="control-label col-sm-3">Số tuẩn của học kỳ 3: <sup class="red">*</sup>:</span>
										<div class="col-sm-4">
											<input type="number" class="form-control" name="total3">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-10">
											<button type="button" class="btn btn-info" onclick="javascript: addNamHoc();"><span class="glyphicon glyphicon-plus-sign"></span> Thêm</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<h5>Danh sách năm học:</h5>
						<span class="hidden small" id="load24"><img alt="Loading..." src="${url }/images/loader.gif" width="10pt" height="10pt"> Loading...</span>
						<table style="width: 100%; margin-left: 15pt">
							<c:set value="1" var="no"></c:set>
							<c:forEach items="${listNamHoc }" var="namHoc">
								<tr>
									<td><a href="nam_hoc.ad?action=view&namHoc=${namHoc }"><span class="glyphicon glyphicon-eye-open"></span> ${no }</a></td>
									<td>${namHoc }</td>
								</tr>
								<c:set value="${no + 1 }" var="no"></c:set>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>