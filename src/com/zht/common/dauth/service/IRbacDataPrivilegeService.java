package com.zht.common.dauth.service;

import java.util.Collection;
import java.util.Map;

import org.zht.framework.service.IBaseService;

import com.zht.common.dauth.model.RbacDataPrivilege;

public interface IRbacDataPrivilegeService extends IBaseService<RbacDataPrivilege>{
	public Map<String, Collection<String>> loadDataPrivilegeRoleMapSource();
	public Map<String, Collection<String>> loadPermissionDataPrivilegeMapSource();
}