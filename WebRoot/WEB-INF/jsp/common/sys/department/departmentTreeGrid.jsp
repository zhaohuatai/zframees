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
		<th>名称:</th>
	<td><input type="text"  name="webParams[name]"    class="easyui-textbox validatebox"  /></td>
	<th>父级部门:</th>
	<td><input type="text"  name="webParams[parentDepartment.id]"     class="easyui-combotree"  /></td>
	</tr>	
	<tr>
	<td><zht:authButton text="查询" onclick="loaddata('load');;" iconCls="icon-search" /></td>
	</tr>
</table>
  	</form>
</div>

<table id="departmentTreeGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>

<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
<zht:authButton text="添 加" onclick="openAddDialog();" iconCls="icon-add" />
<zht:authButton text="编 辑" onclick="openeditDialog();" iconCls="icon-edit" />
<zht:authButton text="删 除" onclick="doDelete();" iconCls="icon-remove" />
</div>
<!----------------------------------------------------->	
<script>
var treegrid;
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}/common/sys/department/loadDepartmentGridView";
    treeDrid = $("#departmentTreeGrid").treegrid({
				title:"",
				width:'100%',
				height:$(this).height()-(($("#easyuiqueryform tr").length-1)*37+50),
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
					{field:'name',title:'名称',width:120,},
					{field:'remark',title:'备注',width:120,},
					{field:'modifyTime',title:'修改时间',width:100,},
					{field:'parentDepartment',title:'父级部门',width:null,},
					{field:'creater',title:'创建者',width:100,},
				]],onDblClickRow:function(row){//双击编辑
				    if(row){openeditDialog();}
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});//window onload
	function loaddata(reload) {  
		var queryParams =$("#departmentTreeGrid").treegrid("options").queryParams;
	      $.each($("#searchform").form().serializeArray(), function (index) {
		    	var name=this['name'];var value=this['value'];
		    	queryParams[""+name]=value;
		    });
	    $("#departmentTreeGrid").treegrid(reload);//重新加载table  
	}
	
		function deleteTreeNode(){//删除某节点
			var rows = $("#departmentTreeGrid").treegrid("getSelections");
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
					var ajaxUrl="${ctx}/common/sys/department/simpleBatchDeleteDepartment";
					var param={"ids":idArray};
					ajaxTodo(ajaxUrl,param,function(data){
						ZHT.ajaxDone(data);
						loaddata('reload');
					});
				}
			});
		}


 function openAddDialog(){
	var url="${ctx}/common/sys/department/enterAddDepartment";
	var options={title:"部门机构添加",width:'800',height:'600', url:url,onClosed:function(){loaddata('reload');}};
	editDialog.open(options,function(){loaddata('reload');;});

 }
 function openeditDialog(){
		 var rows = $("#departmentTreeGrid").treegrid("getSelections");
		 if(rows.length == 0){
			alertMsg.warn('请选择数据');return;
		 }
		 if(rows.length>1){
			alertMsg.warn('请选择一条数据');return;
		}
	    var url="${ctx}/common/sys/department/enterUpdateDepartment";
		var options={title:'部门机构编辑',width:800,height:600, url:url,params:{id:rows[0].id},onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
 }
</script>
</body>
</html>