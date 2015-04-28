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
						<th><font style="color: red">显示字段</font></th>
						<td>
						<input class="easyui-combobox" name="manyToOneDisplayField" style="width:170px;" value="${genEntityProperty.manyToOneDisplayField}" 
							data-options="
								url:'${ctx}/common/generator/genEntityProperty/loadPropertyNameByEntityName?entityName=${fn:replace(genEntityProperty.propertyType,'.','_')}',
								method:'post',
								valueField:'val',
								textField:'text',
								panelHeight:'auto'">
						</td>
					</tr>
						<tr>
						<th>是否排序字段</th>
						<td>
							<select name="isSorter"class="easyui-combobox" style="width:170px;">
								<option value="false" <c:if test="${genEntityProperty.isSorter eq false }">selected</c:if>  >否</option>
								<option value="true"  <c:if test="${genEntityProperty.isSorter eq true }">selected</c:if>  >是</option>
							</select>
						</td>
						<th>显示列宽</th>
						<td>
							<input type="text" name="columnWidth" value="${genEntityProperty.columnWidth}" class="easyui-numberbox easyui-validatebox" />
						</td>
					 </tr>
					<tr>
						<th>简单格式化</th>
						<td colspan="2">
							<input type="text" name="simpleFormat" value="${genEntityProperty.simpleFormat}" 
							data-options="multiline:true"  
							 class="easyui-textbox easyui-validatebox"
						    style="width: 270px;height:120px"/>
						</td>
						 <th>严格格式如：<br/>
						 	formatter: function(value,row){<br/>
							&nbsp;&nbsp;if(true==value){<br/>
							&nbsp;&nbsp;&nbsp;return 启用; <br/>
							&nbsp;&nbsp;}else if(false==value){<br/>
							&nbsp;&nbsp;&nbsp;return 禁用; <br/>
							&nbsp;&nbsp;}<br/>
							&nbsp;}<br/>
						  </th>
					 </tr>
					 	<tr>
						<th colspan="2"><font style="color: red">树形结构中是否父属性</font> </th>
						<td colspan="2" style="text-align: left;">
							<select name="isTreeProperty" class="easyui-combobox easyui-validatebox" style="width:170px;"  >
									<option value="">请选择</option>
									<option value="false" <c:if test="${genEntityProperty.isTreeProperty eq false }">selected</c:if> >否</option>
									<option value="true"  <c:if test="${genEntityProperty.isTreeProperty eq true }">selected</c:if>>是</option>
							</select>
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
								<option value="combobox"   <c:if test="${genEntityProperty.editType eq 'combobox' }">selected="selected"</c:if>>combobox</option>
								<option value="combotree" <c:if test="${genEntityProperty.editType eq 'combotree' }">selected="selected"</c:if>>combotree</option>
								<option value="lookup" <c:if test="${genEntityProperty.editType eq 'lookup' }">selected="selected"</c:if>>lookup</option>
							</select>
						</td>
					 </tr>
					 <tr>
						  <th>内容编辑</th>	
						  <td colspan="4">
						    <input type="text" name="editContent" value="${genEntityProperty.editContent}" 
							    data-options="multiline:true,prompt:'针对combobox,select,lookup'"  class="easyui-textbox easyui-validatebox"
							    style="width: 430px;height:100px"/>
						     </td>
					  </tr>
					  <tr>
						  <th>内容编辑例如</th>	
						  <th colspan="4">
						   select：{display-value}&nbsp;&nbsp;严格格式如：&nbsp;{北京-1}{上海-2}{深圳-3}<br/><br/>
						   combobox:&nbsp;格式：{url:/core/abc.do,valueField:val,textField:text}<br/><br/>
						   lookup:{url}&nbsp;&nbsp;严格格式如：&nbsp;{url:/core/abc.do}
						   </th>
					  </tr>		       
					  <tr><th colspan="4"><hr></th></tr>
					  
				 </table>
			</fieldset>
			<div style="position: absolute;bottom: 5px;right: 10px;"  >
				<zht:authButton text="保存" onclick="doSubmit();" iconCls="icon-ok" />
				<zht:authButton text="取消" onclick="cancel();" iconCls="icon-cancel" />
			</div>
			
		</form>
