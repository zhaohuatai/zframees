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
		<form id="formss" method="post" action="${ctx}/project/demomany/addDemomany">
			<input type="hidden" name="id" value="${demomany.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>测试Many添加</legend>
				 <table>
					<tr>
						<th>name:</th>
						<td><input type="text" name="name" value="${demomany.name}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,100]'"/></td>
						<th>Demo:</th>
						<td><input type="text" name="Demo" value="${demomany.Demo}"  class="easyui-combobox"  data-options=" url:'${ctx}/core/abc.do',method:'post',valueField:'val',textField:'text'"/></td>
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