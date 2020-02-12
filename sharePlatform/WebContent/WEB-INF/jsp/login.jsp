<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
 
<script type="text/javascript">
	function allIsNull() {
		var name = document.loginForm.name.value;
		var pwd = document.loginForm.pwd.value;
		var code = document.loginForm.code.value;
		if (name == "") {
			alert("请输入用户名！");
			document.loginForm.name.focus();
			return false;
		}
		if (pwd == "") {
			alert("请输入密码！");
			document.loginForm.pwd.focus();
			return false;
		}
		if (code == ""){
			alert("请输入验证码！");
			document.loginForm.code.focus();
			return false;
		}
		document.loginForm.submit();
		return true;
	}
	function goRegist() {
		window.location
				.replace("${pageContext.request.contextPath }/user/register")
	}
	function refreshCode(){
		document.getElementById("code").src = "${pageContext.request.contextPath }/validateCode?" + Math.random();
    }
</script>
</head>
<body>
	<div style="width: 400px; margin: 0 auto">
		<form:form class="layui-form" modelAttribute="user" method="post"
			action="${pageContext.request.contextPath }/user/doLogin"
			name="loginForm">
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>用户登录</legend>
			</fieldset>

			<div class="layui-form-item">
				<label class="layui-form-label"><i
					class="layui-icon layui-icon-username" style="font-size: 30px;"></i>
				</label>
				<!-- path绑定到类的域 -->
				<div class="layui-input-inline">
					<form:input class="layui-input" id="name" path="name" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><i
					class="layui-icon layui-icon-password" style="font-size: 30px;"></i></label>
				<div class="layui-input-inline">
					<form:input class="layui-input" id="pwd" type="password" path="pwd" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><i
					class="layui-icon layui-icon-vercode" style="font-size: 30px;"></i></label>
				<div class="layui-input-inline">
					<input class="layui-input" name="code" />
				</div>
			</div>
			<div>
				<img id="code" src="${pageContext.request.contextPath }/validateCode" style="margin-left: 6em;margin-bottom: 10px"/>
				<a href="javascript:refreshCode();">看不清，换一张</a>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button type="button" class="layui-btn" onclick="allIsNull()">登录</button>
					<button type="button" class="layui-btn layui-btn-primary"
						onclick="goRegist()">注册</button>
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