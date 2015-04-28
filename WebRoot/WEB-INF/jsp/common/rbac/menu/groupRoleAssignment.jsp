<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
var InGroupRole;
var NotInGroupRole;
var FromParents;
$(function(){
	var groupId=$("#rbacGroupId").val();
	var url_InGroupRole="${ctx}/rbac/group/loadRoleGridByGroup";
	var table_InGroupRole="table_InGroupRole";
	var toolbar_InGroupRole="toolbar_InGroupRole";
	initGrid(InGroupRole,url_InGroupRole,table_InGroupRole,{groupId:groupId,type:'InGroupRole'},toolbar_InGroupRole);
	
	var url_NotInGroupRole="${ctx}/rbac/group/loadRoleGridByGroup";
	var table_NotInGroupRole="table_NotInGroupRole";
	var toolbar_NotInGroupRole="toolbar_NotInGroupRole";
	initGrid(NotInGroupRole,url_NotInGroupRole,table_NotInGroupRole,{groupId:groupId,type:'NotInGroupRole'},toolbar_NotInGroupRole);
	
	var url_FromParents="${ctx}/rbac/group/loadRoleGridByGroup";
	var table_FromParents="table_FromParents";
	var toolbar_FromParents="toolbar_FromParents";
	initGrid(FromParents,url_FromParents,table_FromParents,{groupId:groupId,type:'FromParents'},toolbar_FromParents);
});
function initGrid(vae,loadurl,table,params,toolbar){
	vae = $("#"+table+"").datagrid({
		title:"",
		width:'100%',
		height:'91%',
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
		queryParams:params,
		checkbox:true,
		idField:'id',//分页保留选中
		toolbar:$("#"+toolbar+""),
		columns:[[
			{field:'id',width:20,checkbox:true},
			{field:'name',title:'角色名',width:80,sortable:true},
			{field:'code',title:'角色编码',width:80,sortable:true},
			{field:'enabled',title:'是否启用',width:80,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>启用<font>"; 
				 }else if(false==value){
					 return "<font color=red>禁用<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'description',title:'描述',width:300,},
		]]
	});
}

function relaodAll(){
	$("#table_InGroupRole").datagrid('load');
	$("#table_NotInGroupRole").datagrid('load');
	$("#table_InGroupRole").datagrid('clearSelections');
	$("#table_NotInGroupRole").datagrid('clearSelections');
}


function removeFromGroup(){
	var rows = $("#table_InGroupRole").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定要将数据从该角色移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var groupId=$("#rbacGroupId").val();
			var ajaxUrl="${ctx}/rbac/group/removeRolesFromGroupRole";
			var param={"roleIds":idArray,"groupId":groupId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
}
function addToGroup(){
	var rows = $("#table_NotInGroupRole").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);  
	alertMsg.confirm("确定将数据添加到该角色？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var groupId=$("#rbacGroupId").val();
			var ajaxUrl="${ctx}/rbac/group/addRolesToGroupRole";
			var param={"roleIds":idArray,"groupId":groupId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
}
function cancel(){
	editDialog.close(100);
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
<!-- ---------------------------------------FromParents--------------------------------------------------------------------   -->
<div data-options="region:'west',border:true,split:true,collapsible:true" title="继承父用户组获取的角色" 
		style="overflow:hidden;padding: 0px;width:33%;height: 100%">
	<table id="table_FromParents"></table>
</div>
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'center',border:true ,split:false" title="属于该用户组角色列表"
	 style="overflow:hidden;padding: 0px;width: 33%;height:100%">
	<table id="table_InGroupRole" style="height: 100%"></table>
		<div id="toolbar_InGroupRole" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="从本组移除" onclick="removeFromGroup();" iconCls="icon-remove" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'east',border:true ,split:false" title="不属于该用户组的角色" 
	style="overflow:hidden;padding: 0px;width: 33%;height:100%">
<table id="table_NotInGroupRole"></table>
		<div id="toolbar_NotInGroupRole" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="添加到本组" onclick="addToGroup();" iconCls="icon-add" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------  -->	
<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:7%; width: 100%">
		 <input type="hidden" name="rbacGroup.id" id="rbacGroupId" value="${rbacGroup.id}"   />
				 <table style="height: 40px;" >
					 <tr>
					    <th >用户组名称:</th><td>${rbacGroup.name}</td>
						<th>　是否启用:</th>
						<td><c:if test="${rbacGroup.enabled eq true }">是</c:if><c:if test="${rbacGroup.enabled eq false }">否</c:if>
						</td>
						<th>　创建时间:</th><td>${rbacGroup.createTime}</td>
						<th>　　创建者:</th><td>${rbacGroup.creater}</td>
						<td><th >&nbsp;</th><th >&nbsp;</th><th >&nbsp;</th><td>
						<zht:authButton text="关闭" onclick="cancel();" iconCls="icon-cancel" />
						</td>
					 </tr>
				 </table>
</div>
</div>