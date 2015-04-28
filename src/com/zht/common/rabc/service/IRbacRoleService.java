/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;


import java.util.List;
import org.zht.framework.data.DataSet;
import org.zht.framework.service.IBaseService;
import com.zht.common.rabc.model.RbacRole;
public interface IRbacRoleService extends IBaseService<RbacRole>{
 public void deleteRbacRole(Long[] ids);
 
 
 //------------------------------USER------------------------------------------------
 public List<?> findRoleCodeUserHaveForCombox(Long userId,Boolean isEnable);
 /**
  * 模式A： user <----> role{userRole+group-userRoleRject} <----> permission
  * userRole(+)   user extends group (+)  -- userRoleRject(-)
  * 用户所拥有的角色Code集合
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeUserHaveInPatternA(Long userId,Boolean isEnable);
/**
 * 模式A： user <----> role{userRole+group-userRoleRject} <----> permission
 * @param userName
 * @param isEnable
 * @return
 */
 public List<String> findRoleCodeUserHaveInPatternA(String userName,Boolean isEnable);
 /**
  * 模式B： user <----> positon <----> role{position+userRole-userRoleReject[group不再参与,职位即可起到group的作用]} <----> permission
  * 
  * from position + from userRole - from userRoleReject
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeUserHaveInPatternB(Long userId,Boolean isEnable);
 /**
  * 模式B： user <----> positon <----> role{position+userRole-userRoleReject[group不再参与,职位即可起到group的作用]} <----> permission
  * @param userName
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeUserHaveInPatternB(String userName,Boolean isEnable);

 
 /**
  * roleCode from=>userRole: by userId
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeInUserRoleByUserId(Long userId,Boolean isEnable);
 public DataSet findRoleDataSetInUserRoleByUserId(Long userId,Boolean isEnable);
 
 public List<String> findRoleCodeInUserRoleRejectByUserId(Long userId,Boolean isEnable);
 public DataSet findRoleDataSetInUserRoleRejectByUserId(Long userId,Boolean isEnable);
 
 public List<String> findRoleCodeFromUserExtendsGroupsByUserId(Long userId,Boolean isEnable);
 public DataSet findRoleDataSetFromUserExtendsGroupsByUserId(Long userId,Boolean isEnable);
 
 
 public DataSet findRoleDataSetNotInUserRole$UserRoleReject(Long userId,Boolean isEnable);
 
 public void removeRolesFromUserRole(Long[] roleIds,Long userId);
 
 public void addRolesToUserRole(Long[] roleIds,Long userId);
 
 public void removeRolesFromUserRoleReject(Long[] roleIds,Long userId);
 
 public void addRolesToUserRoleReject(Long[] roleIds,Long userId);

 //---------------------------------GROUP-------------------------------------------------
 /**
  * from_grouprole + from_parents  --> 去掉重复
  * @param groupId：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public List<String> findRoleCodeGroupHave(Long groupId,Boolean isEnable);

 
 public List<String> findRoleCodeInGroupRole(Long groupId,Boolean isEnable);
 /**
  * 只是从 GroupRole 表中根据GroupId 查询 RoleCode 
  * @param groupId ：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetInGroupRole(Long groupId,Boolean isEnable);
 

 /**
  *  表中根据GroupId 查询 不在  GroupRole 表中的 RoleCode 
  * @param groupId：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetNotInGroupRole(Long groupId,Boolean isEnable);
 
 public List<String> findRoleCodeFromParentGroups(Long groupId,Boolean isEnable);
 
 /**
  * 查询父级用户组 所有的 roleCode ，（直到根节点的累加）
  * @param groupId：用户组ID
  * @param isEnable  ：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetFromGroupParents(Long groupId,Boolean isEnable);
 
 public void removeRolesFromGroupRole(Long[] roleIds,Long groupId);
 
 public void addRolesToGroupRole(Long[] roleIds,Long groupId);
 
 
}
