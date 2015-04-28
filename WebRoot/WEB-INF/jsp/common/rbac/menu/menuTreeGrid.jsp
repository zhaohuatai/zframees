<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单编辑</title>
<script>
var treegrid;
$(function(){
	var loadurl="${ctx}/rbac/menu/loadMenuTreeGrid";
    treeDrid = $("#rbacMenuTreeGrid").treegrid({
				title:"菜单数据列表",
				width:'100%',
				height:$(this).height()-60,
				nowrap: false,
				rownumbers: true,
				fitColumns: true,
				animate:true,
				url:loadurl,
				idField:'id',
				treeField:'display',
				overflow:scroll,
				frozenColumns:[[
	              	   {title:'ID',field:'id',width:60,
		                formatter:function(value){return '<span style="color:red">'+value+'</span>';}
	                	}
				]],
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'display',title:'菜单名称',width:160},
					{field:'iconCls',title:'图标',width:100,},
					{field:'type',title:'类型',width:60,formatter:function(value,row){
						 if('M'==value){return "<font color=green>菜单<font>"; 
						 }else{return "<font color=#1C86EE>操作<font>"; }}},
					{field:'disIndex',title:'显示顺序',width:100,},
					{field:'tabTitle',title:'TAB标题',width:160,},
					{field:'url',title:'操作地址',width:300,},
					{field:'description',title:'描述',width:200,}
				]],onDblClickRow:function(row){//双击编辑
				    if(row){openeditDialog();}
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});//window onload

		function reload(){
			var node = $("#rbacMenuTreeGrid").treegrid('getSelected');
			if (node){
				$("#rbacMenuTreeGrid").treegrid('reload', node.code);
			} else {
				$("#rbacMenuTreeGrid").treegrid('reload');
			}
		}
		
		function deleteTreeNode(){//删除某节点
			var node = $("#rbacMenuTreeGrid").treegrid('getSelected');
			if(!node){
				alertMsg.warn("请选择节点");return;
			}
			alertMsg.confirm("确定要删除该节点？", {
				cancelCall:function(){
					alertMsg.close();
				},okCall:function(){
					alertMsg.close();
					var ajaxUrl="${ctx}/rbac/menu/deleteMenu";
					var param={id:node.id};
					ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
						ZHT.ajaxDone(data);
						reload();
					});
					
				}
			});
		}


 function openAddDialog(){
	var url="${ctx}/rbac/menu/enterAddMenu";
	var options={title:"资源添加",width:600,height:400, url:url,onClosed:function(){reload();}};
	editDialog.open(options);
 }
 function openeditDialog(){
		var node = $("#rbacMenuTreeGrid").treegrid('getSelected');
		if(!node){
			alertMsg.warn("请选择节点");return;
		}
		
	    //var url="${ctx}/rbac/menu/enterEidtMenu?id="+node.id;
	    var url="${ctx}/rbac/menu/enterEidtMenu";
	    var params={id:node.id};
		var options={title:"资源编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload();}};
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

<table id="rbacMenuTreeGrid"></table>
<div id="toolbarDiv" style="padding:4px;height:auto">
<zht:authButton text="添 加" iconCls="icon-add"  onclick="openAddDialog();" />
<zht:authButton text="编 辑" iconCls="icon-edit"  onclick="openAddDialog();" />
<zht:authButton text="删 除" iconCls="icon-remove"  onclick="openAddDialog();" />
</div>	
</body>
</html>