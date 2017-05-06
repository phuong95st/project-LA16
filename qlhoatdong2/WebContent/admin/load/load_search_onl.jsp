<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:choose>
	<c:when test="${listOnl != null && listOnl.size() != 0 }">
		<p class="small text-success">Tìm được ${listOnl.size()} kết quả:
		</p>
		<div class="table-responsive">
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
				<c:set var="no" value="1" />
				<c:forEach items="${listOnl}" var="onl">
					<tr>
						<td><a href="onl.ad?action=edit&onlId=${onl.id }"><span class="glyphicon glyphicon-pencil"></span> ${no }</a></td>
						<td>${my:mapDayOfWeek(onl.dateOfWeek) }</td>
						<td>${my:getHour(onl.timeStart) }-${my:getHour(onl.timeEnd) }</td>
						<td>${onl.week.weekCount }</td>
						<td>${my:getDay(onl.week.startDate) }</td>
						<td>${onl.week.hocKy.name }</td>
						<td>${my:mapType(onl.caTruc) }</td>
						<td>${onl.user.name }</td>
						<td align="center"><img id="loader21" class="hidden small" alt="Loading..." src="${url }/images/loader.gif" width="10pt" height="10pt"><button class="btn btn-xs btn-danger" onclick="javascript: removeOnl(${onl.id });"><span class="glyphicon glyphicon-remove-sign"></span> Delete</button></td>
					</tr>
					<c:set var="no" value="${no + 1 }" />
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:when test="${listOnl != null && listOnl.size() == 0 }">
		<p class="text-danger small">Không có kết quả nào được tìm thấy!</p>
	</c:when>
	<c:otherwise>
		<p class="text-warning small">Vui lòng nhập điều kiện tìm kiếm!</p>
	</c:otherwise>
</c:choose>