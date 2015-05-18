package com.zht.demo.jta.atomikos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.dynamic.dataSource.DataSourceType;
import com.zht.common.dynamic.dataSource.SessionFactoryType;
import com.zht.common.sys.service.ILogService;

import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.spring.DataSourceContextHolder;
import org.zht.framework.spring.SessionFactoryContextHolder;

import com.zht.common.sys.dao.ILogDao;
import com.zht.common.sys.model.Log;
import com.zht.demo.jta.atomikos.model.DemoEntity;
import com.zht.demo.jta.atomikos.service.IDemoEntityService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DemoEntityServiceImpl extends BaseServiceImpl<DemoEntity> implements IDemoEntityService{
//	@Resource(name = "sessionFactory")
//    protected SessionFactory sessionFactory;
//
//    public Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//    @Resource(name = "sessionFactoryB")
//    protected SessionFactory sessionFactoryB;
//
//    public Session getCurrentSessionB() {
//        return sessionFactoryB.getCurrentSession();
//    }
    @Override
	public void dynamicDataSourceTest(){
    	
//    	SessionFactoryContextHolder.setSessionFactoryName(SessionFactoryType.sessionFactory.name());
    	changeFactoryType(SessionFactoryType.sessionFactory);
    	
		DemoEntity entryA=new DemoEntity();
		entryA.setCode("ABC");
		entryA.setName("EDF");
		baseDaoImpl.save(entryA);
		baseDaoImpl.flush();
		
		String hql=" from DemoEntity where 1=1";
		List<?> list=baseDaoImpl.findByHql(hql);
		System.out.println("AAAAAAAAAAAAAAA_----------"+list.get(0));
		
		changeFactoryType(SessionFactoryType.sessionFactoryB);
//		SessionFactoryContextHolder.setSessionFactoryName(SessionFactoryType.sessionFactoryB.name());
//		if(1==1){
//			throw new ServiceLogicalException("sd");
//		}
		baseDaoImpl.save(entryA);
		baseDaoImpl.flush();
		list=baseDaoImpl.findByHql(hql);
		
		//System.out.println("BBBBBBBBBBBBBBB_----------"+list.get(0));
	}
	@Override
	public void onedatasourceTest() {
		DemoEntity entryA=new DemoEntity();
		entryA.setCode("ABC");
		entryA.setName("EDF");
		baseDaoImpl.save(entryA);
		baseDaoImpl.flush();
		String hql=" from DemoEntity where 1=1";
		List<?> list=baseDaoImpl.findByHql(hql);
		System.out.println("AAAAAAAAAAAAAAA_----------"+list.get(0));
	}
 
    
}