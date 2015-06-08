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
	
	
	
	//------------------------------------------------------------------------
 /**
  * defaultRoleHave+RbacUserPermission-RbacUserPermissionReject
  * 
  * 模式A： user <----> role{userRole+group-userRoleRject} <----> permission
  * @param userName
  */
 public List<String> findAllPermsUserHaveAndInDefaultRoleInPatternA(String userName);
 
 /**
  * 模式B： user <----> positon <----> role{position+userRole-userRoleReject[group不再参与,职位即可起到group的作用]} <----> permission
  * @param userName
  * @return
  */
 public List<String> findAllPermsUserHaveAndInDefaultRoleInPatternB(String userName);
 
 /**
  * 删除
  * @param ids
  */
 public void deletePermission(Long[] ids);
 
 
 public List<Long> findPermissionIdsByRoleId(Long roleId) ;
 
 //-------------------------------Role--Permission----------------------------------------
 public DataSet loadPermissionForRoleAssign(ParamObject paramObject,RowMap rowMap);
 
 public void addPermissionsToRole(Long[] permissionsIds,Long roleId);
 
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
 

 //2015 0526 10:18
 public List<Map> findAllPermissionList();
}
