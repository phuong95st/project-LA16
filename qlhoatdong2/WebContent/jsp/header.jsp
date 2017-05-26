<%@page import="utils.Common"%>
<%@page import="dao.impl.WeekDaoImpl"%>
<%@page import="entity.Week"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<%
	Week currentWeek = new WeekDaoImpl().getCurrentWeek(Common.getNow());
%>
<div class="container" id="banner">
	<div class="col-sm-2">
		<img class="img-thumbnail" alt="Logo" src="${url }/images/logo.jpg"
			width="100" height="200">
	</div>
	<div class="col-sm-10">
		<p id="title">Hệ thống quản lý hoạt động</p>
		
		<span id="title2">Bộ môn Kỹ thuật máy tính</span>
		<span class="small" style="float: right; color: white;">Học kỳ <%= currentWeek.getHocKy().getName() %>, tuần thứ <%= currentWeek.getWeekCount() %></span>
		<p style="color: #FFF; text-align: right">
			Xin chào <b>${sessionScope.user.name }</b> [<a href="../jsp/logout.do"
				style="color: #FFF">Logout</a>]
		</p>
	</div>
</div>