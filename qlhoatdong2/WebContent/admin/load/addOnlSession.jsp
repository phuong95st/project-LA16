<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:if
	test="${sessionScope.listOnl != null && sessionScope.listOnl.size() != 0}">
	<div style="clear: both" class="table-responsive ">
		<table class="table table-condensed table-bordered">
			<tr>
				<th>No.</th>
				<th>Thứ</th>
				<th>Thời gian</th>
				<th>Tuần</th>		
				<th>Ngày bắt đầu tuần</th>
				<th>Học kỳ</th>
				<th>Ca trực</th>
				<th>Nhân viên phụ trách</th>
				<th></th>
			</tr>
			<c:set value="1" var="no"></c:set>
			<c:forEach items="${sessionScope.listOnl }" var="onl">
				<tr>
					<td>${no }</td>
					<td>${my:mapDayOfWeek(onl.dateOfWeek) }</td>
					<td>${my:getHour(onl.timeStart) }-${my:getHour(onl.timeEnd) }</td>
					<td>${onl.week.weekCount }</td>
					<td>${my:getDay(onl.week.startDate) }</td>
					<td>${onl.week.hocKy.name }</td>
					<td>${my:mapType(onl.caTruc) }</td>
					<td>${onl.user.name }</td>
					<td align="center"><button class="btn btn-primary btn-xs" onclick="javascript:removeItemOnl(${no});"><span class="small hidden" id="loader16"><img alt="Loading..." src="${url }/images/loader.gif" width="10pt" height="10pt"></span> Remove</button></td>
				</tr>
				<c:set value="${no + 1 }" var="no"></c:set>
			</c:forEach>
		</table>
	</div>
	<div>
		<button class="btn btn-info"
			style="margin-left: 10pt; margin-bottom: 10pt" onclick="javascript: insertOnl()"><span class="hidden" id="loader20"><img alt="Loading..." src="${url }/images/loader.gif"></span> Thêm</button>
		<a class="btn btn-danger"
			style="margin-left: 10pt; margin-bottom: 10pt"
			href="onl.ad?action=cencel">Hủy</a>
	</div>
</c:if>