/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;

import java.util.List;

import com.zht.common.rabc.model.RbacModule;

public interface IRbacModuleService {

	public RbacModule findRbacMenuModuleById(Long id);
	
	public List<RbacModule> findAllRbacMenuModuleList();
	
	public void saveOrUpdataRbacMenuModule(RbacModule rbacMenuModule);
	public void saveOrUpdataRbacMenuModuleList(List<RbacModule> rbacMenuModuleList);
}
