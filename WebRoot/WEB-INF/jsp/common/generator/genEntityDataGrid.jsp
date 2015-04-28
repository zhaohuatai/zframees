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
<link href="${pageContext.request.contextPath}/resources/css/easyuiqueryform.css"  rel="stylesheet" type="text/css"></link>
<script>
var treegrid;
$(function(){
	//1:50 , 2:87 3:124
	var loadurl="${ctx}/common/generator/genEntity/loadGenEntityDataGrid";
    treeDrid = $("#genEntityDataGrid").datagrid({
				title:"",
				width:'100%',
				height:$(this).height()-60,
				nowrap: true,//设置为true，当数据长度超出列宽时将会自动截取
				rownumbers: true,
				fitColumns: false,//滚动条
				animate:true,
				collapsible:true,//显示可折叠按钮
				striped:true,//设置为true将交替显示行背景。
				singleSelect:true,//为true时只能选择单行
				pagination : true,//分页
				rownumbers : true,//行数
				pageSize: 20,
				pageList: [20, 50, 100, 200, 500],
				url:loadurl,
				checkbox:true,
				idField:'id',//分页保留选中
				toolbar:"#toolbarDiv",
				columns:[[
					//{field:'id',width:20,checkbox:true},
					{field:'name',title:'实体类名',width:330,sortable:true},
					//{field:'careateTime',title:'初始化时间',width:125},
					{field:'lastModifyTime',title:'最后修改时间',width:125,},
					{field:'tableName',title:'数据表名',width:100,
						editor:{ type: 'validatebox',options:{ required:true  }}},
					
					{field:'entityDisName',title:'实体名称 ',width:100,
						editor:{ type: 'validatebox',options:{ required:true  }}},
					{field:'controllerNameSpace',title:'访问路径 ',width:190,
						editor:{ type: 'validatebox',options:{ required:true  }}},
						
					{field:'isTree',title:'是否树形',width:65,
							editor:{ type: 'combobox', 
								options:{
										valueField: 'value', textField: 'display', 
										data:[{value:'true',display:'true'},
											   {value:'false',display:'false'},
											   ],
										required:true     
									} 
								}},		
				    {field:'cz',title:'操作',width:80,align:'center',  
			            formatter:function(value,row,index){ 
			            	if (row.editing){ 
			            		 var s = '<a href="javascrit:void(0);" onclick="saveEditRow('+index+')">保存</a> ';  
				                 var c = '<a href="javascrit:void(0);" onclick="cancelEditRow('+index+')">取消</a>';  
				                 return s+c;  
			            	}
			            }  
			        } ,
					{field:'genType',title:'生成类型',width:100,
			        	 formatter:function(value,row,index){ 
				            		var comboxx='<select class="easyui-combobox" style="width:99px;" onchange="genCRUD('+index+',this.value)">'
				            		+'<option value="">生成部分</option>'
				            		+'<option value="all">all</option>'
				            		+'<option value="java_all">java_all</option>'
				            		+'<option value="java_model">java_model</option>'
				            		+'<option value="java_action">java_action</option>'
				            		+'<option value="java_service">java_service</option>'
				            		+'<option value="java_dao">java_dao</option>'
				            		+'<option value="jsp_all">jsp_all</option>'
				            		+'<option value="jsp_list">jsp_list</option>'
				            		+'<option value="jsp_add">jsp_add</option>'
				            		+'<option value="jsp_edit">jsp_edit</option>'
				            		+'</select>';
					                 return comboxx;  
				            }},
				]],
				  onDblClickRow: beginEditRow,
				  onBeforeEdit:function(index,row){ 
				        row.editing = true;  
				        $("#genEntityDataGrid").datagrid('refreshRow', index);  
				    },  
				    onAfterEdit:function(index,row){  
				        row.editing = false;  
				        $("#genEntityDataGrid").datagrid('refreshRow', index);  
				    },  
				    onCancelEdit:function(index,row){  
				        row.editing = false;  
				        $("#genEntityDataGrid").datagrid('refreshRow', index);  
				    }  
			});
});//window onload
	var editIndex = undefined;
	function beginEditRow(index){
		if(editIndex==undefined){
			$("#genEntityDataGrid").datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		}
		if (editIndex != index){
			$("#genEntityDataGrid").datagrid('selectRow', editIndex);
			alertMsg.warn("请先保存在编辑的行数据 ");
		}else{
			$("#genEntityDataGrid").datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		}
	}
	function cancelEditRow(index){
		$("#genEntityDataGrid").datagrid('cancelEdit', index);
		editIndex = undefined;
	}
	function saveEditRow(){
		$("#genEntityDataGrid").datagrid('selectRow', editIndex);
		$("#genEntityDataGrid").datagrid('endEdit', editIndex);
		var row = $("#genEntityDataGrid").datagrid('getSelected');
		if(row){
			if ($("#genEntityDataGrid").datagrid("validateRow", editIndex)){
				var ajaxUrl="${ctx}/common/generator/genEntity/updateGenEntity";
				ZHTAJAX.ajaxTodo(ajaxUrl,row,function(data){
					if(data.statusCode==200){
						$("#genEntityDataGrid").datagrid('acceptChanges');
						editIndex = undefined;
					}else{
						alertMsg.error(data.message);
					}
					loaddata('reload');
				});
			}
		}
	}
	function loaddata(reload) {  
	    $("#genEntityDataGrid").datagrid(reload);//重新加载table  
	}
	
	function openeAddDialog(){
		var url="${ctx}/common/generator/genEntity/enterGenEntityAdd";
		var options={title:"实体添加",width:600,height:400, url:url,onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
	}
	
	 function openEditProperty(){
		 var rows = $("#genEntityDataGrid").datagrid("getSelections");
		 if(rows.length == 0){
			alertMsg.warn("请选择要编辑的数据");return;
		 }
		 if(rows.length>1){
			alertMsg.warn("请选择一条数据");return;
		}
	    var url="${ctx}/common/generator/genEntityProperty/enterEntityProperty";
		///var options={title:"角色编辑",width:600,height:400, url:url};
		var options={title:"生成编辑",width:'98%',height:'96%', url:url,params:{entityId:rows[0].id},onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
 	}	

 function openeditDialog(){
		 var rows = $("#genEntityDataGrid").datagrid("getSelections");
		 if(rows.length == 0){
			alertMsg.warn("请选择要编辑的数据");return;
		 }
		 if(rows.length>1){
			alertMsg.warn("请选择一条数据");return;
		}
	    var url="${ctx}/common/generator/genEntityDetail/enterEntityDetailPage";
		///var options={title:"角色编辑",width:600,height:400, url:url};
		var options={title:"生成编辑",width:'98%',height:'96%', url:url,params:{entityId:rows[0].id},onClosed:function(){loaddata('reload');}};
		editDialog.open(options);
 }
 
 function genCRUD(index,genType){
	$("#genEntityDataGrid").datagrid('selectRow', index);
	 var rows = $("#genEntityDataGrid").datagrid("getSelections");
	 if(rows.length == 0){
		alertMsg.warn("请选择要编辑的数据");return;
	 }
	 if(rows.length>1){
		alertMsg.warn("请选择一条数据");return;
	}
	var param={"genEntityId":rows[0].id,"genType":genType};
    var ajaxUrl="${ctx}/common/generator/genEntity/genEntityCRUD";
    ZHTAJAX.ajaxTodo(ajaxUrl,param,function(data){
		if(data.statusCode==200){
			alertMsg.info(data.message);
			loaddata('reload');
		}else{
			alertMsg.error(data.message);
		}
	});
 }
</script>
</head>
<body style="overflow: hidden;">
 	<div  style="margin-left: 5px;margin-top: 5px">
		<span>提示</span>
		<p>
			在此你可以对<span><strong>菜单功能</strong></span>进行编辑!  &nbsp;<span><strong>注意</strong></span>操作功能是对菜单功能的操作权限！
			请谨慎填写程序编码，权限区分标志，请勿重复!
		</p>
	</div>
<table id="genEntityDataGrid" style="margin-bottom: 0px;vertical-align: bottom;" ></table>
<div id="toolbarDiv" class="easyui-toolbar" style="padding:4px;height:auto">
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-color"  onclick="openeditDialog();">生成编 辑　 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-color"  onclick="openeAddDialog();">创建实体 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-color"  onclick="openEditProperty();">编辑属性 </a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove"  onclick="deleteTreeNode();">查看 </a>
</div>	
</body>
</html>