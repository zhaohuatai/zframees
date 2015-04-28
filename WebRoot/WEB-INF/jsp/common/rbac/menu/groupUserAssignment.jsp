<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
var treegrid;
$(function(){
	//1:50 , 2:87 3:124	
	var groupId=$("#rbacGroupId").val();;
	var url="${ctx}/rbac/group/loadUserGridForUserAssign?groupId="+groupId;
	initataGridInGroup(url+"&isIn="+true);
    initataGridNotInGroup(url+"&isIn="+false);
   
});

function initataGridInGroup(loadurl){
	userDataGridInGroup = $("#userDataGridInGroup").datagrid({
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
		queryParams: ZHTPUBUtil.form2Json($("#searchformInGroup").serializeArray()),
		checkbox:true,
		idField:'id',//分页保留选中
		toolbar:"#toolbarDivInGroup",
		columns:[[
			{field:'id',width:20,checkbox:true},
			{field:'userName',title:'登录名',width:80,sortable:true},
			{field:'alias',title:'别名',width:80,sortable:true},
			{field:'defaultRole',title:'默认角色',width:80,sortable:true},
			{field:'enabled',title:'是否启用',width:80,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>启用<font>"; 
				 }else if(false==value){
					 return "<font color=red>禁用<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'accountNonExpired',title:'账户是否过期',width:90,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'credentialsNonExpired',title:'用户认证是否过期',width:120,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'accountNonLocked',title:'用户账户是否锁定',width:120,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'description',title:'描述',width:300,},
		]]
	});
}
function initataGridNotInGroup(loadurl){
	userDataGridNotInGroup = $("#userDataGridNotInGroup").datagrid({
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
		queryParams:ZHTPUBUtil.form2Json($("#searchformNotInGroup").serializeArray()),
		checkbox:true,
		idField:'id',//分页保留选中
		toolbar:"#toolbarDivNotInGroup",
		columns:[[
			{field:'id',width:20,checkbox:true},
			{field:'userName',title:'登录名',width:80,sortable:true},
			{field:'alias',title:'别名',width:80,sortable:true},
			{field:'defaultRole',title:'默认角色',width:80,sortable:true},
			{field:'enabled',title:'是否启用',width:80,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>启用<font>"; 
				 }else if(false==value){
					 return "<font color=red>禁用<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'accountNonExpired',title:'账户是否过期',width:90,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'credentialsNonExpired',title:'用户认证是否过期',width:120,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'accountNonLocked',title:'用户账户是否锁定',width:120,formatter:function(value,row){
				 if(true==value){
					 return "<font color=green>否<font>"; 
				 }else if(false==value){
					 return "<font color=red>是<font>";  
				 }else{
					 return "<font color=red>错误状态<font>";  
				 }
			}},
			{field:'description',title:'描述',width:300,},
		]]
	});
}
function relaodAll(){
	reloadgridInGroup('reload');
	reloadgridNotInGroup('reload');
	$("#userDataGridInGroup").datagrid('clearSelections');
	$("#userDataGridNotInGroup").datagrid('clearSelections');
}

function reloadgridInGroup(reload) {
	var queryParams =$("#userDataGridInGroup").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchformInGroup").form().serializeArray());
	$("#userDataGridInGroup").datagrid(reload);
	}
function reloadgridNotInGroup(reload) {
	var queryParams =$("#userDataGridNotInGroup").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchformNotInGroup").form().serializeArray());
	$("#userDataGridNotInGroup").datagrid(reload);
}

function deleteFromGroup(){
	var rows = $("#userDataGridInGroup").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);
	alertMsg.confirm("确定要将数据从该组移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var groupId=$("#rbacGroupId").val();
			var ajaxUrl="${ctx}/rbac/group/removeUserFromGroup";
			var param={"userIds":idArray,"groupId":groupId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
}
function addToGroup(){
	var rows = $("#userDataGridNotInGroup").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = [];
	$.each(rows, function(index, item){
		idArray.push(item.id);
	});  
	alertMsg.confirm("确定将数据添加到该组？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var groupId=$("#rbacGroupId").val();
			var ajaxUrl="${ctx}/rbac/group/addUserToGroup";
			var param={"userIds":idArray,"groupId":groupId};
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

<div data-options="region:'center',border:true ,split:false" title="属于该组用户列表" style="overflow:hidden;padding: 0px;width: 50%;height:94%">
<div  class="easyui-panel" style="margin:1px;padding:0px;width:100%;height:9%; " >
	<form  method="post" action="" id ="searchformInGroup">
       <table class="easyuiqueryform" >
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="webParams['userName']"  class="easyui-textbox "/></td>
				<td>是否启用:</td>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:70px;">
					<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td>&nbsp;</td>
				<td><zht:authButton text="查询" onclick="reloadgridInGroup('load');;" iconCls="icon-search" /></td>
			</tr>
		</table>
  	</form>
</div>
		<table id="userDataGridInGroup"></table>
		<div id="toolbarDivInGroup" class="easyui-toolbar" style="padding:4px;height:auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="deleteFromGroup();">从本组移除 </a>
	</div>	
</div>
	
<div data-options="region:'east',border:true ,split:false,collapsible:false" title="不属于该组用户列表" style="overflow:hidden;padding: 0px;width:50%;height: 92%">
	<div id="queryFormDivNotInGroup" class="easyui-panel" style="margin:1px;padding:0px;width:100%;height:9%; " >
	<form  method="post" action="" id ="searchformNotInGroup">
	<input type="hidden" name="webParams['ingroup']" value="notin"/>
	<input type="hidden" name="webParams['groupId']" value="${rbacGroup.id}" />
    <table class="easyuiqueryform">
			<tr>
				<th>用户名:</th>
				<td><input type="text" name="webParams['userName']" value="${webParams['userName']}" class="easyui-textbox "/></td>
				<th>是否启用:</th>
				<td>
					<select id="type" name="webParams['enabled']"   class="easyui-combobox"  style="width:70px;">
					<option value="">请选择</option>
						<option value="true"  <c:if test="${webParams['enabled'] eq true }">selected="selected"</c:if>>是</option>
					    <option value="false" <c:if test="${webParams['enabled'] eq false }">selected="selected"</c:if>>否</option>
					</select>
				</td>
				<td>&nbsp;</td>
				<td><zht:authButton text="查询" onclick="reloadgridNotInGroup('load');;" iconCls="icon-search" /></td>
			</tr>
			<!--  -->
		</table>
  	</form>
</div>
		<table id="userDataGridNotInGroup"></table>
		<div id="toolbarDivNotInGroup" class="easyui-toolbar" style="padding:4px;height:auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add"  onclick="addToGroup();">添加到该组 </a>
	</div>	
</div>

<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:7%; width: 100%">
		 <input type="hidden" name="rbacGroup.id" id="rbacGroupId" value="${rbacGroup.id}"   />
		<table style="height: 40px;" >
					 <tr> <th >用户组名称:</th><td>${rbacGroup.name}</td><th>　是否启用:</th>
						<td>
							<c:if test="${rbacGroup.enabled eq true }">是</c:if>
							<c:if test="${rbacGroup.enabled eq false }">否</c:if>
						</td>
						<th>　创建时间:</th><td>${rbacGroup.createTime}</td>
						<th>　　创建者:</th><td>${rbacGroup.creater}</td>
						<td><th >&nbsp;</th><th >&nbsp;</th><th >&nbsp;</th>
						<td><zht:authButton text="关闭" onclick="cancel();" iconCls="icon-cancel" /></td>
					 </tr>
				 </table>
</div>
</div>