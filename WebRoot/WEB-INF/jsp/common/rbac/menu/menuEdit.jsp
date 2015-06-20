<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
	function doSubmit(){
		if(!ZHTAJAX.validateFromCallback($("#formss"),ZHT.ajaxDoneAndCloseDialog)){
			alertMsg.info("请确认校验不通过数据");
		}
	}
	function cancel(){
		editDialog.close(100);
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false ," title="" style="overflow: hidden;padding: 10px;">
		<form id="formss" method="post" action="${pageContext.request.contextPath}/rbac/menu/updateMenu">
			<input type="hidden" name="id" value="${rbacMenu.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>菜单添加</legend>
				 <table>
					 <tr>
					    <th>菜单名称</th>
						<td><input id="display" name="display"  value="${rbacMenu.display}" placeholder="请输入菜单名称" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[3,30]'" type="text" /></td>
						<th>上级菜单</th>
						<td>
							<input class="easyui-combotree"
								data-options="url:'${pageContext.request.contextPath}/rbac/menu/loadMenuCombotree',method:'post',valueField:'id',textField:'text'"
							 name="parentRbacMenu.id" value="${rbacMenu.parentRbacMenu.id}"  /></td>
					 </tr>
					  <tr>
					    <th>类型</th>
						<td>	
						    <select id="enabled" name="type"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="G" <c:if test="${rbacMenu.type eq 'G' }">selected="selected"</c:if>>分组</option>
								<option value="M" <c:if test="${rbacMenu.type eq 'M' }">selected="selected"</c:if>>菜单</option>
							</select>
						</td>
						<th>TAB标题</th>
						<td><input id="tabTitle"  name="tabTitle"  value="${rbacMenu.tabTitle}" type="text" class="easyui-textbox" data-options="required:true"/></td>
					 </tr>
					 <tr>
						<th>显示顺序</th>
						<td><input id="disIndex" name="disIndex"  value="${rbacMenu.disIndex}" type="text" class="easyui-textbox easyui-validatebox"  /></td>
				
					 	<th>操作地址</th>
						<td>
							<input class="easyui-combotree"
								data-options="url:'${pageContext.request.contextPath}/rbac/permission/loadPermissionList',method:'post',valueField:'id',textField:'text'"
							 name="rbacPermission.id" value="${rbacMenu.rbacPermission.id}"  style="width:300px"/></td>
					 </tr>
					 <tr>
						<th>显示图标</th>
						<td><input id="iconCls" name="iconCls"  value="${rbacMenu.iconCls}" type="text" class="easyui-textbox easyui-validatebox"  /></td>
				
					    <th>用户组描述</th>
						<td><input id="description" name="description"  value="${rbacMenu.description}" type="text" class="easyui-textbox easyui-validatebox" /></td>
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