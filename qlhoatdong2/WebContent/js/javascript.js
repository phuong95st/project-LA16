function clickTeach(id) {
	$(document).ready(function() {
		$("#loader1").removeClass("hidden");

		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				// alert("latitude: " + position.coords.latitude + "\nlongitude:
				// " + position.coords.longitude);
				$.ajax({
					type : "POST",
					url : "teach.htm",
					data : {
						"action" : "position",
						"latitude" : position.coords.latitude,
						"longitude" : position.coords.longitude,
						"id" : id
					},
					header : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
					success : function(data) {
						result = $.parseJSON(data);
						if (!result["status"]) {
							if (result["flagHol"]) {
								alert(result["data"]);
								window.location.reload();
							} else {
								$("#loader1").addClass("hidden");
								$.bootstrapGrowl(result["data"], {
									ele : 'body', // which element to append
									// to
									type : 'danger', // (null, 'info',
									// 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or
									// 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message
									// will be displayed.
									// It's not equivalent to the *demo*
									// timeOut!
									allow_dismiss : true, // If true then will
									// display a cross
									// to close the
									// popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
							}
						} else {
							alert(result["data"]);
							window.location.reload();
						}

					},
					error : function() {
						alert("Không thể gửi dữ liệu");
					}
				});
			}, function(error) {
				$("#loader1").addClass("hidden");
				showError(error);
			});
		} else {
			$.bootstrapGrowl("Geolocation is not supported by this browser.", {
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
		}
	});
}

function showError(error) {
	switch (error.code) {
	case error.PERMISSION_DENIED:
		$.bootstrapGrowl("User denied the request for Geolocation.", {
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
		break;
	case error.POSITION_UNAVAILABLE:
		$.bootstrapGrowl("Location information is unavailable.", {
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
		break;
	case error.TIMEOUT:
		$.bootstrapGrowl("The request to get user location timed out.", {
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
		break;
	case error.UNKNOWN_ERROR:
		$.bootstrapGrowl("An unknown error occurred.", {
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
		break;
	}
}

function addReason(teachWeekId) {
	$("#loader2").removeClass("hidden");
	var reason = $("input[name='reason']").val();
	if (reason == "") {
		$("#loader2").addClass("hidden");
		$.bootstrapGrowl("Nhập lý do đi dạy muộn.", {
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
	$.ajax({
		type : "POST",
		url : "teach.htm",
		data : {
			"action" : "addReason",
			"id" : teachWeekId,
			"reason" : $("input[name='reason']").val()
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				alert(result["data"]);
			} else {
				alert(result["data"]);
				window.location.reload();
			}
		},
		error : function() {
			alert("Không thể gửi dữ liệu.");
		}
	});
}

function addTeach() {
	var userId = $("select[name='user']").find(":selected").val();
	var hocKy = $("select[name='hocKy']").find(":selected").val();
	var message = "";
	var flag = false;
	if (userId == "none") {
		flag = true;
		message = "Hãy chọn 1 nhân viên trước khi thêm lịch giảng dạy!";
	} else if (hocKy == "none") {
		flag = true;
		message = "Hãy chọn học kỳ tương ứng với lịch giảng dạy cần thêm!";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
	$("input[name='user']").val(userId);
	$("input[name='hocKy']").val(hocKy);

	return true;
}

function clickGiaoBan(id) {
	$(document).ready(function() {
		$("#loader9").removeClass("hidden");
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				// alert("latitude: " + position.coords.latitude + "\nlongitude:
				// " + position.coords.longitude);
				$.ajax({
					type : "POST",
					url : "onl.htm",
					data : {
						"action" : "position",
						"latitude" : position.coords.latitude,
						"longitude" : position.coords.longitude,
						"id" : id
					},
					header : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
					success : function(data) {
						result = $.parseJSON(data);
						if (!result["status"]) {
							if (result["flagHol"]) {
								alert(result["data"]);
								window.location.reload();
							} else {
								$("#loader9").addClass("hidden");
								$.bootstrapGrowl(result["data"], {
									ele : 'body', // which element to append
									// to
									type : 'danger', // (null, 'info',
									// 'danger', 'success')
									offset : {
										from : 'top',
										amount : 20
									}, // 'top', or 'bottom'
									align : 'center', // ('left', 'right', or
									// 'center')
									width : "auto", // (integer, or 'auto')
									delay : 5000, // Time while the message
									// will be displayed.
									// It's not equivalent to the *demo*
									// timeOut!
									allow_dismiss : true, // If true then will
									// display a cross
									// to close the
									// popup.
									stackup_spacing : 10
								// spacing between consecutively stacked growls.
								});
							}
						} else {
							alert(result["data"]);
							window.location.reload();
						}

					},
					error : function() {
						alert("Không thể gửi dữ liệu");
					}
				});
			}, function(error) {
				$("#loader9").addClass("hidden");
				showError(error);
			});
		} else {
			$.bootstrapGrowl("Geolocation is not supported by this browser.", {
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
		}
	});
}

function clickReason(id) {
	$("#loader10").removeClass("hidden");
	var reason = $("input[name='reasonOnl']").val();
	if (reason == "") {
		$("#loader10").addClass("hidden");
		$.bootstrapGrowl("Nhập lý do giao ban muộn", {
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
	$.ajax({
		type : "POST",
		url : "onl.htm",
		data : {
			"action" : "addReason",
			"id" : id,
			"reason" : reason
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				alert(result["data"]);
			} else {
				alert(result["data"]);
				window.location.reload();
			}
		},
		error : function() {
			alert("Không thể gửi dữ liệu.");
		}
	});
}
// function isEmail(email) {
// if(email == null){
// return false;
// }
// var isValid = false;
// var regex =
// /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
// if(regex.test(email)) {
// isValid = true;
// }
// alert(isValid);
// return isValid;
// }
//
// function isPhoneNumber(phoneNumber){
// if(phoneNumber == null){
// return false;
// }
// var regex = /^[0-9-+]+$/;
// var isValid = false;
// if(regex.test(phoneNumber)) {
// isValid = true;
// }
// alert(isValid);
// return isValid;
// }

// function isCMT(cmt){
// if(phoneNumber == null){
// return false;
// }
// var regex = /^[0-9]{9}$/;
// if(!regex.test(cmt)){
// regex = /^[0-9]{12}$/;
// if(regex.test(cmt)){
// return true;
// }
// return false;
// }
// return true;
// }

function addUser() {
	// $(document).ready(function(){
	var name = $("input[name='name']").val();
	var birth = $("input[name='birth']").val();
	var que = $("input[name='que']").val();
	var cmt = $("input[name='cmt']").val();
	var danToc = $("input[name='dantoc']").val();
	var title = $("input[name='title']").val();
	var hienTai = $("input[name='hientai']").val();
	var email = $("input[name='email']").val();
	var tel = $("input[name='tel']").val();

	var flag = false;
	var message = "";
	if (name == "") {
		flag = true;
		message = "Nhập họ tên.";
	} else if (birth == "") {
		flag = true;
		message = "Nhập Ngày sinh.";
	} else if (que == "") {
		flag = true;
		message = "Nhập Quê quán.";
	} else if (cmt == "") {
		flag = true;
		message = "Nhập số chứng minh thư.";
	} else if (danToc == "") {
		flag = true;
		message = "Nhập dân tộc.";
	} else if (title == "") {
		flag = true;
		message = "Nhập chức danh.";
	} else if (hienTai == "") {
		flag = true;
		message = "Nhập nơi ở hiện tại.";
	} else if (email == "") {
		flag = true;
		message = "Nhập email";
	} else if (tel == "") {
		flag = true;
		message = "Nhập SĐT";
	}
	// // }else if(!isCMT(cmt)){
	// // flag = true;
	// // message = "Chứng minh thư không hợp lệ";
	// }else if(!isEmail(email)){
	// flag = true;
	// message = "Email không hợp lệ.";
	// }else if(!isPhoneNumber(phoneNumber)){
	// flag = true;
	// message = "Số điện thoại không đúng.";
	// }

	if (flag) {
		$.bootstrapGrowl(message, {
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
	return true;
	// });
	// return false;
}

function deleteUser(userId) {
	if (confirm("Việc này có thể làm mất dữ liệu quan trọng. Bạn có chắc chắn xóa?")) {
		$(document).ready(function() {
			$("#loader14").removeClass("hidden");

			$.ajax({
				type : "POST",
				url : "info.ad",
				data : {
					"action" : "delete",
					"userId" : userId
				},
				header : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				success : function(data) {
					result = $.parseJSON(data);
					alert(result['data']);
					if (!result['status']) {
						$("#loader14").addClass("hidden");
					} else {
						window.location.href = "info.ad";
					}
				},
				error : function() {
					alert("System error!");
					$("#loader14").addClass("hidden");
				}
			});
		});
	}
}

function clickSearchTeach() {
	$(document).ready(function() {
		$("#loader13").removeClass("hidden");
		var userId = $("select[name='user']").find(":selected").val();
		var hocKy = $("select[name='hocKy']").find(":selected").val();
		if (userId == "none" && hocKy == "none") {
			$.bootstrapGrowl("Vui lòng nhập dữ liệu tra cứu!", {
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
			$("#loader13").addClass("hidden");
			return;
		}
		$.ajax({
			type : "POST",
			url : "teach.ad",
			data : {
				"action" : "search",
				"userId" : userId,
				"hocKy" : hocKy
			},
			success : function(data) {
				$("#resule_search").html(data);
				$("#loader13").addClass("hidden");
			},
			error : function() {
				alert("System error!!!");
				$("#loader13").addClass("hidden");
			}
		});

	});
}

function addTeachToSession() {
	$("#loader15").removeClass("hidden");

	var timeStart = $("#timeStart").val();
	var timeEnd = $("#timeEnd").val();
	var dateOfweek = $("select[name='dateOfweek']").find(":selected").val();
	var wStartId = $("select[name='wStartId']").find(":selected").val();
	var wEndId = $("select[name='wEndId']").find(":selected").val();
	var phong = $("select[name='phong']").find(":selected").val();
	var codeClass = $("#codeClass").val();
	var codeSubject = $("#codeSubject").val();
	var name = $("#name").val();
	var userId = $("input[name='userId']").val();
	flag = false;
	message = "";

	if (timeStart == "") {
		flag = true;
		message = "Vui lòng nhập thời gian bắt đầu.";
	} else if (timeEnd == "") {
		flag = true;
		message = "Vui lòng nhập thời kết thúc.";
	} else if (codeClass == "") {
		flag = true;
		message = "Hãy nhập mã lớp học.";
	} else if (codeSubject == "") {
		flag = true;
		message = "Hãy nhập mã môn học";
	} else if (name == "") {
		flag = true;
		message = "Hãy nhập tên môn học";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
		$("#loader15").addClass("hidden");
		return;
	}
	$.ajax({
		type : "POST",
		url : "teach.ad",
		data : {
			"action" : "add",
			"timeStart" : timeStart,
			"timeEnd" : timeEnd,
			"dateOfweek" : dateOfweek,
			"wStartId" : wStartId,
			"wEndId" : wEndId,
			"phong" : phong,
			"codeClass" : codeClass,
			"codeSubject" : codeSubject,
			"name" : name,
			"userId" : userId
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				$("#error").html(result['data']);
				setTimeout(function() {
					$("#error").html("");
				}, 2000);
			} else {
				$("#addTeachSession").load("load/addTeachSession.jsp");
			}
			$("#loader15").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader15").addClass("hidden");
		}
	});
}

function removeItemTeach(pos) {
	$("#loader16").removeClass("hidden");
	$.ajax({
		type : "POST",
		url : "teach.ad",
		data : {
			"action" : "removeItem",
			"pos" : pos
		},
		success : function(data) {
			$("#addTeachSession").load("load/addTeachSession.jsp");
			$("#loader16").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader16").addClass("hidden");
		}
	});
}

function insertTeach() {
	$("#loader17").removeClass("hidden");

	$.ajax({
		type : "POST",
		url : "teach.ad",
		data : {
			"action" : "insert"
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			alert(result['data']);
			if (!result['status']) {
				window.location.reload();
			} else {
				window.location.href = "teach.ad";
			}
		},
		error : function() {
			alert("System error!!!");
			$("#loader17").addClass("hidden");
		}
	});
}

function updateTeach(teachId) {
	$("#loader15").removeClass("hidden");

	var timeStart = $("#timeStart").val();
	var timeEnd = $("#timeEnd").val();
	var dateOfweek = $("select[name='dateOfweek']").find(":selected").val();
	// var wStartId = $("select[name='wStartId']").find(":selected").val();
	// var wEndId= $("select[name='wEndId']").find(":selected").val();
	var phong = $("select[name='phong']").find(":selected").val();
	var codeClass = $("#codeClass").val();
	var codeSubject = $("#codeSubject").val();
	var name = $("#name").val();
	var userId = $("input[name='userId']").val();
	flag = false;
	message = "";

	if (timeStart == "") {
		flag = true;
		message = "Vui lòng nhập thời gian bắt đầu.";
	} else if (timeEnd == "") {
		flag = true;
		message = "Vui lòng nhập thời kết thúc.";
	} else if (codeClass == "") {
		flag = true;
		message = "Hãy nhập mã lớp học.";
	} else if (codeSubject == "") {
		flag = true;
		message = "Hãy nhập mã môn học";
	} else if (name == "") {
		flag = true;
		message = "Hãy nhập tên môn học";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
		$("#loader15").addClass("hidden");
		return;
	}
	$.ajax({
		type : "POST",
		url : "teach.ad",
		data : {
			"action" : "edit",
			"timeStart" : timeStart,
			"timeEnd" : timeEnd,
			"dateOfweek" : dateOfweek,
			// "wStartId": wStartId,
			// "wEndId":wEndId,
			"phong" : phong,
			"codeClass" : codeClass,
			"codeSubject" : codeSubject,
			"name" : name,
			"teachId" : teachId,
			"userId" : userId
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				if (result['flag']) {
					alert(result['data']);
				} else {
					$("#error").html(result['data']);
					setTimeout(function() {
						$("#error").html("");
					}, 2000);
				}
			} else {
				alert(result['data']);
				window.location.href = "teach.ad";
			}
			$("#loader15").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader15").addClass("hidden");
		}
	});
}
function changeHocKy() {
	$("#loader15").removeClass("hidden");
	var hocKy = $("select[name='hocKy']").find(":selected").val();
	$.ajax({
		type : "POST",
		url : "onl.ad",
		data : {
			"action" : "loadWeek",
			"hocKy" : hocKy
		},
		success : function(data) {
			$("#loadWeek").html(data);
			$('.selectpicker').selectpicker("refresh");
			$("#loader15").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader15").addClass("hidden");
		}
	});
}

function clickSearchOnl() {
	$(document).ready(function() {
		$("#loader15").removeClass("hidden");

		var userId = $("select[name='user']").find(":selected").val();
		var weekId = $("select[name='weekId']").find(":selected").val();
		var hocKy = $("select[name='hocKy']").find(":selected").val();
		if (userId == "none" && hocKy == "none" && weekId == "none") {
			$.bootstrapGrowl("Vui lòng dữ liệu cần tra cứu!", {
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
			$("#loader15").addClass("hidden");
			return;
		}
		$.ajax({
			type : "POST",
			url : "onl.ad",
			data : {
				"action" : "search",
				"userId" : userId,
				"weekId" : weekId,
				"hocKy" : hocKy
			},
			success : function(data) {
				$("#searchOnl").html(data);
				$("#loader15").addClass("hidden");
			},
			error : function() {
				alert("System error!!!");
				$("#loader15").addClass("hidden");
			}
		});

	});
}

function addOnl() {
	var userId = $("select[name='user']").find(":selected").val();
	var hocKy = $("select[name='hocKy']").find(":selected").val();
	var weekId = $("select[name='weekId']").find(":selected").val();
	var message = "";
	var flag = false;
	if (userId == "none") {
		flag = true;
		message = "Hãy chọn 1 nhân viên trước khi thêm lịch trực!";
	} else if (weekId == "none") {
		flag = true;
		message = "Hãy chọn tuần trực tương ứng với học kỳ!";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
	$("input[name='user']").val(userId);
	$("input[name='weekId']").val(weekId);
	$("input[name='hocKy']").val(hocKy);

	return true;
}

function addOnlToSession() {
	$("#loader16").removeClass("hidden");

	var timeStart = $("#timeStart").val();
	var timeEnd = $("#timeEnd").val();
	var dateOfweek = $("select[name='dateOfweek']").find(":selected").val();
	var caTruc = $("select[name='caTruc']").find(":selected").val();

	var userId = $("input[name='userId']").val();
	var weekId = $("input[name='weekId']").val();
	flag = false;
	message = "";

	if (timeStart == "") {
		flag = true;
		message = "Vui lòng nhập thời gian bắt đầu.";
	} else if (timeEnd == "") {
		flag = true;
		message = "Vui lòng nhập thời kết thúc.";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
		$("#loader16").addClass("hidden");
		return;
	}
	$.ajax({
		type : "POST",
		url : "onl.ad",
		data : {
			"action" : "add",
			"timeStart" : timeStart,
			"timeEnd" : timeEnd,
			"dateOfweek" : dateOfweek,
			"caTruc" : caTruc,
			"userId" : userId,
			"weekId" : weekId,
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				$("#error2").html(result['data']);
				setTimeout(function() {
					$("#error2").html("");
				}, 2000);
			} else {
				$("#addOnlSession").load("load/addOnlSession.jsp");
			}
			$("#loader16").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader16").addClass("hidden");
		}
	});
}

function insertOnl() {
	$("#loader20").removeClass("hidden");

	$.ajax({
		type : "POST",
		url : "onl.ad",
		data : {
			"action" : "insert"
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			alert(result['data']);
			if (!result['status']) {
				window.location.reload();
			} else {
				window.location.href = "onl.ad";
			}
		},
		error : function() {
			alert("System error!!!");
			$("#loader20").addClass("hidden");
		}
	});
}

function updateOnl(onlId) {
	$("#loader16").removeClass("hidden");

	var timeStart = $("#timeStart").val();
	var timeEnd = $("#timeEnd").val();
	var dateOfweek = $("select[name='dateOfweek']").find(":selected").val();
	var caTruc = $("select[name='caTruc']").find(":selected").val();
	flag = false;
	message = "";

	if (timeStart == "") {
		flag = true;
		message = "Vui lòng nhập thời gian bắt đầu.";
	} else if (timeEnd == "") {
		flag = true;
		message = "Vui lòng nhập thời kết thúc.";
	}

	if (flag) {
		$.bootstrapGrowl(message, {
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
		$("#loader16").addClass("hidden");
		return;
	}
	$.ajax({
		type : "POST",
		url : "onl.ad",
		data : {
			"action" : "edit",
			"timeStart" : timeStart,
			"timeEnd" : timeEnd,
			"dateOfweek" : dateOfweek,
			"onlId" : onlId,
			"caTruc" : caTruc
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(data) {
			result = $.parseJSON(data);
			if (!result['status']) {
				$("#error2").html(result['data']);
				setTimeout(function() {
					$("#error2").html("");
				}, 2000);
			} else {
				alert(result['data']);
				window.location.href = "onl.ad";
			}
			$("#loader16").addClass("hidden");
		},
		error : function() {
			alert("System error!!!");
			$("#loader16").addClass("hidden");
		}
	});
}

function removeOnl(onlId) {
	$("#loader21").addClass("hidden");
	if (confirm("Bạn có chắc chắn muốn xóa?")) {
		$.ajax({
			type : "POST",
			url : "onl.ad",
			data : {
				"action" : "delete",
				"onlId" : onlId
			},
			header : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(data) {
				result = $.parseJSON(data);
				alert(result['data']);
				if (!result['status']) {
					$("#loader21").addClass("hidden");
				} else {
					window.location.href = 'onl.ad';
				}
			},
			error : function() {
				alert("System error!!!");
				$("#loader21").addClass("hidden");
			}
		});
	}
}

function formatDate(date) {
	var datearray = date.split("/");
	return datearray[1] + '/' + datearray[0] + '/' + datearray[2];
}

function searchAdmin() {
	$("#messAdmin").addClass("hidden");
	$("#load22").removeClass("hidden");

	var timeStart = $('.timepicker[name="timeStart"]').val();
	var timeEnd = $('.timepicker[name="timeEnd"]').val();
	var date = $(".datepicker").val();
	var userId = $("select[name='userId']").find(":selected").val();

	var flag = false;
	var message = "";
	if ((timeStart == "" || timeEnd == "") && date == "" && userId == "none") {
		flag = true;
		message = "Hãy nhập thông tin tìm kiếm!";
	} else if ((timeStart == "" && timeEnd != "")
			|| (timeStart != "" && timeEnd == "")) {
		flag = true;
		message = "Hãy nhập chính xác thời gian!";
	} else if (date == "") {
		flag = true;
		message = "Vui lòng nhập 1 ngày trong quá khứ!";
	} else {
		var inputDate = new Date(formatDate(date)).getTime();
		var now = new Date();
		var now2 = new Date(now.getFullYear(), now.getMonth(), now.getDate())
				.getTime();
		if (inputDate >= now2) {
			flag = true;
			message = "Hệ thống chỉ tìm kiếm thời gian trong quá khứ. Vui lòng nhập lại";
		}
	}
	if (flag) {
		$.bootstrapGrowl(message, {
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
		$("#load22").addClass("hidden");
	} else {
		$.ajax({
			type : "POST",
			url : "search.ad",
			data : {
				"timeStart" : timeStart,
				"timeEnd" : timeEnd,
				"date" : date,
				"userId" : userId
			},
			success : function(data) {
				$("#resultSearchAdmin").html(data);
				$("#load22").addClass("hidden");
			},
			error : function() {
				alert("System error!!!");
				$("#load22").addClass("hidden");
			}
		});
	}

}

function addNamHoc(){
	$("#load24").removeClass("hidden");
	
	var namHoc = $("input[name='namHoc']").val();
	var startDate = $("input[name='startDate']").val();
	var total1 = $("input[name='total1']").val();
	var total2 = $("input[name='total2']").val();
	var total3 = $("input[name='total3']").val();
	
	var flag = false;
	var message = "";
	if(namHoc == ""){
		flag = true;
		message = "Năm học không được trống!";
	}else if(startDate == ""){
		flag = true;
		message = "Nhập ngày bắt đầu của tuần đầu tiên.";
	}else if(total1 == ""){
		flag = true;
		message = "Nhập số tuần của học học kỳ 1";
	}else if(total2 == ""){
		flag = true;
		message = "Nhập số tuần của học học kỳ 2";
	}else if(total3 == ""){
		flag = true;
		message = "Nhập số tuần của học học kỳ 2";
	}
	
	if(flag){
		$.bootstrapGrowl(message, {
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
		$("#load24").addClass("hidden");
	}else{
		$.ajax({
			type: "POST",
			url: "nam_hoc.ad",
			data:{
				"action": "add",
				"namHoc": namHoc,
				"startDate": startDate,
				"total1": total1,
				"total2": total2,
				"total3": total3
			},
			header : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success: function(data){
				result = $.parseJSON(data);
				if(!result['status']){
					if(result['addFail']){
						alert(result['data']);
					}else{
						$("#listErr").html(result['data']);
						window.setTimeout(function(){
							$("#listErr").html("");
						}, 5000);
						$("#load24").addClass("hidden");
					}	
				}else{
					alert(result['data']);
					window.location.reload();
				}		
			},
			error: function(){
				alert("System error!!!");
				$("#load24").addClass("hidden");
			}
		});
	}
} 