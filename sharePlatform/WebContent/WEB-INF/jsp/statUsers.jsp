<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>users</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
</head>
<body>
	管理员 ${userStat }
	<ul class="layui-nav">
		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/admin/field"/>">领域管理</a></li>
		<li class="layui-nav-item" style="width: 150px; text-align: center;">
			<a href="#">资源统计</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="<c:url value="/admin/paper"/>">论文统计</a>
				</dd>
				<dd>
					<a href="<c:url value="/admin/code"/>">代码统计</a>
				</dd>
				<dd>
					<a href="<c:url value="/admin/dataset"/>">数据集统计</a>
				</dd>
			</dl>
		</li>

		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/admin/users"/>">用户统计</a></li>
		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/admin/quit"/>">安全退出</a></li>
	</ul>
	
	<div class="layui-form">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>用户统计</legend>
		</fieldset>
		<table class="layui-table">
			<thead>
				<tr>
					<th>用户名</th>
					<th>密码</th>
					<th>类型</th>
					<th>总被下载次数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users }" var="u">
					<tr>
						<td>${u.name }</td>
						<td>${u.pwd }</td>
						<td>${u.clazz }</td>
						<td>${u.times }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

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