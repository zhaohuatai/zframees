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
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>详细编辑</legend>
				 <table>
					 <tr>
					    <th>列表显示</th>
						<td>
							<select name="isList"class="easyui-combobox" style="width:170px;">
								<option value="true"  <c:if test="${genEntityProperty.isList eq true }">selected</c:if>>是</option>
								<option value="false" <c:if test="${genEntityProperty.isList eq false }">selected</c:if> >否</option>
							</select>
						</td>
						<th>是否排序字段</th>
						<td>
							<select name="isSorter"class="easyui-combobox" style="width:170px;">
								<option value="false" <c:if test="${genEntityProperty.isSorter eq false }">selected</c:if>  >否</option>
								<option value="true"  <c:if test="${genEntityProperty.isSorter eq true }">selected</c:if>  >是</option>
							</select>
						</td>
					</tr>
						<tr>
						<th>显示列宽</th>
						<td>
							<input type="text" name="columnWidth" value="${genEntityProperty.columnWidth}" class="easyui-numberbox easyui-validatebox"  data-options="required:true"/>
						</td>
					 </tr>
					<tr>
						<th>简单格式化</th>
						<td colspan="3">
							<input type="text" name="simpleFormat" value="${genEntityProperty.simpleFormat}" data-options="prompt:'(condition)-(display) 例如： (true==value)-(是),(false==value)-(否)',validType:'email'"  class="easyui-textbox easyui-validatebox" style="width: 430px;"/>
						</td>
					 </tr>
					 
					 <tr><th colspan="4"><hr></th></tr>
					 
					 <tr>
					    <th>是否查询条件</th>
						<td>
							<select name="isQueryCondtion"class="easyui-combobox" style="width:170px;">
								<option value="false" <c:if test="${genEntityProperty.isQueryCondtion eq false }">selected</c:if> >否</option>
								<option value="true"  <c:if test="${genEntityProperty.isQueryCondtion eq true }">selected</c:if>>是</option>
							</select>
						</td>
						<th>查询逻辑类型</th>
						<td>
							<select name="queryType" class="easyui-combobox" style="width:170px;">
							   <option value="" >--请选择--</option>
							   <option value="="  <c:if test="${genEntityProperty.queryType eq '=' }">selected</c:if> >=</option>
								<option value="&lt;&gt;"  <c:if test="${genEntityProperty.queryType eq '<>' }">selected</c:if> >&lt;&gt;</option>
								<option value="&gt;"   <c:if test="${genEntityProperty.queryType eq '>'   }">selected</c:if>  >&gt;</option>
								<option value="&gt;="   <c:if test="${genEntityProperty.queryType eq '>='   }">selected</c:if>  >&gt;=</option>
								<option value="&lt;"   <c:if test="${genEntityProperty.queryType eq '<'   }">selected</c:if>  >&lt;</option>
								<option value="&lt;="   <c:if test="${genEntityProperty.queryType eq '<='   }">selected</c:if>  >&lt;=</option>
								<option value="LIKE"   <c:if test="${genEntityProperty.queryType eq 'LIKE'   }">selected</c:if>  >LIKE</option>
								<option value="NOT LIKE"   <c:if test="${genEntityProperty.queryType eq 'NOT LIKE'   }">selected</c:if>  >NOTLIKE</option>
								<option value="IN"   <c:if test="${genEntityProperty.queryType eq 'IN'   }">selected</c:if>  >IN</option>
								<option value="NOTIN"   <c:if test="${genEntityProperty.queryType eq 'NOT IN'   }">selected</c:if>  >NOT IN</option>
							</select>
						</td>
					</tr>
					 <tr><th colspan="4"><hr></th></tr>
					 
						<tr>
						<th>编辑类型</th>
						<td>
							<select name="editType" class="easyui-combobox" style="width:170px;"  data-options="required:true">
							    <option value="datetime" <c:if test="${genEntityProperty.editType eq 'datetime' }">selected="selected"</c:if>>datetime</option>
							    <option value="currenttime" <c:if test="${genEntityProperty.editType eq 'currenttime' }">selected="selected"</c:if>>currenttime</option>
							</select>
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
