
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<title>Đăng nhập vào hệ thống</title>
</head>
<body align="center" >
<form action="Login.do" method="post">
<center>
	<table class="tbl_input" cellpadding="4" cellspacing="0" width="400px">
		<tr>
			<th width="120px">&nbsp;</th><th></th>
		</tr>
		<tr>
			<th colspan="2" align="left">Hãy nhập tên đăng nhập và mật khẩu</th>
		</tr>
		<%
		if(request.getAttribute("lsErrMessage") != null) {
			List<String> lsErrMessage = (ArrayList<String>)request.getAttribute("lsErrMessage");
			for (String errMessage : lsErrMessage) {
		%>
		<tr>
			<td class="errMsg" colspan="2"><%= errMessage %></td>
		</tr>
		<%
			}
		}

		String loginId = "";
		if(session.getAttribute("loginId") != null) {
			loginId = session.getAttribute("loginId").toString();
		}

		String pass = "";
		if(session.getAttribute("password") != null) {
			pass = session.getAttribute("password").toString();
		}

		%>

		<tr align="left">
			<td class="lbl_left">Tên đăng nhập:</td>
			<td align="left">
				<input class="txBox" type="text" name="loginId" value="<%= loginId%>" size="20" onfocus="this.style.borderColor='#0066ff';"
				onblur="this.style.borderColor='#aaaaaa';" />
			</td>
		</tr>
		<tr>
			<td class="lbl_left">Mật khẩu:</td>
			<td align="left">
				<input class="txBox" type="password" name="password" value="<%= pass%>"	size="22" onfocus="this.style.borderColor='#0066ff';"
				onblur="this.style.borderColor='#aaaaaa';" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="left">
				<input type="submit" value="Đăng nhập" />
			</td>
		</tr>
	</table>
	</center>
</form>
</body>
</html>