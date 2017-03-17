<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vị trí của bạn</title>
<jsp:include page="head.jsp"></jsp:include>
<script
	src="https://maps.google.com/maps/api/js?key=AIzaSyBUGbINAiV5YFOrgTEFa_DhpVPJi3BeIlA"></script>
<!--
To use this code on your website, get a free API key from Google.
Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
-->
<script>
	$(document).ready(function() {
		var typeMap = google.maps.MapTypeId.ROADMAP;
		var value = $("#typeMap").find(":selected").val();
		if(value == "ROADMAP"){
			typeMap = google.maps.MapTypeId.ROADMAP;
		}else if(value == "SATELLITE"){
			typeMap = google.maps.MapTypeId.SATELLITE;
		}else if(value == "HYBRID"){
			typeMap = google.maps.MapTypeId.HYBRID;
		}else{
			typeMap = google.maps.MapTypeId.TERRAIN;
		}	
		$("#typeMap").change(function(){
			value = $(this).find(":selected").val();
			if(value == "ROADMAP"){
				typeMap = google.maps.MapTypeId.ROADMAP;
			}else if(value == "SATELLITE"){
				typeMap = google.maps.MapTypeId.SATELLITE;
			}else if(value == "HYBRID"){
				typeMap = google.maps.MapTypeId.HYBRID;
			}else{
				typeMap = google.maps.MapTypeId.TERRAIN;
			}
			getLocation();
		});
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition, showError);
			} else {
				$.bootstrapGrowl("Geolocation is not supported by this browser.",{
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
						stackup_spacing : 10
						// spacing between consecutively stacked growls.
				});
			}
		}
		
		function showPosition(position) {
			lat = position.coords.latitude;
			lon = position.coords.longitude;
			latlon = new google.maps.LatLng(lat, lon);
			mapholder = document.getElementById('myPositionMap');
			//mapholder.style.height = '100pt';
			//mapholder.style.width = '100pt';

			var myOptions = {
				center : latlon,
				zoom : 18,
				mapTypeId : typeMap
			};

			var map = new google.maps.Map(mapholder,myOptions);
			var marker = new google.maps.Marker({
				position : latlon,
				animation: google.maps.Animation.BOUNCE,
				map : map,
				title : "You are here!(latitude: "+lat+", longitude: "+lon+")"
			});
		}
		getLocation();
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
					<h3>Vị trí của tôi</h3>
					<hr>
					<div align="center">
						<table>
							<tr>
								<td>Chọn loại bản đồ:</td>
								<td><select class="selectpicker" data-width="fit"
									id="typeMap">
										<option value="ROADMAP" selected="selected">Mặc định</option>
										<option value="SATELLITE">Hình ảnh</option>
										<option value="HYBRID">Hình ảnh và tên thành phố</option>
										<option value="TERRAIN">Sông và núi</option>
								</select></td>
							</tr>
						</table>
						<div>
							<div id="myPositionMap"  style="position: relative; overflow: hidden; transform: translateZ(0px); background-color: rgb(229, 227, 223);">

							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>