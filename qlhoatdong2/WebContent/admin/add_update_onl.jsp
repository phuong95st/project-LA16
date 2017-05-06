<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý hoạt động</title>
<jsp:include page="../jsp/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".timepicker").timepicker({
			format : "dd/mm/yyyy",
			showMeridian : false
		});
		$("#timeStart").change(function() {
			$("#timeEnd").val($(this).val());
		});
		$("#reset").click(function() {
			$(".selectpicker").val('default');
			$(".selectpicker").selectpicker("refresh");
		});
	});
</script>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<jsp:include page="../jsp/header.jsp"></jsp:include>
		<div class="container text-center">
			<div class="row content" id="content">
				<!-- slidebar -->
				<jsp:include page="slidebar_admin.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<c:choose>
						<c:when test="${event != 'edit' }">
							<h3>
								Thêm lịch trực<small> ${requestScope.user.name }</small>, tuần <small>${requestScope.week.weekCount }</small>,
								học kỳ <small>${hocKy }</small>
							</h3>
						</c:when>
						<c:otherwise>
							<h3>
								Cập nhật thông tin lịch trực <small>${onl.user.name }</small>,
								học kỳ <small>${onl.week.hocKy.name  }</small>
							</h3>
						</c:otherwise>
					</c:choose>
					<hr>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2"></label>
							<div class="col-sm-10">
								<p id="error2"></p>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="email">Thời
								gian bắt đầu <sup class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${event != 'edit' }">
										<input type="email" class="form-control timepicker"
											id="timeStart" placeholder="Thời gian bắt đầu">
									</c:when>
									<c:otherwise>
										<input type="email" class="form-control timepicker"
											id="timeStart" placeholder="Thời gian bắt đầu"
											value="${my:getHour(onl.timeStart) }">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Thời gian
								kết thúc <sup class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${event != 'edit' }">
										<input type="text" class="form-control timepicker"
											id="timeEnd" placeholder="Thời gian kết thúc">
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control timepicker"
											id="timeEnd" placeholder="Thời gian kết thúc"
											value="${my:getHour(onl.timeEnd) }">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2"> Thứ <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit" name="dateOfweek">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<option value="2">T2</option>
											<option value="3">T3</option>
											<option value="4">T4</option>
											<option value="5">T5</option>
											<option value="6">T6</option>
											<option value="7">T7</option>
											<option value="1">CN</option>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 2 }">
													<option value="2" selected="selected">T2</option>
												</c:when>
												<c:otherwise>
													<option value="2">T2</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 3 }">
													<option value="3" selected="selected">T3</option>
												</c:when>
												<c:otherwise>
													<option value="3">T3</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 4 }">
													<option value="4" selected="selected">T4</option>
												</c:when>
												<c:otherwise>
													<option value="4">T4</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 5 }">
													<option value="5" selected="selected">T5</option>
												</c:when>
												<c:otherwise>
													<option value="5">T5</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 6 }">
													<option value="6" selected="selected">T6</option>
												</c:when>
												<c:otherwise>
													<option value="6">T6</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 7 }">
													<option value="7" selected="selected">T7</option>
												</c:when>
												<c:otherwise>
													<option value="7">T7</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.dateOfWeek == 1 }">
													<option value="1" selected="selected">CN</option>
												</c:when>
												<c:otherwise>
													<option value="1">CN</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Ca trực <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit" name="caTruc">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<option value="1">Sáng</option>
											<option value="2">Chiều</option>
											<option value="3">Cả ngày</option>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${onl.caTruc == 1 }">
													<option value="1" selected="selected">Sáng</option>
												</c:when>
												<c:otherwise>
													<option value="1">Sáng</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.caTruc == 2 }">
													<option value="2" selected="selected">Chiều</option>
												</c:when>
												<c:otherwise>
													<option value="2">Chiều</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${onl.caTruc == 3 }">
													<option value="3" selected="selected">Cả ngày</option>
												</c:when>
												<c:otherwise>
													<option value="3">Cả ngày</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>

						<c:if test="${event != 'edit' }">
							<input type="hidden" name="userId"
								value="${requestScope.user.userId }">
							<input type="hidden" name="weekId"
								value="${requestScope.week.weekId }">
						</c:if>


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<span class="hidden" id="loader16"><img alt="Loading..."
									src="${url }/images/loader.gif"> </span>
								<c:choose>
									<c:when test="${event != 'edit' }">
										<button type="button" class="btn btn-success"
											onclick="javascript: addOnlToSession();">Add</button>
										<button type="reset" class="btn btn-warning" id="reset">Reset</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-success"
											onclick="javascript: updateOnl(${onl.id});">Update</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</form>
					<br>
					<div id="addOnlSession">
						<jsp:include page="load/addOnlSession.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>