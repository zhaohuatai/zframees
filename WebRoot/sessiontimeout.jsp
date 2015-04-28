<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${ctx}/resources/jquery/juqery1.11/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/jquery/md5-min.js"></script>
     <link rel="stylesheet" href="${ctx}/resources/bootstrap/334/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/resources/bootstrap/334/css/bootstrap-theme.min.css"/>
    <script src="${ctx}/resources/bootstrap/334/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    function goTologin(){
      window.top.location.href="login.jsp";
    }
    </script>
    <style type="text/css">
    .container {
    width:360px;
	  margin-left: auto;
	  margin-right: auto;
	  *zoom: 1;
	}
    </style>
  </head>
  
  <body>
    <div class='container'>
     <h2>你已经处于离线状态  </h2>
      <div style='height:10px;clear:both;display:block'></div>
     <button class='btn btn-lg btn-primary btn-block' type='button' onclick="goTologin()">点此登录</button>
    
    </div> 
  </body>
</html>