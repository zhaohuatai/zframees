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
						<td><input type="text" name="name" value="${demo.name}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,100]'"/></td>
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
						<td>	<select  name="zBoolean" class="easyui-combobox"  style="width: 170px;"   >	<option value="true" <c:if test="${demo.zBoolean eq true }">selected='selected' </c:if> >是</option>	<option value="false" <c:if test="${demo.zBoolean eq false }">selected='selected' </c:if> >否</option>	</select>	</td>
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
						<th>textPassword:</th>
						<td><input type="password" name="textPassword" value="${demo.textPassword}" class="easyui-textbox validatebox" data-options=" required:true,"/></td>
					</tr>
					<tr>
						<th>texttextarea:</th>
						<td><textarea name="texttextarea" value="${demo.texttextarea}" rows="3" cols="20" class="easyui-textbox validatebox" data-options=" "></textarea></td>
						<th>textSelect:</th>
						<td>	<select  name="textSelect" class="easyui-combobox" style="width: 170px;" >	<option value="1" <c:if test="${demo.textSelect eq '1' }">selected='selected' </c:if> >北京</option>	<option value="2" <c:if test="${demo.textSelect eq '2' }">selected='selected' </c:if> >上海</option>	<option value="3" <c:if test="${demo.textSelect eq '3' }">selected='selected' </c:if> >深圳</option>	</select>	</td>
						<th>textcombox:</th>
						<td><input type="text" name="textcombox" value="${demo.textcombox}"  class="easyui-combobox"  data-options=" required:true,url:'${ctx}/core/abc.do',method:'post',valueField:'val',textField:'text'"/></td>
					</tr>
					<tr>
						<th>textcheckbox:</th>
						<td>	<input type="checkbox"  name="textcheckbox" value="1" <c:if test="${demo.textcheckbox eq '1' }">checked="checked" </c:if> >北京	<input type="checkbox"  name="textcheckbox" value="2" <c:if test="${demo.textcheckbox eq '2' }">checked="checked" </c:if> >上海	<input type="checkbox"  name="textcheckbox" value="3" <c:if test="${demo.textcheckbox eq '3' }">checked="checked" </c:if> >深圳	</td>
						<th>ZIntegerSelect:</th>
						<td>	<select  name="ZIntegerSelect" class="easyui-combobox" style="width: 170px;"  data-options=" required:true">	<option value="1" <c:if test="${demo.ZIntegerSelect eq '1' }">selected='selected' </c:if> >北京</option>	<option value="2" <c:if test="${demo.ZIntegerSelect eq '2' }">selected='selected' </c:if> >上海</option>	<option value="3" <c:if test="${demo.ZIntegerSelect eq '3' }">selected='selected' </c:if> >深圳</option>	</select>	</td>
						<th>ZDoubleSelect:</th>
						<td>	<select  name="ZDoubleSelect" class="easyui-combobox"  style="width: 170px;"   data-options=" required:true">	<option value="1" <c:if test="${demo.ZDoubleSelect eq '1' }">selected='selected' </c:if> >北京</option>	<option value="2" <c:if test="${demo.ZDoubleSelect eq '2' }">selected='selected' </c:if> >上海</option>	<option value="3" <c:if test="${demo.ZDoubleSelect eq '3' }">selected='selected' </c:if> >深圳</option>	</select>	</td>
					</tr>
					<tr>
						<th>zcombotree:</th>
						<td><input type="text" name="zcombotree" value="${demo.zcombotree}"  class="easyui-combotree"  data-options=" url:'${ctx}/core/abc.do',method:'post',valueField:'val',textField:'text'"/></td>
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