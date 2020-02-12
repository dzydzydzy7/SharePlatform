<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user upload data</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/layui/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function change(){
		var obj = document.getElementById("file");
		var name = obj.files[0].name;
		document.getElementsByName("name")[0].value = name;
	}
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

	<form class="layui-form" method="post" name="theForm" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/source/doUploadData">
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;">
			<legend>上传代码</legend>
		</fieldset>
		<div class="layui-form-item">
			<label class="layui-form-label"> 上传数据集: </label>
			<div class="layui-input-block">
				<input type="file" id="file" name="file" onchange="change();"
					class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 数据集名称: </label>
			<div class="layui-input-block">
				<input type="text" name="name" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 数据集作者: </label>
			<div class="layui-input-block">
				<input type="text" name="author" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 所属的领域: </label>
			<div class="layui-input-inline">
				<select name="field" >
				<c:forEach var="field" items="${fields}">
					<option value="${field.fieldcn }">${field.fieldcn }</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 对应的论文: </label>
			<div class="layui-input-block">
				<select name="paper">
				<c:forEach var="paper" items="${papers }">
					<option value="${paper.id }">${paper.title }</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"> 数据集简介: </label>
			<div class="layui-input-block">
				<textarea class="layui-textarea" name="intro"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="button" class="layui-btn" onclick="allIsNull()">上传</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

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