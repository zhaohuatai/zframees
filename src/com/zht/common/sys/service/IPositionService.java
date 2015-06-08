package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.Position;

public interface IPositionService extends IBaseService<Position>{

	public List<Map> loadPositionCombox();
	public List<Long> findPositionIdsByUserId(Long userId);
	public List<Long> findAvilablePositionIdsByUserId(Long userId);
	public List<Map> loadAvilablePositionComboxByUserId(Long userId);
	

}