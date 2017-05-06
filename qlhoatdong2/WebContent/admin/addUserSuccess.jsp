<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formImage").submit(function() {
			var image = $("input[name='image']").val();
			if (image == "") {
				$.bootstrapGrowl("Vui lòng chọn 1 ảnh đại diện.", {
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
				return false;
			}
			return true;
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
					<div align="center">
						<c:if test="${message != null }">
							<div class="alert alert-success">${message }</div>
							<br>
						</c:if>
						<c:if test="${messageErorr != null}">
							<div class="alert alert-danger">
								<span class="small">${messageErorr }</span>
							</div>
						</c:if>

						<form action="image.ad" method="post"
							enctype="multipart/form-data" id="formImage">
							<h4>
								Cập nhật ảnh đại diện cho nhân viên <i>${name }</i>
							</h4>
							<input type="file" name="image"><br> <br> <input
								type="hidden" name="userId" value="${userId }"> <input
								type="submit" value="Cập nhật" class="btn btn-success">
							<a class="btn btn-warning" href="info.ad">Hủy</a>
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>