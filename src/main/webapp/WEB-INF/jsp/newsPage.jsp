<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(newsList)>0 }">
		<c:forEach items="${newsList}" var="item" varStatus="status">
			<div class="layui-card">
				<div class="layui-card-header" style="padding: 1px 0 0 10px;"><a onclick="newsDetails('${item.id}')"><h3>${item.title }</h3></a></div>
				<div class="layui-card-body">
					<a onclick="newsDetails('${item.id}')">${item.content }</a>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<a>查无此数据</a>
	</c:otherwise>
</c:choose>
