function submitLate(id){
	$(document).ready(function(){
		$("#loader1").removeClass("hidden");
		var reason  = $("input[name='reason']").val();
		
		$.ajax({
			type: "POST",
			url : "late.htm",
			data: {
				"id": id,
				"reason": reason,
			},
			header:{
				Accept:"application/json; charset=utf-8", 
				"Content-Type" : "application/json; charset=utf-8"
			},
			success: function(data){
				result = $.parseJSON(data);
				if(!result["status"]){
					$("#loader1").html("<p class='text-warning small'>"+result["data"]+"</p>");
					return;
				}
				$("#loader1").html("<p class='text-success'>"+result["data"]+"</p>");
				$("#form_late").addClass("hidden");
			}
		});
	});
}

function cencleHol(id){
	if(confirm("Bạn có chắc chắn muốn hủy?")){
		$("#loader2").removeClass("hidden");
		$.ajax({
			type: "POST",
			url: "holiday.htm",
			data: {
				"action" : "cencle",
				"id" : id
			},
			header:{
				Accept:"application/json; charset=utf-8", 
				"Content-Type" : "application/json; charset=utf-8"
			},
			success: function(data){
				result = $.parseJSON(data);
				alert(result["data"]);
				if(result["status"]){
					window.location.reload();
				}else{
					$("#loader2").addClass("hidden");
				}
			}
		});
	}
}