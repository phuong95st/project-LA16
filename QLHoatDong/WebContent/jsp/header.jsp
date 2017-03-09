<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<div class="container" id="banner">
	<div class="col-sm-2">
		<img class="img-thumbnail" alt="Logo" src="${url }/images/logo.jpg"
			width="100" height="200">
	</div>
	<div class="col-sm-10">
		<p id="title">Hệ thống quản lý hoạt động</p>
		<p id="title2">Bộ môn Kỹ thuật máy tính</p>
		<p style="color: #FFF; text-align: right">
			Xin chào <b>${sessionScope.user.name }</b> [<a href="" style="color: #FFF">Logout</a>]
		</p>
	</div>
</div>