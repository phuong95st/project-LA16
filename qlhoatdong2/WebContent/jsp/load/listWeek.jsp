<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>
<div class="form-group">
	<label for="pwd">Tuần:</label> 
	<select class="selectpicker" data-width="fit" name="weekId">
	<c:forEach items="${listWeek }" var="week">
		<c:choose>
			<c:when test="${week.weekId == currentWeek.weekId }">
				<option value="${week.weekId }" selected="selected">${week.weekCount }
					(${my:getDay(week.startDate) })</option>
			</c:when>
			<c:otherwise>
				<option value="${week.weekId }">${week.weekCount }
					(${my:getDay(week.startDate) })</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
</div>

