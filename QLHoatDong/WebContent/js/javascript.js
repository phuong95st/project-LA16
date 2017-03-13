function submitLate(id) {
	$(document).ready(
			function() {
				$("#loader1").removeClass("hidden");
				var reason = $("input[name='reason']").val();

				$.ajax({
					type : "POST",
					url : "late.htm",
					data : {
						"id" : id,
						"reason" : reason,
					},
					header : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
					success : function(data) {
						result = $.parseJSON(data);
						if (!result["status"]) {
							$("#loader1").html(
									"<p class='text-warning small'>"
											+ result["data"] + "</p>");
							return;
						}
						$("#loader1").html(
								"<p class='text-success'>" + result["data"]
										+ "</p>");
						$("#form_late").addClass("hidden");
					}
				});
			});
}

function cencleHol(id) {
	if (confirm("Bạn có chắc chắn muốn hủy?")) {
		$("#loader2").removeClass("hidden");
		$.ajax({
			type : "POST",
			url : "holiday.htm",
			data : {
				"action" : "cencle",
				"id" : id
			},
			header : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(data) {
				result = $.parseJSON(data);
				alert(result["data"]);
				if (result["status"]) {
					window.location.reload();
				} else {
					$("#loader2").addClass("hidden");
				}
			}
		});
	}
}

function addHol() {
	$(document).ready(function() {
		var start = $("input[name='start']").val();
		var end = $("input[name='end']").val();
		var reason = $("input[name='reason']").val();
		var type = $("select[name='type']").val();

		if ("" == start || "" == end) {
			$.bootstrapGrowl("Vui lòng nhập thời gian!", {
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
		} else if ("" == reason) {
			$.bootstrapGrowl("Vui lòng nhập lý do", {
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
		} else {
			$.ajax({
				type : "POST",
				url : "holiday.htm",
				data : {
					"action" : "add",
					"start" : start,
					"end" : end,
					"reason" : reason,
					"type" : type
				},
				header : {
					Accept : "application/json; charset=utf-8",
					"Content-Type" : "application/json; charset=utf-8"
				},
				success : function(data) {
					result = $.parseJSON(data);
					if(!result['status']){
						$.bootstrapGrowl(result['data'], {
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
					}else{
						$("#include").load("load/row_add_hol.jsp");
						$("#btnRegister").load("load/btn_register.jsp");
					}
				}
			});
		}
	});
}

function delHol(id){
	$(document).ready(function(){
		$.ajax({
			type: "POST",
			url : "holiday.htm",
			data: {
				"action" : "del",
				"id": id
			},
			header : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success: function(data){
				$("#include").load("load/row_add_hol.jsp");
				$("#btnRegister").load("load/btn_register.jsp");
			}
		});
	});
}

function registerHol(){
	$(document).ready(function(){
		$("#loader3").removeClass("hidden");
		$.ajax({
			type: "POST",
			url: "holiday.htm",
			data : {
				"action" : "register"
			},
			header : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success: function(data){
				result = $.parseJSON(data);
				if(!result['status']){
					$("#loader3").addClass("hidden");
					$.bootstrapGrowl(result['data'], {
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
				}else{
					alert(result['data']);
					window.location.href = 'index.htm';
				}
			}
		});
	});
}