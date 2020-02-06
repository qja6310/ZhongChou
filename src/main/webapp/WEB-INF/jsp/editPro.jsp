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
	left: 604px;
}

.center {
	text-align: center;
}

.lw {
	width: 110px;
}
</style>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend style="font-weight: 500;">审核详情</legend>
	</fieldset>
	<div>
		<table class="layui-table">
			<tbody>
				<tr>
					<th style="width: 5%;">审核人</th>
					<td>${al.adminName }</td>
					<th style="width: 5%;">审核结果</th>
					<td>
						<c:if test="${al.status == 3 }">审核不通过</c:if>
						<c:if test="${al.status == 2 }">审核通过</c:if>
						<c:if test="${al.status == 1 }">待审核</c:if>
					</td>
					<th style="width: 5%;">审核时间</th>
					<td>${al.auditTime }</td>
				</tr>
				<tr>
					<th style="width: 5%;">审核说明</th>
					<td colspan="6">${al.explains }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend style="font-weight: 500;">项目信息</legend>
	</fieldset>
	<form class="layui-form" action="">
		<input type="hidden" id="id" value="${pro.id }" />
		<div class="layui-container fm">
			<div class="layui-row">
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">患者姓名</label>
						<div class="layui-input-inline">
							<input type="text" id="name" name="name" value="${pro.patientName }" class="layui-input" maxlength="16">
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">患者年龄</label>
						<div class="layui-input-inline">
							<input type="text" id="age" name="age" value="${pro.patientAge }" class="layui-input" maxlength="3" style="text-align: right;">
						</div>
						<div class="layui-form-mid layui-word-aux">岁</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">患者性别</label>
						<div class="layui-input-inline">
							<input type="radio" name="sex" value="1" title="男" <c:if test="${pro.patientSex == 1 }">checked</c:if>>
							<input type="radio" name="sex" value="0" title="女" <c:if test="${pro.patientSex == 0 }">checked</c:if>>
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">联系号码</label>
						<div class="layui-input-inline">
							<input type="text" id="phone" name="phone" value="${pro.phone }" class="layui-input" maxlength="11" 
							oninput="value=value.replace(/[^\d]|^[0]/g,'')">
						</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">身份证号</label>
						<div class="layui-input-inline">
							<input type="text" id="identityNum" name="identityNum" value="${pro.identityNum }" class="layui-input" maxlength="18">
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">目标金额</label>
						<div class="layui-input-inline">
							<input type="text" id="targetMoney" name="targetMoney" class="layui-input" value="${pro.targetMoney }"
							 onkeyup="var p2 = parseFloat(value).toFixed(2);value = p2>=0?(/\.0?$/.test(value)?value:p2.replace(/0$/,'').replace(/\.0$/,'')):''" 
							 onblur="value = value.replace(/\.0*$/,'')"
							 maxlength="10" style="text-align: right;">
						</div>
						<div class="layui-form-mid layui-word-aux">元</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">就诊医院</label>
						<div class="layui-input-inline">
							<input type="text" id="hospital" name="hospital" value="${pro.hospital }" class="layui-input" maxlength="255"
								style="width: 400px;">
						</div>
					</div>
				</div>
				<div class="layui-col-md6">
					<div class="layui-form-item">
						<label class="layui-form-label lw">捐款截止日期</label>
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="endTime" value="${pro.endTime }" readonly>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-row">
				<div class="layui-col-md12">
					<div class="layui-form-item">
						<label class="layui-form-label lw">现住址</label>
						<div class="layui-input-inline">
							<textarea id="nowAddress" name="nowAddress" placeholder="请输入地址" class="layui-textarea"
								style="width: 750px;" maxlength="255">${pro.nowAddress }</textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label lw">申请详情 <i
					class="layui-icon layui-icon-tips" style="color: #FF5722;"></i> <font
					style="color: #FF5722; font-size: 12px;">
						请尽量详细填写患者个人资料(姓名、年龄、性别、身份证号等)、病情、就诊医院、家庭背景、详细地址以及联系电话等详情,并上传至少一张有效图片,以便
						他人了解具体情况;若资料填写不全,将会审核不通过,如果三次没有审核过,该项目将不再更改且无法发布,并面临拉黑风险!!! </font>
				</label>
				<div class="layui-input-inline" style="width: 1000px;">
					<div style="margin-bottom: 0; background-color: white;">
						<textarea id="asdfg" style="display: none;">${pro.details }</textarea>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="text-align: end;">
				<button type="button" class="layui-btn" onclick="editProject()" id="btn">
					<i class="layui-icon layui-icon-ok"></i> 确认提交
				</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
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
		//Demo
		layui.use('form', function() {
			var form = layui.form;

			//监听提交
			form.on('submit(formDemo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});
		});
		
		var currTime = myformatter(new Date());
	    function myformatter(date){
	        var y = date.getFullYear();
	        var m = date.getMonth()+1;
	        var d = date.getDate();
	        var h = date.getHours();
	        var min = date.getMinutes();
	        var s = date.getSeconds();
	        var str=y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日  '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(s<10?('0'+s):s);
	        jQuery(".time").html(str);
	        setTimeout("myformatter(new Date());", 1000);
	        return str;
	    }
	    
		layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  
			  //执行一个laydate实例
			  laydate.render({
			    elem: '#endTime', //指定元素
			    type: 'datetime',
			    min: currTime
			  });
			});
	</script>
	<script type="text/javascript">
		var index;
		var layedit;
		layui.use('layedit', function() {
			layedit = layui.layedit;
			layedit.set({
				uploadImage : {
					url : '../imageUpload',//接口url
					type : 'post' //默认post
				}
			});
			layedit.build('asdfg'); //建立编辑器
			index = layedit.build('asdfg', {
				height : 480, //设置编辑器高度
				tool : [ 'strong' //加粗
				, 'italic' //斜体
				, 'left' //左对齐
				, 'center' //居中对齐
				, 'right' //右对齐
				, 'image' //插入图片
				, '|' //分割线
				, 'del' //删除线
				, 'underline' //下划线
				]
			});
		});
		
		function clearNoNum(obj) {
			obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
			obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
			obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
		}
		//提交
		function editProject(){
			var id = $("#id").val();
			if(id == '' || id == undefined){
				layer.msg("缺失关键字段", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var name = $("#name").val().trim();
			if(name == '' || name == undefined){
				layer.msg("姓名不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var age = $("#age").val().trim();
			if(age == '' || age == undefined){
				layer.msg("年龄不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var sex = $("input[name='sex']:checked").val(); 
			var phone = $("#phone").val().trim();
			if(phone == '' || phone == undefined){
				layer.msg("联系号码不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var targetMoney = $("#targetMoney").val().trim();
			if(phone == '' || phone == undefined){
				layer.msg("目标金额不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var identityNum = $("#identityNum").val().trim();
			if(identityNum == '' || identityNum == undefined){
				layer.msg("身份证号不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var hospital = $("#hospital").val().trim();
			if(hospital == '' || hospital == undefined){
				layer.msg("就诊医院不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var endTime = $("#endTime").val().trim();
			if(endTime == '' || endTime == undefined){
				layer.msg("捐款日期截止不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var nowAddress = $("#nowAddress").val().trim();
			if(nowAddress == '' || nowAddress == undefined){
				layer.msg("现住址不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var details = layedit.getContent(index);
			if (details == '' || details == undefined) {
				layer.msg("详情不为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../projects/doEditProject",
				data : {
					"id" : id,
					"name" : name,
					"age" : age,
					"sex" : sex,
					"phone" : phone,
					"targetMoney" : targetMoney,
					"identityNum" : identityNum,
					"hospital" : hospital,
					"endTime" : endTime,
					"nowAddress" : nowAddress,
					"details" : details
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						$("#btn").text("提交成功");
						$("#btn").addClass("layui-btn-disabled");
						$("#btn").attr("disabled",true);
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
