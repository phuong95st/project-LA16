<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:choose>
	<c:when test="${ listTeach.size() == 0}">
		<p style="color: red">Không có lịch dạy</p>
	</c:when>
	<c:otherwise>
		<div class="table-responsive">
			<table class="table table-condensed table-bordered">
				<tr>
					<th>No.</th>
					<th>Thứ</th>
					<th>Thời gian</th>
					<th>Phòng học</th>
					<th>tuần học</th>
					<th>Mã lớp</th>
					<th>Mã môn</th>
					<th>Tên môn học</th>
				</tr>
				<c:set value="1" var="no"></c:set>
				<c:forEach items="${listTeach}" var="teach">
					<tr>
						<td>${no }</td>
						<td>${my:mapDayOfWeek(teach.dateOfWeek) }</td>
						<td>${my:getHour(teach.timeStart) } - ${my:getHour(teach.timeEnd) }</td>
						<td>${teach.phong.key }</td>
						<td>${teach.weekStart.weekCount } - ${teach.weekEnd.weekCount }</td>
						<td>${teach.codeClass }</td>
						<td>${teach.codeSubject }</td>
						<td>${teach.name }</td>
					</tr>
					<c:set value="${no + 1 }" var="no"></c:set>
				</c:forEach>
			</table>
		</div>
	</c:otherwise>
</c:choose>

