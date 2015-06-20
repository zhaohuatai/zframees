package com.zht.common.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.sys.service.IDepartmentService;
import com.zht.common.sys.service.IPositionService;

import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.sys.dao.IPositionDao;
import com.zht.common.sys.model.Position;

@Service
@Transactional(rollbackFor=Exception.class)
public class PositionServiceImpl extends BaseServiceImpl<Position> implements IPositionService{
 	@Autowired
	private IPositionDao positionDao;
 	@Autowired
 	private IRbacRoleService rbacRoleService;
	@Autowired
	private IDepartmentService departmentervice;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> loadPositionCombox() {
		String hql="select new map(name as text,id as id )from Position p";
		List<Map> list=(List<Map>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findPositionIdsByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		String hql = " select pu.position.id from PositionUserDetail  pu where pu.userDetail.id=:userId ";
		List<Long> groupIdList = (List<Long>) baseDaoImpl.findJustList(hql,
				new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		return groupIdList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findAvilablePositionIdsByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		List<String> roleCodesUserHave=rbacRoleService.findRoleCodeUserHave(userId, true);
		if(roleCodesUserHave==null||roleCodesUserHave.size()==0){
			return null;
		}
		List<Long> deptIds =departmentervice.findDepartmentIdsByUserId(userId);
		if(deptIds==null||deptIds.size()==0){
			return null;
		}
		String hql = " select p.id from Position  p where 1=1 "
				+ " and p.department.id in (:deptIds) "
				+ " and p.rbacRole.code in (:roleCodesUserHave )";
		List<Long> postionIdList = (List<Long>) baseDaoImpl.findJustList(hql,new ParamObject(POType.H_NO_NC)
							.addAllowNull("deptIds", deptIds)
							.addAllowNull("roleCodesUserHave", roleCodesUserHave));
		return postionIdList;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> loadAvilablePositionComboxByUserId(Long userId) {
		List<Long> posiotnIds=findAvilablePositionIdsByUserId(userId);
		if(posiotnIds==null||posiotnIds.size()==0){
			return null;
		}
		String hql="select new map(name as text,id as id )from Position p where p.id in(:posiotnIds)";
		List<Map> list=(List<Map>) baseDaoImpl.findJustList(hql, 
				new ParamObject(POType.H_NO_NC).addAllowNull("posiotnIds", posiotnIds));
		return list;
	}
}