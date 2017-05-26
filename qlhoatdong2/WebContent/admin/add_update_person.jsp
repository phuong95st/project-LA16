<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hồ sơ nhân viên</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		$(".datepicker").datepicker({
			format : "dd/mm/yyyy"
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
						<h4>Thêm nhân viên</h4>

						<form class="form-horizontal" method="post" action="info.ad"
							onsubmit="javascript: return addUser();">
							<c:if test="${lisError != null}">
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="alert alert-danger alert-dismissable fade in">
											<a href="#" class="close" data-dismiss="alert"
												aria-label="close">&times;</a>
											<c:forEach items="${lisError }" var="error">
												<span class="small">${error }</span>
												<br>
											</c:forEach>
										</div>
									</div>
								</div>
							</c:if>


							<div class="form-group">
								<label class="control-label col-sm-2">Họ và tên <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="name"
										placeholder="Họ và tên" value="${requestScope.user.name }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Ngày sinh <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control datepicker" name="birth"
										placeholder="Ngày sinh"
										value="${my:getDay(requestScope.user.birthDay) }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Giới tính <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<c:choose>
										<c:when test="${requestScope.user == null }">
											<input type="radio" name="sex" value="nam" checked="checked"> Nam 
												<input type="radio" name="sex" value="nu"> Nữ
											</c:when>
										<c:when test="${requestScope.user.sex}">
											<input type="radio" name="sex" checked="checked" value="nam"> Nam 
												<input type="radio" name="sex" value="nu"> Nữ
											</c:when>
										<c:when test="${!requestScope.user.sex}">
											<input type="radio" name="sex" value="nam"> Nam 
												<input type="radio" name="sex" value="nu" checked="checked"> Nữ
											</c:when>
									</c:choose>

								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Quê quán <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="que"
										placeholder="Quê quán" value="${requestScope.user.queQuan }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Số CMT <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="cmt"
										placeholder="Số CMT nhân dân"
										value="${requestScope.user.cmt }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Dân tộc <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="dantoc"
										placeholder="Dân tộc" value="${requestScope.user.danToc }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Chức danh <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title"
										placeholder="Chức danh" value="${requestScope.user.title }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Địa chỉ làm việc<sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="lamviec"
										value="Phòng 502 nhà B1" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Nơi ở hiện tại<sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="hientai"
										placeholder="Nơi ở hiện tại"
										value="${requestScope.user.addressNow }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Email<sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="email"
										placeholder="email" value="${requestScope.user.email }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Tel<sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="tel"
										placeholder="Tel" value="${requestScope.user.phone }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Fax: </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="fax"
										placeholder="Fax" value="${requestScope.user.fax }">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Tiểu sử bản thân:
								</label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="tieusu"
										name="tieusu">${requestScope.user.congTac }</textarea>
									<script>
										CKEDITOR.replace('tieusu');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Các môn giảng dạy
								</label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="monday"
										name="monday">${requestScope.user.listSubject }</textarea>
									<script>
										CKEDITOR.replace('monday');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Hướng nghiên cứu:
								</label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="huongnc"
										name="huongnc">${requestScope.user.research }</textarea>
									<script>
										CKEDITOR.replace('huongnc');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Công trình đã công
									bố: </label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="congbo"
										name="congbo">${requestScope.user.releasedEngine }</textarea>
									<script>
										CKEDITOR.replace('congbo');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Sách đã xuất bản:
								</label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="xuatban"
										name="xuatban">${requestScope.user.releasedBook }</textarea>
									<script>
										CKEDITOR.replace('xuatban');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Thông tin khác: </label>
								<div class="col-sm-10">
									<textarea rows="2" class="form-control" id="other" name="other">${requestScope.user.other }</textarea>
									<script>
										CKEDITOR.replace('other');
									</script>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">Quyền hạn <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-10">
									<select class="selectpicker" data-width="fit" name="role">
										<c:choose>
											<c:when test="${requestScope.user.role }">
												<option value="0">Nhân viên</option>
												<option value="1" selected="selected">Quản trị</option>
											</c:when>
											<c:otherwise>
												<option value="0" selected="selected">Nhân viên</option>
												<option value="1">Quản trị</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<span class="small"><sup class="red">*</sup> : bắt buộc
										nhập</span>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="hidden" name="action" value="${event }"> 
									<c:if test='${event == "edit" }'>
										<input type="hidden" name="userId" value="${requestScope.user.userId }">
									</c:if>
									<c:choose>
										<c:when test='${event == "edit" }'>
											<input type="submit" class="btn btn-success disabled" value="OK" id="clickEdit"/> 
										</c:when>
										<c:otherwise>
											<input type="submit" class="btn btn-success" value="OK"/>
										</c:otherwise>
									</c:choose>
									
									<input type="reset" class="btn btn-info" value="Reset" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>