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
				<li><a href="../admin/index.ad">Home <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'hoso' }">
				<li class="active"><a>Hồ sơ nhân viên</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="../admin/info.ad">Hồ sơ nhân viên <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'onl' }">
				<li class="active"><a>Quản lý giao ban</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="../admin/onl.ad">Quản lý giao ban <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'teach' }">
				<li class="active"><a>Quản lý lịch dạy</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="../admin/teach.ad">Quản lý lịch dạy <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'thongke' }">
				<li class="active"><a>Thống kê dạy</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="thong_ke.ad">Thống kê dạy <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${action == 'search' }">
				<li class="active"><a>Tìm kiếm</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="../admin/search.ad">Tìm kiếm <span
						class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
			<c:choose>
			<c:when test="${action == 'nam_hoc' }">
				<li class="active"><a>Quản lý năm học</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="../admin/nam_hoc.ad">Quản lý năm học <span class="glyphicon glyphicon-chevron-right small"></a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>