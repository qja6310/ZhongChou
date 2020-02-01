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
	<input type="hidden" id="total" value="${total }" />
	<div>
		<input type="text" id="details" name="title" placeholder="关键字搜素"
			class="layui-input" style="width: 600px;">
		<button type="button" class="layui-btn bp" onclick="getTotal(1)">
			<i class="layui-icon layui-icon-search"></i> 搜索
		</button>
	</div>
	<div id="proDiv"></div>
	<div id="paging"></div>
	<script type="text/javascript">
		// 定义每页的记录数
		var limit = 10;
		var total = $("#total").val();
		var currPage = 1;
		var laypage;

		/*页面初始化*/
		$(document).ready(function() {
			getTotal(1);
		});
		//查询数量的总数
		function getTotal(index) {
			if (index == 1) {
				currPage = 1;
			}
			var details = $("#details").val();
			$.ajax({
				type : "post",
				url : "../projects/getCount",
				data : {
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
								projectsPage();
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

		function projectsPage() {
			var details = $("#details").val();
			$('#proDiv').load('../projects/projectsPage?currPage=' + currPage + '&limit='
							+ limit + '&details=' + details);
		}
		
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
