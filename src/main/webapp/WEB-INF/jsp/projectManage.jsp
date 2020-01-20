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
	<input type="hidden" id="total" value="${total }" />
	<form class="layui-form" lay-filter="form1">
		<div class="layui-row">
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">申请人</label>
					<div class="layui-input-inline">
						<input type="text" id="userName" name="userName"
							class="layui-input" maxlength="16">
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">患者姓名</label>
					<div class="layui-input-inline">
						<input type="text" id="patientName" name="patientName"
							class="layui-input" maxlength="16">
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">患者性别</label>
					<div class="layui-input-inline">
						<select id="patientSex" name="patientSex" lay-verify="required">
							<option value=""></option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">联系电话</label>
					<div class="layui-input-inline">
						<input type="text" id="phone" name="phone" class="layui-input"
							maxlength="16">
					</div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-md3">
				<div class="layui-form-item">
					<label class="layui-form-label lw">状态</label>
					<div class="layui-input-inline">
						<select id="status" name="status" lay-verify="required">
							<option value=""></option>
							<option value="1">待审核</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-col-md4">
				<div class="layui-form-item">
					<label class="layui-form-label lw">申请时间</label>
					<div class="layui-input-inline" style="width: 70%;">
						<input type="text" id="applyTime" name="applyTime"
							class="layui-input" placeholder="yyyy-MM-dd hh:mi:ss ~ yyyy-MM-dd hh:mi:ss" readonly>
					</div>
				</div>
			</div>
			<div class="layui-col-md4" style="margin-left: -40px;">
				<div class="layui-form-item">
					<label class="layui-form-label lw">关键字查询</label>
					<div class="layui-input-inline" style="width: 75%">
						<input type="text" id="details" name="details" class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-col-md1">
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
					<th style="text-align: center;">序号</th>
					<th style="text-align: center;">申请人</th>
					<th style="text-align: center;">患者</th>
					<th style="text-align: center;">患者年龄</th>
					<th style="text-align: center;">患者性别</th>
					<th style="text-align: center;">目标金额</th>
					<th style="text-align: center;">截止捐款时间</th>
					<th style="text-align: center;">联系电话</th>
					<th style="text-align: center;">状态</th>
					<th style="text-align: center;">申请时间</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="projectsDiv">

			</tbody>
		</table>
	</div>
	<div id="paging"></div>
	<script type="text/javascript">
		layui.use('form', function() {
			var form = layui.form;
			layui.form.render('select','form1');
		});
		layui.use('laydate', function(){
			 var laydate = layui.laydate;
			 laydate.render({
			    elem: '#applyTime'
			    ,type: 'datetime'
			    ,range: '~'
			  });
		}); 
		// 定义每页的记录数
		var limit = 2;
		var total = $("#total").val();
		var currPage = 1;
		var laypage;

		/*页面初始化*/
		$(document).ready(function() {
			getTotal(1);
			//newsManagePage();
		});

		function issueNews() {
			$('#content').load('../news/issueNews');
		}

		function projectManagePage() {
			//getTotal();
			var userName = $("#userName").val();
			var patientName = $("#patientName").val();
			var patientSex = $("#patientSex").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			var applyTime = $("#applyTime").val();
			var details = $("#details").val();
			$('#projectsDiv').load(
					'../projects/projectManagePage?userName=' + userName + '&patientName='+patientName
							+'&patientSex='+patientSex +'&phone='+ phone +'&status='+status
							+'&applyTime='+applyTime+'&details='+details+ '&currPage='
							+ currPage + '&limit=' + limit);
		}

		//查询数量的总数
		function getTotal(index) {
			if (index == 1) {
				currPage = 1;
			}
			var userName = $("#userName").val();
			var patientName = $("#patientName").val();
			var patientSex = $("#patientSex").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			var applyTime = $("#applyTime").val();
			var details = $("#details").val();
			$.ajax({
				type : "post",
				url : "../projects/getTotal",
				data : {
					'userName' : userName,
					'patientName' : patientName,
					'patientSex' : patientSex,
					'phone' : phone,
					'status' : status,
					'applyTime' : applyTime,
					'details' : details
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
								projectManagePage();
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
	</script>
</body>
</html>
