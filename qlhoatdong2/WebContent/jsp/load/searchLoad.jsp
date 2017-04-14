<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:choose>
	<c:when test="${listTeachInfo.size() != 0 }">
		<span class="text-success small">Tìm thấy lịch giảng dạy:</span>
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
					<th>Trạng thái</th>
				</tr>
				<c:set value="1" var="no"></c:set>
				<c:forEach items="${listTeachInfo}" var="teachInfo">
					<tr>
						<td>${no }</td>
						<td>${my:mapDayOfWeek(teachInfo.teach.dateOfWeek) }</td>
						<td>${my:getHour(teachInfo.teach.timeStart) }-
							${my:getHour(teachInfo.teach.timeEnd) }</td>
						<td>${teachInfo.teach.phong.key }</td>
						<td>${teachInfo.teach.weekStart.weekCount }-
							${teachInfo.teach.weekEnd.weekCount }</td>
						<td>${teachInfo.teach.codeClass }</td>
						<td>${teachInfo.teach.codeSubject }</td>
						<td>${teachInfo.teach.name }</td>
						<td><c:choose>
								<c:when test="${teachInfo.hol }">
									<span class="text-danger">Bạn đã không dạy</span>
								</c:when>
								<c:when test="${late }">
									<span class="text-warning"> Bạn đã đến muộn <b>${teachInfo.lateMin}</b>
										phút. Lý do: ${teachInfo.reason }
									</span>
								</c:when>
								<c:otherwise>
									<span class="text-success">Bạn có dạy</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<c:set value="${no + 1 }" var="no"></c:set>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:when test="${lisScheStus.size() != 0 }">
		<span class="text-success small">Tìm thấy lịch hướng dẫn:</span>
		<div class="table-responsive">
			<table class="table table-condensed table-bordered">
				<tr>
					<th>No.</th>
					<th>Thứ</th>
					<th>Thời gian</th>
					<th>Phòng</th>
					<th>Tên sinh viên</th>
					<th>Loại sinh viên</th>
				</tr>
				<c:set var="no" value="1" />
				<c:forEach items="${lisScheStus}" var="scheStu">
					<tr>
						<td>${no }</td>
						<td>${my:mapDayOfWeek(scheStu.dateOfWeek) }</td>
						<td>${my:getHour(scheStu.start) }-${my:getHour(scheStu.end) }</td>
						<td>${scheStu.phong.key }</td>
						<td>${scheStu.studentName }</td>
						<td>${my:mappingTypeStudent(scheStu.type) }</td>
					</tr>
					<c:set var="no" value="${no + 1}"></c:set>
				</c:forEach>

			</table>
		</div>
	</c:when>
	<c:otherwise>
		<span class="text-danger small">Không có hoạt động nào của nhân
			viên <b>${sessionScope.user.name }</b> được tìm thấy.
		</span>
	</c:otherwise>
</c:choose>
