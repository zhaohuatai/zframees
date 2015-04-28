/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import com.zht.common.rabc.dao.IRbacGroupDao;
import com.zht.common.rabc.model.RbacGroup;
import com.zht.common.rabc.service.IRbacGroupService;
import com.zht.common.rabc.util.RbacGroupUtil;
@Service
@Transactional(rollbackFor=Exception.class)
@SuppressWarnings("unchecked")
public class RbacGroupServiceImpl extends BaseServiceImpl<RbacGroup> implements IRbacGroupService{
	@Autowired
	private IRbacGroupDao  rbacGroupDao;
	
	@Override
	public DataSet  loadRbacGroupTreeGrid() {
	  String hql=" select "
						 + "g.id as id ,"
				         + "g.parentRbacGroup.id as _parentId ,"
				         + "g.name as name ,"
				         + "g.enabled as enabled,"
				         + "g.createTime as createTime,"
				         + "g.creater as creater "
				         + "@from RbacGroup g ";
		DataSet dataSet=baseDaoImpl.loadDataSet(hql,new ParamObject(POType.H_NO_NC));
		return dataSet;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> loadGroupCombotree() {
		List<Long> rootIdList=(List<Long>) baseDaoImpl.findJustList("select id from RbacGroup g where g.parentRbacGroup.id is null ", new ParamObject(POType.H_NO_NC));
		if(rootIdList==null||rootIdList.size()==0){
			throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
		}
		if(rootIdList.size()>1){
			throw new ServiceLogicalException("发现多个根节点数据，请检查数据 ");
		}
		String hql=" select "
						 + "g.id as id ,"
				         + "g.parentRbacGroup.id as _parentId ,"
				         + "g.name as text "
				         + "@from RbacGroup g ";
		
		DataSet dataSet=baseDaoImpl.loadDataSet(hql,new ParamObject(POType.H_NO_NC));
		Map root=dataSet.getRows().get(0);
		RbacGroupUtil.traverse(root,dataSet.getRows());
		List<Map> mapList=new ArrayList<Map>();
		mapList.add(root);
		return mapList;
	}
	

	@Override
	public void deleteRbacGroup(Long id) {}

	@Override
	public List<Long> findGroupIdsByUserId(Long userId) {
		if(userId==null){
			return null;
		}
		String hql=" select gu.rbacGroup.id from RbacGroupUser  gu where gu.rbacUser.id=:userId ";
		List<Long> groupIdList=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		return groupIdList;
	}
	@Override
	public List<Long> findGroupIdsByRoleId(Long roleId) {
		if(roleId==null){
			return null;
		}
		String hql=" select gr.rbacGroup.id from RbacGroupRole  gr where gr.rbacRole.id=:roleId ";
		List<Long> groupIdList=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("roleId", roleId));
		return groupIdList;
	}

}
