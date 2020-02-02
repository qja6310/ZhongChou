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
.sqtx{
	width: 600px;
    position: absolute;
    left: 30%;
    top: 20%;
    display : none;
}
.bg{
    FILTER: alpha(opacity=50); 
    opacity: 0.5; 
    moz-opacity: 0.5;
}
</style>
</head>
<body>
	<div id="detailsDiv">
		<table class="layui-table">
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th style="text-align: center; width: 4%;">序号</th>
					<th style="text-align: center; width: 8%;">患者</th>
					<th style="text-align: center; width: 6%;">患者年龄</th>
					<th style="text-align: center; width: 6%;">患者性别</th>
					<th style="text-align: center;">目标金额</th>
					<th style="text-align: center;">截止捐款时间</th>
					<th style="text-align: center;">联系电话</th>
					<th style="text-align: center;">状态</th>
					<th style="text-align: center;">申请时间</th>
					<th style="text-align: center; width: 8%;">审核不过(次)</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="projectsDiv">
				<c:choose>
					<c:when test="${fn:length(proList)>0 }">
						<c:forEach items="${proList}" var="item" varStatus="status">
							<tr>
								<td class="center">${status.index + 1 }</td>
								<td>${item.patientName }</td>
								<td>${item.patientAge }</td>
								<td><c:if test="${item.patientSex == 1}">男</c:if> <c:if
										test="${item.patientSex == 0}">女</c:if></td>
								<td>${item.targetMoney }</td>
								<td>${item.endTime }</td>
								<td>${item.phone }</td>
								<td><c:if test="${item.status == 1}">待审核</c:if> <c:if
										test="${item.status == 2}">审核通过</c:if> <c:if
										test="${item.status == 3}">审核未通过</c:if></td>
								<td>${item.applyTime }</td>
								<td>${item.refuseCount }</td>
								<td class="center">
									<button type="button" class="layui-btn layui-btn-sm layui-btn-normal" onclick="proDetails('${item.id}')">
										<i class="layui-icon layui-icon-about"></i> 详情
									</button>
									<c:if test="${item.refuseCount < 3 && item.status == 3}">
										<button type="button"
											class="layui-btn layui-btn-sm layui-btn-normal"
											onclick="edit('${item.id}')">
											<i class="layui-icon layui-icon-edit"></i> 修改
										</button>
									</c:if> 
									<c:if test="${item.status == 2}">
										<button type="button"
											class="layui-btn layui-btn-sm layui-btn-warm"
											onclick="showSqtx('${item.id}','${item.currentMoney }')">
											<i class="layui-icon layui-icon-rmb"></i> 资金管理
										</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="11">暂无数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<input type="hidden" id="proId"/>
	<input type="hidden" id="currentMoney"/>
	<div class="layui-card sqtx" id="sqtx">
		<div class="layui-card-header"><h3>申请提现</h3></div>
		<div class="layui-card-body">
			您当前最多可提取：<font id="txFont"></font>元
			<input type="text" id="yhkh" class="layui-input" 
			placeholder="请输入银行卡号" 
			oninput="value=value.replace(/[^\d]|^[0]/g,'')"
			style="width: 250px;"/>
			
			<input type="text" id="txText" class="layui-input" 
			onkeyup="var p2 = parseFloat(value).toFixed(2);value = p2>=0?(/\.0?$/.test(value)?value:p2.replace(/0$/,'').replace(/\.0$/,'')):''" 
			onblur="value = value.replace(/\.0*$/,'')"
			maxlength="10"
			placeholder="请输入提现金额" 
			style="width: 250px;"/>
			<div style="margin-top: 5px;">
				<button type="button"
					class="layui-btn"
					onclick="sureTX()">
					<i class="layui-icon layui-icon-ok-circle"></i> 确定
				</button>
				<button type="button"
					class="layui-btn layui-btn-danger"
					onclick="hideSqtx()">
					<i class="layui-icon layui-icon-close"></i> 取消
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		layui.use('layer', function() { //独立版的layer无需执行这一句
			layer = layui.layer; //独立版的layer无需执行这一句
		});
		function edit(id) {
			if (id == '' || id == undefined) {
				layer.msg("缺少关键参数", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$('#content').load('../projects/toEditPro?id=' + id);
		}
		
		//显示提现框
		function showSqtx(proId,currentMoney){
			$("#detailsDiv").addClass("bg");
			$("#detailsDiv button").addClass("layui-btn-disabled");
			$("#detailsDiv button").attr('disabled',true);
			$("#proId").val(proId);
			$("#currentMoney").val(currentMoney);
			$("#txFont").text(currentMoney);
			$("#txText").val(currentMoney);
			$("#sqtx").toggle();
		}
		//隐藏提现框
		function hideSqtx(){
			$("#detailsDiv").removeClass("bg");
			$("#detailsDiv button").removeClass("layui-btn-disabled");
			$("#detailsDiv button").attr('disabled',false);
			$("#proId").val("");
			$("#currentMoney").val("");
			$("#sqtx").toggle();
		}
		//确定提现
		function sureTX(){
			var yhCardNum = $("#yhkh").val().trim();
			if(yhCardNum == '' || yhCardNum == undefined){
				layer.msg("收款银行卡号不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			var currentMoney = $("#currentMoney").val() * 1;
			var txMoney = $("#txText").val() * 1;
			if(txMoney == '' || txMoney == undefined){
				layer.msg("提现金额不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			if(currentMoney < txMoney){
				layer.msg("提现金额已超出当前金额", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			
			var proId = $("#proId").val();
			if(proId == '' || proId == undefined){
				layer.msg("缺失关键参数", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../withdraw/apply",
				data : {
					"proId" : proId,
					"txMoney" : txMoney,
					"yhCardNum" : yhCardNum
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						hideSqtx();
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
		
		//项目详情
		function proDetails(id){
			if(id == '' || id == undefined){
				layer.msg("缺失关键参数", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$('#content').load('../projects/details?id='+id);
		}
	</script>
</body>
</html>
