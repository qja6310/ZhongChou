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
	margin-right: 15%;
}
</style>
</head>
<body>
<div>
	<div style="text-align: center;"><label><h1>${news.title }</h1></label></div>
	<div style="text-align: end;"><label><h5>发布时间:${news.issueTime }</h5></label></div>
	<div>${news.content }</div>
	</div>
</body>
</html>
