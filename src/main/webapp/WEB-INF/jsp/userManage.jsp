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
	width: 82px;
}
</style>
</head>
<body>
	<input type="hidden" id="total" value="${total }" />
	<form class="layui-form" lay-filter="form1">
		<div class="layui-row">
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">用户名</label>
					<div class="layui-input-inline">
						<input type="text" id="userName" name="userName"
							class="layui-input" maxlength="16">
					</div>
				</div>
			</div>
			<!-- <div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">性别</label>
					<div class="layui-input-inline">
						<select id="sex" name="sex" lay-verify="required">
							<option value=""></option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>
				</div>
			</div> -->
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">联系电话</label>
					<div class="layui-input-inline">
						<input type="text" id="phone" name="phone" class="layui-input"
							maxlength="16">
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">状态</label>
					<div class="layui-input-inline">
						<select id="status" name="status" lay-verify="required">
							<option value=""></option>
							<option value="1">正常</option>
							<option value="2">拉黑</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<div class="layui-input-inline">
						<button type="button" class="layui-btn" onclick="getTotal(1)">
							<i class="layui-icon layui-icon-search"></i> 搜索
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div>
		<table class="layui-table">
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th style="text-align: center; width: 4%;">序号</th>
					<th style="text-align: center;">姓名</th>
					<th style="text-align: center;">年龄</th>
					<th style="text-align: center;">性别</th>
					<th style="text-align: center;">电话</th>
					<th style="text-align: center;">状态</th>
					<th style="text-align: center;">创建时间</th>
					<th style="text-align: center;">审核未通过项目(个)</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="userDiv">

			</tbody>
		</table>
	</div>
	<div id="paging"></div>
	<script type="text/javascript">
		layui.use('form', function() {
			var form = layui.form;
			layui.form.render('select', 'form1');
		});
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			laydate.render({
				elem : '#applyTime',
				type : 'datetime',
				range : '~',
				format : 'yyyyMMddHHmmss'
			});
		});
		// 定义每页的记录数
		var limit = 10;
		var total = $("#total").val();
		var currPage = 1;
		var laypage;

		/*页面初始化*/
		$(document).ready(function() {
			getTotal(1);
			//newsManagePage();
		});

		function userManagePage() {
			var userName = $("#userName").val();
			//var sex = $("#sex").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			$('#userDiv').load(
					'../user/userManagePage?userName=' + userName
							+ '&currPage=' + currPage + '&limit=' + limit
							//+ '&sex=' + sex 
							+ '&phone=' + phone + '&status='
							+ status);
		}

		//查询数量的总数
		function getTotal(index) {
			if (index == 1) {
				currPage = 1;
			}
			var userName = $("#userName").val();
			//var sex = $("#sex").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			$.ajax({
				type : "post",
				url : "../user/getTotal",
				data : {
					'userName' : userName,
					//'sex' : sex,
					'phone' : phone,
					'status' : status
				},
				dataType : "json",
				success : function(data) {
					total = data.total;
					$("#total").val(total);
					layui.use([ 'laypage', 'layer' ], function() {
						laypage = layui.laypage, layer = layui.layer;
						//总页数大于页码总数
						laypage.render({
							elem : 'paging',
							count : total, //数据总数,
							limit : limit,
							curr : currPage,
							jump : function(obj, first) {
								currPage = obj.curr; //得到当前页，以便向服务端请求对应页的数据。
								limit = obj.limit; //得到每页显示的条数
								userManagePage();
							}
						});
					});
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
		
		//拉黑
		function lhUser(id){
			if(id == '' || id == undefined){
				layer.msg("缺失主要参数", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../user/editUser",
				data : {
					'id' : id,
					'status' : '2'
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0020") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						getTotal(-9999);
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
		
		//拉回正常
		function lhzcUser(id){
			if(id == '' || id == undefined){
				layer.msg("缺失主要参数", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../user/editUser",
				data : {
					'id' : id,
					'status' : '1'
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0020") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						getTotal(-9999);
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
