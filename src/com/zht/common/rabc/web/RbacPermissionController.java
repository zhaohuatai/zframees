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
import com.zht.common.rabc.model.RbacPermission;
import com.zht.common.rabc.service.IRbacMenuService;
import com.zht.common.rabc.service.IRbacPermissionService;

@Controller
@RequestMapping("/rbac/permission")
public class RbacPermissionController extends BaseController{
	private static final String prefix="common/rbac/permission/";
	@Autowired
    private IRbacMenuService  rbacMenuService;
	@Autowired
    private IRbacPermissionService rbacPermissionService;
	
	@RequestMapping(value="/scanPacgeToloadPermis")
	@ResponseBody 
    public Object scanPacgeToloadPermis(){
		//rbacPermissionService.scanPacgeToloadPermis("com.zht.common.rabc");
		rbacPermissionService.scanPacgeToloadPermis("com.zht.common.sys");
		return ajaxDoneSuccess("数据...成功 ");
    }
//------------------------------------------CRUD----------------------------------------------------

	@RequiresPermissions("RbacPermission:enterPermissionPage")
	@RequestMapping(value="/enterPermissionPage")
    public String enterPermissionPage(){
		return prefix+"permissionDataGrid";
    }
	
	RowMap rowMap=new RowMap(RbacPermission.class);

	@RequiresPermissions("RbacPermission:loadPermissionGridView")
    @RequestMapping(value="/loadPermissionGridView")
    @ResponseBody 
    public Object loadPermissionGridView(@ModelAttribute("paramObject") ParamObject paramObject){
    	paramObject.addParamItem("name", new ParamItem("name","%??%",paramObject.getReqParam("name") ,Querylogic.L_like));
	    paramObject.addParamItem("code",  new ParamItem("code",paramObject.getReqParam("code"),Querylogic.L_equal));
	    paramObject.addParamItem("enabled",  new ParamItem("enabled",paramObject.getReqParam("enabled"),Querylogic.L_equal));
	    paramObject.addParamItem("type",  new ParamItem("type",paramObject.getReqParam("type"),Querylogic.L_equal));
	    DataSet dataSet= rbacPermissionService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
	    return FastjsonUtil.convert(dataSet);
    }
	//----------------JJM 2015-0527 10:18
	@RequiresPermissions("RbacPermission:loadPermissionList")
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/loadPermissionList")
    @ResponseBody 
    public Object loadPermission() throws Exception{
    	List<Map> list=rbacPermissionService.findAllPermissionListForComobox();  
        return FastjsonUtil.convert(list);
    }
	
	
	@RequiresPermissions("RbacPermission:enterAddPermission")
    @RequestMapping(value="/enterAddPermission")
    public String enterAddPermission(){
        return prefix+"permissionAdd";
    }

	@RequiresPermissions("RbacPermission:addPermission")
    @RequestMapping(value="/addPermission")
    @ResponseBody 
    public Object addPermission(@Valid @ModelAttribute("rbacPermission")RbacPermission rbacPermission,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacPermissionService.$base_save(rbacPermission);
		return ajaxDoneSuccess("数据添加成功 ");
    }  

	@RequiresPermissions("RbacPermission:enterUpdatePermission")
    @RequestMapping(value="/enterUpdatePermission")
    public String enterUpdatePermission(Long id,Model model){
    	RbacPermission rbacPermission=rbacPermissionService.$base_find(id);
    	setDataAttribute(model,rbacPermission,"rbacPermission");
        return prefix+"permissionEdit";
    }
	
	@RequiresPermissions("RbacPermission:updatePermission")
    @RefreashAuthCacahe
    @RequestMapping(value="/updatePermission")
    @ResponseBody 
    public Object updatePermission(@Valid @ModelAttribute("rbacPermission")RbacPermission rbacPermission,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacPermissionService.$base_update(rbacPermission);
		return ajaxDoneSuccess("数据修改成功 ");
    }

	@RequiresPermissions("RbacPermission:deletePermission")
    @RefreashAuthCacahe
    @RequestMapping(value="/deletePermission")
    @ResponseBody 
    public Object deletePermission(Long[] ids){
    	rbacPermissionService.deletePermission(ids);
		return ajaxDoneSuccess("数据删除成功 ");
    }
    

}
