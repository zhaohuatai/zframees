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
</head>
<body style="overflow: hidden;">
<div id="queryFormDiv" class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="searchform" method="post" action="" id ="searchform">
<table class="easyuiqueryform" id="easyuiqueryform">
	<tr>
	<#list model.genEntityPropertyList as entityDetail>
	<#if (entityDetail.isQueryCondtion ? exists &&entityDetail.isQueryCondtion==true) >
	<#if (entityDetail_index+1)%3==0>
	<th>${entityDetail.display}:</th>
	<td>${entityDetail.generatedQueryOfPropertyStr ?default('【未找到】')}</td>
	</tr>
	<tr>
	<#else>
	<th>${entityDetail.display}:</th>
	<td>${entityDetail.generatedQueryOfPropertyStr  ?default('【未找到】') }</td>
	</#if>
	</#if>
	</#list>
	</tr>	
	<tr>
	<td><zht:authButton text="查询" onclick="reload('load');;" iconCls="icon-search" /></td>
	</tr>
</table>
  	</form>
</div>

<table id="${model.entitySimpleClassName ? uncap_first}DataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>

<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
<zht:authButton text="添 加" onclick="openAddDialog();" iconCls="icon-add" />
<zht:authButton text="编 辑" onclick="openeditDialog();" iconCls="icon-edit" />
<zht:authButton text="删 除" onclick="doDelete();" iconCls="icon-remove" />
</div>
<!----------------------------------------------------->
<script>
$(function(){
	var h1=$(this).height();
	var loadurl="${model.jstlstart}ctx${model.jstlend}/${model.genEntity.controllerNameSpace}/load${model.entitySimpleClassName}GridView";
   $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid({
				title:"",
				width:'100%',
				height:(h1-(($("#easyuiqueryformTable").height()+$("#queryDivButton").height()))-28 ),
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
					<#list model.genEntityPropertyList as entityDetail>
					<#if ((entityDetail.isList ? exists && entityDetail.isList==true))>
					${entityDetail.generatedListDisplayOfPropertyStr}
					</#if>
					</#list>
				]], onBeforeLoad:function(){ 
					//去掉分页后之前页仍选中 选中
		        	$(this).datagrid("clearSelections");
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});

 
function reload(reload) {  
	var queryParams =$("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchform").form().serializeArray());
	$("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid(reload);
	$("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("clearSelections");
}
function openAddDialog(){
	var url="${model.jstlstart}ctx${model.jstlend}/${model.genEntity.controllerNameSpace}/enterAdd${model.entitySimpleClassName}";
	var options={title:"添加",width:'600',height:'400', url:url,onClosed:function(){reload('reload');}};
	editDialog.open(options);
}
function openeditDialog(){
	 var rows = $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
   var url="${model.jstlstart}ctx${model.jstlend}/${model.genEntity.controllerNameSpace}/enterUpdate${model.entitySimpleClassName}";
   var params={id:rows[0].id};
   var options={title:"编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload('reload');}};
   editDialog.open(options);
}
function doDelete() {
	var rows = $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("getSelections");
	if (rows.length == 0) {
		alertMsg.warn("请选择要删除的数据");
		return;
	}
	var idArray =ZHTEASYUtil.selectRowsToArray(rows);
	alertMsg.confirm("确定要删除该数据？", {
		cancelCall : function() {alertMsg.close();},
		okCall : function() {alertMsg.close();
				var ajaxUrl = "${model.jstlstart}ctx${model.jstlend}/${model.genEntity.controllerNameSpace}/simpleBatchDelete${model.entitySimpleClassName}";
				var param = {"ids" : idArray};
				ZHTAJAX.ajaxTodo(ajaxUrl, param, function(data) {
					ZHT.ajaxDone(data);
					reload('reload');
				});
			}
		});
}
</script>
</body>
</html>