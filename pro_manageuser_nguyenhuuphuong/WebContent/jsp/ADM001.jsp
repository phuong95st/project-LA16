
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />

<title>Đăng nhập vào hệ thống</title>
</head>
<body align="center">
<form action="Login.do" method="post">
<center>
<table class="tbl_input" cellpadding="4" cellspacing="0" width="400px">
	<tr>
		<th width="120px">&nbsp;</th>
		<th></th>
	</tr>
	<tr>
		<th colspan="2" align="left">アカウント名およびパスワードを入力してください</th>
	</tr>
	<c:if test="${requestScope.lsErrMessage != null}">
		<c:forEach items="${lsErrMessage}" var="errMessage">
			<tr>
				<td class="errMsg" colspan="2">
					<c:out value="${errMessage}"></c:out>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<%--
		if (request.getAttribute("lsErrMessage") != null) {
			List<String> lsErrMessage = (ArrayList<String>) request
					.getAttribute("lsErrMessage");
			for (String errMessage : lsErrMessage) {
	--%>

	<c:if test="${sessionScope.loginId != null}">
		<c:set var="loginId" scope="session" value="${sessionScope.loginId}"></c:set>
	</c:if>

	<c:if test="${sessionScope.password != null}">
		<c:set var="pass" scope="session" value="${sessionScope.password}"></c:set>
	</c:if>
	<%--
		}
		}

		String loginId = "";
		if (session.getAttribute("loginId") != null) {
			loginId = session.getAttribute("loginId").toString();
		}

		String pass = "";
		if (session.getAttribute("password") != null) {
			pass = session.getAttribute("password").toString();
		}
	--%>

	<tr align="left">
		<td class="lbl_left">パスワード:</td>
		<td align="left"><input class="txBox" type="text" name="loginId"
			value="${loginId}" size="20"
			onfocus="this.style.borderColor='#0066ff';"
			onblur="this.style.borderColor='#aaaaaa';" /></td>
	</tr>
	<tr>
		<td class="lbl_left">アカウント名:</td>
		<td align="left"><input class="txBox" type="password"
			name="password" value="${pass}" size="22"
			onfocus="this.style.borderColor='#0066ff';"
			onblur="this.style.borderColor='#aaaaaa';" /></td>
	</tr>
	<tr>
		<td></td>
		<td align="left"><input class="btn" type="submit" value="ログイン" />
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>