<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload ok</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<style type="text/css">
h1 {
	margin-top: 30px; color : green;
	text-align: center;
	color: green;
}

a {
	color: blue;
}

p {
	text-align: center;
}
</style>
</head>
<body>
	<blockquote class="layui-elem-quote layui-quote-nm">
		<h1>
			<i class="layui-icon layui-icon-ok"
				style="font-size: 30px; color: green;"></i> 上传成功
		</h1>
		<br>
		<br>
		<br>
		<p>
			<a href="<c:url value="/source/search"/>">返回</a>
		</p>
	</blockquote>

	<script src="${pageContext.request.contextPath }/layui/layui.all.js"
		charset="utf-8"></script>
	<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
	<script>
		;
		!function() {
			var layer = layui.layer, form = layui.form;
		}();
	</script>
</body>
</html>