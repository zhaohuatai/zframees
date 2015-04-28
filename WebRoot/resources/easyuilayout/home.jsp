<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目主页</title>

<link href="${pageContext.request.contextPath}/resources/css/common.css"  rel="stylesheet" type="text/css"></link>
	<script type="text/javascript">
	$(function(){
		});
	function getTabById(tabId){
		var tab = $("#modules_nav_tabs").tabs("getTabById",tabId);
	    var tbId =tab.panel('options').id;
	  	alert('id='+tbId);
	}
	function selectById(tabId){
		$("#modules_nav_tabs").tabs("selectById",tabId);

	}
	function testConfirmMsg(){
		
		alertMsg.confirm("确认？", {
			okCall:function(){
				alertMsg.close();
				alert("OK");
			},cancelCall:function(){
				alertMsg.close();
				alert("CANCEL");
			}
		});
	}
	</script>
  </head>
  <body>
  	<div class="easyui-accordion" style="width:100%;height:600px;">
	<div title="Top Panel" data-options="iconCls:'icon-search',collapsed:false,collapsible:true" style="padding:10px;">
<a class="easyui-linkbutton" href="javascript:;" onclick="testConfirmMsg();"><span>确认（是/否）</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="alertMsg.error('您提交的数据有误，请检查后重新提交！')"><span>错误提示</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="alertMsg.info('您提交的数据有误，请检查后重新提交！')"><span>信息提示</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="alertMsg.warn('您提交的数据有误，请检查后重新提交！')"><span>警告提示</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="alertMsg.correct('您的数据提交成功！')"><span>成功提示</span></a><br /><br />

<a class="easyui-linkbutton" href="javascript:;" onclick="alertMsg.correct('您的数据提交成功！')"><span>成功提示</span></a><br /><br />


<a class="easyui-linkbutton" href="javascript:;" onclick="loadMask.open();"><span>openMask</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="loadMask.close();"><span>closeMask</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="getTabById(2)"><span>getTabById</span></a><br /><br />
<a class="easyui-linkbutton" href="javascript:;" onclick="selectById(3)"><span>selectById</span></a><br /><br />

<a class="easyui-linkbutton" href="javascript:;" onclick="returnBackDialog(3)"><span>selectById</span></a><br /><br />
	

		</div>
		<div title="About" data-options="selected:false" style="padding:10px;">
			<h3 style="color:#0099FF;">Accordion for jQuery</h3>
			<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
		</div>
		<div title="Title1" style="padding:10px">
			<p>Content1</p>
		</div>
		<div title="Title2" style="padding:10px">
			<p>Content2</p>
		</div>
	</div>

</body>
</html>