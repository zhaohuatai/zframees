/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.service.IBaseService;

import com.zht.common.codegen.model.GenEntityProperty;

public interface IGenEntityPropertyService extends IBaseService<GenEntityProperty>{
	public void addOrUpdateGenEntityProperty(GenEntityProperty entityProperty);
	
	public void updatePropertyDetail(GenEntityProperty entityProperty);
	
	public List<Map<String,String>> loadPropertyNameByEntityName(String entityName);
	
}