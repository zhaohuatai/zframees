<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
$(function(){
	var url_InRole="${ctx}/rbac/role/loadPermissionForRoleAssign";
	var table_InRole="table_InRole";
	var toolbar_InRole="toolbar_InRole";
	var form_InRole="form_InRole";
	initGrid(url_InRole,table_InRole,toolbar_InRole,form_InRole);
	
	var url_NotInRole="${ctx}/rbac/role/loadPermissionForRoleAssign";
	var table_NotInRole="table_NotInRole";
	var toolbar_NotInRole="toolbar_NotInRole";
	var form_NotInRole="form_NotInRole";
	initGrid(url_NotInRole,table_NotInRole,toolbar_NotInRole,form_NotInRole);
});
function initGrid(loadurl,table,toolbar,form){
	 $("#"+table+"").datagrid({	
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
		queryParams: ZHTPUBUtil.form2Json($("#"+form+"").serializeArray()),
		url:loadurl,
		checkbox:true,
		idField:'id',
		toolbar:"#"+toolbar+"",
		columns:[[
			{field:'id',width:20,checkbox:true},
			{field:'code',title:'权限编码',width:160,sortable:true},
			{field:'name',title:'权限名称',width:100,sortable:true},
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
			{field:'url',title:'权限地址',width:400,},
			{field:'description',title:'权限描述',width:100,},
		]], onBeforeLoad:function(){ 
			//去掉分页后之前页仍选中 选中
        	$(this).datagrid("clearSelections");
		}
	});
}

function relaodAll(){
	$("#table_InRole").datagrid('load');
	$("#table_NotInRole").datagrid('load');
	$("#table_InRole").datagrid('clearSelections');
	$("#table_NotInRole").datagrid('clearSelections');
}
function  relaodwhitch(tableId,formId,relaodType){
	var queryParams =$("#"+tableId+"").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#"+formId+"").form().serializeArray());
	$("#"+tableId+"").datagrid(relaodType);
	$("#"+tableId+"").datagrid("clearSelections");
}

function removeFromRole(){
	var rows = $("#table_InRole").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定要将数据从该角色移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var roleId=$("#roleId").val();
			var ajaxUrl="${ctx}/rbac/role/removePermissionsFromRole";
			var param={"permissionsIds":idArray,"roleId":roleId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
		}
	});
}
function addToRole(){
	var rows = $("#table_NotInRole").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);  
	alertMsg.confirm("确定将数据添加到角色？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var roleId=$("#roleId").val();
			var ajaxUrl="${ctx}/rbac/role/addPermissionsToRole";
			var param={"permissionsIds":idArray,"roleId":roleId};
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
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'center',border:true ,split:false" title="属于该角色权限列表" style="overflow:hidden;padding: 0px;width: 50%;height:94%">
	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="form_InRole" method="post" action="" id ="form_InRole">
	<input type="hidden" name="webParams['isInRole']" value="true">
	<input type="hidden" name="webParams['roleId']" value="${rbacRole.id}">
       <table class="easyuiqueryform" id="easyuiqueryform">
			<tr>
				<td>编码:</td>
				<td style="width: 60"><input type="text" name="webParams['code']" value="${webParams['code']}" style="width:100px;"  class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}" style="width:100px;" class="easyui-textbox" /></td>
				<td>是否启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:70px;">
						<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td><zht:authButton text="查询" onclick="relaodwhitch('table_InRole','form_InRole','load');" iconCls="icon-search" /></td>
			</tr>
		</table>
  	</form>
</div>
	<table id="table_InRole" style="height: 100%"></table>
		<div id="toolbar_InRole" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="从本角色移除" onclick="removeFromRole();" iconCls="icon-remove" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'east',border:true ,split:false" title="不属于该角色权限列表" style="overflow:hidden;padding: 0px;width: 50%;height:94%">
	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form name="form_NotInRole" method="post" action="" id ="form_NotInRole">
	<input type="hidden" name="webParams['isInRole']" value="false">
	<input type="hidden" name="webParams['roleId']" value="${rbacRole.id}">
       <table class="easyuiqueryform" id="easyuiqueryform">
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${webParams['code']}"  style="width:100px;" class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}"  style="width:100px;" class="easyui-textbox" /></td>
				<td>是否启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:70px;">
						<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td><zht:authButton text="查询" onclick="relaodwhitch('table_NotInRole','form_NotInRole','load');" iconCls="icon-search" /></td>
			</tr>
			<!--  -->
		</table>
  	</form>
</div>	  	  	
	<table id="table_NotInRole"></table>
		<div id="toolbar_NotInRole" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="添加到本角色" onclick="addToRole();" iconCls="icon-add" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------  -->	
<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:7%; width: 100%">
		 <input type="hidden" id="roleId" name="rbacRole.id" id="rbacRoleId" value="${rbacRole.id}"   />
				 <table style="height: 40px;" >
					 <tr>
					    <th >角色名称:</th><td>${rbacRole.name}</td>
					    <th >编码:</th><td>${rbacRole.code}</td>
						<th>　是否启用:</th>
						<td><c:if test="${rbacRole.enabled eq true }">是</c:if><c:if test="${rbacRole.enabled eq false }">否</c:if></td>
						<td><th >&nbsp;</th><th >&nbsp;</th><th >&nbsp;</th><td>
						<zht:authButton text="关闭" onclick="cancel();" iconCls="icon-cancel" />
						</td>
					 </tr>
				 </table>
</div>
</div>