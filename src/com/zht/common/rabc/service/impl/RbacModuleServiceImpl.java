/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.rabc.dao.IRbacModuleDao;
import com.zht.common.rabc.model.RbacModule;
import com.zht.common.rabc.service.IRbacModuleService;
@Service
@Transactional(rollbackFor=Exception.class)
public class RbacModuleServiceImpl implements IRbacModuleService{
	@Autowired
	private IRbacModuleDao  rbacMenuModuleDao;
	@Override
	public RbacModule findRbacMenuModuleById(Long id) {
		if(id!=null){
			return rbacMenuModuleDao.find(RbacModule.class, id);
		}
		return null;
	}

	@Override
	public List<RbacModule> findAllRbacMenuModuleList() {
		
		return rbacMenuModuleDao.findByHqlWhere(RbacModule.class, " where 1=1 order by disIndex ");
	}

	@Override
	public void saveOrUpdataRbacMenuModule(RbacModule rbacMenuModule) {
		if(rbacMenuModule==null){
			throw new ServiceLogicalException("数据对象为空，请检查数据");
		}
		if(ZStrUtil.isEmpty(rbacMenuModule.getDisplay())){
			throw new ServiceLogicalException("必填字段为空，请检查数据");
		}
		rbacMenuModuleDao.saveOrUpdate(rbacMenuModule);
//		long start=System.currentTimeMillis();
//		List<RbacModule> rbacMenuModuleList=new ArrayList<RbacModule>();
//		for(int i=0;i<200000;i++){
//			System.out.println("i="+i);
//			 rbacMenuModule=new RbacModule();
//			rbacMenuModule.setDisplay("系统模块"+(i));
//			rbacMenuModule.setEnabled(true);
//			rbacMenuModule.setDisIndex( i);
//			rbacMenuModuleList.add(rbacMenuModule);
//			rbacMenuModuleDao.save(rbacMenuModule);
//		}
////		rbacMenuModuleDao.save(rbacMenuModuleList);
//		long end=System.currentTimeMillis();
//		System.out.println("耗时："+(end-start));
		
	}

	@Override
	public void saveOrUpdataRbacMenuModuleList(List<RbacModule> rbacMenuModuleList) {
		if(rbacMenuModuleList!=null){
			 for(RbacModule rbacMenuModule:rbacMenuModuleList){
				 if(ZStrUtil.isEmpty(rbacMenuModule.getDisplay())){
					throw new ServiceLogicalException("必填字段为空，请检查数据");
				}
			 }
			rbacMenuModuleDao.saveOrUpdate(rbacMenuModuleList);
		}
		
	}

}
