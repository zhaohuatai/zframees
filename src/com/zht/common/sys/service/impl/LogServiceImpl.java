package com.zht.common.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.zht.common.sys.service.ILogService;
import org.zht.framework.service.impl.BaseServiceImpl;
import com.zht.common.sys.dao.ILogDao;
import com.zht.common.sys.model.Log;

@Service
@Transactional(rollbackFor=Exception.class)
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService{
 	@Autowired
	private ILogDao logDao;
	
 
}