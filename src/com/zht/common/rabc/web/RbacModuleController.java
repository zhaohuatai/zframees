/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
  
}
