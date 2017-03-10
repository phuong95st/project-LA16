<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<jsp:include page="head.jsp"></jsp:include>
<script
	src='//cdn.jsdelivr.net/jquery.marquee/1.4.0/jquery.marquee.min.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#wellcome').marquee({
			//speed in milliseconds of the marquee
			duration : 9000,
			//gap in pixels between the tickers
			gap : 50,
			//time in milliseconds before the marquee will start animating
			delayBeforeStart : 0,
			//'left' or 'right'
			direction : 'left',
			//true or false - should the marquee be duplicated to show an effect of continues flow
			duplicated : false
		});
	});
</script>
<script type="text/javascript" src="${url }/js/javascript.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Bắt đầu row 2 -->
		<div class="container text-center">
			<div class="row content" id="content">
				<jsp:include page="slidebar.jsp"></jsp:include>
				<!-- Content -->
				<div class="col-sm-10 text-left" id="all_content">
					<div class="col-sm-9">
						<div id="wellcome">Chúc bạn 1 ngày làm việc hiệu quả.</div>
						<c:if test="${sessionScope.rangeMin != null }">
							<div id="late">
								<span class="text-danger small"> Bạn đã đi muộn <b>${rangeMin }</b>
									phút. Hãy nhập lý do!
								</span> <br> <span id="loader1" class="small hidden"> <img
									alt="Loading" src="${url }/images/loader.gif"> Loading...
								</span>
								<div id="form_late">
									<div class="col-sm-11">
										<input type="text" placeholder="Lý do ..."
											class="form-control" name="reason">
									</div>
									<button type="button" class="btn btn-info col-sm-1"
										onclick="submitLate(${sessionScope.lateId})">
										<span class="glyphicon glyphicon-edit"></span>
									</button>
									<div style="clear: both"></div>
								</div>
							</div>
						</c:if>
						<div id="envent">
							Các sự kiện sẽ diễn ra trong tuần
							<c:choose>
								<c:when test="${lisEvents.size() == 0 }">
									<br>
									<div class="alert alert-danger small">Không có sự kiện
										nào trong tuần</div>
								</c:when>
								<c:otherwise>
									<table class="table">
										<tr>
											<th>Tên</th>
											<th>Thời gian</th>
											<th>Địa điểm</th>
											<th>Loại</th>
											<th>Nội dung</th>
										</tr>
										<c:forEach items="${lisEvents }" var="envent">
											<tr class="small">
												<td>${envent.title }</td>
												<td>${envent.start }</td>
												<td>${envent.place }</td>
												<td>${envent.type.typeName }</td>
												<td>${envent.content }</td>
											</tr>
										</c:forEach>
									</table>
								</c:otherwise>
							</c:choose>
						</div>

					</div>
					<div class="col-sm-3">
						<script src="http://www.clocklink.com/embed.js"></script>

						<script type="text/javascript">
							obj = new Object;

							obj.clockfile = "0009-magenta.swf";
							obj.TimeZone = "ICT";

							obj.width = 200;
							obj.height = 200;

							obj.wmode = "transparent";
							showClock(obj);
						</script>
					</div>
					<div id="OnlCal" class="col-sm-12">
						Lịch trực bộ môn ngày hôm nay
						<c:choose>
							<c:when test="${lisOnls.size() == 0 }">
								<div class="alert alert-danger small">Không có lịch trực
									tuần này</div>
							</c:when>
							<c:otherwise>
								<table class="table">
									<tr>
										<th>STT</th>
										<th>Thời gian</th>
										<th>Ca trực</th>
									</tr>
									<c:set var="no" value="1" />
									<c:forEach items="${lisOnls}" var="onl">
										<tr>
											<td>${no }</td>
											<td>${my:getHour(onl.start) }-${my:getHour(onl.end) }</td>
											<td>${my:mapType(onl.caTruc) }</td>
										</tr>
										<c:set var="no" value="${no + 1}"></c:set>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>

					</div>

					<div id="stuCal" class="col-sm-12">
						Lịch gặp sinh viên hôm nay
						<c:choose>
							<c:when test="${lisScheStus.size() == 0 }">
								<div class="alert alert-danger small">Không có lịch gặp
									nào</div>
							</c:when>
							<c:otherwise>
								<table class="table">
									<tr>
										<th>No.</th>
										<th>Thời gian</th>
										<th>Loại sinh viên</th>
									</tr>
									<c:set var="no" value="1" />
									<c:forEach items="${lisScheStus}" var="scheStu">
										<tr>
											<td>${no }</td>
											<td>${my:getHour(scheStu.start) }-
												${my:getHour(scheStu.end) }</td>
											<td>${my:mappingTypeStudent(scheStu.type) }</td>
										</tr>
										<c:set var="no" value="${no + 1}"></c:set>
									</c:forEach>

								</table>
							</c:otherwise>
						</c:choose>
					</div>

					<div id="teach" class="col-sm-12">
						Lịch giảng dạy hôm nay của bạn
						<c:choose>
							<c:when test="${listTeachs.size() == 0 }">
								<div class="alert alert-danger small">Bạn không có lịch
									dạy nào ngày hôm nay</div>
							</c:when>
							<c:otherwise>
								<table class="table">
									<tr>
										<th>No.</th>
										<th>Thời gian</th>
										<th>Phòng học</th>
										<th>Mã lớp</th>
										<th>Mã môn học</th>
										<th>Tên môn học</th>
									</tr>
									<c:set var="no" value="1" />
									<c:forEach items="${listTeachs }" var="teach">
										<tr>
											<td>${no }</td>
											<td>${my:getHour(teach.start) }-${my:getHour(teach.end) }</td>
											<td>${teach.phong }</td>
											<td>${teach.codeClass }</td>
											<td>${teach.codeSubject }</td>
											<td>${teach.name }</td>
										</tr>
										<c:set var="no" value="${no + 1}"></c:set>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>

					<div id="holDay" class="col-sm-12">
						<button type="button" class="btn btn-primary" onclick="window.location.href='holiday.htm?action=addHol'">Đăng ký
							nghỉ phép</button>

						<p id="txt-hol">
							<span class="glyphicon glyphicon-group"></span> Đơn xin nghỉ phép
							trong năm
						</p>
						<c:if test="${listHol.size() != 0 }">
							<table class="table">
								<tr>
									<th>No.</th>
									<th>Thời gian</th>
									<th>Loại</th>
									<th>Lý do</th>
									<th>Trạng thái</th>
									<th>Action</th>
								</tr>
								<c:set var="no" value="1" />
								<c:forEach items="${listHol }" var="hol">
									<c:choose>
										<c:when test="${hol.phep && !hol.status}">
											<tr>
												<td>${no }</td>
												<td>${my:getDay(hol.start) }-${my:getDay(hol.end) }</td>
												<td>${my:mapType(hol.type) }</td>
												<td>${hol.reason }</td>
												<td>${my:mappingStatus(hol.status) }</td>
												<c:choose>
													<c:when test="${hol.end > now}">
														<td>
															<button type="button" class="btn btn-success btn-xs"
																onclick="cencleHol(${hol.id})" >
																<img alt="Loading" src="${url }/images/loader.gif"
																	class="hidden" id="loader2"> Cencle
															</button>
															<button type="button" class="btn btn-info btn-xs">
																<img alt="Loading" src="${url }/images/loader.gif"
																	class="hidden" id="loader3"> Update
															</button>
														</td>
													</c:when>
													<c:otherwise>
														<td></td>
													</c:otherwise>
												</c:choose>
											</tr>
										</c:when>
										<c:when test="${hol.phep && hol.status}">
											<tr class="text-success">
												<td>${no }</td>
												<td>${my:getDay(hol.start) }-${my:getDay(hol.end) }</td>
												<td>${my:mapType(hol.type) }</td>
												<td>${hol.reason }</td>
												<td>${my:mappingStatus(hol.status) }</td>
												<td></td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr class="text-danger">
												<td>${no }</td>
												<td>${my:getDay(hol.start) }-${my:getDay(hol.end) }</td>
												<td>${my:mapType(hol.type) }</td>
												<td>${hol.reason }</td>
												<td>${my:mappingStatus(hol.status) }</td>
												<td></td>
											</tr>
										</c:otherwise>
									</c:choose>

									<c:set var="no" value="${no + 1}"></c:set>
								</c:forEach>
							</table>
						</c:if>

					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>