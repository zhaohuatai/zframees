<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
var FromUserExtendsGroup;
var FromUserExtendsDepartment;
var InUserRole;
var InUserRoleReject;
var NotInUserRoleANDUserRoleReject;
$(function(){
	var userId=$("#rbacUserId").val();
	var url="${ctx}/rbac/user/loadRoleGridForUserRoleAssign";
	
	//01
	var table_InGroupRole="table_FromUserExtendsGroup";
	initGrid(url,table_InGroupRole,{userId:userId,type:'FromUserExtendsGroup'});
	//02
//	var table_FromUserExtendsDepartment="table_FromUserExtendsDepartment";
//	initGrid(url,table_FromUserExtendsDepartment,{userId:userId,type:'FromUserExtendsDepartment'});
	//03
	var table_InUserRole="table_InUserRole";
	var toolbar_InUserRole="toolbar_InUserRole";
	var params={userId:userId,type:'InUserRole'};
	initGrid(url,table_InUserRole,params,toolbar_InUserRole);
	//04
	var table_InUserRoleReject="table_InUserRoleReject";
	var toolbar_InUserRoleReject="toolbar_InUserRoleReject";
	var params={userId:userId,type:'InUserRoleReject'};
	initGrid(url,table_InUserRoleReject,params,toolbar_InUserRoleReject);
	
	//05
	var table_NotInUserRoleANDUserRoleReject="table_NotInUserRoleANDUserRoleReject";
	var toolbar_NotInUserRoleANDUserRoleReject="toolbar_NotInUserRoleANDUserRoleReject";
	var params={userId:userId,type:'NotInUserRoleANDUserRoleReject'};
	initGrid(url,table_NotInUserRoleANDUserRoleReject,params,toolbar_NotInUserRoleANDUserRoleReject);
});
function initGrid(loadurl,table,params,toolbar){
	$("#"+table+"").datagrid({
		title:"",
		width:'100%',
		height:'100%',
		nowrap: true,//设置为true，当数据长度超出列宽时将会自动截取
		rownumbers: true,
		fitColumns: false,//滚动条
		animate:true,
		collapsible:false,//显示可折叠按钮
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
			{field:'enabled',title:'是否启用',width:70,formatter:function(value,row){
				 if(true==value){ return "<font color=green>启用<font>"; 
				 }else if(false==value){ return "<font color=red>禁用<font>";  
				 }else{ return "<font color=red>错误状态<font>";}}},
			{field:'description',title:'描述',width:80,},
		]],onBeforeLoad:function(){ 
        	$(this).datagrid("clearSelections");
        },loadFilter:function(data,parentId){
			return ZHT.ajaxDoneForServerError(data);
		}
	});
}

function relaodAll(){
	$("#table_InUserRole").datagrid('load');
	$("#table_InUserRoleReject").datagrid('load');
	$("#table_NotInUserRoleANDUserRoleReject").datagrid('load');
	
	$("#table_InUserRole").datagrid('clearSelections');
	$("#table_InUserRoleReject").datagrid('clearSelections');
	$("#table_NotInUserRoleANDUserRoleReject").datagrid('clearSelections');
}

function removeFromUserRole(){
	var rows = $("#table_InUserRole").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定移除数据？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#rbacUserId").val();
			var ajaxUrl="${ctx}/rbac/user/removeRolesFromUserRole";
			var param={"roleIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
} 

function removeFromUserRoleReject(){
	var rows = $("#table_InUserRoleReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要移除的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows);   
	alertMsg.confirm("确定要将数据从该角色移除？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#rbacUserId").val();
			var ajaxUrl="${ctx}/rbac/user/removeRolesFromUserRoleReject";
			var param={"roleIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
} 
function addToUserRole(){
	var rows = $("#table_NotInUserRoleANDUserRoleReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows); 
	alertMsg.confirm("确定该操作？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#rbacUserId").val();
			var ajaxUrl="${ctx}/rbac/user/addRolesToUserRole";
			var param={"roleIds":idArray,"userId":userId};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				relaodAll();
			});
			
		}
	});
} 
function addToUserRoleReject(){
	var rows = $("#table_NotInUserRoleANDUserRoleReject").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要添加的数据");return;
	}
	var idArray = ZHTEASYUtil.selectRowsToArray(rows); 
	alertMsg.confirm("确定该操作？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var userId=$("#rbacUserId").val();
			var ajaxUrl="${ctx}/rbac/user/addRolesToUserRoleReject";
			var param={"roleIds":idArray,"userId":userId};
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
<!-- ---------------------------------------继承获得--------------------------------------------------------------------   -->
		<div data-options="region:'west',border:true,split:true,collapsible:true" title="继承自用户组的角色"style="overflow:hidden;padding: 0px;width:30%;height: 100%">
			<table id="table_FromUserExtendsGroup"></table>
		</div>
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'center',border:true ,split:false" title=""
	 style="overflow:hidden;padding: 0px;width: 35%;height:100%">
	 <div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',border:true ,split:false" title="用户拥有角色"style="overflow:hidden;padding: 0px;height:50%">
			<table id="table_InUserRole" style="height: 100%"></table>
			<div id="toolbar_InUserRole" class="easyui-toolbar" style="padding:4px;height:auto">
				<zht:authButton text="从本用户移除" onclick="removeFromUserRole();" iconCls="icon-remove" />
				<%--<zht:authButton text="移入拒绝角色" onclick="removeFromUserRole();" iconCls="icon-remove" />--%>
			</div>
		</div>
		<div data-options="region:'south',border:true ,split:false" title="用户拒绝角色"style="overflow:hidden;padding: 0px;height:50%">
			<table id="table_InUserRoleReject" style="height: 100%"></table>
			<div id="toolbar_InUserRoleReject" class="easyui-toolbar" style="padding:4px;height:auto">
				<zht:authButton text="从本用户移除" onclick="removeFromUserRoleReject();" iconCls="icon-remove" />
				<%-- <zht:authButton text="移入用户角色" onclick="removeFromUserRoleReject();" iconCls="icon-remove" />--%>
			</div>
		</div>	
	</div>
		
</div>
<!-- -----------------------------------------------------------------------------------------------------------   -->
<div data-options="region:'east',border:true ,split:false" title="不属于用户的角色" 
	style="overflow:hidden;padding: 0px;width: 35%;height:100%">
<table id="table_NotInUserRoleANDUserRoleReject"></table>
		<div id="toolbar_NotInUserRoleANDUserRoleReject" class="easyui-toolbar" style="padding:4px;height:auto">
		<zht:authButton text="添加到用户" onclick="addToUserRole();" iconCls="icon-edit" />
		<zht:authButton text="添加到拒绝" onclick="addToUserRoleReject();" iconCls="icon-edit" />
	</div>	
</div>
<!-- -----------------------------------------------------------------------------------------------  -->	
<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:7%; width: 100%">
		 <input type="hidden" name="rbacUser.id" id="rbacUserId" value="${rbacUser.id}"   />
				 <table style="height: 40px;" >
					 <tr>
					    <th >用户组名称:</th><td>${rbacUser.userName}</td>
						<th>　是否启用:</th>
						<td><c:if test="${rbacUser.enabled eq true }">是</c:if><c:if test="${rbacGroup.enabled eq false }">否</c:if>
						</td>
						<th>　创建时间:</th><td>${''}</td>
						<th>　　创建者:</th><td>${''}</td>
						<td><th >&nbsp;</th><th >&nbsp;</th><th >&nbsp;</th><td>
						<zht:authButton text="关闭" onclick="cancel();" iconCls="icon-cancel" />
						</td>
					 </tr>
				 </table>
</div>
</div>