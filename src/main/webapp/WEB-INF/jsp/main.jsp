<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>众筹</title>
<link rel="stylesheet" href="../assets/layui/css/layui.css">
<link rel="stylesheet" href="../assets/zui/css/zui.min.css">
<script type="text/javascript" src="../assets/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../assets/layui/layui.js"></script>
<script type="text/javascript" src="../assets/zui/js/zui.min.js"></script>
<style>
	.menu{
		cursor:pointer;
		text-decoration:none;
	}
	.warn{
		color: rgb(255,87,34);
		margin-top: 20px;
	}
</style>
</head>
<body style="background-color: #eeeeee;">
	<input type="hidden" id="roleId" value="${user.id }">
	<input type="hidden" id="jump" value="${jump }">
	<input type="hidden" id="domoney" value="${money }">
	<input type="hidden" id="retCode" value="${retCode }">
	<input type="hidden" id="retMsg" value="${retMsg }">
	<input type="hidden" id="projectId" value="${proId }">
	<div class="layui-layout layui-layout-admin">
		<input type="hidden" id="userId" value="${user.id }" />
		<div class="layui-header">
			<div class="layui-logo" style="font-size: 30px;font-weight: 700;">爱心筹</div>
			<!-- <div class="layui-logo" style="margin-left: 100px;">
				<i class="layui-icon layui-icon-prev" id="hiddenIcon" onclick="isShowLeft('hid')"></i>
				<i class="layui-icon layui-icon-next" id="showIcon" style="display: none;" onclick="isShowLeft('show')"></i>
			</div> -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a onclick="news()">实时新闻</a>
					<c:if test="${user.role == 'a' }">
						<dl class="layui-nav-child">
							<dd>
								<a onclick="newsManage()">新闻管理</a>
							</dd>
						</dl>
					</c:if>
				</li>
				<li class="layui-nav-item"><a onclick="projects()">筹款项目</a>
					<c:if test="${user.role == 'a' }">
						<dl class="layui-nav-child">
							<dd>
								<a onclick="projectManage()">项目管理</a>
							</dd>
							<dd>
								<a onclick="fundManage()">资金管理</a>
							</dd>
						</dl>
					</c:if>
					<c:if test="${user.role == 'u' }">
						<dl class="layui-nav-child">
							<dd>
								<a onclick="applyProject()">项目申报</a>
							</dd>
							<dd>
								<a onclick="myPro()">我的项目</a>
							</dd>
							<dd>
								<a onclick="myFundManage()">提现申请</a>
							</dd>
						</dl>
					</c:if>
				</li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<c:choose>
						<c:when test="${user.id != null && user.id != ''}"><a>${user.name }
						<c:if test="${user.role=='u' }">(普通用户)</c:if><c:if test="${user.role=='a' }">(管理员)</c:if></a></c:when>
						<c:otherwise><a href="../index">请登录</a></c:otherwise>
					</c:choose>
					<c:if test="${user.id != null && user.id != ''}">
						<dl class="layui-nav-child">
							<dd>
								<c:if test="${user.role=='u' }"><a onclick="updateUser()">个人信息</a></c:if>
								<c:if test="${user.role=='a' }"><a onclick="updateAdmin()">个人信息</a></c:if>
							</dd>
						</dl>
					</c:if>
				</li>
				<li class="layui-nav-item">
					<c:choose>
						<c:when test="${user.id != null && user.id != ''}"><a><i class="layui-icon layui-icon-logout" onclick="logout()"></i></a></c:when>
						<c:otherwise><a href="../index"><i class="layui-icon layui-icon-username"></i></a></c:otherwise>
					</c:choose>
				</li>
			</ul>
			<%-- <c:if test="${haveWarn == true }">
				<div style="margin-left: 20%; margin-right: 20%;">
	        		<marquee direction="left" class="warn" align="bottom" height="25" width="100%" onmouseout="this.start()" onmouseover="this.stop()" scrollamount="6" scrolldelay="1">
	        			<!-- <i class="icon icon-warning-sign"> 分析所有评论,您的原文已走向负面,您可以重新 <a onclick="setWarning()" style="font-style: oblique;">设置预警</a> !!!</i>  -->
	        			<i class="icon icon-warning-sign"> 您所设定的预警分值(${wScore }) 条件评论达到预警条件，请及时 <a onclick="setWarning()" style="font-style: oblique;">查看处理</a> </i>
	        		</marquee>
	        	</div>
        	</c:if> --%>
		</div>
        
		<%-- <div class="layui-side layui-bg-black" id="menuDiv">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item" onclick="aaa()"><a>实时新闻</a></li>
					<li class="layui-nav-item"><a>众筹项目</a></li>
					<!-- 管理员 -->
					<c:if test="${user.role == 'a' }">
						<li class="layui-nav-item"><a>新闻管理</a></li>
					</c:if>
					<c:if test="${user.role == 'a' }">
						<li class="layui-nav-item"><a>项目管理</a></li>
					</c:if>
					<!-- /管理员 -->
					
					<!-- 普通用户 -->
					<c:if test="${user.role == 'u' }">
						<li class="layui-nav-item"><a>项目管理</a>
							<dl class="layui-nav-child">
								<dd><a class="menu">项目申报</a></dd>
								<dd><a class="menu">资金管理</a></dd>
								<dd><a class="menu">项目凭证</a></dd>
							</dl>
						</li>
					</c:if>
					<!-- /普通用户 -->
				</ul>
			</div>
		</div> --%>

		<div>
			<!-- 内容主体区域 -->
			<div style="padding: 15px;" id="content">
				
			</div>
		</div>
	</div>
	<!-- 初始化弹框 -->
	<script type="text/javascript">
		layui.use('layer', function(){ //独立版的layer无需执行这一句
			layer = layui.layer; //独立版的layer无需执行这一句
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
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
	<script type="text/javascript">
		$(document).ready(function () {
			if($("#jump").val() == 'donation'){
				jumpDonation();
			}else{
				news();
			}
	    });
		function updateUser(){
			var userId = $("#roleId").val();
			$('#content').load('../user/updateUser?roleId='+userId);  
		}
		function updateAdmin(){
			var userId = $("#roleId").val();
			$('#content').load('../admin/updateAdmin?roleId='+userId);  
		}
		function newsManage(){
			$('#content').load('../news/newsManage');  
		}
		function applyProject(){
			var userId = $("#roleId").val();
			$('#content').load('../projects/applyProject?roleId='+userId);  
		}
		function news(){
			$('#content').load('../news/');
		}
		function projects(){
			$('#content').load('../projects/');
		}
		function projectManage(){
			$('#content').load('../projects/projectManage');
		}
		function myPro(){
			var userId = $("#roleId").val();
			$('#content').load('../projects/myProject?userId='+userId);
		}
		function jumpDonation(){
			var proId = $("#projectId").val();
			var retCode = $("#retCode").val();
			var retMsg = $("#retMsg").val();
			var money = $("#domoney").val();
			$('#content').load('../projects/details2?id='+proId+'&retCode='+retCode+'&retMsg='+retMsg+'&money='+money);
		}
		function fundManage(){
			$('#content').load('../fund/fundManage');
		}
		function myFundManage(){
			var userId = $("#roleId").val();
			$('#content').load('../fund/myFundManage?userId='+userId);
		}
		//菜单显示隐藏
		/* function isShowLeft(index){
			if(index == 'hid'){
				$("#menuDiv").hide(1000);
				$("#hiddenIcon").hide();
				$("#showIcon").show();
			}else{
				$("#menuDiv").show(1000);
				$("#hiddenIcon").show();
				$("#showIcon").hide();
			}
		} */
		
		function logout(){
			//layer.confirm('真的要离开嘛?', {icon: 3, title:'提示'}, function(index){
				window.location.href="../logout";
				//layer.close(index);
			//});
		}
	</script>
</body>
</html>
