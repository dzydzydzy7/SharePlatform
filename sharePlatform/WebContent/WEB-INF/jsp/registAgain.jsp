<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<style type="text/css">
	p{
		color:red;
		margin-left: 4em;
	}
</style>
<script type="text/javascript">
	function checkPwd() {
		var pwd = document.registForm.pwd.value;
		var repwd = document.registForm.repwd.value;
		var name = document.registForm.name.value;
		if (name == "") {
			alert("用户名不能为空！");
			document.registFrom.name.focus();
			return false;
		}
		if (pwd != repwd) {
			alert("2次密码不一致，请重新输入！");
			document.registForm.pwd.value = "";
			document.registForm.repwd.value = "";
			document.registForm.pwd.focus();
			return false;
		}
		document.registForm.submit();
		return true;
	}
	function clearForm1() {
		window.location
		.replace("${pageContext.request.contextPath }/user/register")
	}
</script>
</head>
<body>
	<div style="width:500px;margin:0 auto">
	<form:form class="layui-form" modelAttribute="user" method="post"
		action="${pageContext.request.contextPath }/user/doRegist"
		name="registForm">
		
		<fieldset class="layui-elem-field layui-field-title"
			style="margin-top: 20px;text-align:center;">
			<legend>用户注册</legend>
		</fieldset>
		
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<!-- path绑定到类的域 -->
			<div class="layui-input-block">
				<form:input class="layui-input" id="name" path="name" />
			</div>
		</div>
		<p>该用户名已被注册！</p>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<form:input class="layui-input" id="pwd" type="password" path="pwd" />
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-block">
				<input class="layui-input" id="repwd" type="password" />
			</div>
		</div>
		<div class="layui-form-item" style="margin-left: 70px">
			<div class="layui-input-block">
				<button type="button" class="layui-btn" onclick="checkPwd()">注册</button>
				<button type="button" class="layui-btn layui-btn-primary" onclick="clearForm1()">重置</button>
			</div>
		</div>
	</form:form>
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