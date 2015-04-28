package com.zht.common.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findPositionIdsByRbacUserId(Long userId) {
		String hql="select id from RbacUserPosition p where p.rbacUser.id=:userId";
		List<Long> positionList=(List<Long>) baseDaoImpl.findJustList(hql,
				new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		return positionList;
	}
	
 
}