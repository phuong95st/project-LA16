<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.luvina.manageuser.entities.*"%>
<%@page import="net.luvina.manageuser.utils.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp" />

<!-- Begin vung dieu kien tim kiem -->
<form action="ListUser.do" method="post" name="mainform"><!-- Begin Tag hidden -->
	<input type="hidden" name="page" value="1" /> <%
 	// Start fix bug ID 7 – PhuongNH 2016/08/04
 %> <input type="hidden" name="clickSearch" value="0"> <%
 	// End fix bug ID 7 – PhuongNH 2016/08/04
 %> <!-- End Tag hidden -->
<table class="tbl_input" border="0" width="90%" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
	</tr>
	<tr>
		<td width="100%">
		<table class="tbl_input" cellpadding="4" cellspacing="0">
			<tr>
				<c:set var="fullName" value="${requestScope.full_name}"></c:set>
				<td class="lbl_left">氏名:</td>
				<td align="left"><input class="txBox" type="text"
					name="full_name" value="${fullName }" size="20"
					onfocus="this.style.borderColor='#0066ff';"
					onblur="this.style.borderColor='#aaaaaa';" /></td>
				<td></td>
			</tr>
			<tr>
				<td class="lbl_left">グループ:</td>
				<td align="left" width="80px"><select name="group_id">
					<option value="0">全て</option>
					<c:set var="groupId" value="${requestScope.group_id}"></c:set>
					<c:forEach items="${listGroup}" var="group">
						<c:choose>
							<c:when test="${groupId == group.id}">
								<option value="${group.id }" selected="selected">${group.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${group.id }">${group.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select></td>
				<td align="left"><input class="btn" type="submit" value="検索" />
				<input class="btn" type="button" value="新規追加" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<!-- End vung dieu kien tim kiem --></form>
<!-- Begin vung hien thi danh sach user -->
<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
	width="80%">
	<tr class="tr2">
		<th align="center" width="20px">ID</th>
		<th align="left">氏名 <a href="">▲▽</a></th>
		<th align="left">生年月日</th>
		<th align="left">グループ</th>
		<th align="left">メールアドレス</th>
		<th align="left" width="70px">電話番号</th>
		<th align="left">日本語能力 <a href="">▲▽</a></th>
		<th align="left">失効日 <a href="">△▼</a></th>
		<th align="left">点数</th>
	</tr>

	<c:set var="flag" value="${requestScope.flag}"></c:set>
	<c:choose>
		<c:when test="${flag > 0}">
			<tr>
				<td colspan="9" class="errMsg">${message}</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${listUser}" var="user">
				<tr>
					<td align="right"><a href="ADM005.html">${user.id}</a></td>
					<td>${user.fullName}</td>
					<td align="center">${user.birthday}</td>
					<td>${user.groupName}</td>
					<td>${user.email}</td>
					<td>${user.tel}</td>
					<td>${user.nameLevel}</td>
					<td align="center">${user.endDate}</td>
					<td align="right">${user.total}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>

</table>
<!-- End vung hien thi danh sach user -->

<!-- Begin vung paging -->
<table>
	<tr>
		<!--  <td class="lbl_paging"><%--<%=request.getAttribute("strPaging")%>--%></td> -->
		<c:set var="totalPage" value="${requestScope.totalPage}"></c:set>
		<c:set var="listPaging" value="${requestScope.listPaging}"></c:set>
		<c:set var="page" value="${requestScope.page}"></c:set>
		<td class = "lbl_paging">
			<c:forEach items="${listPaging}" var="paging">
				<a href="#">${paging}</a>&nbsp;
			</c:forEach>
		</td>
	</tr>
</table>
<!-- End vung paging -->

<jsp:include page="footer.jsp" />