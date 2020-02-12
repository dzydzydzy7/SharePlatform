<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user search page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<script type="text/javascript">
	function allIsNull(){
		document.theForm.submit();
		return true;
	}
</script>
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
	<form class="layui-form" method="post" name="theForm"
		action="${pageContext.request.contextPath }/source/doSearch">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>搜索资料</legend>
		</fieldset>
		<div class="layui-form-item">
			<label class="layui-form-label"> 资料类型: </label>
			<div class="layui-input-inline">
				<select name="source">
					<option value="请选择">请选择</option>
					<option value="论文">论文</option>
					<option value="代码">代码</option>
					<option value="数据集">数据集</option>
				</select>
			</div>
			<label class="layui-form-label"> 所属领域: </label>
			<div class="layui-input-inline">
				<select name="field">
					<option value="请选择">请选择</option>
					<c:forEach var="field" items="${fields}">
						<option value="${field.fieldcn }">${field.fieldcn }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 关键字: </label>
			<div class="layui-input-block">
				<input type="text" name="name" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="button" class="layui-btn" onclick="allIsNull()">搜索</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>搜索结果</legend>
	</fieldset>
	<%String url = null; %>
	<c:forEach var="source" items="${sources }">
		<a href="<c:url value="/source/${source.label}?id=${source.id }"/>">
			<div class="layui-col-md10 layui-col-md-offset1" style="margin-bottom: 30px">
				<div class="layui-card">
					<div class="layui-card-header"
						style="font-weight: bold; font-size: 24px;">${source.name }</div>
					<div class="layui-card-body">
						${source.intro }<br>
					</div>
					<div class="layui-card-header">
						类型：${source.clazz }&emsp;&emsp;领域：${source.field }&emsp;&emsp;作者：${source.author }&emsp;&emsp;上传时间：${source.uploadTime}&emsp;&emsp;下载次数：${source.downTimes }</div>
				</div>
			</div>
		</a>
	</c:forEach>
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