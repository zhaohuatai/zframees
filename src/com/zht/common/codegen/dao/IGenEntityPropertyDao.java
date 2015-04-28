/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.dao;/**

 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
import java.util.List;
import java.util.Map;

import org.zht.framework.zhtdao.base.IBaseDao;

import com.zht.common.codegen.model.GenEntityProperty;

public interface IGenEntityPropertyDao extends IBaseDao{
	public List<String>  findPropertyNameListByEntityId(Long entityId);
	public List<String>  findColumnNameListByEntityId(Long entityId);
	
	public void saveIfnotExits(List<GenEntityProperty> entityGenEntityPropertyList);
	
	
	public List<Map<String, String>> loadPropertyNameByEntityName(String entityName);
}