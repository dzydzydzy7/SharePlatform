<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user home</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
</head>
<body>
	用户 ${userStat }

	<ul class="layui-nav">
		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/source/search"/>">资源搜索</a></li>
		<li class="layui-nav-item" style="width: 150px; text-align: center;">
			<a href="#">资源上传</a>
			<dl class="layui-nav-child">
				<dd>
					<a href="<c:url value="/source/uploadPaper"/>">上传论文</a>
				</dd>
				<dd>
					<a href="<c:url value="/source/uploadCode"/>">上传代码</a>
				</dd>
				<dd>
					<a href="<c:url value="/source/uploadData"/>">上传数据集</a>
				</dd>
			</dl>
		</li>

		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/source/home"/>">个人主页</a></li>
		<li class="layui-nav-item" style="width: 150px; text-align: center;"><a
			href="<c:url value="/source/quit"/>">安全退出</a></li>
	</ul>
	
	<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
		<legend>选择类型</legend>
	</fieldset>
	<form class="layui-form" method="post" action="${pageContext.request.contextPath }/source/doSelect">
		<div class="layui-form-item">
			<label class="layui-form-label"> 我上传的 </label>
			<div class="layui-input-inline">
				<select name="source">
					<option value="论文">论文</option>
					<option value="代码">代码</option>
					<option value="数据集">数据集</option>
				</select>
			</div>
			<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="submit" class="layui-btn">查看</button>
			</div>
		</div>
		</div>
	</form>
	
	<c:if test="${clz eq 'paper' }">
		<div class="layui-form">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>我上传的论文</legend>
		</fieldset>
		<table class="layui-table">
			<thead>
				<tr>
					<th>论文名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${upds }" var="upd">
				<tr>
					<td><a href='<c:url value="/source/paper?id=${upd.id }"/>'>${upd.title }</a></td>
					<td><a href='<c:url value="/source/deletepaper?id=${upd.id }"/>'>删除</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	<c:if test="${clz eq 'code' }">
		<div class="layui-form">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>我上传的代码</legend>
		</fieldset>
		<table class="layui-table">
			<thead>
				<tr>
					<th>代码名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${upds }" var="upd">
				<tr>
					<td><a href='<c:url value="/source/code?id=${upd.id }"/>'>${upd.name }</a></td>
					<td><a href='<c:url value="/source/deletecode?id=${upd.id }"/>'>删除</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	<c:if test="${clz eq 'dataset' }">
		<div class="layui-form">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>我上传的数据集</legend>
		</fieldset>
		<table class="layui-table">
			<thead>
				<tr>
					<th>数据集名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${upds }" var="upd">
				<tr>
					<td><a href='<c:url value="/source/dataset?id=${upd.id }"/>'>${upd.name }</a></td>
					<td><a href='<c:url value="/source/deletedataset?id=${upd.id }"/>'>删除</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>

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