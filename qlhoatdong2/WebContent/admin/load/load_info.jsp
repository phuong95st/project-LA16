<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>

<c:choose>
	<c:when test="${user.role }">
		<p>
			<span class="small hidden" id="loader12"><img alt="Loading..."
				src="${url }/images/loader.gif" width="10pt" height="10pt">
				Loading...</span> Hồ sơ của bạn:
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<span class="small hidden" id="loader12"><img alt="Loading..."
				src="${url }/images/loader.gif" width="10pt" height="10pt">
				Loading...</span> Hồ sơ của nhân viên <i>${user.name }</i>:
		</p>
	</c:otherwise>
</c:choose>
<div>
	<div class="col-sm-2"></div>
	<div class="col-sm-10">
		<a class="btn btn-success" href="info.ad?action=add"><span
			class="glyphicon glyphicon-plus"></span> Thêm</a>
	</div>
</div>
<br>
<br>
<br>
<div class="col-sm-2">
	<img class="img-responsive" src="${url }/${user.image}"
		alt="${user.name}" width="100" height="150">
</div>
<div class="col-sm-10 table-responsive">
	<table class="table table-condensed table-bordered">
		<tr>
			<th>Họ và tên</th>
			<td>${user.name}</td>
		</tr>
		<tr>
			<th>Ngày sinh</th>
			<td>${my:getDay(user.birthDay)}</td>
		</tr>
		<tr>
			<th>Giới tính</th>
			<td><c:choose>
					<c:when test="${user.sex }">
						<c:out value="Nam"></c:out>
					</c:when>
					<c:otherwise>
						<c:out value="Nữ"></c:out>
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<th>Quê quán</th>
			<td>${user.queQuan}</td>
		</tr>
		<tr>
			<th>Số CMT</th>
			<td>${user.cmt}</td>
		</tr>
		<tr>
			<th>Dân tộc</th>
			<td>${user.danToc}</td>
		</tr>
		<tr>
			<th>Chức danh</th>
			<td>${user.title}</td>
		</tr>
		<tr>
			<th>Địa chỉ nơi làm việc</th>
			<td>${user.office}</td>
		</tr>
		<tr>
			<th>Nơi ở hiện tại</th>
			<td>${user.addressNow}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${user.email}</td>
		</tr>
		<tr>
			<th>Tel</th>
			<td>${user.phone}</td>
		</tr>
		<tr>
			<th>Fax</th>
			<td>${user.fax}</td>
		</tr>
		<tr>
			<th>Tóm tắt quá trình công tác</th>
			<td>${user.congTac}</td>
		</tr>
		<tr>
			<th>Các môn giảng dạy</th>
			<td>${user.listSubject}</td>
		</tr>
		<tr>
			<th>Hướng nghiên cứu</th>
			<td>${user.research}</td>
		</tr>
		<tr>
			<th>Các công trình NC đã công bố</th>
			<td>${user.releasedEngine}</td>
		</tr>
		<tr>
			<th>Các sách đã xuất bản</th>
			<td>${user.releasedBook}</td>
		</tr>
		<tr>
			<th>Các thông tin khác (nếu có)</th>
			<td>${user.other}</td>
		</tr>
	</table>
	<a class="btn btn-info" href="info.ad?action=editInfo&userId=${user.userId}"><span class="glyphicon glyphicon-edit"></span> Cập nhật</a>
	<c:if test="${!user.role }">
		<button class="btn btn-warning" onclick="javascript: deleteUser(${user.userId})"> 
		<span class="hidden" id="loader14"><img alt="Loading..." src="${url }/images/loader.gif"></span> <span class="glyphicon glyphicon-remove"></span> Xóa
	</button>
	</c:if>
	<a class="btn btn-default" href="info.ad?action=editImage&userId=${user.userId}"><span class="glyphicon glyphicon-user"></span> Cập nhật ảnh
		<span class="glyphicon glyphicon-image"></span>
	</a>
	<br>
	<br>
</div>
