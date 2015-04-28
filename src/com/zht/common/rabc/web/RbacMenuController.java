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
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.rabc.aop.RefreashAuthCacahe;
import com.zht.common.rabc.model.RbacMenu;
import com.zht.common.rabc.service.IRbacMenuService;
import com.zht.common.rabc.view.AccordtionView;
/**
 * 
* @ClassName :RbacMenuController     
* @Description :   
* @createTime :2015年4月3日  下午4:17:17   
* @author ：zhaohuatai   
* @version :1.0
 */
@Controller
@RequestMapping("/rbac/menu")
public class RbacMenuController extends BaseController{
	
	private String jspPrefix="/common/rbac/menu/";
	
	
	@Autowired
    private IRbacMenuService rbacMenuService;
    @RequestMapping(value="/loadMenus")
    @ResponseBody 
    public Object loadMenus(Long moduleId,Boolean useModule,Model model){
    	List<AccordtionView> rbacMenuList=rbacMenuService.findMenuListByModuleId(moduleId);
        return FastjsonUtil.convert(rbacMenuList);
    }
    
	@RequiresPermissions("RbacMenu:loadMenuCombotree")
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/loadMenuCombotree")
    @ResponseBody 
    public Object loadMenuCombotree() throws Exception{
    	List<Map> list=rbacMenuService.loadMenuCombotree();  
        return FastjsonUtil.convert(list);
    }
    //=----------------------------------------------------------------
	@RequiresPermissions("RbacMenu:enterMenuPage")
	@RequestMapping(value = "/enterMenuPage")
	public Object enterMenuPage() {
		return jspPrefix + "menuTreeGrid";
	}
	// + "g.parentRbacGroup.id as _parentId ,"
RowMap menuMap=new RowMap(RbacMenu.class)
	.addColMap("_parentId", "parentRbacMenu.id")
	.addColMap("url", "rbacPermission.url");


	@RequiresPermissions("RbacMenu:loadMenuTreeGrid")
	@ResponseBody
	@RequestMapping(value = "/loadMenuTreeGrid")
	public Object loadMenuTreeGrid(ParamObject paramObject) throws Exception {
		DataSet dataSet = rbacMenuService.loadRbacMenuTreeGrid(paramObject);
		return FastjsonUtil.convert(dataSet);
	}
    @RequiresPermissions("RbacMenu:enterAddMenu")
    @RequestMapping(value="/enterAddMenu")
    public String enterAddMenu(){
        return jspPrefix+"menuAdd";
    }
    @RequiresPermissions("RbacMenu:enterEidtMenu")
    @RequestMapping(value="/enterEidtMenu")
    public String enterEidtMenu(Long id,Model model){
    	RbacMenu rbacMenu=rbacMenuService.$base_find(id);
    	setDataAttribute(model,rbacMenu,"rbacMenu");
        return jspPrefix+"groupEdit";
    }
    
    @RefreashAuthCacahe
    @RequiresPermissions("RbacMenu:addMenu")
    @RequestMapping(value="/addMenu")
    @ResponseBody 
    public Object addMenu(@Valid @ModelAttribute("rbacMenu")RbacMenu rbacMenu,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacMenuService.$base_save(rbacMenu);
		return ajaxDoneSuccess("数据添加成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacMenu:updateMenu")
    @RequestMapping(value="/updateMenu")
    @ResponseBody 
    public Object updateMenu(@Valid @ModelAttribute("rbacMenu")RbacMenu rbacMenu,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	rbacMenuService.$base_update(rbacMenu);
		return ajaxDoneSuccess("数据修改成功 ");
    }
    @RefreashAuthCacahe
    @RequiresPermissions("RbacMenu:deleteMenu")
    @RequestMapping(value="/deleteMenu")
    @ResponseBody 
    public Object deleteMenu(Long id){
    	rbacMenuService.deleteRbacMenu(id);
		return ajaxDoneSuccess("数据删除成功 ");
    }
}
