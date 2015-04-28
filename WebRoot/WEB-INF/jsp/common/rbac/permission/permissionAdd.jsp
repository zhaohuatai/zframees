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
		<form id="formss" method="post" action="${ctx}/rbac/permission/addPermission">
		<input type="hidden" name="id" value="${rbacPermission.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>权限添加</legend>
				 <table>
					 <tr>
					    <th>权限名称</th>
						<td colspan="3"><input type="text" name="name" value="${rbacPermission.name}"  class="easyui-textbox easyui-validatebox" data-options="required:true" style="width: 430px;" /></td>
					</tr>
					<tr>
						<th>权限编码</th>
						<td  colspan="3"><input type="text" id="code" name="code" value="${rbacPermission.code}"  class="easyui-textbox easyui-validatebox"  data-options="required:true" style="width: 430px;" /></td>
					 </tr>
					 
					 <tr>
					    <th>权限类型</th>
						<td>
							<select id="type" name="type" class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="P" <c:if test="${rbacPermission.type eq 'P' }">selected="selected"</c:if>>操作</option>
								<option value="M" <c:if test="${rbacPermission.type eq 'M' }">selected="selected"</c:if>>菜单</option>
							</select>
						</td>
						<th>是否启用</th>
						<td>
							<select id="type" name="enabled"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacPermission.enabled eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacPermission.enabled eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
					 </tr>
					 <tr>
					    <th>链接地址</th>
						<td colspan="3"><input type="text" id="url" name="url" value="${rbacPermission.url}" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width: 430px;"/></td>
					 </tr>
					 <tr>
						<th>描述</th>
						<td colspan="3"><input type="text"  id="description" name="description"   value="${rbacPermission.description}" class="easyui-textbox"  style="width: 430px;"   /></td>
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