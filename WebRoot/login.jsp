<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<%@ include file="/resources/meta/bootstrap.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${ctx}/resources/jquery/md5-min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#userName").bind("keyup", function(event){
 		   if (event.keyCode=="13"){
 		      $("#j_password").focus();
 		   }
 		});
 		
 		$("#password").bind("keyup", function(event){
 		   if (event.keyCode=="13"){
 		      $("#j_captcha").focus();
 		   }
 		});
 		$("#jcaptchaCode").bind("keyup", function(event){
 			if (event.keyCode=="13"){
 				 $("#loginButton").focus();
 				
 		   }
 		});
    });
    
    function reloadValidateCode(){
        $("#validateCodeImg").hide().attr("src","${ctx}/jcaptcha/jcaptcha.jpg?" + Math.floor(Math.random()*100)).fadeIn();
    }
    function convertPassword(username,password){
    	//pwxwT1d6SMiYXYZ0ARGFhg+_94DABGioQOq2tTUO0AXYow
    	var salt=username+"@zhtframework_94DABGioQOq2tTUO0AXYow";
    //	var salt=username;
		//return hex_md5(password+"{"+salt+"}");
		return hex_md5(salt+password);
	}
    function dologin(){
    	var userName = $("#userName");
		var password = $("#password");
		if(userName.val().length < 1){
			alert("请输入帐号");
			userName.focus();
			return;
		}
		if(password.val().length < 2){
			password.focus();
			alert("密码至少2位");
			return;
		}
		$("#password").val(convertPassword(userName.val().trim(),password.val().trim()));
		ZHTAJAX.validateFromCallback($("#loginForm"), function(json){
			if(json.statusCode == ZHT.statusCode.error) {
				$("#password").val("");
				$("#jcaptchaCode").val("");
				reloadValidateCode();
				if(json.message && alertMsg){
					$.messager.alert('提示',json.message,'error');
				}
			}else if(json.statusCode == ZHT.statusCode.serverError) {
				$("#password").val("");
				$("#jcaptchaCode").val("");
				reloadValidateCode();
				if(json.message && alertMsg){
					$.messager.alert('提示',json.message,'error');
				}
			}else {
				$("#password").val("");
				$("#jcaptchaCode").val("");
				reloadValidateCode();
				if(json.message && alertMsg){
					window.top.location.href="${ctx}/rbac/user/core";
				}
			};
		});
    }
    </script>
    <style type="text/css">
    .container {
    width:300px;
	  margin-left: auto;
	  margin-right: auto;
	  *zoom: 1;
	}
    </style>
  </head>
  
  <body>
    <div class='container'>
      <form  id="loginForm" class='form-signin' role='form' action="${ctx}/rbac/user/login" method="post">
        <h4 class='form-signin-heading'>用户登录</h4>
        <input type='text' id="userName" name="userName" class='form-control' placeholder='请输入用户名' required autofocus>
            <div style='height:10px;clear:both;display:block'></div>
        <input type='password'  id="password" name="password" class='form-control' placeholder='请输入密码' required>
         <div style='height:10px;clear:both;display:block'></div>
       <input type="text" style="height: 38;border-radius:3px;" id="jcaptchaCode"  name="jcaptchaCode" placeholder='请输入密验证码'  />
       
       <img border="0" width="90" height="40" id="validateCodeImg" src="${pageContext.request.contextPath}/jcaptcha/jcaptcha.jpg" onclick="javascript:reloadValidateCode();" />
        
         <div style='height:10px;clear:both;display:block'></div>
         <!-- 
        <div class='checkbox'>
          <label>
            <input type='checkbox' id="loginButton" name="rememberMe" value='true'> 记住登录状态
          </label>
        </div>
         -->
        <button class='btn btn-lg btn-primary btn-block' type='button' onclick="dologin()">登录</button>
        <h2>${massage}</h2>
      </form>
    </div> 
  </body>
</html>