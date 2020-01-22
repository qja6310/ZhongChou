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
	<input id="proId" type="hidden" value="${pro.id }" />
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
					<td>${pro.targetMoney }</td>
					<th>截止时间</th>
					<td>${pro.endTime }</td>

				</tr>
				<tr>
					<th>就诊医院</th>
					<td>${pro.hospital }</td>
					<th>现住址</th>
					<td>${pro.nowAddress }</td>
					<th>拒绝次数</th>
					<td>${pro.refuseCount }</td>
					<th>申请时间</th>
					<td>${pro.applyTime }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 40px;">
		<legend style="font-weight: 500;">患者病况详情</legend>
	</fieldset>
	<div>${pro.details }</div>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 40px;">
		<legend style="font-weight: 500;">审核</legend>
	</fieldset>
	<div>
		<form class="layui-form" lay-filter="form3">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 90px;">审核结果</label>
				<div class="layui-input-block" id="status">
					<input type="radio" name="status" value="1" title="审核不过" checked>
					<input type="radio" name="status" value="2" title="审核通过">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label" style="width: 90px;">审核说明</label>
				<div class="layui-input-block" style="margin-right: 100px;">
					<textarea placeholder="请输入内容" class="layui-textarea" name="explain" id="explain"></textarea>
				</div>
			</div>
			<div class="layui-form-item"  style="margin-right: 100px;text-align: right;">
				<button type="button" class="layui-btn" onclick="audit()"><i class="layui-icon layui-icon-auz"></i> 审核提交</button>
			</div>
		</form>
	</div>
	<script>
		layui.use('layer', function() {
			var layer = layui.layer;
		});
		layui.use('form', function() {
			var form = layui.form;
			layui.form.render('radio', 'form3');
		});
	</script>

	<script type="text/javascript">
		function audit() {
			var status = $("input[name='status']:checked").val();
			var id = $("#proId").val();
			$.ajax({
				type : "post",
				url : "../projects/audit",
				data : {
					"id" : id,
					"status" : status
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0010") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
					} else {
						layer.msg(data.retMsg, {
							icon : 2,
							time : 2000,
						});
					}
				},
				error : function(data) {
					layer.msg("操作失败", {
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
