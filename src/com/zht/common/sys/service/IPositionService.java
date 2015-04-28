package com.zht.common.sys.service;

import java.util.List;

import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.Position;

public interface IPositionService extends IBaseService<Position>{
	public List<Long> findPositionIdsByRbacUserId(Long userId);
}