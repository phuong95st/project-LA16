<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:if
	test="${sessionScope.listTeach != null && sessionScope.listTeach.size() != 0}">
	<div style="clear: both" class="table-responsive">
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
				<th></th>
			</tr>
			<c:set value="1" var="no"></c:set>
			<c:forEach items="${sessionScope.listTeach }" var="teach">
				<tr>
					<td>${no }</td>
					<td>${my:mapDayOfWeek(teach.dateOfWeek) }</td>
					<td>${my:getHour(teach.timeStart) }-${my:getHour(teach.timeEnd) }</td>
					<td>${teach.phong.key }</td>
					<td>${teach.weekStart.weekCount }-${teach.weekEnd.weekCount }</td>
					<td>${teach.codeClass }</td>
					<td>${teach.codeSubject }</td>
					<td>${teach.name }</td>
					<td align="center"><button class="btn btn-primary btn-xs" onclick="javascript:removeItemTeach(${no});"><span class="small hidden" id="loader16"><img alt="Loading..." src="${url }/images/loader.gif" width="10pt" height="10pt"></span> Remove</button></td>
				</tr>
				<c:set value="${no + 1 }" var="no"></c:set>
			</c:forEach>
		</table>
	</div>
	<div>
		<button class="btn btn-info"
			style="margin-left: 10pt; margin-bottom: 10pt" onclick="javascript: insertTeach()"><span class="hidden" id="loader17"><img alt="Loading..." src="${url }/images/loader.gif"></span> Thêm</button>
		<a class="btn btn-danger"
			style="margin-left: 10pt; margin-bottom: 10pt"
			href="teach.ad?action=cencel">Hủy</a>
	</div>
</c:if>