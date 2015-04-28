<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<link href="${pageContext.request.contextPath}/resources/css/easyuiqueryform.css"  rel="stylesheet" type="text/css"></link>
<script type="text/javascript">
var treegrid;
$(function(){
	//1:50 , 2:87 3:124	
	var entityId=$("#genEntityId").val();;
	var url="${ctx}/common/generator/genEntityProperty/loadGenEntityPropertyDataGrid?entityId="+entityId;
	initataGridInGroup(url);
});

function initataGridInGroup(loadurl){
	userDataGridInGroup = $("#genEntityPropertyDataGrid").datagrid({
		title:"",
		width:'100%',
		height:'91%',
		nowrap: true,//设置为true，当数据长度超出列宽时将会自动截取
		rownumbers: true,
		fitColumns: false,//滚动条
		animate:true,
		collapsible:true,//显示可折叠按钮
		striped:true,//设置为true将交替显示行背景。
		singleSelect:true,//为true时只能选择单行
		pagination : false,//分页
		rownumbers : true,//行数
		pageSize: 50,
		pageList: [50, 100, 200, 500],
		url:loadurl,
		checkbox:true,
		idField:'id',//分页保留选中
		toolbar:"#toolbarDivInGroup",
		columns:[[
			{field:'propertyName',title:'属性名',width:120,
				editor:{ type: 'validatebox',options:{ required:true  }}},
			{field:'columnName',title:'数据库列名',width:100,
				editor:{ type: 'validatebox',options:{ required:true  }}},
		  {field:'relationType',title:'关系类型',width:100,
					editor:{ type: 'combobox', 
					options:{
						 valueField: 'valuez', textField: 'displayz', 
						 data:[{valuez:'property',displayz:'property'},
						       {valuez:'manytoone',displayz:'manytoone'},
						       {valuez:'onetomany',displayz:'onetomany'},
						       {valuez:'manytomanyMarster',displayz:'manytomanyMarster'},
						       {valuez:'manytomanySlaver',displayz:'manytomanySlaver'},
						       {valuez:'onetoonefk',displayz:'onetoonefk'},
						       
						       //{valuez:'onetoone',displayz:'onetoone'},
						       ],
						 } 
				}},		
			{field:'propertyType',title:'类型',width:140,
					editor:{type: 'combobox', 
						options:{
							 valueField: 'valuez', textField: 'displayz', method:'post',
							 url:'${ctx}/common/generator/genEntity/loadPropertyTypeData',
							 required:true
							 } 
					}},	

			{field:'display',title:'显示',width:100,
				editor:{ type: 'validatebox',options:{ required:true  }}},
			{field:'actiodn',title:'状态',width:80,align:'center'},
			 {field:'action',title:'操作',width:90,align:'center',  
	            formatter:function(value,row,index){ 
	            	if (row.editing){ 
	            		 var s = '<a href="javascrit:void(0);" onclick="saveEditRow('+index+')">保存</a> &nbsp;&nbsp;';  
		                 var c = '<a href="javascrit:void(0);" onclick="cancelEditRow('+index+')">取消</a>&nbsp;&nbsp;';
		                 return s+c;  
	            	}else{
	            		var s = '<a href="javascrit:void(0);" onclick="openDetailPanel('+index+')">详细</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	            		var c = '<a href="javascrit:void(0);" onclick="deleteRow('+index+')">删除</a>&nbsp;&nbsp;';
	            		return s+c;  
	            	}
	            }  
	        } ,
		]],
	   onDblClickRow: beginEditRow,
	   onBeforeEdit:function(index,row){ 
	        row.editing = true;  
	        $("#genEntityPropertyDataGrid").datagrid('refreshRow', index);  
	    },  
	    onAfterEdit:function(index,row){  
	        row.editing = false;  
	        $("#genEntityPropertyDataGrid").datagrid('refreshRow', index);  
	    },  
	    onCancelEdit:function(index,row){  
	        row.editing = false;  
	        $("#genEntityPropertyDataGrid").datagrid('refreshRow', index);  
	    }  
	});
}
var editIndex = undefined;
function beginEditRow(index){
	if(editIndex==undefined){
		$("#genEntityPropertyDataGrid").datagrid('selectRow', index).datagrid('beginEdit', index);
		editIndex = index;
	}
	if (editIndex != index){
		$("#genEntityPropertyDataGrid").datagrid('selectRow', editIndex);
		alertMsg.warn("请先保存在编辑的行数据 ");
	}else{
		$("#genEntityPropertyDataGrid").datagrid('selectRow', index).datagrid('beginEdit', index);
		editIndex = index;
	}
}
function addRow(){
	 if (editIndex != undefined) {
		 alert(editIndex);
		 alertMsg.warn("请先保存在编辑的行数据 ");
     }else{
    	 $("#genEntityPropertyDataGrid").datagrid('appendRow', {row: {}});
    	 editIndex=$("#genEntityPropertyDataGrid").datagrid('getRows').length-1;
    	 $("#genEntityPropertyDataGrid").datagrid('selectRow', editIndex);
    	 $("#genEntityPropertyDataGrid").datagrid('beginEdit', editIndex);
     }
}
function cancelEditRow(index){
	$("#genEntityPropertyDataGrid").datagrid('selectRow', index);
	$("#genEntityPropertyDataGrid").datagrid('cancelEdit', index);
	editIndex = undefined;
	var rows = $("#genEntityPropertyDataGrid").datagrid("getSelections");
	if(!(rows[0].id)){
		$("#genEntityPropertyDataGrid").datagrid('deleteRow', index);
	}
} 
function saveEditRow(index){
	if(index!=editIndex){
		 alertMsg.warn("请先保存在编辑的行数据 ");
	}else{
		$("#genEntityPropertyDataGrid").datagrid('endEdit', editIndex);
		$("#genEntityPropertyDataGrid").datagrid('endEdit', index);
		//$("#genEntityDetailDataGrid").datagrid('acceptChanges');
		
		var row = $("#genEntityPropertyDataGrid").datagrid('getSelected');
		var genEntityId=$("#genEntityId").val();
		row["genEntity.id"]=genEntityId;
		console.log(row);
		if(row){
			if ($("#genEntityPropertyDataGrid").datagrid("validateRow", editIndex)){
				var ajaxUrl="${ctx}/common/generator/genEntityProperty/genEntityPropertyAddOrUpdate";
				ZHTAJAX.ajaxTodo(ajaxUrl,row,function(data){
					if(data.statusCode==200){
						reload('reload');
						$("#genEntityPropertyDataGrid").datagrid('selectRow', editIndex);
						$("#genEntityPropertyDataGrid").datagrid('acceptChanges');
						editIndex = undefined;
					}else{
						
						alertMsg.error(data.message);
						editIndex = undefined;
						$("#genEntityPropertyDataGrid").datagrid('rejectChanges');
						$("#genEntityPropertyDataGrid").datagrid('beginEdit', editIndex);
						
					}
				});
			}
		}
	}
}
function reload(reload) {  
	    $("#genEntityPropertyDataGrid").datagrid(reload);
	}
	
