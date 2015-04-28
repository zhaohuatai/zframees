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
				<legend><img src="resources/images/fromedit.png" style="margin-bottom: -3px;"/>详细编辑-整型数字</legend>
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
					 
					 <tr><th colspan="4"><hr></th></tr>
					 
					 <tr>
					    <th>是否查询条件</th>
						<td>
							<select name="isQueryCondtion"class="easyui-combobox" style="width:170px;">
								<option value="false" <c:if test="${genEntityProperty.isList eq false }">selected</c:if> >否</option>
								<option value="true"  <c:if test="${genEntityProperty.isList eq true }">selected</c:if>>是</option>
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
					    <th>唯一</th>
						<td>
							<select name="unique"class="easyui-combobox" style="width:170px;">
								<option value="false" <c:if test="${genEntityProperty.unique eq false }">selected</c:if> >否</option>
								<option value="true"  <c:if test="${genEntityProperty.unique eq true }">selected</c:if> >是</option>
							</select>
						</td>
						<th>可空</th>
						<td>
							<select name="nullable" class="easyui-combobox" style="width:170px;">
								<option value="true"  <c:if test="${genEntityProperty.nullable eq true }">selected</c:if>  >是</option>
								<option value="false" <c:if test="${genEntityProperty.nullable eq false }">selected</c:if> >否</option>
								
							</select>
						</td>
					 </tr>
					  <tr>
						<th>最大值</th>
						<td>
						<input type="text" name="maxValue" value="${genEntityProperty.maxValue}" class="easyui-numberbox easyui-validatebox"/>
						</td>
						<th>最小值 </th>
						<td>
						<input type="text"  name="minValue" value='${genEntityProperty.minValue}' class="easyui-numberbox easyui-validatebox" />
						</td>
					 </tr>
						<tr>
						<th>编辑类型</th>
						<td>
							<select name="editType" class="easyui-combobox" style="width:170px;"  data-options="required:true">
							    <option value="text" <c:if test="${genEntityProperty.editType eq 'text' }">selected="selected"</c:if>>text</option>
								<option value="combobox"   <c:if test="${genEntityProperty.editType eq 'combobox' }">selected="selected"</c:if>>combobox</option>
								<option value="select" <c:if test="${genEntityProperty.editType eq 'select' }">selected="selected"</c:if>>select</option>
								<option value="lookup" <c:if test="${genEntityProperty.editType eq 'lookup' }">selected="selected"</c:if>>lookup</option>
							</select>
						</td>
						<th ></th>
						<td>
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
						   select / checkbox：{value-display}&nbsp;&nbsp;<font style="color: red">严格格式如：&nbsp;{1-北京}{2-上海}{3-深圳}<br/><br/></font>
						   combobox:&nbsp;<font style="color: red">格式：{url:/core/abc.do,valueField:val,textField:text}<br/><br/></font>
						   lookup:{url}&nbsp;&nbsp;<font style="color: red">严格格式如：&nbsp;{url:/core/abc.do}</font>
						   <br/> <br/>
						   textarea:<font style="color: red">严格格式如：{rows-3}{cols-20}</font>
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
