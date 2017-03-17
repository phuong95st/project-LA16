function clickTeach(id) {
	$(document).ready(function() {
		$("#loader1").removeClass("hidden");
		
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position){
				//alert("latitude: " + position.coords.latitude + "\nlongitude: " + position.coords.longitude);
				$.ajax({
					type: "POST",
					url : "teach.htm",
					data: {
						"action" : "position",
						"latitude" : position.coords.latitude,
						"longitude" : position.coords.longitude,
						"id" : id
					},
					header : {
						Accept : "application/json; charset=utf-8",
						"Content-Type" : "application/json; charset=utf-8"
					},
					success: function(data){
						result = $.parseJSON(data);
						if(!result["status"]){
							if(result["flagHol"]){
								alert(result["data"]);
								window.location.reload();
							}else{
								$("#loader1").addClass("hidden");
								$.bootstrapGrowl(result["data"], {
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
									allow_dismiss : true, // If true then will display a cross to close the popup.
									stackup_spacing : 10 // spacing between consecutively stacked growls.
								});
							}
						}else{
							alert(result["data"]);
							window.location.reload();
						}
						
					},
					error : function(){
						alert("Không thể gửi dữ liệu");
					}
				});
			}, function(error){
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
				allow_dismiss : true, // If true then will display a cross to close the popup.
				stackup_spacing : 10 // spacing between consecutively stacked growls.
			});
		}
	});
}

function showError(error) {
    switch(error.code) {
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
				allow_dismiss : true, // If true then will display a cross to close the popup.
				stackup_spacing : 10 // spacing between consecutively stacked growls.
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

function addReason(teachWeekId){
	$("#loader2").removeClass("hidden");
	var reason = $("input[name='reason']").val();
	if(reason == ""){
		$("#loader2").addClass("hidden");
		$.bootstrapGrowl("Nhập lý do", {
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
	$.ajax({
		type: "POST",
		url: "teach.htm",
		data: {
			"action": "addReason",
			"id": teachWeekId,
			"reason" : $("input[name='reason']").val()
		},
		header : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success: function(data){
			result = $.parseJSON(data);
			alert(result["data"]);
			window.location.reload();
		},
		error : function(){
			alert("Không thể gửi dữ liệu.");
		}
	});
}

function addTeach(){
	$("input[name='user']").val($("select[name='user']").find(":selected").val());
	$("input[name='hocKy']").val($("select[name='hocKy']").find(":selected").val());
	$("#formAddTeach").submit();
}