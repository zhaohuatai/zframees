/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.web;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.rabc.model.RbacModule;
import com.zht.common.rabc.service.IRbacModuleService;
/**
 * 
* @ClassName :RbacModuleController     
* @Description :   
* @createTime :2015年4月3日  下午4:17:26   
* @author ：zhaohuatai   
* @version :1.0
 */
@Controller
@RequestMapping("/rbac/rbacModule")
public class RbacModuleController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IRbacModuleService  rbacMenuModuleService;
	
    @RequestMapping(value="/loadModules")
    @ResponseBody
    public Object loadMenuModules(){
    	List<RbacModule> modulesList=rbacMenuModuleService.findAllRbacMenuModuleList();
    	List<RbacModule> modulessssList=new ArrayList<RbacModule>();
    	modulessssList.addAll(modulesList);
    	return FastjsonUtil.convert(modulessssList);
    }
    @RequestMapping(value="/addMenuModule")
    @ResponseBody 
    public String addMenuModule(){
    	try{
    		List<RbacModule> rbacMenuModuleList=new ArrayList<RbacModule>();
    		for(int i = 1; i <= 20; i++) {  
    			RbacModule rbacMenuModule=new RbacModule();
    			rbacMenuModule.setDisplay("系统模块"+(i));
    			rbacMenuModule.setEnabled(true);
    			rbacMenuModule.setDisIndex(i);
    			rbacMenuModuleList.add(rbacMenuModule);
    		}
    		rbacMenuModuleService.saveOrUpdataRbacMenuModuleList(rbacMenuModuleList);
    	}catch (ServiceLogicalException e){
    		e.printStackTrace();
    	}catch (Exception e) {
			e.printStackTrace();
		}
		return "sdsad";
    }
  
}
