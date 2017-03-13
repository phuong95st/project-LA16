<%@page import="entity.Hol"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Map<Long,Hol> listAddHol = (Map<Long,Hol>)session.getAttribute("listAddHol");
				if(listAddHol != null && listAddHol.size() != 0){
%>
<button class="btn btn-success" onclick="javascript:registerHol()">Register</button>
<%
	}else{
%>
<button class="btn btn-success disabled">Register</button>
<%
	}
%>