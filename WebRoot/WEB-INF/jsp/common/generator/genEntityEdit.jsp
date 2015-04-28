<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script src="${pageContext.request.contextPath}/resources/easyui141/selfdefine/easyui.zht.editDialog.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
	});
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
		<form id="formss" method="post" action="${ctx}/common/generator/genEntity/genEntityAdd">
		 <input type="hidden" name="rbacRole.id" value="${rbacRole.id}"   />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>角色编辑</legend>
				 <table>
					 <tr>
					 	<th>实体类全名</th>
						<td>
						<input type="text" id="name" name="name" value="${name}" class="easyui-textbox easyui-validatebox"  data-options="required:true" style="width: 430px;"/>
						</td>
					   </tr>
					    <tr>
						<th>控制层访问路径</th>
						<td>
							<input type="text" id="controllerNameSpace" name="controllerNameSpace" value="${controllerNameSpace}" class="easyui-textbox easyui-validatebox"  data-options="required:true" style="width: 430px;"/>
						</td>
					 </tr>
					  <tr>
					   <th>实体类显示名称</th>
						<td><input type="text" name="entityDisName" value="${entityDisName}"  class="easyui-textbox easyui-validatebox" data-options="required:true" style="width: 430px;" /></td>
					 </tr>
					 <tr>
						<th>数据表名 </th>
						<td>
						<input type="text" id="tableName" name="tableName" value="${tableName}" class="easyui-textbox easyui-validatebox"  data-options="required:true" style="width: 430px;"/>
						</td>
					 </tr>
					 <tr>
						<th>是否树形</th>
						<td>
							<select id="isTree" name="isTree"   class="easyui-combobox"  style="width:170px;"  data-options="required:true">
							    <option value="false" <c:if test="${isTree eq false }">selected="selected"</c:if>>否</option>
								<option value="true" <c:if test="${isTree eq true }">selected="selected"</c:if>>是</option>
							</select>
						</td>
						</tr>
					    <tr>
						<th>描述</th>
						<td><input type="text"  id="remark" name="remark"   value="${remark}" class="easyui-textbox" style="width: 430px;"/>
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