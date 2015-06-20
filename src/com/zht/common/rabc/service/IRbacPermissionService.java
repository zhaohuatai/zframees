/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.service.IBaseService;

import com.zht.common.rabc.model.RbacPermission;

/**
 * 
* @ClassName :IRbacPermissionService     
* @Description :   
* @createTime :2015年4月3日  下午4:36:57   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface IRbacPermissionService extends IBaseService<RbacPermission>{
	public void scanPacgeToloadPermis(String packagz);
	
	 
/**
 *  删除操作 <br/>
 *  delete from RbacUserPermission up where up.rbacPermission.id in (:ids) "; <br/>
 *  delete from RbacUserPermissionReject upr where upr.rbacPermission.id in (:ids) "; <br/>
 *  delete from RbacRolePermission rp where rp.rbacPermission.id in (:ids) "; <br/>
 *  update RbacMenu m set m.rbacPermission.id=null where m.rbacPermission.id in (:ids) ";<br/>
 * @param ids
 */
public void deletePermission(Long[] ids);	

//--------------------------------Role--Permission----------------------------------------
 /**
  * 获取用户在当前角色下拥有的permission code集合，包括，当前角色+ UserPermission - UserPermissionReject<br/>
  * permissionCode集合： DefaultRole + RbacUserPermission - RbacUserPermissionReject
  * 
  * @param userName
  */
 public List<String> findAllPermsUserHaveInDefaultRole(String userName);

 
 /**
  * 从RbacRolePermission表根据roleId查询permission ID集合
  * @param roleId
  * @return
  */
 public List<Long> findPermissionIdsByRoleId(Long roleId) ;
 
 /**
  * 调用baseDaoImpl.loadDataSetFromOneEntity ，除了 RbacPermission class对象属性，<br/>
  * 还包括 Long roleId，Boolean isInRole
  * @param paramObject ：RbacPermission class对象属， Long roleId，Long roleId
  * @param rowMap
  * @return
  */
 public DataSet loadPermissionForRoleAssign(ParamObject paramObject,RowMap rowMap);
 
 /**
  * 向RbacRolePermission表中添加role_id=roleId & permission_id in permissionsIds的记录 
  * @param permissionsIds
  * @param roleId
  */
 public void addPermissionsToRole(Long[] permissionsIds,Long roleId);
 
 /**
  * 从RbacRolePermission表中删除role_id=roleId & permission_id in permissionsIds的记录 
  * @param permissionsIds
  * @param roleId
  */
 public void removePermsFromRole(Long[] permissionsIds,Long roleId);

 
 
 //-------------------------------User--Permission ---------------------------------------------------------------
 /**
  * 
  * @param paramObject : isEnable ,code,name
  * @param userId
  * @return
  */
 public List<Long> findPermissionIdsInUserPermissionByUserId(ParamObject paramObject,Long userId);
 public DataSet findPermissionDataSetInUserPermissionByUserId(ParamObject paramObject,Long userId);
 
 
 public List<Long> findPermissionIdsInUserPermissionRejectByUserId(ParamObject paramObject,Long userId);
 public DataSet findPermissionDataSetInUserPermissionRejectByUserId(ParamObject paramObject,Long userId);
 
 public DataSet findPermissionDataSetNotInUserPermAndUserPermReject(ParamObject po,Long userId);
 
 public void  addPermissionsToUserPermission(Long[] permissionIds,Long userId);
 
 public void  removePermissionsFromUserPermission(Long[] permissionIds,Long userId);
 
 public void  addPermissionsToUserPermissionReject(Long[] permissionIds,Long userId);
 
 public void  removePermissionsFromUserPermissionReject(Long[] permissionIds,Long userId);
 

 @SuppressWarnings("rawtypes")
public List<Map> findAllPermissionListForComobox();
}
