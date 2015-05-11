package com.zht.demo.jta.atomikos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.sys.service.ILogService;

import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.sys.dao.ILogDao;
import com.zht.common.sys.model.Log;
import com.zht.demo.jta.atomikos.model.DemoEntity;
import com.zht.demo.jta.atomikos.service.IDemoEntityService;

@Service
@Transactional(rollbackFor=Exception.class)
public class DemoEntityServiceImpl implements IDemoEntityService{
	@Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Resource(name = "sessionFactoryB")
    protected SessionFactory sessionFactoryB;

    public Session getCurrentSessionB() {
        return sessionFactoryB.getCurrentSession();
    }
	
	
//	session.save(entryA);
//	session.flush();
//	if(1==1){
//		throw new ServiceLogicalException("bad");
//	}
//	sessionB.save(entryA);
	@Override
	public void queryAndSaveDeleteTest() {
		DemoEntity entryA=new DemoEntity();
		entryA.setCode("ABC");
		entryA.setName("EDF");
		Session sessionA=getCurrentSession();
		Session sessionB=getCurrentSessionB();
		sessionA.save(entryA);
		sessionA.flush();
		String hql=" from DemoEntity where 1=1";
		Query query=sessionA.createQuery(hql);
		List<?> list=query.list();
//		if(1==1){
//			throw new ServiceLogicalException("bad");
//		}
		if(list!=null&&list.size()>0){
			sessionB.save(list.get(0));
		}
		
		
	}
 
    
}