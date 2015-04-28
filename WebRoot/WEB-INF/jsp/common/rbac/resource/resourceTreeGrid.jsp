<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<%@ include file="/resources/meta/meta.jsp" %>
<%@ include file="/resources/meta/jquery.jsp" %>
<%@ include file="/resources/meta/easyui.jsp" %>
<%@ include file="/resources/meta/easyui-selfdefine.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>资源编辑</title>

<script>
var treegrid;
$(function(){
	var loadurl="${pageContext.request.contextPath}/rbac/resource/loadResourceTreeView";
    treeDrid = $("#resourcePermissionTreeGrid").treegrid({
				title:"资源数据列表",
				width:'100%',
				height:$(this).height()-60,
				nowrap: false,
				rownumbers: true,
				fitColumns: true,
				animate:true,
				collapsible:true,
				striped:true,
				url:loadurl,
				idField:'id',
				treeField:'id',
				overflow:scroll,
				frozenColumns:[[
	              	   {title:'ID',field:'id',width:160,
		                formatter:function(value){return '<span style="color:red">'+value+'</span>';}
	                	}
				]],
				toolbar:"#toolbarDiv",
				columns:[[
					{field:'text',title:'资源名称',width:200},
					{field:'disIndex',title:'显示顺序',width:100,},
					{field:'icon',title:'图标',width:100,},
					{field:'description',title:'描述',width:400,},
				]],
				onContextMenu: function(e,row){//右键菜单
					e.preventDefault();
					$(this).treegrid('unselectAll');
					$(this).treegrid('select', row.id);//row.standId:类似主键
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				},
				onDblClickRow:function(row){//双击编辑
				    if(row){
						//initAndOpenEditWindow();//调用编辑
						openeditDialog();
					}
				}
			});
});//window onload
function reload(){
	var node = $('#resourcePermissionTreeGrid').treegrid('getSelected');
	if (node){
		$('#resourcePermissionTreeGrid').treegrid('reload', node.code);
	} else {
		$('#resourcePermissionTreeGrid').treegrid('reload');
	}
}
		function deleteTreeNode(){//删除某节点
			var node = $('#resourcePermissionTreeGrid').treegrid('getSelected');
			if(!node){
				alertMsg.warn("请选择节点");return;
			}
			alertMsg.confirm("确定要删除该节点？", {
				cancelCall:function(){
					alertMsg.close();
				},okCall:function(){
					alertMsg.close();
					var ajaxUrl="${ctx}/rbac/resource/deleteResource";
					var param={id:node.id};
					ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
						ZHT.ajaxDone(data);
						reload();
					});
					
				}
			});
		}


 function openAddDialog(){
	var url="${pageContext.request.contextPath}/rbac/resource/enterAddResource";
	var options={title:"资源添加",width:600,height:400, url:url,onClosed:function(){reload();}};
	editDialog.open(options,function(){reload();});

 }
 function openeditDialog(){
		var node = $("#resourcePermissionTreeGrid").treegrid('getSelected');
		if(!node){
			alertMsg.warn("请选择节点");return;
		}
	    var url="${pageContext.request.contextPath}/rbac/resource/enterEidtResource";
		var options={title:"资源编辑",width:600,height:400, url:url,params:{id:node.id},onClosed:function(){reload();}};
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

<table id="resourcePermissionTreeGrid"></table>
<div id="toolbarDiv" style="padding:4px;height:auto">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add"  onclick="openAddDialog();">添 加　 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit"  onclick="openeditDialog();">编 辑　 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="deleteTreeNode();">删 除　 </a>
</div>	
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="openeditDialog()">编辑资源</div>
		<div onclick="deleteTreeNode()">删除资源</div>
	</div>
</body>
</html>