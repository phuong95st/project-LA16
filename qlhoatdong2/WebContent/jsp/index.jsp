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
						<!--<c:if test="${sessionScope.rangeMin != null }">
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
						</c:if>-->
						<div id="stuCal" class="table-responsive">
							Lịch gặp sinh viên hôm nay
							<c:choose>
								<c:when test="${lisScheStus.size() == 0 }">
									<div class="alert alert-danger small">Không có lịch gặp
										nào</div>
								</c:when>
								<c:otherwise>
									<table class="table table-condensed">
										<tr>
											<th>No.</th>
											<th>Thời gian</th>
											<th>Loại sinh viên</th>
											<th></th>
										</tr>
										<c:set var="no" value="1" />
										<c:forEach items="${lisScheStus}" var="scheStu">
											<tr>
												<td>${no }</td>
												<td>${my:getHour(scheStu.start) }-
													${my:getHour(scheStu.end) }</td>
												<td>${my:mappingTypeStudent(scheStu.type) }</td>
												<td><button class="btn btn-success btn-xs">Chi
														tiết</button></td>
											</tr>
											<c:set var="no" value="${no + 1}"></c:set>
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

							obj.width = "auto";
							obj.height = "auto";

							obj.wmode = "transparent";
							showClock(obj);
						</script>
					</div>
					<div id="OnlCal" class="col-sm-12 table-responsive">
						Lịch trực bộ môn hôm nay
						<c:choose>
							<c:when test="${lisOnls.size() == 0 }">
								<div class="alert alert-danger small">Không có lịch trực
									hôm nay</div>
							</c:when>
							<c:otherwise>
								<table class="table table-condensed">
									<tr>
										<th>No.</th>
										<th>Thứ</th>
										<th>Thời gian</th>
										<th>Ngày</th>
										<th>Ca trực</th>
										<th></th>
									</tr>
									<c:set var="no" value="1" />
									<c:forEach items="${lisOnls}" var="onl">
										<tr>
											<td>${no }</td>
											<td>${my:getThu(onl.start) }</td>
											<td>${my:getHour(onl.start) }- ${my:getHour(onl.end) }</td>
											<td>${my:getDay(onl.start) }</td>
											<td>${my:mapType(onl.caTruc) }</td>
											<c:choose>
												<c:when test="${onl.hol && onl.end < now}">
													<td class="text-danger">Bạn đã không trực</td>
												</c:when>
												<c:when test="${onl.late}">
													<td class="text-danger">Bạn đã giao ban muộn
														${onl.lateMin} phút</td>
												</c:when>
												<c:otherwise>
													<td><button class="btn btn-info btn-xs">Nhận
															giao ban</button></td>
												</c:otherwise>
											</c:choose>
										</tr>
										<c:set var="no" value="${no + 1}"></c:set>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>

					</div>

					<div id="teach" class="col-sm-12 table-responsive">
						Lịch giảng dạy hôm nay
						<c:choose>
							<c:when test="${listTeachInfo.size() == 0 }">
								<div class="alert alert-danger small">Bạn không có lịch
									dạy nào ngày hôm nay</div>
							</c:when>
							<c:otherwise>
								<table class="table table-condensed">
									<tr>
										<th>No.</th>
										<th>Thời gian</th>
										<th>Phòng học</th>
										<th>Mã lớp</th>
										<th>Mã môn học</th>
										<th>Tên môn học</th>
										<th></th>
									</tr>
									<c:set var="no" value="1" />
									<c:forEach items="${listTeachInfo }" var="teachInfo">
										<tr>
											<td>${no }</td>
											<td>${my:getHour(teachInfo.teach.timeStart) }-${my:getHour(teachInfo.teach.timeEnd) }</td>
											<td>${teachInfo.teach.phong.key }</td>
											<td>${teachInfo.teach.codeClass }</td>
											<td>${teachInfo.teach.codeSubject }</td>
											<td>${teachInfo.teach.name }</td>
											<c:choose>
												<c:when test="${teachInfo.hol && (teachInfo.teach.timeEnd < now)}">
													<td class="text-danger">Bạn đã không dạy</td>
												</c:when>
												<c:when test="${teachInfo.late}">
													<td >
														<span class="text-danger small" style="padding-left: 11pt;"><img alt="Loading" src="${url }/images/loader.gif" id="loader2" class="hidden" width="10pt" height="10pt"> Bạn đã đến muộn <b>${teachInfo.lateMin}</b> phút</span>
														<c:choose>
															<c:when test="${teachInfo.reason == null || teachInfo.reason == '' }">
																<div>
																	<p class="col-sm-10">
																		<input type="text" placeholder="Nhập lý do ..." class="form-control" name="reason">
																	</p>
																	<button class="btn btn-warning" onclick="javascript:addReason(${teachInfo.id})"><span class="glyphicon glyphicon-edit"></span></button>
																<div style="clear: both"></div>
															</div>
															</c:when>
														</c:choose>		
													</td>	
												</c:when>
												<c:when test="${!teachInfo.hol && teachInfo.teach.timeStart >= now}">
													<td class="text-success">Good!</td>
												</c:when>
												<c:otherwise>
													<td>
														<img alt="Loading" src="${url }/images/loader.gif" id="loader1" class="hidden">
														<button class="btn btn-info btn-xs" onclick="javascript:clickTeach(${teachInfo.teach.teachId}) ">Tôi có mặt</button>
													</td>
												</c:otherwise>
											</c:choose>
										</tr>
										<c:set var="no" value="${no + 1}"></c:set>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>