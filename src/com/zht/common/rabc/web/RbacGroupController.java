/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.Querylogic;
import org.zht.framework.data.RowMap;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.rabc.aop.RefreashAuthCacahe;
import com.zht.common.rabc.model.RbacGroup;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.service.IRbacGroupService;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.rabc.service.IRbacUserService;
/**
 * 
* @ClassName :RbacGroupController     
* @Description :   
* @createTime :2015年4月3日  下午4:17:13   
* @author ：zhaohuatai   
* @version :1.0
 */
@Controller
@RequestMapping("/rbac/group")
public class RbacGroupController extends BaseController{
	
	private String jspPrefix="/common/rbac/group/";
	
	@Autowired
	private IRbacGroupService rbacGroupService;
	@Autowired
	private IRbacUserService rbacUserService;
	@Autowired
	private IRbacRoleService rbacRoleService;

	
//---------------------------tree---view-------------------------------------------
	@RequiresPermissions("RbacGroup:loadGroupCombotree")
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/loadGroupCombotree")
    @ResponseBody 
    public Object loadGroupCombotree() throws Exception{
    	List<Map> list=rbacGroupService.loadGroupCombotree();  
        return FastjsonUtil.convert(list);
    }
//-------------------------------------------CRUD--------------------------------------------
    @RequiresPermissions("RbacGroup:enterGroupPage")
	@RequestMapping(value="/enterGroupPage")
    public Object enterGroupPage(){
		return jspPrefix+"groupTreeGrid";
    }
    @RequiresPermissions("RbacGroup:loadGroupTreeGrid")
	@ResponseBody 
    @RequestMapping(value="/loadGroupTreeGrid")
    public Object loadGroupTreeGrid() throws Exception{
    	DataSet dataSet=rbacGroupService.loadRbacGroupTreeGrid();  
        return FastjsonUtil.convert(dataSet);
    }
    @RequiresPermissions("RbacGroup:enterAddGroup")
    @RequestMapping(value="/enterAddGroup")
    public String enterAddGroup(){
    	return "project/demo/demoAdd";
//        return jspPrefix+"groupAdd";
    }
    @RequiresPermissions("RbacGroup:enterEidtGroup")
    @RequestMapping(value="/enterEidtGroup")
    public String enterEidtGroup(Long id,Model model){
    	RbacGroup rbacGroup=rbacGroupService.$base_find(id);
    	setDataAttribute(model,rbacGroup,"rbacGroup");
        return jspPrefix+"groupEdit";
    }
    
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:addGroup")
    @RequestMapping(value="/addGroup")
    @ResponseBody 
    public Object addGroup(@Valid @ModelAttribute("rbacGroup")RbacGroup rbacGroup,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacGroupService.$base_save(rbacGroup);
		return ajaxDoneSuccess("用户组添加成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:updateGroup")
    @RequestMapping(value="/updateGroup")
    @ResponseBody 
    public Object updateGroup(@Valid @ModelAttribute("rbacGroup")RbacGroup rbacGroup,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacGroupService.$base_update(rbacGroup);
		return ajaxDoneSuccess("用户组修改成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:deleteGroup")
    @RequestMapping(value="/deleteGroup")
    @ResponseBody 
    public Object deleteGroup(Long id){
    	rbacGroupService.deleteRbacGroup(id);
		return ajaxDoneSuccess("用户组删除成功 ");
    }
    
//-----------------------------Assignment--USER----------------------------------------------------------
    @RequiresPermissions("RbacGroup:enterUserAssignment")
    @RequestMapping(value="/enterUserAssignment")
    public String enterUserAssignment(Long id,Model model){
    	RbacGroup rbacGroup=rbacGroupService.$base_find(id);
    	setDataAttribute(model,rbacGroup,"rbacGroup");
    	return jspPrefix+"groupUserAssignment";
    }
    
    RowMap userRowMap=new RowMap(RbacUser.class).addColMap("defaultRole", "defaultRbacRole.name");
    
    @RequiresPermissions("RbacGroup:loadUserGridForUserAssign")
    @RequestMapping(value="/loadUserGridForUserAssign")
    @ResponseBody 
    public Object loadUserGridForUserAssign(@ModelAttribute("paramObject") ParamObject paramObject,Long groupId,Boolean isIn){
	    paramObject.addParamItem("userName", new ParamItem("userName","%??%",paramObject.getReqParam("userName"),Querylogic.L_like));
	    paramObject.addParamItem("enabled",  new ParamItem("enabled",paramObject.getReqParam("enabled"),Querylogic.L_equal));
	    DataSet grid= rbacUserService.loadUserDataSetIsInGroup(paramObject, userRowMap,groupId,isIn);
	   return FastjsonUtil.convert(grid);
    }
    
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:removeUserFromGroup")
    @RequestMapping(value="/removeUserFromGroup")
    @ResponseBody 
    public Object removeUserFromGroup(Long[] userIds,Long groupId){
    	rbacUserService.removeUserFromGroup(userIds,groupId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:addUserToGroup")
    @RequestMapping(value="/addUserToGroup")
    @ResponseBody 
    public Object addUserToGroup(Long[] userIds,Long groupId){
    	rbacUserService.addUserToGroup(userIds,groupId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
//-------------------------------GROUP-ROLE----Assign-----------------------------------------------

    @RequiresPermissions("RbacGroup:enterRoleAssignment")
    @RequestMapping(value="/enterRoleAssignment")
    public String enterRoleAssignment(Long id,Model model){
    	RbacGroup rbacGroup=rbacGroupService.$base_find(id);
    	setDataAttribute(model,rbacGroup,"rbacGroup");
    	return jspPrefix+"groupRoleAssignment";
    }

    @RequiresPermissions("RbacGroup:loadRoleGridForGroupRoleAssign")
    @RequestMapping(value="/loadRoleGridForGroupRoleAssign")
    @ResponseBody 
    public Object loadRoleGridForGroupRoleAssign(Long groupId,String type){
    	DataSet grid=null;
    	if("InGroupRole".equals(type)){
    		grid= rbacRoleService.findRoleDataSetInGroupRole( groupId, null);
    	}else if("NotInGroupRole".equals(type)){
    		grid=  rbacRoleService.findRoleDataSetNotInGroupRole( groupId, null);
    	}else if("FromParents".equals(type)){
    		grid=  rbacRoleService.findRoleDataSetFromGroupParents( groupId, null);
    	}else{
    		grid=new DataSet(0L,null);
    	}
	    return FastjsonUtil.convert(grid);
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:addRolesToGroupRole")
    @RequestMapping(value="/addRolesToGroupRole")
    @ResponseBody 
    public Object addRolesToGroupRole(Long[] roleIds,Long groupId){
    	rbacRoleService.addRolesToGroupRole(roleIds,groupId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacGroup:removeRolesFromGroupRole")
    @RequestMapping(value="/removeRolesFromGroupRole")
    @ResponseBody 
    public Object removeRolesFromGroupRole(Long[] roleIds,Long groupId){
    	rbacRoleService.removeRolesFromGroupRole(roleIds,groupId);
		return ajaxDoneSuccess("数据修改成功 ");
    }    
}
