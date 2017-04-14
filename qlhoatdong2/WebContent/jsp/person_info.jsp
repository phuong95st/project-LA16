<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%= request.getContextPath() %>" var="url"></c:set> 
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông tin cá nhân</title>
<jsp:include page="head.jsp"></jsp:include>
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
					<h3>Thông tin cá nhân</h3>
					<hr>
					<c:set value="${requestScope.user }" var="user"></c:set>
					<div class="col-sm-2">
						<img class="img-responsive" src="${url }/${user.image}" alt="Bùi Quốc Anh" width="100" height="150">
					</div>
					<div class="col-sm-10 table-responsive">
						<table class="table table-condensed table-bordered">
							<tr>
								<th>Họ và tên</th>
								<td>${user.name}</td>
							</tr>
							<tr>
								<th>Ngày sinh</th>
								<td>${my:getDay(user.birthDay)}</td>
							</tr>
							<tr>
								<th>Giới tính</th>
								<td>
									<c:choose>
										<c:when test="${user.sex }">
											<c:out value="Nam"></c:out>
										</c:when>
										<c:otherwise>
											<c:out value="Nữ"></c:out>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th>Quê quán</th>
								<td>${user.queQuan}</td>
							</tr>
							<tr>
								<th>Số CMT</th>
								<td>${user.cmt}</td>
							</tr>
							<tr>
								<th>Dân tộc</th>
								<td>${user.danToc}</td>
							</tr>
							<tr>
								<th>Chức danh</th>
								<td>${user.title}</td>
							</tr>
							<tr>
								<th>Địa chỉ nơi làm việc</th>
								<td>${user.office}</td>
							</tr>
							<tr>
								<th>Nơi ở hiện tại</th>
								<td>${user.addressNow}</td>
							</tr>
							<tr>
								<th>Email</th>
								<td>${user.email}</td>
							</tr>
							<tr>
								<th>Tel</th>
								<td>${user.phone}</td>
							</tr>
							<tr>
								<th>Fax</th>
								<td>${user.fax}</td>
							</tr>
							<tr>
								<th>Tóm tắt quá trình công tác</th>
								<td>${user.congTac}</td>
							</tr>
							<tr>
								<th>Các môn giảng dạy</th>
								<td>
									${user.listSubject}
								</td>
							</tr>
							<tr>
								<th>Hướng nghiên cứu</th>
								<td>${user.research}</td>
							</tr>
							<tr>
								<th>Các công trình NC đã công bố</th>
								<td>${user.releasedEngine}</td>
							</tr>
							<tr>
								<th>Các sách đã xuất bản</th>
								<td>${user.releasedBook}</td>
							</tr>
							<tr>
								<th>Các thông tin khác (nếu có)</th>
								<td>
									${user.other}
								</td>
							</tr>
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