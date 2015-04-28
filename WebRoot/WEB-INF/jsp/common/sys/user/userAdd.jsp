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
		<form id="formss" method="post" action="${ctx}/common/sys/user/addUser">
			<input type="hidden" name="id" value="${user.id}" />
			<fieldset>
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>系统用户添加</legend>
				 <table>
					<tr>
						<th>用户名称:</th>
						<td><input type="text" name="userName" value="${user.userName}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,40]'"/></td>
						<th>用户编码:</th>
						<td><input type="text" name="userNum" value="${user.userNum}" class="easyui-textbox validatebox"  data-options=" required:true, validType:'length[0,40]'"/></td>
						<th>出生日期:</th>
						<td><input type="text" name="birth" value="${user.birth}" class="easyui-datebox"   data-options=" "/></td>
					</tr>
					<tr>
						<th>性别:</th>
						<td>	<select  name="sex" class="easyui-combobox"  style="width: 170px;"  >	<option value=" 1" <c:if test="${user.sex eq ' 1' }">selected='selected' </c:if> >男</option>	<option value="2" <c:if test="${user.sex eq '2' }">selected='selected' </c:if> >女</option>	</select>	</td>
						<th>邮箱:</th>
						<td><input type="text" name="email" value="${user.email}" class="easyui-textbox validatebox"  data-options="  validType:'email'"/></td>
						<th>电话:</th>
						<td><input type="text" name="phone" value="${user.phone}" class="easyui-textbox validatebox"  data-options="  validType:'phone'"/></td>
					</tr>
					<tr>
						<th>身份证号码:</th>
						<td><input type="text" name="perIdNum" value="${user.perIdNum}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,18]'"/></td>
						<th>QQ号码:</th>
						<td><input type="text" name="qqNum" value="${user.qqNum}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,20]'"/></td>
						<th>微信号:</th>
						<td><input type="text" name="weixinNum" value="${user.weixinNum}" class="easyui-textbox validatebox"  data-options="  validType:'length[0,30]'"/></td>
					</tr>
					<tr>
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