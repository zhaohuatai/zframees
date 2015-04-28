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
<script>
var permissionDataGrid;
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}/rbac/permission/loadPermissionGridView";
	permissionDataGrid = $("#permissionDataGrid").datagrid({
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
				queryParams: ZHTPUBUtil.form2Json($("#searchform").serializeArray()),
				url:loadurl,
				checkbox:true,
				idField:'id',
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'id',width:20,checkbox:true},
					{field:'code',title:'权限编码',width:160,sortable:true},
					{field:'name',title:'权限名称',width:160,sortable:true},
					{field:'enabled',title:'是否启用',width:60,formatter:function(value,row){
						 if(true==value){return "<font color=green>启用<font>"; 
						 }else if(false==value){ return "<font color=red>禁用<font>";  
						 }else{return "<font color=red>错误状态<font>";  }
					}},
					{field:'type',title:'类型',width:60,formatter:function(value,row){
						 if(value=="M"){return "<font color=green>菜单<font>"; 
						 }else if(value=="P"){return "<font color=red>操作<font>";  
						 }else{return "<font color=red>错误类型<font>"; }
					}},
					{field:'description',title:'权限描述',width:160,},
					{field:'url',title:'权限地址',width:400,},
				]], onBeforeLoad:function(){ 
					//去掉分页后之前页仍选中 选中
		        	$(this).datagrid("clearSelections");
				}
			});

});
function reload(reload) {  
	var queryParams =$("#permissionDataGrid").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchform").form().serializeArray());
	$("#permissionDataGrid").datagrid(reload);
	$("#permissionDataGrid").datagrid("clearSelections");
}

function openAddDialog(){
	var url="${ctx}/rbac/permission/enterAddPermission";
	var options={title:"权限添加",width:'600',height:'400', url:url,onClosed:function(){reload('reload');}};
	editDialog.open(options);
 }
function openeditDialog(){
	 var rows = $("#permissionDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
   var url="${ctx}/rbac/permission/enterUpdatePermission";
   var params={id:rows[0].id};
   var options={title:"权限编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload('reload');}};
   editDialog.open(options);
}

function deletePermission() {
	var rows = $("#permissionDataGrid").datagrid("getSelections");
	if (rows.length == 0) {
		alertMsg.warn("请选择要删除的数据");
		return;
	}
	var idArray =ZHTEASYUtil.selectRowsToArray(rows);
	alertMsg.confirm("确定要删除该数据？", {
		cancelCall : function() {alertMsg.close();},
		okCall : function() {alertMsg.close();
				var ajaxUrl = "${ctx}/rbac/permission/deletePermission";
				var param = {"ids" : idArray};
				ZHTAJAX.ajaxTodo(ajaxUrl, param, function(data) {
					ZHT.ajaxDone(data);
					reload('reload');
				});
			}
		});
	}
function scanPacgeToloadPermis(){
	var ajaxUrl = "${ctx}/rbac/permission/scanPacgeToloadPermis";
	var param = {};
	ZHTAJAX.ajaxTodo(ajaxUrl, param, function(data) {
		ZHT.ajaxDone(data);
	});
}
</script>
</head>
<body style="overflow: hidden;">
  	<div id="queryFormDiv" class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="searchform" method="post" action="" id ="searchform">
       <table class="easyuiqueryform" id="easyuiqueryform">
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${webParams['code']}" class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}" class="easyui-textbox" /></td>
				<td>是否启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:170px;">
						<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td>类型:</td>
				<td>
					<select id="type" name="webParams['type']"   class="easyui-combobox"  style="width:170px;">
						<option value="">请选择</option>
						<option value="M"  <c:if test="${webParams['type'] eq 'M' }">selected="selected"</c:if>>菜单</option>
					    <option value="P"  <c:if test="${webParams['type'] eq 'P' }">selected="selected"</c:if>>操作</option>
					</select>
				</td>
				<td><zht:authButton text="查询" onclick="reload('load');;" iconCls="icon-search" /></td>
			</tr>
			<!--  -->
		</table>
  	</form>
</div>
<table id="permissionDataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>
<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
	<zht:authButton text="添 加" onclick="openAddDialog();" iconCls="icon-add" />
	<zht:authButton text="编 辑" onclick="openeditDialog();" iconCls="icon-edit" />
	<zht:authButton text="删 除" onclick="deletePermission();" iconCls="icon-remove" />
	<zht:authButton text="扫描所有类加载到数据库中" onclick="scanPacgeToloadPermis();" iconCls="icon-remove" />
</div>	
</body>
</html>