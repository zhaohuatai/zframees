<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
$(function(){
	
	var url="${ctx}/rbac/user/loadPermissionGridForUserPermissionAssign";
	
	var table_InUserPermission="table_InUserPermission";
	var toolbar_InUserPermission="toolbar_InUserPermission";
	var form_InUserPermission="form_InUserPermission";
	initGrid(url,table_InUserPermission,toolbar_InUserPermission,form_InUserPermission);
	
	var table_InUserPermissionReject="table_InUserPermissionReject";
	var toolbar_InUserPermissionReject="toolbar_InUserPermissionReject";
	var form_InUserPermissionReject="form_InUserPermissionReject";
	initGrid(url,table_InUserPermissionReject,toolbar_InUserPermissionReject,form_InUserPermissionReject);
	
	var table_NotInUserPermAndUserPermReject="table_NotInUserPermAndUserPermReject";
	var toolbar_NotInUserPermAndUserPermReject="toolbar_NotInUserPermAndUserPermReject";
	var form_NotInUserPermAndUserPermReject="form_NotInUserPermAndUserPermReject";
	initGrid(url,table_NotInUserPermAndUserPermReject,toolbar_NotInUserPermAndUserPermReject,form_NotInUserPermAndUserPermReject);
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
			{field:'code',title:'权限编码',width:120,sortable:true},
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
		//	{field:'description',title:'权限描述',width:160,},
			{field:'url',title:'权限地址',width:200,},
		]], onBeforeLoad:function(){ 
        	$(this).datagrid("clearSelections");//去掉分页后之前页仍选中 选中
		},loadFilter:function(data,parentId){
			return ZHT.ajaxDoneForServerError(data);
		}
	});
}

function reloadAll(){
	$("#table_InUserPermission").datagrid('load');
	$("#table_InUserPermissionReject").datagrid('load');
	$("#table_NotInUserPermAndUserPermReject").datagrid('load');
	
	$("#table_InUserPermission").datagrid('clearSelections');
	$("#table_InUserPermissionReject").datagrid('clearSelections');
	$("#table_NotInUserPermAndUserPermReject").datagrid('clearSelections');
}
function reloadwhitch(tableId,formId,relaodType){
	var queryParams =$("#"+tableId+"").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#"+formId+"").form().serializeArray());
	$("#"+tableId+"").datagrid(relaodType);
	$("#"+tableId+"").datagrid("clearSelections");
}

