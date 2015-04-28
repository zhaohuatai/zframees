/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.web;


import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.annos.RepeatToken;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.Querylogic;
import org.zht.framework.data.RowMap;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.rabc.aop.RefreashAuthCacahe;
import com.zht.common.rabc.model.RbacPermission;
import com.zht.common.rabc.model.RbacRole;
import com.zht.common.rabc.service.IRbacGroupRoleService;
import com.zht.common.rabc.service.IRbacPermissionService;
import com.zht.common.rabc.service.IRbacRolePermissionService;
import com.zht.common.rabc.service.IRbacRoleService;
/**
 * 
* @ClassName :RbacRoleController     
* @Description :   
* @createTime :2015年4月3日  下午4:17:45   
* @author ：zhaohuatai   
* @version :1.0
 */
@RequiresPermissions("RbacRole:*")
@Controller
@RequestMapping("/rbac/role")
public class RbacRoleController extends BaseController{
	private static final String jspPrefix="common/rbac/role/";
	@Autowired
    private IRbacRolePermissionService rbacRolePermissionService;
	@Autowired
    private IRbacPermissionService rbacPermissionService;
	@Autowired
    private IRbacRoleService rbacRoleService;
	@Autowired
	private IRbacGroupRoleService rbacGroupRoleService;
	
//----------------------------------------CRUD------------------------------------------------	

	@RequiresPermissions("RbacRole:enterRolePage")
	@RequestMapping(value="/enterRolePage")
    public Object enterRolePage(){
		return jspPrefix+"roleDataGrid";
    }

	RowMap rowMap=new RowMap(RbacRole.class);

	@RequiresPermissions("RbacRole:loadRoleGridView")
    @RequestMapping(value="/loadRoleGridView")
    @ResponseBody 
    public Object loadRoleGridView(@ModelAttribute("paramObject") ParamObject paramObject){
    	paramObject.addParamItem("name", new ParamItem("name","%??%",paramObject.getReqParam("name"),Querylogic.L_like));
	    paramObject.addParamItem("code",  new ParamItem("code",paramObject.getReqParam("code"),Querylogic.L_equal));
	    paramObject.addParamItem("enabled",  new ParamItem("enabled",paramObject.getReqParam("enabled"),Querylogic.L_equal));
	    DataSet dataSet= rbacRoleService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(dataSet);
    }

	@RequiresPermissions("RbacRole:enterAddRole")
    @RequestMapping(value="/enterAddRole")
    public String enterAddRole(){
        return jspPrefix+"roleAdd";
    }

	@RequiresPermissions("RbacRole:addRole")
    @RequestMapping(value="/addRole")
    @ResponseBody
    @RefreashAuthCacahe
    public Object addRole(@Valid @ModelAttribute("rbacRole")RbacRole rbacRole,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacRoleService.$base_save(rbacRole);
    	return ajaxDoneSuccess("数据操作成功 ");
    }

	@RequiresPermissions("RbacRole:enterUpdateRole")
    @RefreashAuthCacahe
    @RequestMapping(value="/enterUpdateRole")
    public Object enterUpdateRole(Long id,Model model){
    	RbacRole rbacRole=rbacRoleService.$base_find(id);
    	setDataAttribute(model,rbacRole,"rbacRole");
        return jspPrefix+"roleEdit";
    }

	@RequiresPermissions("RbacRole:updateRole")
    @RefreashAuthCacahe
    @RequestMapping(value="/updateRole")
    @ResponseBody 
    public Object updateRole(@Valid @ModelAttribute("rbacRole")RbacRole rbacRole,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacRoleService.$base_update(rbacRole);
		return ajaxDoneSuccess("数据操作成功 ");
    }

	@RequiresPermissions("RbacRole:deleteRole")
    @RefreashAuthCacahe
    @RequestMapping(value="/deleteRole")
    @ResponseBody 
    public Object deleteRole(Long[] ids){
    	rbacRoleService.deleteRbacRole(ids);
		return ajaxDoneSuccess("数据删除成功 ");
    }
//----------------------------Permission-----Assignment---------------------------------------------------------------   
    

	@RequiresPermissions("RbacRole:enterPermissionAssignment")
    @RequestMapping(value="/enterPermissionAssignment")
    public String enterPermissionAssignment(Long id,Model model){
    	RbacRole rbacRole=rbacRoleService.$base_find(id);
    	setDataAttribute(model,rbacRole,"rbacRole");
    	return jspPrefix+"rolePermissionAssignment";
    }
    
	RowMap perMissionRowMap=new RowMap(RbacPermission.class);

	@RequiresPermissions("RbacRole:loadPermissionForRoleAssign")
    @ResponseBody 
    @RequestMapping(value="/loadPermissionForRoleAssign")
    public Object loadPermissionForRoleAssign(ParamObject paramObject){
		paramObject.addParamItem("name", new ParamItem("name","%??%",paramObject.getReqParam("name") ,Querylogic.L_like));
	    paramObject.addParamItem("code",  new ParamItem("code",paramObject.getReqParam("code"),Querylogic.L_equal));
	    paramObject.addParamItem("enabled",  new ParamItem("enabled",paramObject.getReqParam("enabled"),Querylogic.L_equal));
	    paramObject.addParamItem("type",  new ParamItem("type",paramObject.getReqParam("type"),Querylogic.L_equal));
    	DataSet grid=rbacPermissionService.loadPermissionForRoleAssign( paramObject,perMissionRowMap);
	    return FastjsonUtil.convert(grid);
    }

	@RequiresPermissions("RbacRole:addPermissionsToRole")
    @RefreashAuthCacahe
    @RepeatToken
    @ResponseBody 
    @RequestMapping(value="/addPermissionsToRole")
    public Object addPermissionsToRole(Long[] permissionsIds,Long roleId){
    	rbacPermissionService.addPermissionsToRole(permissionsIds,roleId);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacRole:removePermissionsFromRole")
    @RefreashAuthCacahe
    @ResponseBody 
    @RequestMapping(value="/removePermissionsFromRole")
    public Object removePermissionsFromRole(Long[] permissionsIds,Long roleId){
    	rbacPermissionService.removePermsFromRole(permissionsIds, roleId);
		return ajaxDoneSuccess("数据修改成功 ");
    }
}
