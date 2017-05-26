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
						<form class="form-inline">
							<div class="form-group">
								<label for="email">Chọn nhân viên:</label> <select
									class="selectpicker" data-width="fit" name="user">
									<option value="none">--choose--</option>
									<c:forEach items="${listUser }" var="user">
										<option value="${user.userId }">${user.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="pwd">Học kỳ:</label> <select class="selectpicker"
									data-width="fit" name="hocKy"
									onchange="javascript: changeHocKy();">
									<option value="none">--choose--</option>
									<c:forEach items="${listHocKy }" var="hocKy">
										<option value="${hocKy.name }">${hocKy.name }</option>
									</c:forEach>
								</select>
							</div>
							<div id="loadWeek" class="form-group">
								<jsp:include page="load/listWeek.jsp"></jsp:include>
							</div>
							<button type="button" class="btn btn-success"
								onclick="javascript: clickSearchOnl();">
								<span class="glyphicon glyphicon-search"></span> Tra cứu
							</button>
						</form>
						<span class="small hidden" id="loader15"><img
							alt="Loading..." src="${url }/images/loader.gif" width="10pt"
							height="10pt"> Loading...</span> 
						<br>
						<div>
							<form id="formAddOnl" onsubmit="javascript: return addOnl();" action="onl.ad" method="get">
								<input type="hidden" value="" name="user"> 
								<input type="hidden" value="" name="weekId"> 
								<input type="hidden" value="" name="hocKy"> 
								<input type="hidden" value="add" name="action">
								<button class="btn btn-info" type="submit">
									<span class="glyphicon glyphicon-plus"></span> Thêm lịch trực
								</button>
							</form>
							<br>
							<div id="searchOnl" >
								<jsp:include page="load/load_search_onl.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>