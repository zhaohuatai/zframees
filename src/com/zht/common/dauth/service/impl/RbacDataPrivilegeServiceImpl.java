package com.zht.common.dauth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.dauth.service.IRbacDataPrivilegeService;

import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.dauth.dao.IRbacDataPrivilegeDao;
import com.zht.common.dauth.model.RbacDataPrivilege;

@Service
@Transactional(rollbackFor=Exception.class)
public class RbacDataPrivilegeServiceImpl extends BaseServiceImpl<RbacDataPrivilege> implements IRbacDataPrivilegeService{
 	@Autowired
	private IRbacDataPrivilegeDao rbacDataPrivilegeDao;
 	
 	//map==><rbac_dataPrivilege,Collection<RoleCode>>
 	@SuppressWarnings("unchecked")
	public Map<String, Collection<String>> loadDataPrivilegeRoleMapSource(){
 		String hql=" "
 				+ " select "
 				+ " new map("
 				+ " drp.rbacRole.code as roleCode,"
 				+ " drp.rbacDataPrivilege.code as dpCode"
 				+ " ) from DataPrivilegeRole  drp where 1=1"
 				+ " and drp.rbacRole.enabled=true and drp.rbacDataPrivilege.enabled=true ";
 		List<Map<?,?>> list=(List<Map<?,?>>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
 		if(list==null||list.size()==0){
 			return null;
 		}
 		Map<String ,Collection<String>> dataSource=new ConcurrentHashMap<String ,Collection<String>>();
 		for(Map<?,?> map :list){
 			String dpCode=(String) map.get("dpCode");
 			List<String> roleCodeList=findroleCodeListByPdCode(list,dpCode);
 			if(dpCode!=null&&roleCodeList!=null){
 				dataSource.put(dpCode, roleCodeList);
 			}
 		}
 		return dataSource;
 	}
 	private List<String> findroleCodeListByPdCode(List<Map<?,?>> list,String dpCode){
 		if(list==null||dpCode==null){
 			return null;
 		}
 		List<String> roleCodeList=new ArrayList<String>();
 		for(Map<?,?> map:list){
 			String dpCode1=(String) map.get("dpCode");
 			
 			if(dpCode.equals(dpCode1)){
 				String roleCode=(String) map.get("roleCode");
 				if(roleCode!=null){
 					roleCodeList.add(roleCode);
 				}
 			}
 		}
 		return roleCodeList;
 	}
 //-----------------------------------------------------------------------------
 	//分配角色之后才可用
 	@SuppressWarnings("unchecked")
	public Map<String, Collection<String>> loadPermissionDataPrivilegeMapSource(){
 		String hql=""
 				+ " select new map("
 				+ " dp.rbacPermission.code as permCode, "
 				+ " dp.code as dpCode "
 				+ " )"
 				+ " from RbacDataPrivilege dp where dp.enabled=true "
 				+ " and dp.id in( "
 				+ " select "
 				+ " drp.rbacDataPrivilege.id as dpId "
 				+ " from DataPrivilegeRole  drp "
 				+  ")";
 		List<Map<?,?>> list=(List<Map<?,?>>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
 		if(list==null||list.size()==0){
 			return null;
 		}
		Map<String ,Collection<String>> dataSource=new ConcurrentHashMap<String ,Collection<String>>();
 		for(Map<?,?> map :list){
 			String permCode=(String) map.get("permCode");
 			List<String> dpCodeList=findDPCodeListByPermCode(list,permCode);
 			if(permCode!=null&&dpCodeList!=null){
 				dataSource.put(permCode, dpCodeList);
 			}
 		}
 		return dataSource;
 		
 	}
	private List<String> findDPCodeListByPermCode(List<Map<?, ?>> list,String permCode) {
		if(list==null||permCode==null){
 			return null;
 		}
 		List<String> dpCodeList=new ArrayList<String>();
 		for(Map<?,?> map:list){
 			String permCode1=(String) map.get("permCode");
 			if(permCode.equals(permCode1)){
 				String dpCode=(String) map.get("dpCode");
 				if(dpCode!=null){
 					dpCodeList.add(dpCode);
 				}
 			}
 		}
 		return dpCodeList;
	}
}