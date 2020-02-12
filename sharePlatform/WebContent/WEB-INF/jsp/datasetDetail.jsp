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
	function allIsNull() {
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
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header"
				style="font-weight: bold; font-size: 24px;">${dataset.name }</div>
			<div class="layui-card-header">作者：${dataset.author}&emsp;&emsp;
				领域：${dataset.field }&emsp;&emsp;
				上传时间：${dataset.uploadtime }&emsp;&emsp; 被下载次数：${dataset.downtimes }</div>
			<div class="layui-card-body">简介：${dataset.intro }</div>
			<div class="layui-card-header">
				相关的论文：
				<c:forEach items="${papers }" var="paper">
					<a href="<c:url value="/source/paper?id=${paper.id }"/>">${paper.title }</a>
					&emsp;
				</c:forEach>
			</div>
			<div class="layui-card-body">
				<form method="post" name="theForm"
					action="${pageContext.request.contextPath }/source/downDataset?id=${dataset.id}">
					<button type="submit" class="layui-btn">下载</button>
				</form>
			</div>
		</div>
	</div>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  		<legend>评论区</legend>
	</fieldset>
	<form method="post" action="${pageContext.request.contextPath }/source/commentDataset?id=${dataset.id }">
		<div class="layui-row">
		<div class="layui-form-item layui-col-xs9">
			<label class="layui-form-label">发表评论：</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea" name="comment"></textarea>
			</div>
		</div>
		<div class="layui-col-xs2">
			<button type="submit" class="layui-btn layui-btn-lg" style="height:6em;margin-left: 10px;">发表评论</button>
		</div>
		</div>	
	</form>
	<c:forEach items="${comments }" var="comment">
		<div class="layui-card  layui-col-md10  layui-col-md-offset1" style="margin-bottom: 15px;">
			<div class="layui-card-header">用户 ${comment.user } 于 ${comment.comTime } 评论</div>
			<div class="layui-card-body">&emsp;${comment.comment }</div>	
		</div>
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