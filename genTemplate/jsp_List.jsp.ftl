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
var treegrid;
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${model.jstlstart}ctx${model.jstlend}${model.controllerNameSpace}/load${model.entitySimpleClassName}GridView";
    treeDrid = $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid({
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
				pageList: [3,10, 20, 50, 100, 200, 500],
				queryParams: form2Json($("#searchform").serializeArray()),
				url:loadurl,
				checkbox:true,
				idField:'id',//分页保留选中
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'id',width:20,checkbox:true},
					<#list model.genEntityDetailList as entityDetail>
					<#if ((entityDetail.isList ? exists && entityDetail.isList==true))>
					{field:'${entityDetail.propertyName}',title:'${entityDetail.display}',width:${entityDetail.columnWidth?default(100)},sortable:<#if (entityDetail.isSorter==true)>true<#else>false</#if>,
						<#if (entityDetail.simpleFormat ? exists)>
							formatter:function(value,row){${entityDetail.simpleFormat}}
						</#if>
					},
					</#if>
					</#list>
				]],
				onContextMenu: function(e,row){//右键菜单
					e.preventDefault();
					$(this).treegrid('unselectAll');
					$(this).treegrid('select', row.id);//row.standId:类似主键
					$('#mm').menu('show', {left: e.pageX,top: e.pageY});
				},
			});
});//window onload
	function loaddata(reload) {  
		var queryParams =$("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("options").queryParams;
	      $.each($("#searchform").form().serializeArray(), function (index) {
		    	var name=this['name'];var value=this['value'];
		    	queryParams[""+name]=value;
		    });
	    $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid(reload);//重新加载table  
	}
	
		function deleteTreeNode(){//删除某节点
			var rows = $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("getSelections");
			if(rows.length == 0){
				alertMsg.warn("请选择要删除的数据");return;
			}
			var idArray = [];
			$.each(rows, function(index, item){
				idArray.push(item.id);
			});  
			alertMsg.confirm("确定要删除该数据？", {
				cancelCall:function(){
					alertMsg.close();
				},okCall:function(){
					alertMsg.close();
					var ajaxUrl="${model.jstlstart}ctx${model.jstlend}${model.controllerNameSpace}/simpleBatchDelete${model.entitySimpleClassName ? uncap_first}";
					var param={"ids":idArray};
					ajaxTodo(ajaxUrl,param,function(data){
						ZHT.ajaxDone(data);
						loaddata('reload');
					});
				}
			});
		}


 function openAddDialog(){
	var url="${model.jstlstart}ctx${model.jstlend}${model.controllerNameSpace}/enterAdd${model.entitySimpleClassName ? uncap_first}";
	var options={title:'${model.genEntity.entityDisName}添加',width:600,height:400, url:url};
	editDialog.open(options,function(){loaddata('reload');;});

 }
 function openeditDialog(){
		 var rows = $("#${model.entitySimpleClassName ? uncap_first}DataGrid").datagrid("getSelections");
		 if(rows.length == 0){
			alertMsg.warn('请选择数据');return;
		 }
		 if(rows.length>1){
			alertMsg.warn('请选择一条数据');return;
		}
	    var url="${model.jstlstart}ctx${model.jstlend}${model.controllerNameSpace}/enterEidt${model.entitySimpleClassName ? uncap_first}";
		var options={title:'${model.genEntity.entityDisName}编辑',width:600,height:400, url:url,params:{id:rows[0].id},onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
 }
</script>
</head>
<body style="overflow: hidden;">
  	<div id="queryFormDiv" class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="searchform" method="post" action="" id ="searchform">
       <table class="easyuiqueryform" id="easyuiqueryform">
			<tr>
				<#list model.genEntityDetailList as entityDetail>
				<td>${entityDetail.display}:</td>
				<#if ((entityDetail.relationType ? exists && entityDetail.relationType=='property'))>
				<td>
				
				<input type="text" name="webParams['${entityDetail.propertyName}']" value="${model.jstlstart}webParams['${entityDetail.propertyName}']${model.jstlend}" class="easyui-textbox "/>
				
				</td>
				</#if>
				</#list>
			</tr>	
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${model.jstlstart}webParams['code']${model.jstlend}" class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${model.jstlstart}webParams['name']${model.jstlend}" class="easyui-textbox" /></td>
				<td>是否启用:</td>
				<td>
				</td>
				<td>&nbsp;</td>
				<td><zht:authLinkButton text="查询" onclick="loaddata('load');;" iconCls="icon-search" /></td>
			</tr>
		</table>
  	</form>
</div>
<table id="${model.entitySimpleClassName ? uncap_first}DataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>
<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add"  onclick="openAddDialog();">添 加　 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"  onclick="openeditDialog();">编 辑　 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="deleteTreeNode();">删 除　 </a>
</div>	
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="openeditDialog()">${model.genEntity.entityDisName}编辑</div>
		<div onclick="deleteTreeNode()">${model.genEntity.entityDisName}删除</div>
	</div>
</body>
</html>