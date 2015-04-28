package com.zht.common.log.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.log.model.OperationLog;
import com.zht.common.log.service.IOperationLogService;
@Service
@Transactional(rollbackFor=Exception.class)
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog> implements IOperationLogService{

	@Override
	public void saveOperateLog(OperationLog log) {
		if(log!=null){
			baseDaoImpl.save(log);
		}
	}
	


}
