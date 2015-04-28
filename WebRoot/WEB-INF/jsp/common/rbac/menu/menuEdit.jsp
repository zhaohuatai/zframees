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
		<form id="formss" method="post" action="${pageContext.request.contextPath}/rbac/group/updateGroup">
			<input type="hidden" name="id" value="${rbacGroup.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>用户组添加</legend>
				 <table>
					 <tr>
					    <th>用户组名称</th>
						<td><input name="name" id="name"  value="${rbacGroup.name}"  placeholder="请输入用户组名称" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:'length[3,30]'" type="text" /></td>
						<th>父用户组项</th>
						<td>
							<input id="pid"  class="easyui-combotree"
								data-options="url:'${ctx}/rbac/group/loadGroupCombotree',method:'post',required:true,valueField:'id',textField:'text'"
							 	name="parentRbacGroup.id" value="${rbacGroup.parentRbacGroup.id}"  />
						</td>
					 </tr>
					  <tr>
					    <th>是否启用</th>
						<td>	
						    <select id="enabled" name="enabled"   class="easyui-combobox"  style="width:170px;"  data-options="required:true" >
								<option value="true" <c:if test="${rbacGroup.enabled eq true }">selected="selected"</c:if>>是</option>
								<option value="false" <c:if test="${rbacGroup.enabled eq false }">selected="selected"</c:if>>否</option>
							</select>
						</td>
						<th>创建者</th>
						<td><input name="creater" value="${rbacGroup.creater}"  id="creater" type="text" class="easyui-textbox" data-options="required:true"/></td>
					 </tr>
					 <tr>
					    <th>用户组描述</th>
						<td colspan="3"><input name="description" value="${rbacGroup.description}"  id="description" type="text" class="easyui-textbox easyui-validatebox"  style="width: 430px;"  /></td>
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