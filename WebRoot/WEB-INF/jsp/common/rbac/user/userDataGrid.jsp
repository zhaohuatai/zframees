<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="${pageContext.request.contextPath}/resources/css/easyuiqueryform.css"  rel="stylesheet" type="text/css"></link>
<script>
var treegrid;
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}/rbac/user/loadUserGridView";
    treeDrid = $("#userDataGrid").datagrid({
				title:"",
				width:'100%',
				height:$(this).height()-(($("#easyuiqueryform tr").length-1)*37+50),
				nowrap: true,//设置为true，当数据长度超出列宽时将会自动截取
				rownumbers: true,
				fitColumns: false,//滚动条
				animate:true,
				collapsible:true,//显示可折叠按钮
				striped:true,//设置为true将交替显示行背景。
				singleSelect:false,//为true时只能选择单行
				pagination : true,//分页
				rownumbers : true,//行数
				pageSize: 10,
				pageList: [10, 20, 50, 100, 200, 500],
				url:loadurl,
				checkbox:true,
				idField:'id',//分页保留选中
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'id',width:20,checkbox:true},
					{field:'userName',title:'登录名',width:100,sortable:true},
					{field:'alias',title:'别名',width:100,sortable:true},
					{field:'defaultRole',title:'默认角色',width:100,sortable:true},
					{field:'enabled',title:'是否启用',width:100,formatter:function(value,row){
						 if(true==value){ return "<font color=green>启用<font>"; 
						 }else if(false==value){return "<font color=red>禁用<font>";  
						 }else{return "<font color=red>错误状态<font>";  
						 }
					}},
					{field:'accountNonExpired',title:'账户是否过期',width:100,formatter:function(value,row){
						 if(true==value){ return "<font color=red>是<font>"; 
						 }else if(false==value){return "<font color=green>否<font>";  
						 }else{ return "<font color=red>错误状态<font>";  
						 }
					}},
					{field:'credentialsNonExpired',title:'用户认证是否过期',width:130,formatter:function(value,row){
						 if(true==value){ return "<font color=red>是<font>"; 
						 }else if(false==value){return "<font color=green>否<font>";  
						 }else{ return "<font color=red>错误状态<font>";  
						 }
					}},
					{field:'accountNonLocked',title:'用户账户是否锁定',width:130,formatter:function(value,row){
						 if(true==value){ return "<font color=red>是<font>"; 
						 }else if(false==value){return "<font color=green>否<font>";  
						 }else{ return "<font color=red>错误状态<font>";  
						 }
					}},
					{field:'description',title:'描述',width:200,},
				]],
		        onBeforeLoad:function(){ 
		        	$(this).datagrid("clearSelections");
		        },loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});
	
	function loaddata(reload) {  
	  	var queryParams =$("#userDataGrid").datagrid("options").queryParams;
		ZHTEASYUtil.genQueryParams(queryParams, $("#searchform").form().serializeArray());
		$("#userDataGrid").datagrid(reload);
		$("#userDataGrid").datagrid("clearSelections");
	}

 function openeditDialog(){
		 var rows = $("#userDataGrid").datagrid("getSelections");
		 if(rows.length == 0){
			alertMsg.warn("请选择要编辑的数据");return;
		 }
		 if(rows.length>1){
			alertMsg.warn("请选择一条数据");return;
		}
	    var url="${ctx}/rbac/user/enterUpdateUser";
	    var param={id:rows[0].id};
		var options={title:"用户编辑",width:600,height:400, url:url,params:param,onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
 }
 function openRoleAssignmentDialog(){
	 var rows = $("#userDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
	 var enabled=rows[0].enabled;
	 if(!enabled){
		 alertMsg.error("该用户处于禁用状态");return;
	 }
	 var param={id:rows[0].id};
    var url="${ctx}/rbac/user/enterRoleAssignment";
	var options={title:"角色权限分配",width:'100%',height:'100%', 
			url:url,params:param,onClosed:function(){loaddata('reload');}};
	editDialog.open(options);
}
 
 function openPermissionAssignmentDialog(){
	 var rows = $("#userDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
	 var enabled=rows[0].enabled;
	 if(!enabled){
		 alertMsg.error("该用户处于禁用状态");return;
	 }
	 var param={id:rows[0].id};
    var url="${ctx}/rbac/user/enterUserPermissionAssignment";
	var options={title:"权限分配",width:'100%',height:'100%', 
			url:url,params:param,onClosed:function(){loaddata('reload');}};
	editDialog.open(options);
}
function kickUser(){
	 var rows = $("#userDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
    var url="${ctx}/rbac/user/enterUpdateUser";
    var param={id:rows[0].id};
	var options={title:"用户编辑",width:600,height:400, url:url,params:param,onClosed:function(){loaddata('reload');}};
	ZHTAJAX.ajaxTodo(url, param, callback);
	
}
</script>
</head>
<body style="overflow: hidden;">
  	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="searchform" method="post" action="" id ="searchform">
       <table class="easyuiqueryform" id="easyuiqueryform">
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="webParams['userName']" value="${webParams['userName']}" class="easyui-textbox "/></td>
				<td>别名:</td>
				<td><input type="text"  name="webParams['alias']" value="${webParams['alias']}" class="easyui-textbox" /></td>
				<td>是否启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:170px;">
					<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td>&nbsp;</td>
				<td><zht:authButton text="查询" onclick="loaddata('load');;" iconCls="icon-search" /></td>
			</tr>
			<!--  -->
		</table>
  	</form>
</div>
<table id="userDataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>
<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
	<%-- <zht:authButton text="添 加" iconCls="icon-add"  onclick="openAddDialog();"/>--%>
	<zht:authButton text="编 辑" iconCls="icon-edit"  onclick="openeditDialog();"/>
	
	<%-- <zht:authButton text="删 除" iconCls="icon-remove"  onclick="deleteTreeNode();"/>--%>
	
	<zht:authButton text="分配角色" iconCls="icon-color"  onclick="openRoleAssignmentDialog();"/>
	<zht:authButton text="分配权限" iconCls="icon-color"  onclick="openPermissionAssignmentDialog();"/>
	<zht:authButton text="强制退出" iconCls="icon-color"  onclick="kickuser();"/>
	<%--
	<zht:authButton text="所属用户组" iconCls="icon-color"  onclick="openAssignmentDialog();"/>
	<zht:authButton text="所属部门" iconCls="icon-color"  onclick="openAssignmentDialog();"/>
	 --%>
</div>	
</body>
</html>