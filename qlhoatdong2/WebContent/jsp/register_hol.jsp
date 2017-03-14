<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng ký ngày nghỉ</title>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$('.datepicker').datepicker({
			format : 'dd-mm-yyyy',
		});
		$("input[name='start']").change(function(){
			var value = $(this).val();
			$("input[name='end']").val(value);
		});
	});
</script>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<h3>Đăng ký nghỉ phép</h3>
					<hr>
					<p class="small hidden" id="loader3"><img alt="Loading"
						src="${url }/images/loader.gif"> Loading ...</p>
					<table class="table table-bordered"
						style="background-color: #F1F5F8;" id="table_register">
						<tr>
							<th>Ngày đăng ký nghỉ phép <sup class="red">(*)</sup></th>
							<th>Loại nghỉ <sup class="red">(*)</sup></th>
							<th>Lý do nghỉ <sup class="red">(*)</sup></th>
							<th></th>
						</tr>
						<tr>
							<%
								Timestamp timeDefault = (Timestamp) request.getAttribute("timeDefault");
								int type = (Integer) request.getAttribute("type");
							%>
							<td>Từ <input type="text" class="datepicker"
								value='<%=new SimpleDateFormat("dd-MM-yyyy").format(new Date(timeDefault.getTime()))%>' name="start"> đến 
								<input type="text" class="datepicker"
								value='<%=new SimpleDateFormat("dd-MM-yyyy").format(new Date(timeDefault.getTime()))%>'name="end">
							</td>
							<td><select class="selectpicker" data-width="fit"
								name="type">
									<option
										<%if (type == 1) out.print("selected='selected'");%>
										value="1">Buổi sáng</option>
									<option
										<%if (type == 2) out.print("selected='selected'");%>
										value="2">Buổi chiều</option>
									<option
										<%if (type == 3) out.print("selected='selected'");%>
										value="3">Cả ngày</option>
							</select></td>
							<td><input type="text" class="form-control"
								placeholder="Nhập lý do ..." name="reason"></td>
							<td>[<a onclick="javascript:addHol()"
								style="cursor: pointer;">Add</a>]
							</td>
						</tr>
						<tbody id="include">
							<jsp:include page="load/row_add_hol.jsp"></jsp:include>
						</tbody>	
					</table>
					<span id="btnRegister">
						<jsp:include page="load/btn_register.jsp"></jsp:include>
					</span>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>