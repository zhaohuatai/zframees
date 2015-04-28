package com.zht.common.dauth.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import com.zht.common.dauth.service.IRbacDataAccessExpService;
import com.zht.common.dauth.service.IRbacDataPrivilegeService;
@Scope("singleton")
@Transactional
public class DataAuthMapSource implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	IRbacDataPrivilegeService rbacDataPrivilegeService;
	
	@Autowired
	IRbacDataAccessExpService rbacDataAccessExpService;
	
	  
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadResourceDefine();
	}
	//<rbac_dataPrivilege,Collection<RoleCode>>
	private static Map<String, Collection<String>> dataPrivilegeRoleMapSource = null;
	//<rbac_permissionCode,Collection<dataPrivilege>>
	private static Map<String, Collection<String>> permissionDataPrivilegeMapSource = null;
	//<rbac_dataPrivilege,Collection<RbacDataAccessExp>>
	private static Map<String, Collection<Map<String,Object>>> dataPrivilegeDataAccessExpMapSource = null;
	
	//<rbac_dataPrivilege,Collection<daomethed>>
//	private static Map<String, Collection<Map<String,Object>>> dataPrivilegeDaoMethedMapSource = null;
	private static boolean hasRuned = false;
	public void loadResourceDefine() {
		if(!hasRuned){
			if(dataPrivilegeRoleMapSource==null){
				dataPrivilegeRoleMapSource=rbacDataPrivilegeService.loadDataPrivilegeRoleMapSource();
			}
			if(permissionDataPrivilegeMapSource==null){
				permissionDataPrivilegeMapSource=rbacDataPrivilegeService.loadPermissionDataPrivilegeMapSource();
			}
			if(dataPrivilegeDataAccessExpMapSource==null){
				dataPrivilegeDataAccessExpMapSource=rbacDataAccessExpService.loadDataPrivilegeDataAccessExpMapSource();
			}
			hasRuned=true;
		}
		
	}
	public void refreshResourceDefine() {
		hasRuned=false;
		if(dataPrivilegeRoleMapSource!=null){
			dataPrivilegeRoleMapSource.clear();
			dataPrivilegeRoleMapSource=null;
		}
		if(permissionDataPrivilegeMapSource!=null){
			permissionDataPrivilegeMapSource.clear();
			permissionDataPrivilegeMapSource=null;
		}
		if(dataPrivilegeDataAccessExpMapSource!=null){
			dataPrivilegeDataAccessExpMapSource.clear();
			dataPrivilegeDataAccessExpMapSource=null;
		}
		loadResourceDefine();
	}
	public  Map<String, Collection<String>> getPermissionDataPrivilegeMapSource() {
		return permissionDataPrivilegeMapSource;
	}
	public  Map<String, Collection<String>> getDataPrivilegeRoleMapSource() {
		return dataPrivilegeRoleMapSource;
	}
	public  Map<String, Collection<Map<String, Object>>> getDataPrivilegeDataAccessExpMapSource() {
		return dataPrivilegeDataAccessExpMapSource;
	}


	

}
 