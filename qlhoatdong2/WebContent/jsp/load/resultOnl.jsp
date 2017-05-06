<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:choose>
	<c:when test="${listOnl.size() != 0 }">
		<table class="table table-condensed table-bordered">
			<tr>
				<th>No.</th>
				<th>Thứ</th>
				<th>Thời gian</th>
				<th>Tuần</th>
				<th>Ngày bắt đầu tuần</th>
				<th>Ca trực</th>
			</tr>
			<c:set var="no" value="1" />
			<c:forEach items="${listOnl}" var="onl">
				<tr>
					<td>${no }</td>
					<td>${my:mapDayOfWeek(onl.dateOfWeek) }</td>
					<td>${my:getHour(onl.timeStart) }-${my:getHour(onl.timeEnd) }</td>
					<td>${onl.week.weekCount }</td>
					<td>${my:getDay(onl.week.startDate) }</td>
					<td>${my:mapType(onl.caTruc) }</td>
				</tr>
				<c:set var="no" value="${no + 1 }" />
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p class="text-danger small">Không có lịch trực!</p>
	</c:otherwise>
</c:choose>
