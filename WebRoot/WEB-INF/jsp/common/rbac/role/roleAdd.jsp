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
		<form id="formss" method="post" action="${ctx}/rbac/role/addRole">
		 <input type="hidden" name="id" value="${rbacRole.id}"   />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>角色编辑</legend>
				 <table>
					 <tr>
					    <th>角色名称</th>
						<td><input type="text" name="name" value="${rbacRole.name}"  class="easyui-textbox easyui-validatebox" data-options="required:true" /></td>
						<th>角色编码</th>
						<td>
							<input type="text" id="code" name="code" value="${rbacRole.code}" class="easyui-textbox easyui-validatebox"  data-options="required:true" />
						</td>
					 </tr>
					 <tr>
						<th>是否启用</th>
						<td>
							<select id="type" name="enabled"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacRole.enabled eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacRole.enabled eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
						<th>描述</th>
						<td><input type="text"  id="description" name="description"   value="${rbacRole.description}" class="easyui-textbox"/></td>
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