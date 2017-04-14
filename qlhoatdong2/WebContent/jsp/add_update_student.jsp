<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý sinh viên hướng dẫn</title>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('.timepicker2').timepicker({
					showMeridian : false,
					minuteStep : 1
				});
				$('.timepicker2[name="timeStart"]').change(function() {
					$('.timepicker2[name="timeEnd"]').val($(this).val());
				});
				$("select[name='hocKy']").change(function() {
					$("#load6").removeClass("hidden");
					var hocKy = $(this).find(":selected").val();
					$.ajax({
						type : "GET",
						url : "student_manager.htm",
						data : {
							"action" : "add",
							"hocKy" : hocKy
						},
						success : function(data) {
							$(".tuan").html(data);
							$('.selectpicker').selectpicker("refresh");
							$("#load6").addClass("hidden");
						}
					});
				});
				$("#fAddStudent").submit(
						function() {
							if ($("input[name='studentName']").val() == "") {
								$.bootstrapGrowl("Nhập tên sinh viên.", {
									ele : 'body', // which element to append to
									type : 'danger', // (null, 'info', 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message will be displayed.
									// It's not equivalent to the *demo* timeOut!
									allow_dismiss : true, // If true then will display a cross to
									// close the popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
								return false;
							} else if ($("input[name='topic']").val() == "") {
								$.bootstrapGrowl("Nhập đề tài.", {
									ele : 'body', // which element to append to
									type : 'danger', // (null, 'info', 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message will be displayed.
									// It's not equivalent to the *demo* timeOut!
									allow_dismiss : true, // If true then will display a cross to
									// close the popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
								return false;
							} else if ($(".timepicker2").val() == "") {
								$.bootstrapGrowl("Nhập thời gian gặp.", {
									ele : 'body', // which element to append to
									type : 'danger', // (null, 'info', 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message will be displayed.
									// It's not equivalent to the *demo* timeOut!
									allow_dismiss : true, // If true then will display a cross to
									// close the popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
								return false;
							} else if (($('select[name="wEndId"]').find(
									":selected").val() - $(
									'select[name="wStartId"]')
									.find(":selected").val()) < 5 && $('input[name="action"]').val() == "add") {
								$.bootstrapGrowl("Tuần gặp không chính xác.", {
									ele : 'body', // which element to append to
									type : 'danger', // (null, 'info', 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message will be displayed.
									// It's not equivalent to the *demo* timeOut!
									allow_dismiss : true, // If true then will display a cross to
									// close the popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
								return false;
							} else {
								var timeStart = parseDate($('input[name="timeStart"]').val()).getTime();
								var timeEnd = parseDate($('input[name="timeEnd"]').val()).getTime();
								if (timeStart >= timeEnd) {
									$.bootstrapGrowl(
											"Thời gian gặp không chính xác", {
												ele : 'body', // which element to append to
												type : 'danger', // (null, 'info', 'danger', 'success')
												offset : {
													from : 'top',
													amount : 20
												}, // 'top', or 'bottom'
												align : 'center', // ('left', 'right', or 'center')
												width : "auto", // (integer, or 'auto')
												delay : 5000, // Time while the message will be displayed.
												// It's not equivalent to the *demo* timeOut!
												allow_dismiss : true, // If true then will display a cross to
												// close the popup.
												stackup_spacing : 10
											// spacing between consecutively stacked growls.
											});
									return false;
								}
							}
						});

				// Chuyển chuỗi kí tự (string) sang đối tượng Date()
				function parseDate(str) {
					var mdy = str.split(':');
					return new Date(0, 0, 0, mdy[0], mdy[1], 0);
				}
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
					<c:choose>
						<c:when test="${flag == null }">
							<h3>Thêm sinh viên</h3>
						</c:when>
						<c:otherwise>
							<h3>Cập nhật sinh viên</h3>
						</c:otherwise>
					</c:choose>
					<hr>
					<p class="red">${error }</p>
					<form class="form-horizontal" action="student_manager.htm"
						method="post" id="fAddStudent">
						<div class="form-group">
							<label class="control-label col-sm-2">Tên sinh viên <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${flag == null }">
										<input type="text" class="form-control" name="studentName"
											placeholder="Nhập tên">
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" name="studentName"
											placeholder="Nhập tên" value="${scheStu.studentName }">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Đề tài <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${flag == null }">
										<input type="text" class="form-control" name="topic"
											placeholder="Nhập đề tài">
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" name="topic"
											placeholder="Nhập đề tài" value="${scheStu.topic }">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Loại sinh viên <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit" name="type">
									<c:choose>
										<c:when test="${flag == null }">
											<option value="1">Project 1</option>
											<option value="2">Project 2</option>
											<option value="3">Project 3</option>
											<option value="4">Đồ án tốt nghiệp</option>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${scheStu.type == 1 }">
													<option value="1" selected="selected">Project 1</option>
												</c:when>
												<c:otherwise>
													<option value="1">Project 1</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.type == 2 }">
													<option value="2" selected="selected">Project 2</option>
												</c:when>
												<c:otherwise>
													<option value="2">Project 2</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.type == 3 }">
													<option value="3" selected="selected">Project 3</option>
												</c:when>
												<c:otherwise>
													<option value="3">Project 3</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.type == 4 }">
													<option value="4" selected="selected">Đồ án tốt
														nghiệp</option>
												</c:when>
												<c:otherwise>
													<option value="4">Đồ án tốt nghiệp</option>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Học kỳ <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<c:choose>
									<c:when test="${flag == null }">
										<select class="selectpicker" data-width="fit" name="hocKy">
											<c:forEach items="${listHocKy }" var="hocKy">
												<option value="${hocKy.name }">${hocKy.name }</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<select class="selectpicker" data-width="fit" name="hocKy">
											<option value="" >${scheStu.wEnd.hocKy.name }</option>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2"> Tuần gặp <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<div class="tuan">
									<jsp:include page="load/add_update_student_load.jsp"></jsp:include>
									<span class="hidden small small" id="load6"><img
										alt="Loading..." src="${url }/images/loader.gif">
										Loading...</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2"> Thứ <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit" name="dateOfweek">
									<c:choose>
										<c:when test="${flag == null }">
											<option value="2">T2</option>
											<option value="3">T3</option>
											<option value="4">T4</option>
											<option value="5">T5</option>
											<option value="6">T6</option>
											<option value="0">T7</option>
											<option value="1">CN</option>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 2 }">
													<option value="2" selected="selected">T2</option>
												</c:when>
												<c:otherwise>
													<option value="2">T2</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 3 }">
													<option value="3" selected="selected">T3</option>
												</c:when>
												<c:otherwise>
													<option value="3">T3</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 4 }">
													<option value="4" selected="selected">T4</option>
												</c:when>
												<c:otherwise>
													<option value="4">T4</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 5 }">
													<option value="5" selected="selected">T5</option>
												</c:when>
												<c:otherwise>
													<option value="5">T5</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 6 }">
													<option value="6" selected="selected">T6</option>
												</c:when>
												<c:otherwise>
													<option value="6">T6</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 0 }">
													<option value="0" selected="selected">T7</option>
												</c:when>
												<c:otherwise>
													<option value="0">T7</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${scheStu.dateOfWeek == 1 }">
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
							<label class="control-label col-sm-2">Thời gian gặp <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-3">
								<c:choose>
									<c:when test="${flag == null }">
										<input type="text" class="timepicker2" name="timeStart">
									</c:when>
									<c:otherwise>
										<input type="text" class="timepicker2" name="timeStart"
											value="${my:getHour(scheStu.start) }">
									</c:otherwise>
								</c:choose>
								đến
							</div>
							<div class="col-sm-3">
								<c:choose>
									<c:when test="${flag == null }">
										<input type="text" class="timepicker2" name="timeEnd">
									</c:when>
									<c:otherwise>
										<input type="text" class="timepicker2" name="timeEnd"
											value="${my:getHour(scheStu.end) }">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2"> Phòng <sup
								class="red">*</sup>:
							</label>
							<div class="col-sm-10">
								<select class="selectpicker" data-width="fit" name="phong">
									<c:choose>
										<c:when test="${flag == null }">
											<c:forEach items="${lisPositions }" var="position">
												<option value="${position.key }">${position.key }</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${lisPositions }" var="position">
												<c:choose>
													<c:when test="${scheStu.phong.key == position.key }">
														<option value="${position.key }" selected="selected">${position.key }</option>
													</c:when>
													<c:otherwise>
														<option value="${position.key }">${position.key }</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2"></label>
							<div class="col-sm-10 ">
								<sup class="red">*</sup> <span class="small">: bắt buộc
									nhập</span>
							</div>
						</div>
						<c:choose>
							<c:when test="${flag == null }">
								<input type="hidden" name="action" value="add">
							</c:when>
							<c:otherwise>
								<input type="hidden" name="id" value="${scheStu.id }">
								<input type="hidden" name="action" value="${flag }">
							</c:otherwise>
						</c:choose>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-success">OK</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>