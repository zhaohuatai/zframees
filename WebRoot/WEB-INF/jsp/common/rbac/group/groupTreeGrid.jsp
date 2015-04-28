<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户组编辑</title>
<script>
var treegrid;
$(function(){
	var loadurl="${ctx}/rbac/group/loadGroupTreeGrid";
    treeDrid = $("#rbacGroupTreeGrid").treegrid({
				title:"用户组数据列表",
				width:'100%',
				height:$(this).height()-60,
				nowrap: false,
				rownumbers: true,
				fitColumns: true,
				animate:true,
				url:loadurl,
				idField:'id',
				treeField:'name',
				overflow:scroll,
				frozenColumns:[[
	              	   {title:'ID',field:'id',width:60,
		                formatter:function(value){return '<span style="color:red">'+value+'</span>';}
	                	}
				]],
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'name',title:'用户组名称',width:200},
					{field:'createTime',title:'创建时间',width:200,},
					{field:'creater',title:'创建者',width:100,},
					{field:'enabled',title:'是否启用',width:100, formatter:function(value,row){
						 if(true==value){
							 return "<font color=green>启用<font>"; 
						 }else{
							 return "<font color=#1C86EE>禁用<font>";  
						 }
					}},
					{field:'description',title:'描述',width:400,}
				]],onDblClickRow:function(row){//双击编辑
				    if(row){openeditDialog();}
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});//window onload

		function reload(){
			var node = $("#rbacGroupTreeGrid").treegrid('getSelected');
			if (node){
				$("#rbacGroupTreeGrid").treegrid('reload', node.code);
			} else {
				$("#rbacGroupTreeGrid").treegrid('reload');
			}
		}
		
		function deleteTreeNode(){//删除某节点
			var node = $("#rbacGroupTreeGrid").treegrid('getSelected');
			if(!node){
				alertMsg.warn("请选择节点");return;
			}
			alertMsg.confirm("确定要删除该节点？", {
				cancelCall:function(){
					alertMsg.close();
				},okCall:function(){
					alertMsg.close();
					var ajaxUrl="${ctx}/rbac/group/deleteGroup";
					var param={id:node.id};
					ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
						ZHT.ajaxDone(data);
						reload();
					});
					
				}
			});
		}


 function openAddDialog(){
	var url="${ctx}/rbac/group/enterAddGroup";
	var options={title:"资源添加",width:600,height:400, url:url,onClosed:function(){reload();}};
	editDialog.open(options);
 }
 function openeditDialog(){
		var node = $("#rbacGroupTreeGrid").treegrid('getSelected');
		if(!node){
			alertMsg.warn("请选择节点");return;
		}
		
	    //var url="${ctx}/rbac/group/enterEidtGroup?id="+node.id;
	    var url="${ctx}/rbac/group/enterEidtGroup";
	    var params={id:node.id};
		var options={title:"资源编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload();}};
		editDialog.open(options);
 }
 
 function openUserAssignmentDialog(){
	 var rows = $("#rbacGroupTreeGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
	 var enabled=rows[0].enabled;
	 if(!enabled){
		 alertMsg.error("该用户组处于禁用状态");return;
	 }
    var url="${ctx}/rbac/group/enterUserAssignment?id="+rows[0].id;
	var options={title:"用户组分配用户",width:'100%',height:'100%', url:url,onClosed:function(){reload();}};
	editDialog.open(options);
}
 function openRoleAssignmentDialog(){
	 var rows = $("#rbacGroupTreeGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
	 var enabled=rows[0].enabled;
	 if(!enabled){
		 alertMsg.error("该用户组处于禁用状态");return;
	 }
    var url="${ctx}/rbac/group/enterRoleAssignment?id="+rows[0].id;
	var options={title:"用户组分配用户",width:'100%',height:'100%', url:url,onClosed:function(){reload();}};
	editDialog.open(options);
}
 
</script>
</head>
<body>
  	<div  style="margin-left: 5px;margin-top: 5px">
		<span>提示</span>
		<p>
			在此你可以对<span><strong>菜单功能</strong></span>进行编辑!  &nbsp;<span><strong>注意</strong></span>操作功能是对菜单功能的操作权限！
			请谨慎填写程序编码，权限区分标志，请勿重复!
		</p>
	</div>

<table id="rbacGroupTreeGrid"></table>
<div id="toolbarDiv" style="padding:4px;height:auto">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add"  onclick="openAddDialog();">添 加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"  onclick="openeditDialog();">编 辑 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="deleteTreeNode();">删 除 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="openUserAssignmentDialog();">分配用户 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="openRoleAssignmentDialog();">分配角色 </a>
</div>	
</body>
</html>