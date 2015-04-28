<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>

<script type="text/javascript">
	$(function() {
		var url="${pageContext.request.contextPath}/rbac/resource/loadResourceTreeView";
		$("#pppppname").combotree({
			width:171,
			method: "post",
			url:url,
		 	onSelect:function(node){
		 		//$("#pppppid").val(node.id);
		 		//$("#pppppname").combotree('setText', node.text);
		 	},
		 	 onLoadSuccess:function(node,data){
		         var t = $("#pppppname").combotree('tree');
		         var pppid=$("#pppppname").val();
		         if(pppid){
		        	  for (var i=0;i<data.length ;i++ ){
			        	  node=t.tree('find',pppid);
			        	  t.tree('expandAll', node.target);
			        	  t.tree('select',node.target);
			        	}
		         }
		       
		         }  
		});
	});
	
	function doSubmit(){
		if(!validateFromCallback($("#formss"),ZHT.ajaxDoneAndCloseDialog)){
			alertMsg.info("校验不通过,请确认");
		}
	}
	function cancel(){
		editDialog.close(100);
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false ," title="" style="overflow: hidden;padding: 10px;">
		<form id="formss" method="post" action="${pageContext.request.contextPath}/rbac/resource/updateResource">
		<input name="rbacResource.id" value="${rbacResource.id }" id="rbacResourceId" type="hidden"/>
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>资源编辑 </legend>
				 <table>
					 <tr>
					    <th>资源名称</th>
						<td><input name="rbacResource.name" value="${rbacResource.name }" id="name" placeholder="请输入资源名称" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[3,30]'" type="text" /></td>
						<th>父资源项名称</th>
						<td>
						<!-- 
							<input name="rbacResource.parentRbacResource.id" value="${rbacResource.parentRbacResource.id }" id="pppppid" type="hidden"/>
							 -->
							<input name="rbacResource.parentRbacResource.id" value="${rbacResource.parentRbacResource.id }"  id="pppppname" data-options="required:false" />
						</td>
					 </tr>
					 <tr>
					    <th>资源描述</th>
						<td><input name="rbacResource.description" value="${rbacResource.description }" id="description" type="text" class="easyui-textbox easyui-validatebox" /></td>
						<th>显示顺序</th>
						<td><input name="rbacResource.disIndex" value="${rbacResource.disIndex }" id="disIndex" type="text" class="easyui-numberbox" placeholder="请输入数字" data-options="required:true"   /></td>
					 </tr>
				 </table>
			</fieldset>
			<div style="position: absolute;bottom: 5px;right: 10px;"  >
				<zht:authButton text="保存" onclick="doSubmit();" iconCls="icon-ok" />
				<zht:authButton text="取消" onclick="cancel();" iconCls="icon-cancel" />
			</div>
			
		</form>
	</div>
</div>