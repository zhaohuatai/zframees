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
import org.zht.framework.data.DataSet;
import org.zht.framework.data.RowMap;
import com.zht.common.dauth.model.RbacDataPrivilege;
import com.zht.common.dauth.service.IRbacDataPrivilegeService;
@Controller 
@RequestMapping("/common/rbac/dataPrivilege")
public class RbacDataPrivilegeController extends BaseController {

	private String jspPrefix="/common/rbac/dataPrivilege/";
	@Autowired
	private IRbacDataPrivilegeService rbacDataPrivilegeService;
/*--------------------------------------------------------------------------------*/ 	
	@RequiresPermissions("RbacDataPrivilege:enterRbacDataPrivilegePage")
	@RequestMapping(value="/enterRbacDataPrivilegePage")
	public String enterRbacDataPrivilegePage() throws Exception {
		return jspPrefix+"rbacDataPrivilegeDataGrid";
	}
/*--------------------------------------------------------------------------------*/  	
	
	private static final  RowMap rowMap=new RowMap(RbacDataPrivilege.class);
/*--------------------------------------------------------------------------------*/  		
	@RequiresPermissions("RbacDataPrivilege:loadRbacDataPrivilegeGridView")
    @RequestMapping(value="/loadRbacDataPrivilegeGridView")
    @ResponseBody 
    public Object loadRbacDataPrivilegeGridView(@ModelAttribute("paramObject") ParamObject paramObject){
	    DataSet grid= rbacDataPrivilegeService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(grid);
    }
/*--------------------------------Detail------------------------------------------------*/  
	@RequiresPermissions("RbacDataPrivilege:enterRbacDataPrivilegeDetail")
	@RequestMapping(value="/enterRbacDataPrivilegeDetail")
	public String enterRbacDataPrivilegeDetail(Model model , Long id) throws Exception {	
		RbacDataPrivilege rbacDataPrivilege=rbacDataPrivilegeService.$base_find(id);
		setDataAttribute(model,rbacDataPrivilege,"rbacDataPrivilege");
		return jspPrefix+"rbacDataPrivilegeDetail";
	}	
/*-------------------------Add-------------------------------------------------------*/   	
	@RequiresPermissions("RbacDataPrivilege:enterAddRbacDataPrivilege")
	@RequestMapping(value="/enterAddRbacDataPrivilege")
	public String enterAddRbacDataPrivilege() throws Exception {	
		return jspPrefix+"rbacDataPrivilegeAdd";
	}	   
	@RequiresPermissions("RbacDataPrivilege:addRbacDataPrivilege")
    @ResponseBody
    @RequestMapping(value="/addRbacDataPrivilege") 
    public Object addRbacDataPrivilege(@Valid @ModelAttribute("rbacDataPrivilege")RbacDataPrivilege rbacDataPrivilege,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacDataPrivilegeService.$base_save(rbacDataPrivilege);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
/*---------------------------Update-----------------------------------------------------*/    
	@RequiresPermissions("RbacDataPrivilege:enterUpdateRbacDataPrivilege")
	@RequestMapping(value="/enterUpdateRbacDataPrivilege") 
 	public String enterUpdateRbacDataPrivilege(Model model , Long id) throws Exception {
		RbacDataPrivilege rbacDataPrivilege=rbacDataPrivilegeService.$base_find(id);
		setDataAttribute(model,rbacDataPrivilege,"rbacDataPrivilege");
		return jspPrefix+"rbacDataPrivilegeUpdate";
	}       
	@RequiresPermissions("RbacDataPrivilege:updateRbacDataPrivilege")
    @ResponseBody
    @RequestMapping(value="/updateRbacDataPrivilege") 
    public Object updateRbacDataPrivilege(@Valid @ModelAttribute("rbacDataPrivilege")RbacDataPrivilege rbacDataPrivilege,BindingResult bindingResult) throws Exception{
    	processValidateResult(bindingResult);
    	rbacDataPrivilegeService.$base_update(rbacDataPrivilege);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
/*------------------------------simpleDelete--------------------------------------------------*/    
	@RequiresPermissions("RbacDataPrivilege:simpleDeleteRbacDataPrivilege")
	@ResponseBody 
	@RequestMapping(value="/simpleDeleteRbacDataPrivilege")
    public Object simpleDeleteRbacDataPrivilege(Long id) throws Exception {
    	rbacDataPrivilegeService.$base_delete$Just(id);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
    @RequiresPermissions("RbacDataPrivilege:simpleBatchDeleteRbacDataPrivilege")
	@ResponseBody 
	@RequestMapping(value="/simpleBatchDeleteRbacDataPrivilege")
    public Object simpleBatchDeleteRbacUserSession(Long[] ids) throws Exception {
    	rbacDataPrivilegeService.$base_deleteByIdsInCase$Just(ids);
    	return ajaxDoneSuccess("数据删除成功 ");
    }
/*-------------------------------------------------------------------------------------------------------*/
}