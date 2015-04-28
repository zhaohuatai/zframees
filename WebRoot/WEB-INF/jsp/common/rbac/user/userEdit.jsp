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
		<form id="formss" method="post" action="${ctx}/rbac/user/updateUser">
		 <input type="hidden" name="id" value="${rbacUser.id}"   />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>用户编辑</legend>
				 <table>
					 <tr>
					    <th>用户名称</th>
						<td><input type="text" id="" name="userName" value="${rbacUser.userName}"   class="easyui-textbox easyui-validatebox" data-options="required:true,readonly:true" /></td>
						<th>用户昵称</th>
						<td>
							<input type="text" id="alias" name="alias" value="${rbacUser.alias}" class="easyui-textbox easyui-validatebox"  data-options="required:true" />
						</td>
					 </tr>
					 <tr>
						<th>是否启用</th>
						<td>
							<select id="type" name="enabled"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacUser.enabled eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacUser.enabled eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
						<th>账户是否过期</th>
						<td>
							<select id="type" name="accountNonExpired"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacUser.accountNonExpired eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacUser.accountNonExpired eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
					 </tr>
					 
					<tr>
						<th>用户认证是否过期</th>
						<td>
							<select id="type" name="credentialsNonExpired"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacUser.credentialsNonExpired eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacUser.credentialsNonExpired eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
						<th>用户账户是否锁定</th>
						<td>
							<select id="type" name="accountNonLocked"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacUser.accountNonLocked eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacUser.accountNonLocked eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
					 </tr>
					 
					 <tr>
						<th>默认角色</th>
						<td>
						<input class="easyui-combobox" name="defaultRbacRole.id"
						 data-options="url: '${ctx}/rbac/user/loadRoleCodeUserHaveForCombox?userId=${rbacUser.id }',
							method: 'post',valueField: 'id',textField: 'name',required:true" 
						 value="${rbacUser.defaultRbacRole.id}" >
						</td>
						<th>备注</th>
						<td>
						<input type="text"  id="description" name="description"   value="${rbacUser.description}" class="easyui-textbox"/>
						</td>
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