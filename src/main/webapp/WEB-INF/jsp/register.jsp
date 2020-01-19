<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
<link rel="stylesheet" href="../assets/layui/css/layui.css">
<script type="text/javascript" src="../assets/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../assets/layui/layui.js"></script>
</head>
<body style="background-color: #dddddd;">
	<div style="margin-top: 30px; margin-left: 50px;">
		<a href="../index"><i class="layui-icon layui-icon-prev"></i>返回登陆页</a>
	</div>
	<div class="layui-card"
		style="width: 400px; top: 150px; left: 40%; position: absolute;">
		<div class="layui-card-header" style="text-align: center;">
			<h1>请填写信息</h1>
		</div>
		<div class="layui-card-body">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" id="name" required lay-verify="required"
							placeholder="请输入姓名" autocomplete="off" class="layui-input" maxlength="16">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年龄:</label>
					<div class="layui-input-inline">
						<input type="text" id="age" required lay-verify="required"
							placeholder="请输入年龄" autocomplete="off" class="layui-input" maxlength="3"
							oninput="value=value.replace(/[^\d]|^[0]/g,'')">
					</div>
					<div class="layui-form-mid layui-word-aux">岁</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别:</label>
					<div class="layui-input-block" id="sex">
						<input type="radio" name="sex" value="1" title="男" checked>
						<input type="radio" name="sex" value="0" title="女">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号:</label>
					<div class="layui-input-inline">
						<input type="text" id="phone" required lay-verify="required"
							placeholder="请输入手机号" autocomplete="off" class="layui-input" maxlength="11" 
							oninput="value=value.replace(/[^\d]|^[0]/g,'')">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码:</label>
					<div class="layui-input-inline">
						<input type="password" id="password" required
							lay-verify="required" placeholder="请输入密码" autocomplete="off"
							class="layui-input" maxlength="16">
					</div>
				</div>
				<div class="layui-form-item" style="text-align: center;">
					<button type="button" class="layui-btn" onclick="sureRegister()"
						style="width: 300px;">确认注册</button>
				</div>
			</form>
		</div>
	</div>
	<!-- 初始化弹框 -->
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
	</script>
	<script type="text/javascript">
		//注册页面
		function sureRegister() {
			var name = $("#name").val();
			if(name == '' || name == undefined){
				layer.msg("姓名不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var age = $("#age").val();
			if(name == '' || name == undefined){
				layer.msg("年龄不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var sex = $("input[name='sex']:checked").val(); 
			var phone = $("#phone").val();
			if(name == '' || name == undefined){
				layer.msg("手机号不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var password = $("#password").val();
			if(name == '' || name == undefined){
				layer.msg("密码不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../user/addUser",
				data : {
					"name" : name,
					"age" : age,
					"sex" : sex,
					"phone" : phone,
					"password" : password
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						window.location.href="../index";
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
