<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEBINF/tlds/MyTaglib" prefix="my"%>
<c:set value="<%=request.getContextPath()%>" var="url"></c:set>

<c:choose>
	<c:when test="${flag == null }">
		<select class="selectpicker" data-width="fit" name="wStartId">
			<c:forEach items="${lisWeeks }" var="week">
				<option value="${week.weekId }">${week.weekCount } (${my:getDay(week.startDate) })</option>
			</c:forEach>
		</select>
	</c:when>
	<c:otherwise>
		<select class="selectpicker" data-width="fit" name="wStartId">
			<option value="">${ scheStu.wStart.weekCount} (${my:getDay(scheStu.wStart.startDate) })</option>
		</select>
	</c:otherwise>
</c:choose>
đến
<c:choose>
	<c:when test="${flag == null }">
		<select class="selectpicker" data-width="fit" name="wEndId">
			<c:forEach items="${lisWeeks }" var="week">
				<option value="${week.weekId }">${week.weekCount } (${my:getDay(week.startDate) })</option>
			</c:forEach>
		</select>
	</c:when>
	<c:otherwise>
		<select class="selectpicker" data-width="fit" name="wEndId">
			<option value="">${ scheStu.wEnd.weekCount} (${my:getDay(scheStu.wEnd.startDate) })</option>
		</select>
	</c:otherwise>
</c:choose>
