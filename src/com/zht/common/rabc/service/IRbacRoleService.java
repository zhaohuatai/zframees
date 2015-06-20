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
/**
 * 
 * @author z_huatai
 *
 */
public interface IRbacRoleService extends IBaseService<RbacRole>{
 /**
  * 1.delete from RbacRolePermission <br/>
  * 2.delete from RbacGroupRole <br/>
  * 3.delete from RbacUserRole <br/>
  * 4.delete from RbacUserRoleReject <br/>
  * 5.delete from RbacRole <br/>
  * @param ids
  */
 public void deleteRbacRole(Long[] ids);
 
 
 //------------------------------USER------------------------------------------------
 public List<?> findRoleCodeUserHaveForCombox(Long userId,Boolean isEnable);
 
 public List<?> findRoleComoboxData();
//------------------------------------------------------------------------------------------------------------
 
 /**
  * 加载用户所拥有的角色集合 ：包括 ： userRole(+)   user extends group (+)   userRoleRject(-) <br/>
  * 即：UserRole表  + User继承自用户组(包括直接继承用户组，以及用户组继承自父节点获得的Role)-UserRoleRject表
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeUserHave(Long userId,Boolean isEnable);
 
 /**
  * 加载用户所拥有的角色名称集合 ：包括 ： userRole(+)   user extends group (+)   userRoleRject(-) <br/>
  * 即：UserRole表  + User继承自用户组(包括直接继承用户组，以及用户组继承自父节点获得的Role)-UserRoleRject表
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleNameUserHave(Long userId,Boolean isEnable);
 
 /**
  *  加载用户所拥有的角色名称集合 ：包括 ： userRole(+)   user extends group (+)   userRoleRject(-) <br/>
  *  即：UserRole表  + User继承自用户组(包括直接继承用户组，以及用户组继承自父节点获得的Role)-UserRoleRject表
  * @param userName
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeUserHave(String userName,Boolean isEnable);
 
//------------------------------------------------------------------------------------------------------------
 
 /**
  * 从RbacUserRole表中，根据userId查询RoleCode集合 
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeInUserRoleByUserId(Long userId,Boolean isEnable);
 
 /**
  * 从RbacUserRole表中，根据userId查询Role记录的DataSet集合 
  * @param userId
  * @param isEnable
  * @return
  */
 public DataSet findRoleDataSetInUserRoleByUserId(Long userId,Boolean isEnable);
 
//------------------------------------------------------------------------------------------------------------
 
 /**
  * 从UserRoleReject表中，根据userId查询RoleCode集合 
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeInUserRoleRejectByUserId(Long userId,Boolean isEnable);
 /**
  * 从UserRoleReject表中，根据userId查询Role记录的DataSet集合 
  * @param userId
  * @param isEnable
  * @return
  */
 public DataSet findRoleDataSetInUserRoleRejectByUserId(Long userId,Boolean isEnable);
 
//------------------------------------------------------------------------------------------------------------
 
 /**
  * 获取用户继承自所在各个用户组的角色code集合 (**包括用户组继承自父用户组的role集合)
  * @param userId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeFromUserExtenGroupsByUserId(Long userId,Boolean isEnable);
 
 /**
  *  获取用户继承自所在各个用户组的角色DataSet集合,(**包括用户组继承自父用户组的role集合) <br/>
  *  (group中的角色不包括字节点所拥有的角色)
  * @param userId
  * @param isEnable
  * @return
  */
 public DataSet findRoleDataSetFromUserExtendsGroupsByUserId(Long userId,Boolean isEnable);
 
//------------------------------------------------------------------------------------------------------------
 /**
  * 根据用户ID获取不在UserRole 而且不在 UserRoleReject中的role集合<br/>
  * 用作给用户分配权限
  * @param userId
  * @param isEnable
  * @return
  */
 public DataSet findRoleDataSetNotInUserRole$UserRoleReject(Long userId,Boolean isEnable);
 
 /**
  * 从UserRole表中删除user_id=userId & role_id in roleIds的记录
  * @param roleIds
  * @param userId
  */
 public void removeRolesFromUserRole(Long[] roleIds,Long userId);
 
 /**
  * 向UserRole表中添加user_id=userId & role_id in roleIds的记录 
  * @param roleIds
  * @param userId
  */
 public void addRolesToUserRole(Long[] roleIds,Long userId);
 /**
  * 从UserRoleReject表中删除user_id=userId & role_id in roleIds的记录
  * @param roleIds
  * @param userId
  */
 public void removeRolesFromUserRoleReject(Long[] roleIds,Long userId);
 
 /**
  * 向UserRoleReject表中添加user_id=userId & role_id in roleIds的记录 
  * @param roleIds
  * @param userId
  */
 public void addRolesToUserRoleReject(Long[] roleIds,Long userId);

 //---------------------------------GROUP-------------------------------------------------
 /**
  *根据groupid查询 group所拥有的角色code集合，包括 group自身以及继承自父用户组的role集合，去掉重复<br/>
  * from_grouprole + from_parents  --> 去掉重复
  * @param groupId：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public List<String> findRoleCodeGroupHave(Long groupId,Boolean isEnable);

 /**
  * 从GroupRole表中，根据groupId查询RoleCode集合 
  * @param groupId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeInGroupRole(Long groupId,Boolean isEnable);
 /**
  * 只是从 GroupRole 表中根据GroupId 查询 RoleCode 集合
  * @param groupId ：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetInGroupRole(Long groupId,Boolean isEnable);
 

 /**
  * 表中根据GroupId 查询 不在  GroupRole 表中的 RoleCode 
  * @param groupId：用户组ID
  * @param isEnable：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetNotInGroupRole(Long groupId,Boolean isEnable);
 
 /**
  * 获取group继承获得的role集合
  * @param groupId
  * @param isEnable
  * @return
  */
 public List<String> findRoleCodeFromParentGroups(Long groupId,Boolean isEnable);
 
 /**
  * 查询父级用户组 所有的 roleCode ，（直到根节点的累加）
  * @param groupId：用户组ID
  * @param isEnable  ：role是否enable，若为空则不限制
  * @return
  */
 public DataSet findRoleDataSetFromGroupParents(Long groupId,Boolean isEnable);
 
 /**
  * 从GroupRole表中删除group_id=groupId & role_id in roleIds的记录
  * @param roleIds
  * @param groupId
  */
 public void removeRolesFromGroupRole(Long[] roleIds,Long groupId);
 
 /**
  * 向GroupRole表中添加group_id=groupId & role_id in roleIds的记录 
  * @param roleIds
  * @param groupId
  */
 public void addRolesToGroupRole(Long[] roleIds,Long groupId);
 
 
}
