<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý sinh viên hướng dẫn</title>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$("#form_tiendo").submit(function(){
			var NoiDungTextArea = CKEDITOR.instances.noidung.getData();
			if(NoiDungTextArea == ""){
				$.bootstrapGrowl("Vui lòng nhập nội dung báo cáo!", {
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
				return false;
			}
			return true;
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
					<h4>
						Tiến độ sinh viên <small>Nguyễn Hữu Phương</small>
					</h4>
					<hr>
					<form class="form-horizontal" action="student_detail.htm" method="post" id="form_tiendo">
						<div class="form-group">
							<label class="control-label col-sm-2">Học kỳ:</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit">
									<option value="">${checkInStudent.week.hocKy.name }</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Tuần:</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit">
									<option value="">${checkInStudent.week.weekCount }</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Ngày bắt đầu tuần:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" readonly="readonly"
									value="${my:getDay(checkInStudent.week.startDate) }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<c:choose>
									<c:when test="${checkInStudent.status }">
										<div class="radio">
											<label><input type="radio" name="status"
												checked="checked" value="1"> Có mặt</label>
										</div>
										<div class="radio">
											<label><input type="radio" name="status" value="0">
												Vắng</label>
										</div>
									</c:when>
									<c:otherwise>
										<div class="radio">
											<label><input type="radio" name="status" value="1"> Có
												mặt</label>
										</div>
										<div class="radio">
											<label><input type="radio" name="status"
												checked="checked" value="0">Vắng</label>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Nội dung <sup class="red">*</sup>: </label>
							<div class="col-sm-10">
								<textarea rows="2" class="form-control" id="noidung" name="noidung">${checkInStudent.content }</textarea>
								<script>
									CKEDITOR.replace('noidung');
								</script>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Ghi chú: </label>
							<div class="col-sm-10">
								<textarea rows="2" class="form-control" id="ghichu" name="ghichu">${checkInStudent.other }</textarea>
								<script>
									CKEDITOR.replace('ghichu');
								</script>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<span class="small"><sup class="red">*</sup> : bắt buộc nhập</span>
							</div>
						</div>
						<input type="hidden" name="id" value="${checkInStudent.id }"> 
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" class="btn btn-success" value="OK" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>