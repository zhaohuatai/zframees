<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.sdses.com/zht" prefix="zht"%>
<form id="pagerForm" method="post" action="${model.str1}pageContext.request.contextPath${model.str2}/${model.actionNameSpace}/${model.entityClassName ? uncap_first}!${model.entityClassName ? uncap_first}ListForLookUp.action">
	<input type="hidden" name="pageNum" value="${model.str1}pageNum ${model.str2}" />
	<input type="hidden" name="numPerPage" value="${model.str1}numPerPage${model.str2}" />
	<input type="hidden" name="orderField" value="${model.str1}orderField${model.str2}" />
	<input type="hidden" name="orderDirection" value="${model.str1}orderDirection${model.str2}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" action="${model.str1}pageContext.request.contextPath${model.str2}/${model.actionNameSpace}/${model.entityClassName ? uncap_first}!${model.entityClassName ? uncap_first}ListForLookUp.action" method="post" onsubmit="return dwzSearch(this, 'dialog');" >
	<div class="searchBar">
<table  class="searchContent" >
<tr><#rt>
<#list model.queyParamlist as field>
<#if (field_index%3==0&&field_index > 0) ></tr></#if>
<#if (field_index%3==0&&field_index > 0) ><tr></#if>
<#lt><th>${field['desc']?default("")}：</th>
<td>
<#if (field['ManyToOne'] ? exists &&field['ManyToOne']=="ManyToOne") >
<#if (field['LookUpType'] ? exists &&field['LookUpType']=="combox") >
		<select name="${model.entityClassName ? uncap_first}.${field['field']?default("")}.id" class="${field['required']?default("required")} combox">
			<option value="">--请选择--</option>
				<c:forEach items="${model.str1}${field['field']}List${model.str2}" var="${field['field']}">
					<option value="${model.str1}${field['field']}.id${model.str2}" <c:if test="${model.str1}${field['field']}.id eq ${model.entityClassName ? uncap_first}.${field['field']}.id${model.str2}"> selected</c:if>>
					<c:out value="${model.str1}${field['field']}.${field['MTOdisplayField']}${model.str2}"></c:out>
					</option>
				</c:forEach>
		</select>
<#elseif field['LookUpType'] ? exists &&field['LookUpType']=="lookup">
			<a class="btnLook" href="${model.str1}pageContext.request.contextPath${model.str2}/${field['LookUpUrl']?default("")}" lookupGroup="orgLookup">查找带回lookup</a>		
			<input type="hidden" id="lookupFor_field" name="${model.entityClassName ? uncap_first}.${field['field']?default("")}.${field['LookUpField']?default("")}" value="${model.str1}${model.entityClassName ? uncap_first}.${field['field']?default("")}.${field['LookUpField']?default("")}${model.str2}"/>
			<input type="text" id="lookupFor_display" class="required" name="${model.entityClassName ? uncap_first}.${field['field']?default("")}.${field['LookUpDisplay']?default("")}" value=""  lookupGroup="abc" />
<#elseif field['LookUpType'] ? exists &&field['LookUpType']=="lookupTree">
 </#if>
		  
<#elseif field['special']? exists && field['special']==" boolean">
		<select class="combox" name="${model.entityClassName ? uncap_first}.${field['field']?default("")}">
			<option value="1" <c:if test="${model.str1}${model.entityClassName ? uncap_first}.${field['field']?default("")} eq '1' ${model.str2}">selected</c:if> >是</option>
			<option value="0" <c:if test="${model.str1}${model.entityClassName ? uncap_first}.${field['field']?default("")} eq '0'${model.str2}">selected</c:if> >否</option>
		</select>
