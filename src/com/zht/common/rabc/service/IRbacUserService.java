/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;


import java.util.List;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.service.IBaseService;

import com.zht.common.rabc.model.RbacUser;

public interface IRbacUserService extends IBaseService<RbacUser>{
 /**
  *  根据用户名，获取RbacUser对象
  * @param username
  * @return
  */
 public RbacUser findUserByName(String username) ;
//---------------------------------------------------------------------------------------------
 
 /**
  * 
  * @param username
  * @return
  */
 public Boolean checkDefaultRole(String username) ;
 
//----------------------GROUP@S-----------------------------------------------------------------------
 
 public DataSet loadUserDataSetIsInGroup(ParamObject paramObject,RowMap rowMap,Long groupId,Boolean isIn);
 
 /**
  * 从GroupUser表中删除group_id=groupId & user_id in userIds的记录
  * @param userIds
  * @param groupId
  */
 public void removeUserFromGroup(Long[] userIds,Long groupId);
 
 /**
  *  向GroupUser表中添加group_id=groupId & user_id in userIds的记录 
  * @param userIds
  * @param groupId
  */
 public void addUserToGroup(Long[] userIds,Long groupId);
 
 /**
  * 查询当前用户组中的RbacUser ID集合 ，不包括继承父用户组
  * @param groupid
  * @return
  */
 public List<Long> findUserIdsByGroupId(Long groupid);
 
 //-------------------------------Department@S---------------------------------------------------------------------------
 
 public void kickOutUser(Long userId);
 
}
