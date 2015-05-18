package com.zht.demo.jta.atomikos.service;

import org.zht.framework.service.IBaseService;

import com.zht.demo.jta.atomikos.model.DemoEntity;

public interface IDemoEntityService extends IBaseService<DemoEntity> {
	public void dynamicDataSourceTest();
	public void onedatasourceTest();
	
}
