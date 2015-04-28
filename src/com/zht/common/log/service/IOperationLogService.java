package com.zht.common.log.service;

import org.zht.framework.service.IBaseService;

import com.zht.common.log.model.OperationLog;

public interface IOperationLogService extends IBaseService<OperationLog> {

	public void saveOperateLog(OperationLog log );
}
