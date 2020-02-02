<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(wlList)>0 }">
		<c:forEach items="${wlList}" var="item" varStatus="status">
			<tr>
				<td class="center">${status.index + 1 }</td>
				<td>${item.userName }</td>
				<td>${item.patientName }</td>
				<td>${item.phone }</td>
				<td>${item.currentMoney }</td>
				<td>${item.money }</td>
				<td>${item.yhCardNum }</td>
				<td>
					<c:if test="${item.status == 1}">提现待审核</c:if>
					<c:if test="${item.status == 2}">提现审核通过</c:if>
					<c:if test="${item.status == 3}">提现审核未通过</c:if>					
					<c:if test="${item.status == 4}">凭证提交待审核</c:if>
					<c:if test="${item.status == 5}">凭证审核不通过</c:if>
					<c:if test="${item.status == 0}">完结</c:if>					
				</td>
				<td>${item.applyTime }</td>
				<td class="center">
					<c:if test="${item.status == 1}">
						<button type="button" class="layui-btn layui-btn-xs" onclick="auditWPass('${item.id}')">
							<i class="layui-icon layui-icon-auz"></i> 申请审核通过
						</button>
						<button type="button" class="layui-btn layui-btn-xs layui-btn-danger" onclick="auditWNotPass('${item.id}')">
							<i class="layui-icon layui-icon-close-fill"></i> 申请审核不通过
						</button>
					</c:if>
					<c:if test="${item.status == 4 || item.status == 0}">
						<button type="button" class="layui-btn layui-btn-xs layui-btn-normal" onclick="pzDetails('${item.id}')">
							<i class="layui-icon layui-icon-about"></i> 凭证详情
						</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="8">查无此数据</td></tr>
	</c:otherwise>
</c:choose>
