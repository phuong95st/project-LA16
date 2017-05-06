<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>

<c:choose>
	<c:when test="${requestScope.user != null && hocKy != null}">
		<p class="small text-success">
			Lịch dạy của nhân viên <i>${user.name }</i> trong học kỳ <i>${hocKy }</i>
			như sau:
		</p>
	</c:when>
	<c:when test="${requestScope.user != null && hocKy == null }">
		<p class="small text-success">
			Lịch dạy của nhân viên <i>${requestScope.user.name }</i> trong tất cả học kỳ như
			sau:
		</p>
	</c:when>
	<c:when test="${requestScope.user == null && hocKy != null }">
		<p class="small text-success">
			Lịch dạy của tất cả nhân viên <i>${requestScope.user.name }</i> trong học kỳ <i>${hocKy }</i>
			như sau:
		</p>
	</c:when>
	<c:otherwise>
		<p class="small text-warning">Vui lòng nhập dữ liệu để tra cứu!</p>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${listTeach != null && listTeach.size() != 0}">
		<div class="table-responsive">
			<table class="table table-condensed table-bordered">
				<tr>
					<th>No.</th>
					<th>Thứ</th>
					<th>Thời gian</th>
					<th>Phòng học</th>
					<th>tuần học</th>
					<th>Học kỳ</th>
					<th>Mã lớp</th>
					<th>Mã môn</th>
					<th>Tên môn học</th>
					<th>Nhân viên phụ trách</th>
					<th>Action</th>
				</tr>
				<c:set var="no" value="1"></c:set>
				<c:forEach items="${listTeach }" var="teach">
					<tr>
						<td><a href="teach.ad?action=edit&teachId=${teach.teachId }&userId=${teach.user.userId }&hocKy=${teach.weekEnd.hocKy.name}"><span class="glyphicon glyphicon-pencil"></span> ${no }</a></td>
						<td>${my:mapDayOfWeek(teach.dateOfWeek) }</td>
						<td>${my:getHour(teach.timeStart)}-${my:getHour(teach.timeEnd)}</td>
						<td>${teach.phong.key }</td>
						<td>${teach.weekStart.weekCount }-${teach.weekEnd.weekCount }</td>
						<td>${teach.weekEnd.hocKy.name }</td>
						<td>${teach.codeClass }</td>
						<td>${teach.codeSubject }</td>
						<td>${teach.name }</td>
						<td>${teach.user.name }</td>
						<td><a href="teach.ad?action=delete&teachId=${teach.teachId }" onclick="return confirm('Bạn có chắc chắn muốn xóa?');"><span class="glyphicon glyphicon-remove-sign"></span> Del</a></td>
					</tr>
					<c:set var="no" value="${no +1 }"></c:set>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:when test="${listTeach != null && listTeach.size() == 0 }">
		<p class="small text-danger">Không có kết quả!</p>
	</c:when>
</c:choose>
