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
					    <th>过去的时间</th>
						<td>
							<select name="isDatePost"class="easyui-combobox" style="width:170px;">
								<option value="" >不限制</option>
								<option value="false" <c:if test="${genEntityProperty.isDatePost eq false }">selected</c:if> >否</option>
								<option value="true"  <c:if test="${genEntityProperty.isDatePost eq true }">selected</c:if> >是</option>
							</select>
						</td>
						<th>将来的时间</th>
						<td>
							<select name="isDateFutrue" class="easyui-combobox" style="width:170px;">
								<option value="">不限制</option>
								<option value="true"  <c:if test="${genEntityProperty.isDateFutrue eq true }">selected</c:if>  >是</option>
								<option value="false" <c:if test="${genEntityProperty.isDateFutrue eq false }">selected</c:if> >否</option>
							</select>
						</td>
					 </tr>
					 
						<tr>
						<th>编辑类型</th>
						<td>
							<select name="editType" class="easyui-combobox" style="width:170px;"  data-options="required:true">
							    <option value="date" <c:if test="${genEntityProperty.editType eq 'date' }">selected="selected"</c:if>>date</option>
							   <!--   <option value="time" <c:if test="${genEntityProperty.editType eq 'time' }">selected="selected"</c:if>>time</option>-->
							    <option value="datetime" <c:if test="${genEntityProperty.editType eq 'datetime' }">selected="selected"</c:if>>datetime</option>
							    <option value="autoCurrentTime" <c:if test="${genEntityProperty.editType eq 'autoCurrentTime' }">selected="selected"</c:if>>autoCurrentTime</option>
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
