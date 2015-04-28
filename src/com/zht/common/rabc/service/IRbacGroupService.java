/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;


import java.util.List;
import java.util.Map;
import org.zht.framework.data.DataSet;
import org.zht.framework.service.IBaseService;
import com.zht.common.rabc.model.RbacGroup;
/**
 * 
*      
* 类名称：IRbacGroupService   
* 类描述：   用户组业务逻辑接口
* 创建人：zhaohuatai   
* 创建时间：2015年4月3日 下午3:30:49   
* 修改人：zhaohuatai   
* 修改时间：2015年4月3日 下午3:30:49   
* 修改备注：   
* @version  1.0  
*
 */
@SuppressWarnings("rawtypes")
public interface IRbacGroupService extends IBaseService<RbacGroup>{

	/**
	 * 加载树形结构--TreeGrid
	 * @return
	 */
	 public DataSet loadRbacGroupTreeGrid();
	 
	 /**
	  * 加载树形结构--ComboTree
	  * @return
	  */
	 public List<Map> loadGroupCombotree() ;
	 
	 /**
	  * 删除
	  * @param id： grouoId
	  */
	 public void deleteRbacGroup(Long id);
	 
	 /**
	  * 从 GroupByUser表中根据userId 加载groupId 集合
	  * @param userId
	  * @return
	  */
	 public List<Long> findGroupIdsByUserId(Long userId);
	 
	 /**
	  * 从 GroupByRole表中根据roleId 加载groupId 集合
	  * @param roleId
	  * @return
	  */
	 public List<Long> findGroupIdsByRoleId(Long roleId);
	 
}
