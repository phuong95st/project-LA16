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
				<li><a href="index.htm">Home <span class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="#">Xem lịch giảng dạy <span class="glyphicon glyphicon-chevron-right small"></span></a></li>
		<li><a href="#">Lịch trực bộ môn <span class="glyphicon glyphicon-chevron-right small"></a></li>
		<li><a href="#">Quản lý gặp sinh viên <span class="glyphicon glyphicon-chevron-right small"></a></li>
		<li><a href="#">Tìm kiếm <span class="glyphicon glyphicon-chevron-right small"></a></li>
		<c:choose>
			<c:when test="${action == 'my_position' }">
				<li class="active"><a>Vị trí của tôi</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="my_position.htm">Vị trí của tôi <span class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>