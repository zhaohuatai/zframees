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
	<td><input type="text" name="name" value="${demo.name}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,100]'"/></td>
	<th>ZInteger:</th>
	<td><input type="text" name="ZInteger" value="${demo.ZInteger}" class="easyui-numberbox textbox"  data-options=" required:true,min:0.0,max:100.0 "/></td>
	<th>zByte:</th>
	<td><input type="text" name="zByte" value="${demo.zByte}" class="easyui-numberbox textbox"  data-options=" min:-128.0,max:127.0 "/></td>
	</tr>
	<tr>
	<th>zDouble:</th>
	<td><input type="text" name="zDouble" value="${demo.zDouble}" class="easyui-numberbox textbox"  data-options=" min:-20.0,max:100.0,scale:10 "/></td>
	<th>ZBigDecimal:</th>
	<td><input type="text" name="ZBigDecimal" value="${demo.ZBigDecimal}" class="easyui-numberbox textbox"  data-options=" min:1.0,max:100.0,scale:10 "/></td>
	<th>zBoolean:</th>
	<td>	<select  name="zBoolean" class="easyui-combobox"  style="width: 170px;"   >	<option value="true" <c:if test="${demo.zBoolean eq true }">selected='selected' </c:if> >是</option>	<option value="false" <c:if test="${demo.zBoolean eq false }">selected='selected' </c:if> >否</option>	</select>	</td>
	</tr>
	<tr>
	<th>textcombox:</th>
	<td><input type="text" name="textcombox" value="${demo.textcombox}"  class="easyui-combobox"  data-options=" required:true,url:'${ctx}/core/abc.do',method:'post',valueField:'val',textField:'text'"/></td>
	</tr>
	<tr>
	<th>ZDoubleSelect:</th>
	<td>	<select  name="ZDoubleSelect" class="easyui-combobox"  style="width: 170px;"   data-options=" required:true">	<option value="1" <c:if test="${demo.ZDoubleSelect eq '1' }">selected='selected' </c:if> >北京</option>	<option value="2" <c:if test="${demo.ZDoubleSelect eq '2' }">selected='selected' </c:if> >上海</option>	<option value="3" <c:if test="${demo.ZDoubleSelect eq '3' }">selected='selected' </c:if> >深圳</option>	</select>	</td>
	</tr>
	<tr>
	<th>zcombotree:</th>
	<td><input type="text" name="zcombotree" value="${demo.zcombotree}"  class="easyui-combotree"  data-options=" url:'${ctx}/core/abc.do',method:'post',valueField:'val',textField:'text'"/></td>
	</tr>	
	<tr>
	<td><zht:authButton text="查询" onclick="reload('load');;" iconCls="icon-search" /></td>
	</tr>
</table>
  	</form>
</div>

<table id="demoDataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>

<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
<zht:authButton text="添 加" onclick="openAddDialog();" iconCls="icon-add" />
<zht:authButton text="编 辑" onclick="openeditDialog();" iconCls="icon-edit" />
<zht:authButton text="删 除" onclick="doDelete();" iconCls="icon-remove" />
</div>
<!----------------------------------------------------->
<script>
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}//project/demo/loadDemoGridView";
   $("#demoDataGrid").datagrid({
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
					{field:'name',title:'名称',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'ZInteger',title:'ZInteger',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zByte',title:'zByte',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zDouble',title:'zDouble',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'ZBigDecimal',title:'ZBigDecimal',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zBoolean',title:'zBoolean',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zDate',title:'zDate',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zDateTime',title:'zDateTime',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zTimeStamp',title:'zTimeStamp',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'textPassword',title:'textPassword',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'texttextarea',title:'texttextarea',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'textSelect',title:'textSelect',width:200,sortable:false,
							formatter:function(value,row){}
					},
					{field:'textcombox',title:'textcombox',width:200,sortable:false,
							formatter:function(value,row){}
					},
					{field:'textcheckbox',title:'textcheckbox',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'ZIntegerSelect',title:'ZIntegerSelect',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'ZDoubleSelect',title:'ZDoubleSelect',width:100,sortable:false,
							formatter:function(value,row){}
					},
					{field:'zcombotree',title:'zcombotree',width:100,sortable:false,
							formatter:function(value,row){}
					},
				]], onBeforeLoad:function(){ 
					//去掉分页后之前页仍选中 选中
		        	$(this).datagrid("clearSelections");
				},loadFilter:function(data,parentId){
					return ZHT.ajaxDoneForServerError(data);
				}
			});
});

 
function reload(reload) {  
	var queryParams =$("#demoDataGrid").datagrid("options").queryParams;
	ZHTEASYUtil.genQueryParams(queryParams, $("#searchform").form().serializeArray());
	$("#demoDataGrid").datagrid(reload);
	$("#demoDataGrid").datagrid("clearSelections");
}
function openAddDialog(){
	var url="${ctx}//project/demo/enterAddDemo";
	var options={title:"添加",width:'600',height:'400', url:url,onClosed:function(){reload('reload');}};
	editDialog.open(options);
}
function openeditDialog(){
	 var rows = $("#demoDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
   var url="${ctx}//project/demo/enterUpdateDemo";
   var params={id:rows[0].id};
   var options={title:"编辑",width:600,height:400, url:url,params:params,onClosed:function(){reload('reload');}};
   editDialog.open(options);
}
function doDelete() {
	var rows = $("#demoDataGrid").datagrid("getSelections");
	if (rows.length == 0) {
		alertMsg.warn("请选择要删除的数据");
		return;
	}
	var idArray =ZHTEASYUtil.selectRowsToArray(rows);
	alertMsg.confirm("确定要删除该数据？", {
		cancelCall : function() {alertMsg.close();},
		okCall : function() {alertMsg.close();
				var ajaxUrl = "${ctx}//project/demo/simpleBatchDeleteDemo";
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