<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(newsList)>0 }">
		<c:forEach items="${newsList}" var="item" varStatus="status">
			<tr>
				<td class="center">${status.index + 1 }</td>
				<td>${item.title }</td>
				<td class="center">${item.issueTime }</td>
				<td class="center">
					<button type="button" class="layui-btn layui-btn-warm" onclick="editNews('${item.id}')">
						<i class="layui-icon layui-icon-edit"></i> 编辑
					</button>
					<button type="button" class="layui-btn layui-btn-danger" onclick="delNews('${item.id}')">
						<i class="layui-icon layui-icon-delete"></i> 删除
					</button>
				</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="4">查无此数据</td></tr>
	</c:otherwise>
</c:choose>
