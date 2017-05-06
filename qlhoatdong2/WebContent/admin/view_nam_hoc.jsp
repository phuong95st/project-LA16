<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hoạt động</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="../jsp/header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<div>
						<p style="margin-top: 10pt;">
							Bạn là <b>quản trị viên!</b>
						</p>
					</div>
					<hr>
					<h4>Lịch năm học ${namHoc} </h4>
					<div class="table-responsive">
						<table class="table table-bordered table-condensed table-striped">
							<tr>
								<th>Tuần thứ</th>
								<th>Ngày đầu tuần</th>
								<th>Học kỳ</th>
								<th>Năm học</th>
							</tr>
							<c:forEach items="${listWeek }" var="week">
								<tr>
									<td>${week.weekCount }</td>
									<td>${my:getDay(week.startDate) }</td>
									<td>${week.hocKy.name }</td>
									<td>${week.hocKy.namHoc }</td>
								</tr>
							</c:forEach>
						</table>	
					</div>
					<button class="btn btn-success" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left"></span> Back</button>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>