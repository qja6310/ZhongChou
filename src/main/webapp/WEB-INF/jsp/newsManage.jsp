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
</style>
</head>
<body>
	<input type="hidden" id="total" value="${total }"/>
	<div>
		<input type="text" id="title" name="title" placeholder="关键字搜素"
			class="layui-input" style="width: 600px;">
		<button type="button" class="layui-btn bp" onclick="getTotal(1)">
			<i class="layui-icon layui-icon-search"></i> 搜索
		</button>
		<button type="button" class="layui-btn layui-btn-normal bp"
			onclick="issueNews()">
			<i class="layui-icon layui-icon-add-1"></i> 发布新闻
		</button>
	</div>
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
					<th style="text-align: center;">标题</th>
					<th style="text-align: center;">发布时间</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="newsDiv">

			</tbody>
		</table>
	</div>
	<div id="paging">
	
	</div>
	<script type="text/javascript">
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

		function issueNews() {
			$('#content').load('../news/issueNews');
		}

		function newsManagePage() {
			//getTotal();
			var title = $("#title").val();
			$('#newsDiv').load('../news/newsManagePage?keyword='+title+'&currPage='+currPage+'&limit='+limit);
		}
		
		//查询数量的总数
		function getTotal(index){
			if(index==1){
				currPage = 1;
			}
			var title = $("#title").val();
			$.ajax({
				type : "post",
				url : "../news/getTotal",
				data : {
					"keyword" : title
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
							jump : function(obj,first) {
								currPage = obj.curr; //得到当前页，以便向服务端请求对应页的数据。
								limit = obj.limit; //得到每页显示的条数
							    //首次不执行
							    //if(!first){
							    	newsManagePage();
							    //}
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
		
		//删除新闻
		function delNews(id){
			layer.confirm('是否确认删除?', {icon: 3, title:'提示'}, function(index){
				if(id == '' || id == undefined){
					layer.msg("缺失主要关键字段", {
						icon : 2,
						time : 2000,
					});
					return;
				}
				$.ajax({
					type : "post",
					url : "../news/delNews",
					data : {
						"id" : id
					},
					dataType : "json",
					success : function(data) {
						var retCode = data.retCode;
						if (retCode == "0010") {
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
				layer.close(index);
			});
		}
		
		//编辑新闻
		function editNews(id){
			if(id == '' || id == undefined){
				layer.msg("缺失主要关键字段", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$('#content').load('../news/editNews?id='+id);
		}
	</script>
</body>
</html>
