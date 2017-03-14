
<%@page import="utils.Common"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="entity.Hol"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Map<Long,Hol> listAddHol = (Map<Long,Hol>)session.getAttribute("listAddHol");
	if(listAddHol != null && listAddHol.size() != 0){
		for(Entry<Long, Hol> item: listAddHol.entrySet()){
%>
<tr>
	<td><%=Common.getDay(item.getValue().getStart())%> - <%=Common.getDay(item.getValue().getEnd())%></td>
	<td><%=Common.mapType(item.getValue().getType())%></td>
	<td><%=item.getValue().getReason()%></td>
	<td>[<a style="cursor: pointer" onclick="javascript:delHol(<%= item.getValue().getId()%>)">Delete</a>]
	</td>
</tr>
<%
		}
	}
%>

