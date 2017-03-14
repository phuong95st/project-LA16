<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Slide bar -->
<div class="col-sm-2 sidenav">
	<br>
	<p align="center" id="lbl-menu">MENU</p>
	<br>
	<ul class="nav nav-pills nav-stacked">
		<c:choose>
			<c:when test="${action == 'home' }">
				<li class="active"><a>Home</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="index.htm">Home</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="#">Xem lịch giảng dạy</a></li>
		<c:choose>
			<c:when test="${action == 'addHol' }">
				<li class="active"><a>Quản lý nghỉ phép</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="#">Quản lý nghỉ phép</a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="#">Xem sự kiện</a></li>
		<li><a href="#">Những ngày nghỉ</a></li>
		<li><a href="#">Các cuộc họp tham dự</a></li>
		<li><a href="#">Lịch trực bộ môn</a></li>
		<li><a href="#">Quản lý gặp sinh viên</a></li>
	</ul>
</div>