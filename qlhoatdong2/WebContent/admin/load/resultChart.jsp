<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<body>
	<c:choose>
		<c:when test="${hour == null }">
			<p class="red small">Nhập thông tin thống kê!</p>
		</c:when>
		<c:otherwise>
			<p>Số giờ lên lớp của nhân viên <b>${requestScope.user.name }</b></p>
			<div class="table-responsive">
				<table class="table table-condensed table-bordered">
					<tr>
						<td>Tổng số giờ dạy dự kiến:</td>
						<td><b class="text-success">${allHour }</b> giờ</td>
					</tr>
					<tr>
						<td>Tổng số giờ đã dạy:<br>
							Trong đó:
							<table class="table table-condensed table-bordered table-responsive small">
								<tr>
									<td>Số giờ lên lớp muộn</td>
									<td><b class="text-danger">${lateHour }</b> giờ</td>
								</tr>
								<tr>
									<td>Chiếm tỉ lệ</td>
									<td><b class="text-danger">${tilemuon }%</b></td>
								</tr>
							</table>
						</td>
						<td><b class="text-success">${hour }</b> giờ</td>
					</tr>
					<tr>
						<td>Tỉ lệ dạy:</td>
						<td><b class="text-success">${tile }%</b></td>
					</tr>
				</table>
			</div>
			
		</c:otherwise>
	</c:choose>
</body>








