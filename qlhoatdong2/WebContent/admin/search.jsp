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
<script>
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
									<label for="pwd">Thời gian: </label> <input type="text"
										name="timeStart" class="timepicker" value=""> đến <input
										type="text" name="timeEnd" class="timepicker" value="">,
								</div>
							</div>
							<div class="form-group">
								<label for="pwd">Ngày<sup class="red">*</sup>: </label> <input type="text" name="date"
									class="datepicker" value="${my:getDay(now) }">
							</div>
							<div class="form-group">
								<label for="pwd">, Nhân viên: </label> <select
									class="selectpicker" data-width="fit" name="userId">
									<option value="none">--choose--</option>
									<c:forEach items="${listUser }" var="user">
										<option value="${user.userId }">${user.name }</option>
									</c:forEach>
								</select>
							</div>
							<button type="button" class="btn btn-success" name="search" onclick="javascript: searchAdmin()"><span class="glyphicon glyphicon-search"></span> Tìm
								kiếm</button>
						</form>
					</div>
					<hr>
					<p>Kết quả tìm kiếm</p>
					<span class="small hidden" id="load22"><img alt="Loading..."
						src="${url }/images/loader.gif"> Loading...</span>
					<div id="resultSearchAdmin">
						<span class="text-danger small" id="messAdmin">Vui lòng nhập
							điều kiện tiềm kiếm!</span>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>