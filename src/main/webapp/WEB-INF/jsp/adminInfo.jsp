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
body {
	background: #eeeeee;	
}
.fm{
	width: 400px; 
	top: 100px; 
	left: 30%; 
	position: absolute;
	background-color: white;
	padding: 20px;
}
</style>
</head>
<body>
	<form class="layui-form fm" lay-filter="form2"> 
		<div class="layui-form-item">
			<label class="layui-form-label">姓名:</label>
			<div class="layui-input-inline">
				<input type="text" id="name" required lay-verify="required"
					placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${admin.name }"
					maxlength="16">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">年龄:</label>
			<div class="layui-input-inline">
				<input type="text" id="age" required lay-verify="required" value="${admin.age }"
					placeholder="请输入年龄" autocomplete="off" class="layui-input"
					maxlength="3" oninput="value=value.replace(/[^\d]|^[0]/g,'')">
			</div>
			<div class="layui-form-mid layui-word-aux">岁</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">性别:</label>
			<div class="layui-input-block" id="sex">
				<input type="radio" name="sex" value="1" title="男" <c:if test="${admin.sex == 1 }">checked</c:if>>
				<input type="radio" name="sex" value="0" title="女" <c:if test="${admin.sex == '0' }">checked</c:if>>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号:</label>
			<div class="layui-input-inline">
				<input type="text" id="phone" required lay-verify="required" value="${admin.phone }"
					placeholder="请输入手机号" autocomplete="off" class="layui-input"
					maxlength="11" oninput="value=value.replace(/[^\d]|^[0]/g,'')">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码:</label>
			<div class="layui-input-inline">
				<input type="password" id="password" required lay-verify="required" value="${admin.password }"
					placeholder="请输入密码" autocomplete="off" class="layui-input"
					maxlength="16">
			</div>
		</div>
		<div class="layui-form-item">
			<button type="button" class="layui-btn" onclick="saveUpdate()"
				style="width: 300px;margin-left: 30px;">保存修改</button>
		</div>
	</form>
	<script type="text/javascript">
		//Demo
		layui.use('form', function() {
			var form = layui.form;
			layui.form.render('radio','form2');
		});
		/* 登录 */
		function saveUpdate() {
			var id = $("#roleId").val();
			if(id == '' || id == undefined){
				layer.msg("缺失主要关键字", {
					icon : 1,
					time : 2000,
				});
			}
			var name = $("#name").val();
			if(name == '' || name == undefined){
				layer.msg("姓名不可为空", {
					icon : 1,
					time : 2000,
				});
			}
			var age = $("#age").val();
			if(name == '' || name == undefined){
				layer.msg("年龄不可为空", {
					icon : 1,
					time : 2000,
				});
			}
			var sex = $("input[name='sex']:checked").val(); 
			var phone = $("#phone").val();
			if(name == '' || name == undefined){
				layer.msg("手机号不可为空", {
					icon : 1,
					time : 2000,
				});
			}
			var password = $("#password").val();
			if(name == '' || name == undefined){
				layer.msg("密码不可为空", {
					icon : 1,
					time : 2000,
				});
			}
			$.ajax({
				type : "post",
				url : "../admin/saveUpdate",
				data : {
					"id" : id,
					"name" : name,
					"age" : age,
					"sex" : sex,
					"phone" : phone,
					"password" : password
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
					layer.msg("注册失败", {
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
