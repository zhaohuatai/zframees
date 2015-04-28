package com.zht.common.dauth.service;

import java.util.Collection;
import java.util.Map;

import org.zht.framework.service.IBaseService;

import com.zht.common.dauth.model.RbacDataAccessExp;

public interface IRbacDataAccessExpService extends IBaseService<RbacDataAccessExp>{
	public Map<String ,Collection<Map<String,Object>>> loadDataPrivilegeDataAccessExpMapSource();
}