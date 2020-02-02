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
</style>
</head>
<body>
	<input type="hidden" id="wlId" value="${wl.id }"/>
	<div class="layui-form">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend style="font-weight: 500;">基本信息</legend>
		</fieldset>
		<div>
			<table class="layui-table">
				<tbody>
					<tr>
						<th>申请人</th>
						<td>${wl.userName }</td>
						<th>患者姓名</th>
						<td>${wl.patientName }</td>
						<th>联系电话</th>
						<td>${wl.phone }</td>
						<th>提现金额</th>
						<td>${wl.money } 元</td>
					</tr>
					<tr>
						<th>提现申请时间</th>
						<td>${wl.applyTime }</td>
						<th>审核提现时间</th>
						<td>${wl.auditTime }</td>
						<th>提现审核人</th>
						<td>${wl.txAdminName }</td>
						<th>当前状态</th>
						<td>
							<c:if test="${wl.status == 1}">提现待审核</c:if>
							<c:if test="${wl.status == 2}">提现审核通过</c:if>
							<c:if test="${wl.status == 3}">提现审核未通过</c:if>					
							<c:if test="${wl.status == 4}">凭证提交待审核</c:if>
							<c:if test="${wl.status == 5}">凭证审核不通过</c:if>
							<c:if test="${wl.status == 0}">完结</c:if>		
						</td>
	
					</tr>
					<tr>
						<th>凭证提交时间</th>
						<td>${wl.pztjTime }</td>
						<th>凭证审核时间</th>
						<td>${wl.pzshTime }</td>
						<th>凭证审核人</th>
						<td>${wl.pzAdminName }</td>
						<th>银行卡号</th>
						<td>${wl.yhCardNum }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend style="font-weight: 500;">凭证区</legend>
		</fieldset>
		<div style="text-align: center;">${wl.evidence }</div>
		<c:if test="${wl.status == 4 }">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend style="font-weight: 500;">审核</legend>
			</fieldset>
			<div class="layui-form-item" style="text-align: center;">
				<button type="button" class="layui-btn" onclick="pzAuditPass()" id="btn1"><i class="layui-icon layui-icon-auz"></i> 审核通过</button>
				<button type="button" class="layui-btn layui-btn-danger" onclick="pzAuditNotPass()" id="btn2"><i class="layui-icon layui-icon-close-fill"></i> 审核不通过</button>
			</div>
		</c:if>
	</div>
	<script>
		layui.use('layer', function() {
			var layer = layui.layer;
		});
	</script>
	
	<script type="text/javascript">
		var index;
		var layedit ;
		layui.use('layedit', function() {
			layedit = layui.layedit;
			layedit.set({
			    uploadImage: {
				    url: '../imageUpload',//接口url
				    type: 'post' //默认post
				}
		    });
			layedit.build('asdfg'); //建立编辑器
			index = layedit.build('asdfg', {
				height: 480, //设置编辑器高度
				tool: [
					  'strong' //加粗
					  ,'italic' //斜体
					  ,'left' //左对齐
					  ,'center' //居中对齐
					  ,'right' //右对齐
					  ,'image' //插入图片
					  ,'|' //分割线
					  ,'del' //删除线
					  ,'underline' //下划线
				]
			});
		});
		function pzAuditNotPass(){
			var wlId = $("#wlId").val();
			$.ajax({
				type : "post",
				url : "../fund/pzAuditNotPass",
				data : {
					"wlId" : wlId
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						$("#btn1").addClass("layui-btn-disabled");
						$("#btn1").attr("disabled",true);
						$("#btn2").addClass("layui-btn-disabled");
						$("#btn2").attr("disabled",true);
					} else {
						layer.msg(data.retMsg, {
							icon : 2,
							time : 2000,
						});
					}
				},
				error : function(data) {
					layer.msg("登陆失败", {
						icon : 2,
						time : 2000,
					});
					return;
				}
			});
		}
		function pzAuditPass(){
			var wlId = $("#wlId").val();
			$.ajax({
				type : "post",
				url : "../fund/pzAuditPass",
				data : {
					"wlId" : wlId
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						$("#btn1").addClass("layui-btn-disabled");
						$("#btn1").attr("disabled",true);
						$("#btn2").addClass("layui-btn-disabled");
						$("#btn2").attr("disabled",true);
					} else {
						layer.msg(data.retMsg, {
							icon : 2,
							time : 2000,
						});
					}
				},
				error : function(data) {
					layer.msg("登陆失败", {
						icon : 2,
						time : 2000,
					});
					return;
				}
			});
		}
	</script>
</body>
</html>
