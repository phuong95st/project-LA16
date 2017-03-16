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
				zoom : 14,
				mapTypeId : google.maps.MapTypeId.ROADMAP,
				mapTypeControl : false,
				navigationControlOptions : {
					style : google.maps.NavigationControlStyle.SMALL
				}
			}

			var map = new google.maps.Map(document.getElementById("myPositionMap"),
					myOptions);
			var marker = new google.maps.Marker({
				position : latlon,
				map : map,
				title : "You are here!"
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
										<option value="ROADMAP">Mặc định</option>
										<option value="SATELLITE">Hình ảnh</option>
										<option value="HYBRID">Hình ảnh và tên thành phố</option>
										<option value="TERRAIN">Sông và núi</option>
								</select></td>
							</tr>
						</table>
						<div id="myPositionMap" style="width: 400pt; height: 300pt;clear: both">

						</div>
					</div>
				</div>
			</div>	
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>