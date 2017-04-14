<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<c:choose>
	<c:when test="${lisScheStus.size() == 0 }">
		<p style="color: red">Không có dữ liệu</p>
	</c:when>
	<c:otherwise>
		<div class="table-responsive">
			<table class="table table-condensed table-bordered">
				<tr>
					<th>No.</th>
					<th>Thứ</th>
					<th>Thời gian</th>
					<th>Tuần</th>
					<th>Phòng</th>
					<th>Tên sinh viên</th>
					<th>Loại sinh viên</th>
				</tr>
				<c:set value="1" var="no"></c:set>
				<c:forEach items="${lisScheStus }" var="scheStu">
					<tr>
						<td><a href="student_detail.htm?action=view&scheStuId=${scheStu.id }">${no }</a></td>
						<td>${my:mapDayOfWeek(scheStu.dateOfWeek) }</td>
						<td>${my:getHour(scheStu.start) } - ${my:getHour(scheStu.end) }</td>
						<td>${scheStu.wStart.weekCount } - ${scheStu.wEnd.weekCount }</td>
						<td>${scheStu.phong.key }</td>
						<td>${scheStu.studentName }</td>
						<td>${my:mappingTypeStudent(scheStu.type) }</td>
					</tr>
					<c:set value="${no + 1 }" var="no"></c:set>
				</c:forEach>
			</table>
		</div>
	</c:otherwise>
</c:choose>
