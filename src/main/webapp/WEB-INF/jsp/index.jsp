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
<body>
	<div class="layui-carousel" id="bgi">
	  <div carousel-item>
	    <div><img src="../assets/img/bgi1.jpg" style="width:100%;height: 100%;"/></div>
	    <div><img src="../assets/img/bgi2.jpg" style="width:100%;height: 100%;" /></div>
	    <div><img src="../assets/img/bgi3.jpg" style="width:100%;height: 100%;" /></div>
	    <div><img src="../assets/img/bgi4.jpg" style="width:100%;height: 100%;" /></div>
	    <div><img src="../assets/img/bgi5.jpg" style="width:100%;height: 100%;" /></div>
	  </div>
	</div>
	<div class="layui-card" style="width: 400px;top: 150px;left: 65%;position: absolute;">
	  <div class="layui-card-header" style="text-align: center;"><h1>欢迎登陆</h1></div>
	  <div class="layui-card-body">
	    <form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">账号:</label>
				<div class="layui-input-inline">
					<input type="text" id="phone" required lay-verify="required" value="12345678901" placeholder="请输入账号" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码:</label>
				<div class="layui-input-inline">
					<input type="password" id="password" required lay-verify="required" value="123456" placeholder="请输入密码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="text-align: center;">
				<button type="button" class="layui-btn" onclick="doLogin()" style="width: 150px;">普通用户登陆</button>
				<button type="button" class="layui-btn layui-btn-normal" onclick="doLogin2()" style="width: 150px;">管理员登陆</button>
			</div>
			<div class="layui-form-item" style="text-align: center;">
				<button type="button" class="layui-btn layui-btn-danger layui-btn-fluid" onclick="register()" style="width:318px;">普通用户注册</button>
			</div>
		</form>
	  </div>
	</div>
	<!-- 初始化弹框 -->
	<script type="text/javascript">
		layui.use('layer', function() {
			var layer = layui.layer;
		});
		layui.use('carousel', function(){
			  var carousel = layui.carousel;
			  //建造实例
			  carousel.render({
			    elem: '#bgi'
			    ,width: '100%' //设置容器宽度
			    ,height: '100%'
			    ,arrow: 'always' //始终显示箭头
			    //,anim: 'updown' //切换动画方式
			  });
			});
	</script>
	<script type="text/javascript">
		/* 普通用户登录 */
		function doLogin() {
			var phone = $("#phone").val().trim();
			if (phone == '' || phone == undefined) {
				layer.msg("账号不为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var password = $("#password").val().trim();
			if (password == '' || password == undefined) {
				layer.msg("密码不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../user/doLogin",
				data : {
					"phone" : phone,
					"password" : password
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == 1) {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						window.location.href="../main";
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
		
		/* 管理员登录 */
		function doLogin2() {
			var phone = $("#phone").val().trim();
			if (phone == '' || phone == undefined) {
				layer.msg("账号不为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var password = $("#password").val().trim();
			if (password == '' || password == undefined) {
				layer.msg("密码不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../admin/doLogin",
				data : {
					"phone" : phone,
					"password" : password
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == 1) {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						window.location.href="../main";
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
		
		//注册页面
		function register(){
			window.location.href="../register";
		}
	</script>
</body>
</html>
