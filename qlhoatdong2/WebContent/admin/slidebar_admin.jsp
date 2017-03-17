<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%= request.getContextPath() %>" var="url"></c:set>
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
		<li><a href="#">Quản lý trực bộ môn</a></li>
		<c:choose>
			<c:when test="${action == 'teach' }">
				<li class="active"><a>Quản lý lịch dạy</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="teach.htm">Quản lý lịch dạy <span class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Thống kê <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="#">Submenu 1-1</a></li>
				<li><a href="#">Submenu 1-2</a></li>
				<li><a href="#">Submenu 1-3</a></li>
			</ul></li>
		<li><a href="#">Tìm kiếm</a></li>
	</ul>
</div>