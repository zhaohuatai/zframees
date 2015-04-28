<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/meta/taglib.jsp" %>
<script type="text/javascript">
	$(function() {
	});
	function doSubmit(){
		if(!ZHTAJAX.validateFromCallback($("#formss"),ZHT.ajaxDone)){
			alertMsg.info("请确认校验不通过数据");
		}
	}
	function cancel(){
		editDialog.close(100);
	}
</script>
		<form id="formss" method="post" action="${ctx}/common/generator/genEntityProperty/updatePropertyDetail">
		<input type="hidden" name="id" value="${genEntityProperty.id}"/>
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>
				详细编辑： 只支持 one-one 外键的形式，不支持共享主键
				</legend>
				 <table>
					  <tr>
					    <th>是否主控中间表</th>
						<td >
							<select name="isManytoManyMasterControl"class="easyui-combobox" style="width:170px;">
								<option value="true"  <c:if test="${genEntityProperty.isManytoManyMasterControl eq true }">selected</c:if>>是</option>
							</select>
						</td>
						<th>从表-MappedBy</th>
						<td colspan="3">
						<input class="easyui-combobox" name="oneToOneMappedBy" style="width:170px;" value="${genEntityProperty.manytomanyMappedBy}" 
							data-options="
								url:'${ctx}/common/generator/genEntityProperty/loadPropertyNameByEntityName?entityName=${fn:replace(genEntityProperty.propertyType,'.','_')}',
								method:'post',
								valueField:'val',
								textField:'text',
								panelHeight:'auto'">
						</td>
					</tr>
					  <tr><th colspan="4"><hr></th></tr>
					  
				 </table>
			</fieldset>
			<div style="position: absolute;bottom: 5px;right: 10px;"  >
				<zht:authButton text="保存" onclick="doSubmit();" iconCls="icon-ok" />
				<zht:authButton text="取消" onclick="cancel();" iconCls="icon-cancel" />
			</div>
			
		</form>
