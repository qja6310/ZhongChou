<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(proList)>0 }">
		<c:forEach items="${proList}" var="item" varStatus="status">
			<tr>
				<td class="center">${status.index + 1 }</td>
				<td>${item.userName }</td>
				<td>${item.patientName }</td>
				<td>${item.patientAge }</td>
				<td>
					<c:if test="${item.patientSex == 1}">男</c:if>
					<c:if test="${item.patientSex == 0}">女</c:if>
				</td>
				<td>${item.targetMoney }</td>
				<td>${item.endTime }</td>
				<td>${item.phone }</td>
				<td>
					<c:if test="${item.status == 1}">待审核</c:if>
				</td>
				<td>${item.applyTime }</td>
				<td>${item.refuseCount }</td>
				<td class="center">
					<c:if test="${item.refuseCount < 3}">
						<button type="button" class="layui-btn layui-btn-sm layui-btn-warm" onclick="audit('${item.id}')">
							<i class="layui-icon layui-icon-auz"></i> 审核
						</button>
					</c:if>
					<button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="delNews('${item.id}')">
						<i class="layui-icon layui-icon-delete"></i> 删除
					</button>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="11">查无此数据</td></tr>
	</c:otherwise>
</c:choose>
