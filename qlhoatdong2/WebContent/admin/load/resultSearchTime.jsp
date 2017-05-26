<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<div>
	<c:if test="${listUserNoneFree.size() != 0}">
		<p>Danh sách nhân viên có lịch trong khoảng thời gian
			${my:getHour(timeStart) } - ${my:getHour(timeEnd) } ngày
			${my:getDay(date)}:</p>
		<ul>
			<c:forEach items="${listUserNoneFree }" var="noneFree">
				<li>${noneFree }</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${listUserFree.size() != 0 }">
		<p>Danh sách nhân viên không có lịch trong khoảng thời gian
			${my:getHour(timeStart) } - ${my:getHour(timeEnd) } ngày
			${my:getDay(date)}:</p>
		<ul>
			<c:forEach items="${listUserFree }" var="free">
				<li>${free }</li>
			</c:forEach>
		</ul>
	</c:if>
</div>
