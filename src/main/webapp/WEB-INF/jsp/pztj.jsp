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
</style>
</head>
<body>
	<input type="hidden" id="wlId" value="${wl.id }"/>
	<div class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 100px;">填写凭证</label>
			<div class="layui-input-block">
				<div style="margin-bottom: 0;background-color: white;">
					<textarea id="asdfg" style="display: none;">
						<c:if test="${wl.evidence != '' && wl.evidence != undefined && wl.evidence != null}">
							${wl.evidence}
						</c:if>
					</textarea>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="text-align: end;">
			<button type="button" class="layui-btn" onclick="tjpz()" id="btn"><i class="layui-icon layui-icon-add-1"></i> 提交凭证</button>
		</div>
	</div>
	<script>
		layui.use('layer', function() {
			var layer = layui.layer;
		});
	</script>
	
	<script type="text/javascript">
		var index;
		var layedit ;
		layui.use('layedit', function() {
			layedit = layui.layedit;
			layedit.set({
			    uploadImage: {
				    url: '../imageUpload',//接口url
				    type: 'post' //默认post
				}
		    });
			layedit.build('asdfg'); //建立编辑器
			index = layedit.build('asdfg', {
				height: 480, //设置编辑器高度
				tool: [
					  'strong' //加粗
					  ,'italic' //斜体
					  ,'left' //左对齐
					  ,'center' //居中对齐
					  ,'right' //右对齐
					  ,'image' //插入图片
					  ,'|' //分割线
					  ,'del' //删除线
					  ,'underline' //下划线
				]
			});
		});
		function tjpz(){
			var wlId = $("#wlId").val();
			var evidence = layedit.getContent(index);
			if (evidence == '' || evidence == undefined) {
				layer.msg("提交内容不可为空", {
					icon : 2,
					time : 2000,
				});
				return;
			}
			$.ajax({
				type : "post",
				url : "../fund/tjpz",
				data : {
					"wlId" : wlId,
					"evidence" : evidence
				},
				dataType : "json",
				success : function(data) {
					var retCode = data.retCode;
					if (retCode == "0000") {
						layer.msg(data.retMsg, {
							icon : 1,
							time : 2000,
						});
						$("#btn").addClass("layui-btn-disabled");
						$("#btn").attr("disabled",true);
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
	</script>
</body>
</html>
