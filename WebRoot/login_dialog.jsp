<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="pageContent">
<script type="text/javascript">
	function refreshCaptcha() {
		$('#captchaImg').hide().attr('src','${pageContext.request.contextPath}/security/jcaptcha.jpg?' + Math.floor(Math.random()*100)).fadeIn();
	}
</script>
	<form method="post" action="${pageContext.request.contextPath}/rbacUser/rbacUser!login.action" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>登录名</label>
				<input type="text" name="loginName" value="${loginName }" size="30" class="required"/>
			</div>
			<div class="unit">
				<label>密码</label>
				<input type="password" name="password" value="${password }" size="30" class="required"/>
			</div>
			<div class="unit">
				<label>验证码</label>
				<input name="j_captcha" id="j_captcha"   type="text" size="5" />
				&nbsp;&nbsp;&nbsp;
				<a href="javascript:refreshCaptcha();">
					<img border="0" width="85" height="30" style="padding-bottom: 0px;margin-bottom: 0px" id="captchaImg" src="${pageContext.request.contextPath}/security/jcaptcha.jpg?t=<%=System.currentTimeMillis()  %>"/>
				</a>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">登录</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">清除</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

