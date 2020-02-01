<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(proList)>0 }">
		<c:forEach items="${proList}" var="item" varStatus="status">
			<div class="layui-card">
				<div class="layui-card-header" style="padding: 1px 0 0 10px;"><a onclick="proDetails('${item.id}')">
					<h3>${item.patientName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test='${item.patientSex == 0 }'>女</c:if><c:if test='${item.patientSex == 1 }'>男</c:if>
					&nbsp;&nbsp;&nbsp;
					${item.patientAge } 岁
					&nbsp;&nbsp;&nbsp;
					目标金额:${item.targetMoney }
					&nbsp;&nbsp;&nbsp;
					当前筹集金额:${item.totalMoney }
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					截止捐款日期：${item.endTime }</h3>
				</a></div>
				<div class="layui-card-body">
					<a onclick="proDetails('${item.id}')">${item.details }</a>
				</div>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<a>查无此数据</a>
	</c:otherwise>
</c:choose>
