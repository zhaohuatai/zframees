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
		<form id="formss" method="post" action="${ctx}/common/sys/department/addDepartment">
			<input type="hidden" name="id" value="${department.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>部门机构添加</legend>
				 <table>
					<tr>
						<th>名称:</th>
						<td><input type="text" name="name" value="${department.name}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,40]'"/></td>
						<th>备注:</th>
						<td><input type="text" name="remark" value="${department.remark}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,60]'"/></td>
					</tr>
					<tr>
						<th>父级部门:</th>
						<td><input type="text" name="parentDepartment.id" value="${department.parentDepartment.id}"  class="easyui-combotree"  data-options=" url:'${ctx}//common/sys/department/loadDepartmentCombotree',method:'post',valueField:'id',textField:'text'"/></td>
						<th>创建者:</th>
						<td><input type="text" name="creater" value="${department.creater}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,40]'"/></td>
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