function openDetailPanel(index){
	if(editIndex!=undefined){
		$("#genEntityPropertyDataGrid").datagrid('selectRow', editIndex);
		alertMsg.warn("请先保存在编辑的行数据 "); return;
	}
	$("#genEntityPropertyDataGrid").datagrid('selectRow', index);
	var rows = $("#genEntityPropertyDataGrid").datagrid("getSelections");
	var url="${ctx}/common/generator/genEntityProperty/loadEntityPropertyDetail?id="+rows[0].id;
	$("#eastDetailPanel").panel("refresh",url);
}
function cancel(){
	editDialog.close(100);
}

function deleteRow(index){//删除某节点
	$("#genEntityPropertyDataGrid").datagrid('selectRow', index);
	var rows = $("#genEntityPropertyDataGrid").datagrid("getSelections");
	if(rows.length == 0){
		alertMsg.warn("请选择要删除的数据");return;
	}
	var idArray = [];
	$.each(rows, function(index, item){idArray.push(item.id);});  
	alertMsg.confirm("确定要删除该数据？", {
		cancelCall:function(){
			alertMsg.close();
		},okCall:function(){
			alertMsg.close();
			var ajaxUrl="${ctx}/common/generator/genEntityProperty/deleteProperty";
			var param={"id":idArray};
			ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
				ZHT.ajaxDone(data);
				 $("#genEntityPropertyDataGrid").datagrid('reload');//重新加载table  
			});
			
		}
	});
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'center',border:true ,split:false" title="基本信息 " style="overflow:hidden;padding: 0px;width: 57%;height:94%">
	      <table class="easyuiqueryform" id="easyuiqueryform" style="width: 100%">
			<tr><td style="width: 86%">&nbsp;</td><td><zht:authButton text="添加列" onclick="addRow();" iconCls="icon-search" /></td></tr>
		</table>
	<table id="genEntityPropertyDataGrid"></table>
</div>
	
<div data-options="region:'east',border:true ,split:false,collapsible:true" title="详细信息" style="overflow:hidden;padding: 0px;width:43%;height: 94%">
<div id="eastDetailPanel" class="easyui-panel" title="" style="width:100%;height:auto;padding:10px;">
</div>
</div>

<div data-options="region:'north',border:true ,collapsible:true" title="" style="overflow: hidden;padding: 2px;height:6%; width: 100%">
		 <input type="hidden" name="genEntity.id" id="genEntityId" value="${genEntity.id}"   />
				 <table style="height: 40px;border-bottom-color: black;" >
					 <tr>
					     <th>实体类全名:</th><td>${genEntity.name}</td>
						 <th>数据表名称:</th><td>${genEntity.tableName}</td>
						 <th>显示名:</th><td>${genEntity.entityDisName}</td>
						 <th>控制层访问路径:</th><td>${genEntity.controllerNameSpace}</td>
						 <th>是否树形:</th>
						<td>
							<c:if test="${genEntity.isTree eq true }">是</c:if>
							<c:if test="${genEntity.isTree eq false }">否</c:if>
						</td>
						<td>
						<th >&nbsp;</th>
						<th >&nbsp;</th>
						<th >&nbsp;</th>
					 </tr>
				 </table>
</div>
</div>