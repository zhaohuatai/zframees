/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.service;

import java.io.Serializable;
import java.util.List;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.zhtdao.identity.PKBaseEntity;
@SuppressWarnings("hiding")
public interface IBaseService<M extends PKBaseEntity> {
	/**
	 * 保存hibernate映射实体
	 * @param m : 要持久化的model类
	 */
	public void $base_save(M m) ;
	
	/**
	 * 更新hibernate映射实体
	 * @param m : 要更新的model类
	 */
	public void $base_update(M m) ;
	/**
	 *  更新hibernate映射实体
	 * @param m : 要更新的model类
	 * @param ignorNull : 是否忽略空属性，false： 完全覆盖数据库，true:忽略待更新实体值为null的属性
	 */
	public void $base_update(M m,boolean ignorNull);
	
	/**
	 * 保存或 更新hibernate映射实体
	 * @param m ： 要更新的model类
	 */
	public void $base_saveOrUpdate(M m) ;
	//-----------------------------------
	/**
	 *  单纯删除model实体类
	 * @param m : 要更新的model类
	 */
	public void $base_delete$Just(M m);
	/**
	 *  根据ID删除
	 * @param id
	 */
	public void $base_delete$Just(Serializable id);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void $base_deleteByIdsInCase$Just(Serializable[] ids);
	/**
	 * 删除全部数据
	 */
	public void $base_deleteAll();
	//-----------------------------------
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public <M> M  $base_find(Serializable id);
	
	/**
	 * 查询记录总数
	 * @return
	 */
	public Long $base_findTotalCount();
	
	/**
	 * 查询所有Model集合
	 * @return
	 */
	public <M> List<M> $base_findAll();
	
	/**
	 * 根据ids集合查询model集合
	 * @param ids
	 * @return
	 */
	public <M> List<M> $base_findByIdsInCase(Serializable[] ids);
	
	public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap);
	
	public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap,String extraCondition);
	
	public <M> List<M> $base_findListByParamObject(ParamObject paramObject,String extraSQLStr);
	
	public Long $base_findCountByParamObject(ParamObject paramObject,String extraSQLStr);
	
	
	
//	public List<Map> $base_loadCombotree();
}
