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
				<li><a href="index.htm">Home <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'info' }">
				<li class="active"><a>Thông tin cá nhân</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="info.htm">Thông tin cá nhân <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'viewTeach' }">
				<li class="active"><a>Xem lịch dạy</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="viewTeach.htm">Xem lịch dạy <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'viewOnl' }">
				<li class="active"><a>Lịch trực bộ môn</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="onl.htm">Lịch trực bộ môn <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'studentManager' }">
				<li class="active"><a>Hoạt động hướng dẫn</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="student_manager.htm">Hoạt động hướng dẫn <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'thongke' }">
				<li class="active"><a>Thống kê dạy</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="thong_ke.htm">Thống kê dạy <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'my_position' }">
				<li class="active"><a>Vị trí của tôi</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="my_position.htm">Vị trí của tôi <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'search' }">
				<li class="active"><a>Lịch sử hoạt động</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="search.htm">Lịch sử hoạt động <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>