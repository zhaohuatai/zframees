/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.dao.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.zht.framework.zhtdao.base.impl.BaseDaoImpl;

import com.zht.common.codegen.dao.IGenEntityPropertyDao;
import com.zht.common.codegen.model.GenEntityProperty;

@Repository
public class GenEntityPropertyDaoImpl extends BaseDaoImpl implements IGenEntityPropertyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findPropertyNameListByEntityId(Long entityId) {
		String hql=" select propertyName from GenEntityProperty where genEntity.id=:entityId";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("entityId", entityId);
		List<?> list=query.list();
		if(list!=null&&!list.isEmpty()){
			return (List<String>) list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findColumnNameListByEntityId(Long entityId) {
		String hql=" select columnName from GenEntityProperty where genEntity.id=:entityId";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("entityId", entityId);
		List<?> list=query.list();
		if(list!=null&&!list.isEmpty()){
			return (List<String>) list;
		}
		return null;
	}

	@Override
	public void saveIfnotExits(List<GenEntityProperty> entityGenEntityPropertyList) {
		for(GenEntityProperty detail :entityGenEntityPropertyList ){
			String hql=" select id from GenEntityProperty g where g.genEntity.id=:enityId and propertyName=:propertyName";
			Query query=this.getCurrentSession().createQuery(hql);
			query.setParameter("enityId", detail.getGenEntity().getId());
			query.setParameter("propertyName", detail.getPropertyName());
			List<?> list=query.list();
			if(list==null||list.size()==0){
				this.saveOrUpdate(detail);
			}
		
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> loadPropertyNameByEntityName(
			String entityName) {
		String hql="select new map(propertyName as text,propertyName as val ) from GenEntityProperty g where g.genEntity.name=:entityName";
		Query query=this.getCurrentSession().createQuery(hql);
		query.setParameter("entityName", entityName);
		List<?> list=query.list();
		if(list!=null&&list.size()>0){
			return (List<Map<String, String>>) list;
		}
		return null;
	}
 	
 
}