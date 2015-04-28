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
		<form id="formss" method="post" action="${ctx}/project/demo/addDemo">
			<input type="hidden" name="id" value="${demo.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>测试实体类添加</legend>
				 <table>
					<tr>
						<th>名称:</th>
						<td><input type="text" name="name" value="${demo.name}" class="easyui-validatebox textbox"  data-options=" required:true, validType:'length[0,100]'"/></td>
						<th>ZInteger:</th>
						<td><input type="text" name="ZInteger" value="${demo.ZInteger}" class="easyui-numberbox textbox"  data-options=" required:true,min:0.0,max:100.0 "/></td>
						<th>zByte:</th>
						<td><input type="text" name="zByte" value="${demo.zByte}" class="easyui-numberbox textbox"  data-options=" min:-128.0,max:127.0 "/></td>
					</tr>
					<tr>
						<th>zDouble:</th>
						<td><input type="text" name="zDouble" value="${demo.zDouble}" class="easyui-numberbox textbox"  data-options=" min:-20.0,max:100.0,scale:10 "/></td>
						<th>ZBigDecimal:</th>
						<td><input type="text" name="ZBigDecimal" value="${demo.ZBigDecimal}" class="easyui-numberbox textbox"  data-options=" min:1.0,max:100.0,scale:10 "/></td>
						<th>zBoolean:</th>
						<td>	<select  name="zBoolean" class="easyui-combobox"  >	<option value="true" <c:if test="${demo.zBoolean eq true }">selected='selected' </c:if> >是</option>	<option value="false" <c:if test="${demo.zBoolean eq false }">selected='selected' </c:if> >否</option>	</select>
</td>
					</tr>
					<tr>
						<th>zDate:</th>
						<td><input type="text" name="zDate" value="${demo.zDate}" class="easyui-datebox"   data-options=" required:true,"/></td>
						<th>zDateTime:</th>
						<td><input type="text" name="zDateTime" value="${demo.zDateTime}" class="easyui-datetimebox"   data-options=" required:true,"/></td>
						<th>zTimeStamp:</th>
						<td><input type="text" name="zTimeStamp" value="${demo.zTimeStamp}" class="easyui-datetimebox"  data-options=" "/></td>
					</tr>
					<tr>
						<th>zbytes:</th>
						<td>【未找到】</td>
						<th>textPassword:</th>
						<td><input type="password" name="textPassword" value="${demo.textPassword}" class="easyui-validatebox textbox" data-options=" required:true,"/></td>
					</tr>
					<tr>
						<th>texttextarea:</th>
						<td><textarea name="texttextarea" value="${demo.texttextarea}" rows="3" cols="20" class="easyui-validatebox textbox" data-options=" "></textarea></td>
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