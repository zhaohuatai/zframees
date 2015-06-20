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
		<form id="formss" method="post" action="${ctx}/common/sys/position/updatePosition">
			<input type="hidden" name="id" value="${position.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>职位信息表添加</legend>
				 <table>
					<tr>
						<th>职位名称:</th>
						<td><input type="text" name="name" value="${position.name}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,40]'"/></td>
						<th>创建人:</th>
						<td><input type="text" name="creator" value="${position.creator}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,40]'"/></td>
					</tr>
					<tr>
						<th>所属部门:</th>
						<td><input type="text" name="department.id" value="${position.department.id}"  class="easyui-combotree"  data-options=" url:'${ctx}/common/sys/department/loadDepartmentCombotree',method:'post',valueField:'id',textField:'text'"/></td>
						<th>对应角色:</th>
						<td><input type="text" name="rbacRole.id" value="${position.rbacRole.id}"  class="easyui-combobox"  data-options=" url:'${ctx}/rbac/role/loadRoleFoSelect',method:'post',valueField:'id',textField:'name'"/></td>
					</tr>
					<tr>
						<th>备注:</th>
						<td><input type="text" name="remark" value="${position.remark}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,60]'"/></td>
					
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