/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.zhtdao.identity.PKBaseEntity;
@SuppressWarnings("hiding")
public interface IBaseService<M extends PKBaseEntity> {
	
	public void $base_save(M m) ;
	public void $base_update(M m) ;
	
	public void $base_saveOrUpdate(M m) ;
	//-----------------------------------
	public void $base_delete$Just(M m);
	public void $base_delete$Just(Serializable id);
	public void $base_deleteByIdsInCase$Just(Serializable[] ids);
	public void $base_deleteAll();
	//-----------------------------------
	
	public <M> M  $base_find(Serializable id);
	
	public Long $base_findTotalCount();
	
	public <M> List<M> $base_findAll();
	
	public <M> List<M> $base_findByIdsInCase(Serializable[] ids);
	
	public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap);
	
	public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap,String extraCondition);
	
	public <M> List<M> $base_findListByParamObject(ParamObject paramObject,String extraSQLStr);
	public Long $base_findCountByParamObject(ParamObject paramObject,String extraSQLStr);
	
	
	
	public List<Map> $base_loadCombotree();
}