<#else>
<input name="${model.entityClassName ? uncap_first}.${field['field']?default("")}" class="${field['special']?default(" ")?substring(1)}"  type="text"  value="${model.str1}${model.entityClassName ? uncap_first}.${field['field']?default("")}${model.str2}"  />
</#if>
</td><#t>
</#list>
</tr>
</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
			<li class="line">line</li>
		</ul>
	</div>
	<!-- 三行：layoutH="190" ;两行：  layoutH="164" ;一行： layoutH="138" ;零行 layoutH="76"-->
	<table class="table" width="100%" layoutH="<#t><#if (model.queyParamlist?size==0)><#t>
	76<#t>
	</#if><#t>
	<#if (model.queyParamlist?size>0 && model.queyParamlist?size<=3)><#t>
	138<#t>
	</#if><#t>
	<#if (model.queyParamlist?size>3 && model.queyParamlist?size<=6)><#t>
	164<#t>
	</#if><#t>
	<#if (model.queyParamlist?size>6 && model.queyParamlist?size<=9)><#t>
	190<#t>
	</#if><#t>
	<#if (model.queyParamlist?size>9 && model.queyParamlist?size<=12)><#t>
	214<#t>
	</#if>" >
		<thead>
			<tr>
				<#list model.fieldListMap as fieldListMap>
				<th>${fieldListMap['desc']?default("")}</th>
				</#list>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${model.str1}${model.entityClassName ? uncap_first}List${model.str2}" var="${model.entityClassName ? uncap_first}" varStatus="status">
			<tr target="sid_${model.entityClassName ? uncap_first}" rel="${model.str1}${model.entityClassName ? uncap_first}.id${model.str2}" >
<#list model.fieldListMap as fieldListMap> 
<#if (fieldListMap['ManyToOne'] ? exists &&fieldListMap['ManyToOne']=="ManyToOne") >
				<td><c:out value="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']}.${fieldListMap['MTOdisplayField']}${model.str2}" /></td>
<#else>
<#if (fieldListMap['special']? exists &&( fieldListMap['special']==" dateTime" || fieldListMap['special']==" Timestamp" ))>
				<td><fmt:formatDate value="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']?default("")}${model.str2}" type="date" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="full" /></td>
<#elseif fieldListMap['special']? exists && fieldListMap['special']==" date">
				<td><fmt:formatDate value="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']?default("")}${model.str2}" type="date" pattern="yyyy-MM-dd" dateStyle="full" /></td>
<#elseif fieldListMap['special']? exists && fieldListMap['special']==" boolean">
				<td>
					<c:if test="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']?default("")} eq '1' ${model.str2}">是</c:if>
					<c:if test="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']?default("")} eq '0' ${model.str2}">否</c:if>
				</td>
<#else>
				<td><c:out value="${model.str1}${model.entityClassName ? uncap_first}.${fieldListMap['field']?default("")}${model.str2}" /></td>
</#if>	
</#if>
</#list>
				<td>
				<a class="btnSelect" href="javascript:$.bringBackByInputId({lookupFor_field_${model.entityClassName ? uncap_first}_${model.bringBackValue}:'${model.str1}${model.entityClassName ? uncap_first}.${model.bringBackValue}${model.str2}', lookupFor_display_${model.entityClassName ? uncap_first}_${model.bringBackDispaly}:'${model.str1}${model.entityClassName ? uncap_first}.${model.bringBackDispaly}${model.str2}'})" title="查找带回">选择</a>
				</td>
			</tr>
      </c:forEach>
		
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" <c:if test="${model.str1}numPerPage eq '20' ${model.str2}">selected="selected"</c:if> >20</option>
				<option value="50" <c:if test="${model.str1}numPerPage eq '50' ${model.str2}">selected="selected"</c:if> >50</option>
				<option value="100" <c:if test="${model.str1}numPerPage eq '100' ${model.str2}">selected="selected"</c:if> >100</option>
				<option value="200" <c:if test="${model.str1}numPerPage eq '200' ${model.str2}">selected="selected"</c:if> >200</option>
				<option value="500" <c:if test="${model.str1}numPerPage eq '500' ${model.str2}">selected="selected"</c:if> >500</option>
				<option value="1000" <c:if test="${model.str1}numPerPage eq '1000' ${model.str2}">selected="selected"</c:if> >1000</option>
				<option value="2000" <c:if test="${model.str1}numPerPage eq '2000' ${model.str2}">selected="selected"</c:if> >2000</option>
				<option value="100000000" <c:if test="${model.str1}numPerPage eq '100000000' ${model.str2}">selected="selected"</c:if> >不限</option>
			</select>
			<span>条，共${model.str1}totalCount eq null?0:totalCount${model.str2}条</span>&nbsp;&nbsp;
		</div>
		<div class="pagination" targetType="navTab" totalCount="${model.str1}totalCount }" numPerPage="${model.str1}numPerPage }" pageNumShown="10" currentPage="${model.str1}pageNum }"></div>

	</div>
</div>
