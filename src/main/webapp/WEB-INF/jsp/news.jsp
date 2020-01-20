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
		<button type="button" class="layui-btn bp" onclick="newsPage()">
			<i class="layui-icon layui-icon-search"></i> 搜索
		</button>
	</div>
	<div id="newsDiv">
		
	</div>
	<script type="text/javascript">
		/*页面初始化*/
		$(document).ready(function() {
			newsPage();
		});
		function newsPage() {
			var title = $("#title").val();
			$('#newsDiv').load('../news/newsPage?keyword='+title);
		}
		function newsDetails(id){
			if(id == '' || id == undefined){
				layer.msg("缺失主要关键字段", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$('#content').load('../news/newsDetails?id='+id);
		}
	</script>
</body>
</html>
