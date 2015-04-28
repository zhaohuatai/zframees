/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.service.IBaseService;

import com.zht.common.rabc.model.RbacMenu;
import com.zht.common.rabc.view.AccordtionView;

public interface IRbacMenuService extends IBaseService<RbacMenu>{
	public List<AccordtionView> findMenuListByModuleId(Long moduleId);
	
	public DataSet  loadRbacMenuTreeGrid(ParamObject paramObject);
	 /**
	  * 加载树形结构--ComboTree
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
	public List<Map> loadMenuCombotree() ;
	 
	 /**
	  * 删除
	  * @param id： grouoId
	  */
	 public void deleteRbacMenu(Serializable id);
	 
}