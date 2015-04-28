<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<%--
<%@ include file="/resources/meta/bootstrap.jsp" %>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目主页</title>
<!-- 
<link href="${pageContext.request.contextPath}/resources/css/common.css"  rel="stylesheet" type="text/css"></link>
 -->

<script type="text/javascript">
		$(function(){
			
		//	initMenu();\
			if (ZHTPUBUtil.isLessThanIe8()) {
				$.messager.show({
					title : "警告",
					msg : "您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！",
					timeout : 1000 * 30
				});
			}
		});
		function initMenu(){
			var $ma=$("#menuAccordion");
			
			$ma.accordion({animate:true,fit:true,border:false});
			$.post("systemAction!findAllFunctionList.action", {userName:"1"}, function(rsp) {
				$.each(rsp,function(i,e){
					var menulist ="<div class=\"well well-small\">";
					if(e.child && e.child.length>0){
						$.each(e.child,function(ci,ce){
							var effort=ce.name+"||"+ce.iconCls+"||"+ce.url;
							menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
						});
					}
					menulist+="</div>";
					$ma.accordion('add', {
			            title: e.name,
			            content: menulist,
						border:false,
			            iconCls: e.iconCls,
			            selected: false
			        });
				});
			}, "JSON").error(function() {
				$.messager.alert("提示", "获取菜单出错,请重新登陆!");
			});
		}
	       

	</script>
	
  </head>
 <body class="easyui-layout" id="main_page_body_layout_PKjC58slRXqte7eALgbjjA">
 <%--
 <div href="resources/easyuilayout/north_nav.jsp" data-options="region:'north',border:false,split:true" style="height:60px;padding:0px;overflow: hidden;" ></div>
  --%>
   <%--  --%>
<div href="resources/easyuilayout/north_nav_nomodule.jsp" data-options="region:'north',border:false,split:true" style="height:60px;padding:0px;overflow: hidden;" ></div>

	<div data-options="region:'west',split:true,title:'系统菜单'" style="width:196px;">
			<div id="menuAccordion" class="easyui-accordion"></div>
	</div> 
	<div href="resources/easyuilayout/south.jsp" data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" ></div>
	<div href="resources/easyuilayout/center.jsp" data-options="region:'center',plain:true,title:'欢迎使用'" style="overflow: hidden;padding: 0px;" ></div>
</body>
</html>