function removeFromUserPermission (){
	var rows = $("#table_InUserPermission").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定要将数据从该组移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#userId").val();
			var ajaxUrl="${ctx}/rbac/user/removePermissionsFromUserPermission";
			var param={"permissionIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				reloadAll();
			});
		}
	});
}
function removeFromUserPermissionReject(){
	var rows = $("#table_InUserPermissionReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定要将数据从该组移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#userId").val();
			var ajaxUrl="${ctx}/rbac/user/removePermissionsFromUserPermissionReject";
			var param={"permissionIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				reloadAll();
			});
		}
	});
}
function addToUserPermission(){
	var rows = $("#table_NotInUserPermAndUserPermReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);  
	alertMsg.confirm("确定将数据添加到该组？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#userId").val();
			var ajaxUrl="${ctx}/rbac/user/addPermissionsToUserPermission";
			var param={"permissionIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				reloadAll();
			});
			
		}
	});
}
function addToUserPermissionReject(){
	var rows = $("#table_NotInUserPermAndUserPermReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);  
	alertMsg.confirm("确定将数据添加到该组？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#userId").val();
			var ajaxUrl="${ctx}/rbac/user/addPermissionsToUserPermissionReject";
			var param={"permissionIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				reloadAll();
			});
			
		}
	});
}
function cancel(){
	editDialog.close(100);
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
<!-- -------------------------------------InUserPermission----------------------------------------------------------------------   -->
<div data-options="region:'center',border:true ,split:false" title="直接赋予的权限列表" 
	style="overflow:hidden;padding: 0px;width: 35%;height:94%">
	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form  id ="form_InUserPermission" method="post" action="">
	<input type="hidden" name="type" value="InUserPermission">
	<input type="hidden" name="userId" value="${rbacUser.id}">
       <table class="easyuiqueryform">
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${webParams['code']}" style="width:90px;"  class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}" style="width:90px;" class="easyui-textbox" /></td>
				<td><zht:authButton text="查询" onclick="reloadwhitch('table_InUserPermission','form_InUserPermission','load');" iconCls="icon-search" /></td>
			</tr>
		</table>
  	</form>
</div>
	<table id="table_InUserPermission" style="height: 100%"></table>
		<div id="toolbar_InUserPermission" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="从本用户移除" onclick="removeFromUserPermission();" iconCls="icon-remove" />
	</div>	
</div>

<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'west',border:true ,split:false" title="拒绝权限列表" 	
		style="overflow:hidden;padding: 0px;width: 30%;height:94%">
	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form  method="post" action="" id ="form_InUserPermissionReject">
	<input type="hidden" name="type" value="InUserPermissionReject">
	<input type="hidden" name="userId" value="${rbacUser.id}">
       <table class="easyuiqueryform" >
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${webParams['code']}"  style="width:90px;" class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}"  style="width:90px;" class="easyui-textbox" /></td>
				
				<td><zht:authButton text="查询" onclick="reloadwhitch('table_InUserPermissionReject','form_InUserPermissionReject','load');" iconCls="icon-search" /></td>
			</tr>
			<!--  -->
		</table>
  	</form>
</div>	  	  	
	<table id="table_InUserPermissionReject"></table>
		<div id="toolbar_InUserPermissionReject" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="从本用户移除" onclick="removeFromUserPermissionReject();" iconCls="icon-remove" />
	</div>	
</div>

<!-- -----------------------------------------NotInUserPermAndUserPermReject-----------------------------------------------------------------   -->
<div data-options="region:'east',border:true ,split:false" title="系统权限列表(不在【直接赋予】和【拒绝权限】集合中)" 
	style="overflow:hidden;padding: 0px;width: 35%;height:94%">
	<div class="easyui-panel" style="margin:1px;padding:0px;width:100%; overflow-x:sroll;overflow-y:hidden; " >
	<form id ="form_NotInUserPermAndUserPermReject" method="post" action="" >
	<input type="hidden" name="type" value="NotInUserPermAndUserPermReject">
	<input type="hidden" name="userId" value="${rbacUser.id}">
       <table class="easyuiqueryform">
			<tr>
				<td>编码:</td>
				<td><input type="text" name="webParams['code']" value="${webParams['code']}" style="width:70px;"  class="easyui-textbox "/></td>
				<td>名称:</td>
				<td><input type="text"  name="webParams['name']" value="${webParams['name']}" style="width:70px;" class="easyui-textbox" /></td>
				<td>启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:70px;">
						<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td><zht:authButton text="查询" onclick="reloadwhitch('table_NotInUserPermAndUserPermReject','form_NotInUserPermAndUserPermReject','load');" iconCls="icon-search" /></td>
			</tr>
		</table>
  	</form>
</div>
	<table id="table_NotInUserPermAndUserPermReject" style="height: 100%"></table>
		<div id="toolbar_NotInUserPermAndUserPermReject" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="添加到用户权限" onclick="addToUserPermission();" iconCls="icon-edit" />
		<zht:authButton text="添加到用户拒绝权限" onclick="addToUserPermissionReject();" iconCls="icon-edit" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------  -->	
<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:7%; width: 100%">
		 <input type="hidden" id="userId" name="rbacUser.id" id="userId" value="${rbacUser.id}"   />
				 <table style="height: 40px;" >
					 <tr>
					    <th >用户名:</th><td>${rbacUser.userName}</td>
						<th>启用:</th>
						<td><c:if test="${rbacUser.enabled eq true }">是</c:if><c:if test="${rbacUser.enabled eq false }">否</c:if></td>
						<td><th >&nbsp;</th><th >&nbsp;</th><th >&nbsp;</th><td>
						<zht:authButton text="关闭" onclick="cancel();" iconCls="icon-cancel" />
						</td>
					 </tr>
				 </table>
</div>
</div>