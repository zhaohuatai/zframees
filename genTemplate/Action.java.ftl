package ${model.packageName};

import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.RowMap;
import org.zht.framework.data.Querylogic;
import ${model.entityFullClassName};
import ${model.servicePackageName}.I${model.entitySimpleClassName}Service;
@Controller 
@RequestMapping("${model.controllerNameSpace}")
public class ${model.entitySimpleClassName}Controller extends BaseController {

	private String jspPrefix="${model.controllerNameSpace}/";
	@Autowired
	private I${model.entitySimpleClassName}Service ${model.entitySimpleClassName ? uncap_first}Service;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("${model.entitySimpleClassName}:enter${model.entitySimpleClassName}Page")
	@RequestMapping(value="/enter${model.entitySimpleClassName}Page")
	public String enter${model.entitySimpleClassName}Page() throws Exception {
		<#if ((model.genEntity.isTree ? exists &&model.genEntity.isTree == true))>
		return jspPrefix+"${model.entitySimpleClassName? uncap_first}TreeGrid";
		<#else>
		return jspPrefix+"${model.entitySimpleClassName? uncap_first}DataGrid";	
		</#if>
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap( 
	<#list model.genEntityPropertyList as entityDetail>
	<#if ((entityDetail.isList ? exists && entityDetail.isList==true))>
	<#if (entityDetail.relationType=="manytoone")>
	"${entityDetail.propertyName}","${entityDetail.propertyName }.${entityDetail.manyToOneDisplayField }",
	<#elseif (entityDetail.relationType=="onetoonefk")>
	"${entityDetail.propertyName}","${entityDetail.propertyName }.${entityDetail.oneToOneDisplayField }",
	<#else>
	"${entityDetail.propertyName}","${entityDetail.propertyName}",
	</#if>
	</#if>
	<#if ((model.genEntity.isTree ? exists &&model.genEntity.isTree == true))>
	<#if ((entityDetail.isTreeProperty ? exists && entityDetail.isTreeProperty==true))>
	"_parentId","${entityDetail.propertyName }.id",
	</#if>
	</#if>
	</#list>
	"id","id");
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("${model.entitySimpleClassName}:load${model.entitySimpleClassName}GridView")
    @RequestMapping(value="/load${model.entitySimpleClassName}GridView")
    @ResponseBody 
    public Object load${model.entitySimpleClassName}GridView(@ModelAttribute("paramObject") ParamObject paramObject){
    
	    <#list model.genEntityPropertyList as entityDetail>
		<#if ((entityDetail.isQueryCondtion ? exists && entityDetail.isQueryCondtion==true))>
		<#if ((entityDetail.queryType ? exists && entityDetail.queryType=='LIKE'))>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}","%??%",paramObject.getReqParam("${entityDetail.propertyName}"),"${entityDetail.queryType}"));
		<#elseif ((entityDetail.isTreeProperty ? exists && entityDetail.isTreeProperty==true))>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}.id",paramObject.getReqParam("${entityDetail.propertyName}.id"),"${entityDetail.queryType}",Querylogic.T_Long));
		<#elseif ((entityDetail.propertyName ? exists && entityDetail.propertyName=='long'))>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}",paramObject.getReqParam("${entityDetail.propertyName}"),"${entityDetail.queryType}",Querylogic.T_Integer));
		<#elseif  ((entityDetail.propertyName ? exists && entityDetail.propertyName=='integer'))>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}",paramObject.getReqParam("${entityDetail.propertyName}"),"${entityDetail.queryType}",Querylogic.T_Long));
		<#elseif  ((entityDetail.propertyName ? exists && entityDetail.propertyName=='boolean'))>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}",paramObject.getReqParam("${entityDetail.propertyName}"),"${entityDetail.queryType}",Querylogic.T_Boolean));
		<#else>
		paramObject.addParamItem("key_${entityDetail.propertyName}", new ParamItem("${entityDetail.propertyName}",paramObject.getReqParam("${entityDetail.propertyName}"),"${entityDetail.queryType}"));
		</#if>
		</#if>
		</#list>
		
	    DataSet grid= ${model.entitySimpleClassName ? uncap_first}Service.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("${model.entitySimpleClassName}:enter${model.entitySimpleClassName}Detail")
	@RequestMapping(value="/enter${model.entitySimpleClassName}Detail")
	public String enter${model.entitySimpleClassName}Detail(Model model , Long id) throws Exception {	
		${model.entitySimpleClassName} ${model.entitySimpleClassName ? uncap_first }=${model.entitySimpleClassName ? uncap_first}Service.$base_find(id);
		setDataAttribute(model,${model.entitySimpleClassName ? uncap_first },"${model.entitySimpleClassName ? uncap_first }");
		return jspPrefix+"${model.entitySimpleClassName ? uncap_first }Detail";
	}	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("${model.entitySimpleClassName}:enterAdd${model.entitySimpleClassName}")
	@RequestMapping(value="/enterAdd${model.entitySimpleClassName}")
	public String enterAdd${model.entitySimpleClassName}() throws Exception {	
		return jspPrefix+"${model.entitySimpleClassName ? uncap_first }Add";
	}	   
	@RequiresPermissions("${model.entitySimpleClassName}:add${model.entitySimpleClassName}")
    @ResponseBody
    @RequestMapping(value="/add${model.entitySimpleClassName}") 
    public Object add${model.entitySimpleClassName}(@Valid @ModelAttribute("${model.entitySimpleClassName ? uncap_first }")${model.entitySimpleClassName} ${model.entitySimpleClassName ? uncap_first },BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	${model.entitySimpleClassName ? uncap_first}Service.$base_save(${model.entitySimpleClassName ? uncap_first });
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("${model.entitySimpleClassName}:enterUpdate${model.entitySimpleClassName}")
	@RequestMapping(value="/enterUpdate${model.entitySimpleClassName}") 
 	public String enterUpdate${model.entitySimpleClassName}(Model model , Long id) throws Exception {
		${model.entitySimpleClassName} ${model.entitySimpleClassName ? uncap_first }=${model.entitySimpleClassName ? uncap_first}Service.$base_find(id);
		setDataAttribute(model,${model.entitySimpleClassName ? uncap_first },"${model.entitySimpleClassName ? uncap_first }");
		return jspPrefix+"${model.entitySimpleClassName ? uncap_first }Update";
	}       
	@RequiresPermissions("${model.entitySimpleClassName}:update${model.entitySimpleClassName}")
    @ResponseBody
    @RequestMapping(value="/update${model.entitySimpleClassName}") 
    public Object update${model.entitySimpleClassName}(@Valid @ModelAttribute("${model.entitySimpleClassName ? uncap_first }")${model.entitySimpleClassName} ${model.entitySimpleClassName ? uncap_first },BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	${model.entitySimpleClassName ? uncap_first}Service.$base_update(${model.entitySimpleClassName ? uncap_first });
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("${model.entitySimpleClassName}:simpleDelete${model.entitySimpleClassName}")
	@ResponseBody 
	@RequestMapping(value="/simpleDelete${model.entitySimpleClassName}")
    public Object simpleDelete${model.entitySimpleClassName}(Long id) throws Exception {
    	${model.entitySimpleClassName ? uncap_first}Service.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("${model.entitySimpleClassName}:simpleBatchDelete${model.entitySimpleClassName}")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDelete${model.entitySimpleClassName}")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	${model.entitySimpleClassName ? uncap_first}Service.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    
/*-------------------------------------------------------------------------------------------------------*/
<#if ((model.genEntity.isTree ? exists &&model.genEntity.isTree == true))>
	@RequiresPermissions("${model.entitySimpleClassName}:load${model.entitySimpleClassName}Combotree")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/load${model.entitySimpleClassName}Combotree")
	@ResponseBody
	public Object load${model.entitySimpleClassName}Combotree() throws Exception {
		java.util.List<java.util.Map> list= ${model.entitySimpleClassName ? uncap_first}Service.$base_loadCombotree();
		return FastjsonUtil.convert(list);
	}
</#if>
}
