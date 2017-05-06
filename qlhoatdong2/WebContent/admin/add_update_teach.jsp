<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quản lý lịch giảng</title>
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
								Thêm lịch giảng <small> ${requestScope.user.name }</small>, học
								kỳ <small>${hocKy }</small>
							</h3>
						</c:when>
						<c:otherwise>
							<h3>
								Cập nhật thông tin lịch dạy <small> ${requestScope.user.name }</small>, học
								kỳ <small>${hocKy }</small>
							</h3>
						</c:otherwise>
					</c:choose>
					<hr>
					<div class="col-sm-1"></div>
					<div class="col-sm-7">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="control-label col-sm-3"></label>
								<div class="col-sm-9">
									<p id="error"></p>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-3" for="email">Thời
									gian bắt đầu <sup class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<input type="email" class="form-control timepicker"
												id="timeStart" placeholder="Thời gian bắt đầu">
										</c:when>
										<c:otherwise>
											<input type="email" class="form-control timepicker"
												id="timeStart" placeholder="Thời gian bắt đầu"
												value="${my:getHour(teach.timeStart) }">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="pwd">Thời
									gian kết thúc <sup class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<input type="text" class="form-control timepicker"
												id="timeEnd" placeholder="Thời gian kết thúc">
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control timepicker"
												id="timeEnd" placeholder="Thời gian kết thúc"
												value="${my:getHour(teach.timeEnd) }">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"> Thứ <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-9">
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
													<c:when test="${teach.dateOfWeek == 2 }">
														<option value="2" selected="selected">T2</option>
													</c:when>
													<c:otherwise>
														<option value="2">T2</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 3 }">
														<option value="3" selected="selected">T3</option>
													</c:when>
													<c:otherwise>
														<option value="3">T3</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 4 }">
														<option value="4" selected="selected">T4</option>
													</c:when>
													<c:otherwise>
														<option value="4">T4</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 5 }">
														<option value="5" selected="selected">T5</option>
													</c:when>
													<c:otherwise>
														<option value="5">T5</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 6 }">
														<option value="6" selected="selected">T6</option>
													</c:when>
													<c:otherwise>
														<option value="6">T6</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 7 }">
														<option value="7" selected="selected">T7</option>
													</c:when>
													<c:otherwise>
														<option value="7">T7</option>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${teach.dateOfWeek == 1 }">
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
								<label class="control-label col-sm-3" for="pwd">Tuần học
									<sup class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<select class="selectpicker" data-width="fit" name="wStartId">
										<c:choose>
											<c:when test="${event != 'edit' }">
												<c:forEach items="${lisWeeks }" var="week">
													<option value="${week.weekId }">${week.weekCount }
														(${my:getDay(week.startDate) })</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<option value="">${teach.weekStart.weekCount }
													(${my:getDay(teach.weekStart.startDate) })</option>
											</c:otherwise>
										</c:choose>

									</select> đến <select class="selectpicker" data-width="fit"
										name="wEndId">
										<c:choose>
											<c:when test="${event != 'edit' }">
												<c:forEach items="${lisWeeks }" var="week">
													<option value="${week.weekId }">${week.weekCount }
														(${my:getDay(week.startDate) })</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<option value="">${teach.weekEnd.weekCount }
													(${my:getDay(teach.weekEnd.startDate) })</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3"> Phòng <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<select class="selectpicker" data-width="fit" name="phong">
										<c:choose>
											<c:when test="${event != 'edit' }">
												<c:forEach items="${lisPositions }" var="position">
													<option value="${position.key }">${position.key }</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<option value="${teach.phong.key }">${teach.phong.key }</option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="pwd">Mã lớp <sup
									class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<input type="text" class="form-control" id="codeClass"
												placeholder="Mã lớp">
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="codeClass"
												placeholder="Mã lớp" value="${teach.codeClass }">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="pwd">Mã môn
									học <sup class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<input type="text" class="form-control" id="codeSubject"
												placeholder="Mã môn học">
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="codeSubject"
												placeholder="Mã môn học" value="${teach.codeSubject }">
										</c:otherwise>
									</c:choose>

								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-3" for="pwd">Tên môn
									học <sup class="red">*</sup>:
								</label>
								<div class="col-sm-9">
									<c:choose>
										<c:when test="${event != 'edit' }">
											<input type="text" class="form-control" id="name"
												placeholder="Tên môn học">
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="name"
												placeholder="Tên môn học" value="${teach.name }">
										</c:otherwise>
									</c:choose>
								</div>
							</div>

							<input type="hidden" name="userId"
								value="${requestScope.user.userId }">

							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<span class="hidden" id="loader15"><img alt="Loading..."
										src="${url }/images/loader.gif"> </span>
									<c:choose>
										<c:when test="${event != 'edit' }">
											<button type="button" class="btn btn-success"
												onclick="javascript: addTeachToSession();">Add</button>
											<button type="reset" class="btn btn-warning" id="reset">Reset</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn btn-success"
												onclick="javascript: updateTeach(${teach.teachId});">Update</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</form>
						<div class="col-sm-3"></div>
						<br>
					</div>
					<div id="addTeachSession">
						<jsp:include page="load/addTeachSession.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="../jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>