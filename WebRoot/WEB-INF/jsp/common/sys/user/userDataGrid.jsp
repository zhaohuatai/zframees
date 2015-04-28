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
		</tr>	
	<tr>
	<td><zht:authButton text="查询" onclick="reload('load');;" iconCls="icon-search" /></td>
	</tr>
</table>
  	</form>
</div>

<table id="userDataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>

<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
<zht:authButton text="添 加" onclick="openAddDialog();" iconCls="icon-add" />
<zht:authButton text="编 辑" onclick="openeditDialog();" iconCls="icon-edit" />
<zht:authButton text="删 除" onclick="doDelete();" iconCls="icon-remove" />
</div>
<!----------------------------------------------------->
<script>
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}//common/sys/user/loadUserGridView";
   $("#userDataGrid").datagrid({
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
				pageList: [10, 20, 50, 100, 200, 500],
				queryParams: ZHTPUBUtil.form2Json($("#searchform").serializeArray()),
				url:loadurl,
				checkbox:true,
				idField:'id',
				toolbar:"#toolbarDiv",
				columns:[[
				{field:'id',width:20,checkbox:true},
					{field:'userName',title:'用户名称',width:100,},
					{field:'userNum',title:'用户编码',width:100,},
					{field:'birth',title:'出生日期',width:100,},
					{field:'sex',title:'性别',width:20,},
					{field:'email',title:'邮箱',width:40,},
					{field:'phone',title:'电话',width:40,},
					{field:'perIdNum',title:'身份证号码',width:60,},
					{field:'qqNum',title:'QQ号码',width:30,},
					{field:'weixinNum',title:'微信号',width:30,},
				]], onBeforeLoad:function(){ 
					//去掉分页后之前页仍选中 选中
		        	$(this).datagrid("clearSelections");
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});

 
function reload(reload) {  
	var queryParams =$("#userDataGrid").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchform").form().serializeArray());
	$("#userDataGrid").datagrid(reload);
	$("#userDataGrid").datagrid("clearSelections");
}
function openAddDialog(){
	var url="${ctx}//common/sys/user/enterAddUser";
	var options={title:"添加",width:'600',height:'400', url:url,onClosed:function(){reload('reload');}};
	editDialog.open(options);
}
function openeditDialog(){
	 var rows = $("#userDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
   var url="${ctx}//common/sys/user/enterUpdateUser";
   var params={id:rows[0].id};
   var options={title:"编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload('reload');}};
   editDialog.open(options);
}
function doDelete() {
	var rows = $("#userDataGrid").datagrid("getSelections");
	if (rows.length == 0) {
		alertMsg.warn("请选择要删除的数据");
		return;
	}
	var idArray =ZHTEASYUtil.selectRowsToArray(rows);
	alertMsg.confirm("确定要删除该数据？", {
		cancelCall : function() {alertMsg.close();},
		okCall : function() {alertMsg.close();
				var ajaxUrl = "${ctx}//common/sys/user/simpleBatchDeleteUser";
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