<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<style type="text/css">
.fm {
	margin-right: 20%;
}

.bp {
	position: relative;
	top: -38px;
	left: 204px;
}
.bpi {
	position: relative;
	top: 31px;
    left: 7px;
}
</style>
</head>
<body>
	<input id="proId" type="hidden" value="${pro.id }" />
	<input type="hidden" id="retCode" value="${retCode }">
	<input type="hidden" id="retMsg" value="${retMsg }">
	<c:if test="${user.role != 'a' && flag == 1}">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 40px;margin-bottom: -20px;">
			<legend style="font-weight: 500;">献爱心</legend>
		</fieldset>
		<div>
			<i class="layui-icon layui-icon-rmb bpi" style="color: #FF5722;"></i>
			<input type="text" id="money" name="money" class="layui-input"
				style="width: 200px;text-align: right; color: #FF5722;"
				onkeyup="var p2 = parseFloat(value).toFixed(2);value = p2>=0?(/\.0?$/.test(value)?value:p2.replace(/0$/,'').replace(/\.0$/,'')):''"
				onblur="value = value.replace(/\.0*$/,'')" value="${money }" maxlength="10">
			<button type="button" class="layui-btn bp" onclick="toDonation()">
				<i class="layui-icon layui-icon-heart"></i> 捐献
			</button>
		</div>
	</c:if>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend style="font-weight: 500;">基本信息</legend>
	</fieldset>
	<div>
		<table class="layui-table">
			<tbody>
				<tr>
					<th>申请人</th>
					<td>${pro.userName }</td>
					<th>患者姓名</th>
					<td>${pro.patientName }</td>
					<th>患者年龄</th>
					<td>${pro.patientAge }</td>
					<th>患者性别</th>
					<td><c:if test="${pro.patientSex == 1}">男</c:if> <c:if
							test="${pro.patientSex == 0}">女</c:if></td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td>${pro.phone }</td>
					<th>身份证号</th>
					<td>${pro.identityNum }</td>
					<th>目标金额</th>
					<td>${pro.targetMoney } 元</td>
					<th>截止时间</th>
					<td>${pro.endTime }</td>

				</tr>
				<tr>
					<th>就诊医院</th>
					<td>${pro.hospital }</td>
					<th>现住址</th>
					<td>${pro.nowAddress }</td>
					<th>当前筹集金额</th>
					<td>${pro.totalMoney } 元</td>
					<th>申请时间</th>
					<td>${pro.applyTime }</td>
				</tr>
				<tr>
					<th>病症</th>
					<td colspan="7">${pro.disease }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 40px;">
		<legend style="font-weight: 500;">患者病况详情</legend>
	</fieldset>
	<div>${pro.details }</div>
	<script>
		layui.use('layer', function() {
			var layer = layui.layer;
		});
		layui.use('carousel', function() {
			var carousel = layui.carousel;
			//建造实例
			carousel.render({
				elem : '#bgi',
				width : '100%' //设置容器宽度
				,
				height : '100%',
				arrow : 'always' //始终显示箭头
			//,anim: 'updown' //切换动画方式
			});
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function () {
			if($("#retCode").val() != '' && $("#retCode").val() != undefined){
				var retMsg = $("#retMsg").val();
				if($("#retCode").val() == '0000'){
					layer.msg(retMsg, {
						icon : 1,
						time : 2000,
					});
					return;
				}else{
					layer.msg(retMsg, {
						icon : 2,
						time : 2000,
					});
					return;
				}
			}
	    });
		function toDonation() {
			var roleId = $("#roleId").val();
			if(roleId == '' || roleId == undefined){
				layer.msg("请您先登陆再献爱心", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var proId = $("#proId").val();
			if(proId == '' || proId == undefined){
				layer.msg("缺少关键字段", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var money = $("#money").val();
			if(money == '' || money == undefined){
				layer.msg("请您输入捐款金额", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			window.location.href="../toDonation?proId="+proId+'&money='+money;
		}
	</script>
</body>
</html>
