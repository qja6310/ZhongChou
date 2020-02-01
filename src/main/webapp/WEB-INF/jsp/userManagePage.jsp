<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(userList)>0 }">
		<c:forEach items="${userList}" var="item" varStatus="status">
			<tr>
				<td class="center">${status.index + 1 }</td>
				<td>${item.name }</td>
				<td>${item.age }</td>
				<td>
					<c:if test="${item.sex == 1}">男</c:if>
					<c:if test="${item.sex == 0}">女</c:if>
				</td>
				<td>${item.phone }</td>
				<td>
					<c:if test="${item.status == 1}">正常</c:if>
					<c:if test="${item.status == 2}">已拉黑</c:if>
				</td>
				<td>${item.createTime }</td>
				<td>${item.btgxmCount }</td>
				<td class="center">
					<c:if test="${item.btgxmCount >= 3 && item.status == 1}">
						<button type="button" class="layui-btn layui-btn-sm layui-btn-danger" onclick="lhUser('${item.id}')">
							<i class="layui-icon layui-icon-delete"></i> 拉黑
						</button>
					</c:if>
					<c:if test="${item.status == 2}">
						<button type="button" class="layui-btn layui-btn-sm layui-btn-warm" onclick="lhzcUser('${item.id}')">
							<i class="layui-icon layui-icon-ok-circle"></i> 拉回正常
						</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="9">查无此数据</td></tr>
	</c:otherwise>
</c:choose>
