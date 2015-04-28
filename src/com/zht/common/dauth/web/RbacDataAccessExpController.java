package com.zht.common.dauth.web;

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
import com.zht.common.dauth.model.RbacDataAccessExp;
import com.zht.common.dauth.service.IRbacDataAccessExpService;
@Controller 
@RequestMapping("/common/rbac/dataAccessExp")
public class RbacDataAccessExpController extends BaseController {

	private String jspPrefix="/common/rbac/dataAccessExp/";
	@Autowired
	private IRbacDataAccessExpService rbacDataAccessExpService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("RbacDataAccessExp:enterRbacDataAccessExpPage")
	@RequestMapping(value="/enterRbacDataAccessExpPage")
	public String enterRbacDataAccessExpPage() throws Exception {
		return jspPrefix+"rbacDataAccessExpDataGrid";
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap(RbacDataAccessExp.class);
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("RbacDataAccessExp:loadRbacDataAccessExpGridView")
    @RequestMapping(value="/loadRbacDataAccessExpGridView")
    @ResponseBody 
    public Object loadRbacDataAccessExpGridView(@ModelAttribute("paramObject") ParamObject paramObject){
	    DataSet grid= rbacDataAccessExpService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("RbacDataAccessExp:enterRbacDataAccessExpDetail")
	@RequestMapping(value="/enterRbacDataAccessExpDetail")
	public String enterRbacDataAccessExpDetail(Model model , Long id) throws Exception {	
		RbacDataAccessExp rbacDataAccessExp=rbacDataAccessExpService.$base_find(id);
		setDataAttribute(model,rbacDataAccessExp,"rbacDataAccessExp");
		return jspPrefix+"rbacDataAccessExpDetail";
	}	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("RbacDataAccessExp:enterAddRbacDataAccessExp")
	@RequestMapping(value="/enterAddRbacDataAccessExp")
	public String enterAddRbacDataAccessExp() throws Exception {	
		return jspPrefix+"rbacDataAccessExpAdd";
	}	   
	@RequiresPermissions("RbacDataAccessExp:addRbacDataAccessExp")
    @ResponseBody
    @RequestMapping(value="/addRbacDataAccessExp") 
    public Object addRbacDataAccessExp(@Valid @ModelAttribute("rbacDataAccessExp")RbacDataAccessExp rbacDataAccessExp,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacDataAccessExpService.$base_save(rbacDataAccessExp);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("RbacDataAccessExp:enterUpdateRbacDataAccessExp")
	@RequestMapping(value="/enterUpdateRbacDataAccessExp") 
 	public String enterUpdateRbacDataAccessExp(Model model , Long id) throws Exception {
		RbacDataAccessExp rbacDataAccessExp=rbacDataAccessExpService.$base_find(id);
		setDataAttribute(model,rbacDataAccessExp,"rbacDataAccessExp");
		return jspPrefix+"rbacDataAccessExpUpdate";
	}       
	@RequiresPermissions("RbacDataAccessExp:updateRbacDataAccessExp")
    @ResponseBody
    @RequestMapping(value="/updateRbacDataAccessExp") 
    public Object updateRbacDataAccessExp(@Valid @ModelAttribute("rbacDataAccessExp")RbacDataAccessExp rbacDataAccessExp,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	rbacDataAccessExpService.$base_update(rbacDataAccessExp);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("RbacDataAccessExp:simpleDeleteRbacDataAccessExp")
	@ResponseBody 
	@RequestMapping(value="/simpleDeleteRbacDataAccessExp")
    public Object simpleDeleteRbacDataAccessExp(Long id) throws Exception {
    	rbacDataAccessExpService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("RbacDataAccessExp:simpleBatchDeleteRbacDataAccessExp")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteRbacDataAccessExp")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	rbacDataAccessExpService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
/*-------------------------------------------------------------------------------------------------------*/
}