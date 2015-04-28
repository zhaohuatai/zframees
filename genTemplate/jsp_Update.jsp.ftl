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
		<form id="formss" method="post" action="${model.jstlstart}ctx${model.jstlend}${model.genEntity.controllerNameSpace}/update${model.entitySimpleClassName}">
			<input type="hidden" name="id" value="${model.jstlstart}${model.entitySimpleClassName ? uncap_first}.id${model.jstlend}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>${model.genEntity.entityDisName}添加</legend>
				 <table>
					<tr>
						<#list model.genEntityPropertyList as entityDetail>
						<#if ((entityDetail.generatedEditOfPropertyStr?exists))>
						<#if ((entityDetail.relationType=='property'||entityDetail.relationType=='manytoone'))>
						<#if (entityDetail_index+1)%3==0>
						<#if (entityDetail.editType != 'autoCurrentTime')>
						<th>${entityDetail.display}:</th>
						<td>${entityDetail.generatedEditOfPropertyStr ?default('【未找到】')}</td>
						</#if>
					</tr>
					<tr>
						<#else>
						<#if (entityDetail.editType != 'autoCurrentTime')>
						<th>${entityDetail.display}:</th>
						<td>${entityDetail.generatedEditOfPropertyStr  ?default('【未找到】') }</td>
						</#if>
						</#if>
						</#if>
						</#if>
						</#list>
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