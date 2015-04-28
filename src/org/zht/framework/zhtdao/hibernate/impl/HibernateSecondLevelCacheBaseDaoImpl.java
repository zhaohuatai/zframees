/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.hibernate.impl;
//package com.zht.framework.core.base.dao.hibernate.impl;
//
//import java.io.Serializable;
//import java.lang.annotation.Annotation;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.Resource;
//import org.hibernate.LockOptions;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.annotations.Cache;
//import org.hibernate.jdbc.Work;
//import org.springframework.transaction.annotation.Transactional;
//import com.zht.framework.core.base.dao.hibernate.IHibernateBaseDao;
//import com.zht.framework.core.entity.DaoConstant;
//import com.zht.framework.core.exception.dao.DaoException;
//import com.zht.framework.core.query.ParamItem;
//import com.zht.framework.core.query.ParamObject;
//import com.zht.framework.core.util.ZStrUtil;
//
//public class HibernateSecondLevelCacheBaseDaoImpl implements IHibernateBaseDao {
//
//	@Resource(name = "sessionFactory")
//    protected SessionFactory sessionFactory;
//
//    public Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//    @Override
//    public String getDatabaseInfo() {
//		final StringBuilder buf = new StringBuilder("");
//		this.getCurrentSession().doWork(new Work() {
//			public void execute(Connection conn) throws SQLException {
//				try {
//					buf.append(conn.getMetaData().getDatabaseProductName()).append(" ");
//					buf.append(conn.getMetaData().getDatabaseProductVersion()).append(" ");
//					buf.append(conn.getMetaData().getDatabaseMajorVersion()).append(" ");
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		return buf.toString();
//	}
//    /**
//     * openSession 需要手动关闭session 意思是打开一个新的session
//     * 
//     * @return
//     */
//    @Override
//    public Session getNewSession() {
//        return sessionFactory.openSession();
//    }
//    @Transactional
//    @Override
//    public void flush() {
//    	this.getCurrentSession().flush();
//    }
//    @Override
//    public void clear() {
//    	this.getCurrentSession().clear();
//    }
//
//    @SuppressWarnings("unchecked")
//	@Override
//    public <E> E  find(Class<E> clazz, Serializable id)throws DaoException {
//		Annotation annotationInClass = clazz.getAnnotation(Cache.class);
//		E entity = null;
//		if (annotationInClass == null) {
//			 entity=(E) this.getCurrentSession().get(clazz, id);
//		} else {
//			try {
//				entity = (E) this.getCurrentSession().load(clazz, id);
//			} catch (Exception e) {
//				return null;
//			}
//		}
//		return  entity;
//    }
//    @SuppressWarnings("unchecked")
//	@Override
//    public <E> E  findAndLock(Class<E> clazz,Serializable id) throws DaoException {
//    	if(id==null){
//    		return null;
//    	}
//    	E entity = (E) this.getCurrentSession().get(clazz, id,  LockOptions.UPGRADE);
//    	if(entity==null){
//    		return null;
//    	}
//		return entity;
//	}
//
//    @Override
//    public Long findTotalCount(Class<?> c) {
//        String hql = "select count(*) from " + c.getName();
//        Long count = (Long) (this.getCurrentSession().createQuery(hql).uniqueResult());
//        return count != null ? count.longValue() : 0L;
//    }
//
//    //persist把一个瞬态的实例持久化，但是并"不保证"标识符被立刻填入到持久化实例中，标识符的填入可能被推迟   到flush的时间。
//    //persist"保证"，当它在一个transaction外部被调用的时候并不触发一个Sql Insert
//    //通过继承Session/persistence context来封装一个长会话流程的时候，一个persist这样的函数是需要的
//    @Override
//    public void persist(Object bean) throws DaoException{
//    	if(bean!=null){
//    		getCurrentSession().persist(bean);
//    	}
//    	
//      
//    }
//    @Override
//	public void persist(Collection<?> entities) throws DaoException {
//		if (entities != null && entities.size() > 0) {
//			Iterator<?> it = entities.iterator();
//			for (Object entity = null; it.hasNext();) {
//				entity = it.next();
//				if(entity!=null){
//					persist(entity);
//				}
//			}
//		}
//	}
//	//立即执行Sql insert，不管是不是在transaction内部还是外部
//    @Override
//	public void save(Object bean) throws DaoException{
//    	if(bean!=null){
//    		getCurrentSession().save(bean);
//    	}
//	}
//    @Override
//	public void save(Collection<?> entities) throws DaoException {
//		if (entities != null && entities.size() > 0) {
//			Iterator<?> it = entities.iterator();
//			for (Object entity = null; it.hasNext();) {
//				entity = it.next();
//				if(entity!=null){
//					save(entity);
//				}
//			}
//		}
//	}
//	
//    @Override
//    public void update(Object bean) throws DaoException{
//    	if(bean!=null){
//    		this.getCurrentSession().update(bean);
//    	}
//    }
//    
//    @Override
//	public void update(Collection<?> entities) throws DaoException {
//		if (entities != null && entities.size() > 0) {
//			Iterator<?> it = entities.iterator();
//			for (Object entity = null; it.hasNext();) {
//				entity = it.next();
//				if(entity!=null){
//					update(entity);
//				}
//			}
//		}
//	}
//    @Override
//	public void saveOrUpdate(Object bean) throws DaoException {
//    	if(bean!=null){
//    		this.getCurrentSession().saveOrUpdate(bean);
//    	}
//	}
//
//    @Override
//	public void saveOrUpdate(Collection<?> entities) throws DaoException {
//		if (entities != null && entities.size() > 0) {
//			Iterator<?> it = entities.iterator();
//			for (Object entity = null; it.hasNext();) {
//				entity = it.next();
//				if (entity != null) {
//					saveOrUpdate(entity);
//				}
//			}
//		}
//	}
//    @Override
//	public void merge(Object entity) throws DaoException {
//    	if(entity!=null){
//    		this.getCurrentSession().merge(entity);
//    	}
//	}
//    
//    @Override
//	public void merge(Collection<?> entities) throws DaoException {
//		if (entities != null && entities.size() > 0) {
//			Iterator<?> it = entities.iterator();
//			for (Object entity = null; it.hasNext();) {
//				entity = it.next();
//				if (entity != null) {
//					merge(entity);
//				}
//			}
//		}
//	} 
//    
//    @Override
//    public void delete(Object bean)throws DaoException  {
//    	if(bean!=null){
//    		this.getCurrentSession().delete(bean);
//    	}
//    }
//    
//    @Override
//    public void delete(Class<?> clazz, String id) throws DaoException {
//        Session session =this.getCurrentSession();
//        Object obj = session.get(clazz, id);
//        if (obj != null) {
//        	session.delete(obj);
//		}
//     
//    }
//
//	@Override
//	public void delete(Class<?> clazz, String[] ids) throws DaoException {
//		if (ids != null && ids.length > 0) {
//			for (String id : ids) {
//				Object obj = this.getCurrentSession().get(clazz, id);
//				if (obj != null) {
//					this.getCurrentSession().delete(obj);
//				}
//			}
//		}
//
//	}
//    
//    public void deleteByIdsInCase(Class<?> clazz, String[] ids)throws DaoException  {
//		if (ids != null && ids.length > 0) {
////			for (String idStr : ids) {
////				str += "'" + idStr + "',";
////			}
////			str = str.substring(0, str.length() - 1);
////			String sql = " delete from " + clazz.getName() + " where id in ( " + str + " )";
//			String sql = " delete from " + clazz.getName() + " where id in ( :ids )";
//			Query query=this.getCurrentSession().createQuery(sql);
//			query.setParameter("ids", ids);
//			query.executeUpdate();
//		}
//    }
//    public void deleteByIdsInCase(Class<?> clazz, Long[] ids)throws DaoException  {
//		if (ids != null && ids.length > 0) {
//			String sql = " delete from " + clazz.getName() + " where id in ( :ids )";
//			Query query=this.getCurrentSession().createQuery(sql);
//			query.setParameter("ids", ids);
//			query.executeUpdate();
//		}
//    }
//    @Override
//    public void deleteAll(Class<?> clazz) throws DaoException {
//		String hql = "delete from " + clazz.getName() + " ";
//		this.getCurrentSession().createQuery(hql).executeUpdate();
//	}
//    
//    //-------------------find------------
//    @Override
//	@SuppressWarnings("unchecked")
//	public <E> List<E>  findAll( Class<E> clazz) throws DaoException {
//		List<E> entities = new ArrayList<E>();
//		entities = this.getCurrentSession().createQuery("from " + clazz.getName()).list();
//		if(entities!=null&&entities.size()>0){
//			return entities;
//		}
//		return null;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public <E> List<E> findAllOverCache(Class<E> clazz) throws DaoException {
//		List<E> entities =this.getCurrentSession().createCriteria(clazz).setCacheable(true).list();
//		if(entities!=null&&entities.size()>0){
//			return entities;
//		}
//		return null;
//	}
//	
//	@SuppressWarnings("unchecked")
//	 @Override
//	public <E> E findByUniqueParam(Class<E> clazz,String uniqueParamName, String value) throws DaoException {
//		if (uniqueParamName==null||"".equals(uniqueParamName)||value==null||"".equals(value)) {
//			return null;
//		}
//		StringBuilder buf = new StringBuilder();
//		buf.append(" FROM ").append(clazz.getName());
//		buf.append(" WHERE ").append(uniqueParamName.trim()).append(" =:").append("uniqueParam").append("");
//		Query query=this.getCurrentSession().createQuery(buf.toString());
//		query.setParameter("uniqueParam", value.trim());
//		List<E> entityList = query.setCacheable(true).list();
//		if(entityList==null||entityList.size()==0){
//			return null;
//		}
//		return entityList.get(0);
//	}
//
//	// paramObject.addQuery("userName", new ParamMap("user.loginName","zhaohutai"));;
//	@Override@SuppressWarnings("unchecked")
//	public <E> E findByUniqueParams(Class<E> clazz,ParamObject paramObject) throws DaoException {
//		if (paramObject == null ||paramObject.getQueryParams().size()==0) {
//			return null;
//		}
//		StringBuilder hqlBuf = new StringBuilder(" FROM ");
//		hqlBuf.append(clazz.getName());
//		hqlBuf.append(" WHERE 1=1 ");
//		
//		Map<String,ParamItem> paramMap=paramObject.getQueryParams();
//		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
//		
//		if (paramMap != null && paramMap.size() > 0) {
//			Iterator<?> iter = paramMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry<?,?> entry = (Map.Entry<?,?>) iter.next();
//				String paramName = (String) entry.getKey();
//				ParamItem  paramItem = (ParamItem) entry.getValue();
//				if(ZStrUtil.trimOBJToNull(paramName)!=null&&paramItem!=null){
//					Object field=ZStrUtil.trimOBJToNull(paramItem.getField());
//					Object value=ZStrUtil.trimOBJToNull(paramItem.getValue());
//					if(field!=null&&value!=null){
//						hqlBuf.append(" AND ").append(field).append(" =:").append(paramName.trim());
//						queryParameter.put(paramName.trim(), value);
//					}
//					
//				}
//				
//			}
//		}
//		Query query=this.getCurrentSession().createQuery(hqlBuf.toString()).setCacheable(true);
//		
//		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
//		while (iterParameter.hasNext()) {
//			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
//			String paramName = entry.getKey();
//			Object paramValue = entry.getValue();
//			query.setParameter(paramName, paramValue);
//		}
//		List<E> entityList = query.list();
//		if (entityList == null || entityList.size()==0) {
//			return null;
//		}
//		return entityList.get(0);
//	}
//
//	@Override@SuppressWarnings("unchecked")
//	//paramObject.addQuery("userName", new ParamMap("user.userName","%+zhaohutai+%", "like"));;
//	//.append(paramName.trim()
//	public <E> List<E> findListByParamObject(Class<E> clazz,ParamObject paramObject,String extraSQLStr)throws DaoException {
//		if (paramObject==null && (extraSQLStr == null || extraSQLStr.length()==0)) {
//			return this.findAll(clazz);
//		}
//		StringBuilder hqlBuf = new StringBuilder(" FROM ");
//		hqlBuf.append(clazz.getName());
//		hqlBuf.append(" WHERE 1=1 ");
//		
//		Map<String,ParamItem> paramMap=paramObject.getQueryParams();
//		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
//		if (paramMap != null && paramMap.size() > 0) {
//			Iterator<?> iter = paramMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry<String,ParamItem> entry = (Map.Entry<String,ParamItem>) iter.next();
//				String paramName = entry.getKey();
//				ParamItem  paramItem = entry.getValue();
//				if(ZStrUtil.trimOBJToNull(paramName)!=null&&paramItem!=null){
//					Object field=ZStrUtil.trimOBJToNull(paramItem.getField());
//					Object value=ZStrUtil.trimOBJToNull(paramItem.getValue());
//					Object logic=ZStrUtil.trimOBJToNull(paramItem.getLogic());
//					if(field!=null&&value!=null){
//						if(logic==null){
//							logic=" =";
//						}
//						if("in".equalsIgnoreCase(String.valueOf(logic))||"not in".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							hqlBuf.append(" AND ").append(field).append(logic+"(:").append(paramName.trim()).append(") ");
//							queryParameter.put(paramName.trim(),value);
//						}else if("like".equalsIgnoreCase(String.valueOf(logic))||"not like".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							if(DaoConstant.likeKeyWordsList.contains(value)){
//								continue;
//							}
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
//							queryParameter.put(paramName.trim(),value);
//							
//						}else if("is null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))||"is not null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+" ");
//							
//						}else{
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
//							queryParameter.put(paramName.trim(),value);
//						}
//					}
//					
//				}
//				
//			}
//		}
//		if (extraSQLStr != null && extraSQLStr.trim().length() > 0) {
//			hqlBuf.append(" ").append(extraSQLStr.trim());
//		}
//		Object orderField=ZStrUtil.trimOBJToNull(paramObject.getOrderField());
//		Object orderDirection=ZStrUtil.trimOBJToNull(paramObject.getOrderDirection());
//		if(orderField!=null){
//			if(orderDirection!=null&&("asc".equalsIgnoreCase(""+orderDirection)||"desc".equalsIgnoreCase(""+orderDirection))){
//				hqlBuf.append(" order by ").append(orderField);
//				hqlBuf.append(" ").append(orderDirection);
//				hqlBuf.append(" ");
//			}else{
//				hqlBuf.append(" order by ").append(" asc ").append(" ");
//			}
//		}
//		Query query=this.getCurrentSession().createQuery(hqlBuf.toString()).setCacheable(true);
//		
//		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
//		while (iterParameter.hasNext()) {
//			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
//			String paramName = entry.getKey();
//			Object paramValue = entry.getValue();
//			query.setParameter(paramName, paramValue);
//		}
//		Integer numPerPage=paramObject.getNumPerPage();
//		Integer pageNum=paramObject.getPageNum();
//		if(numPerPage!=null&&numPerPage>0&&pageNum>0&&pageNum!=null){
//			query.setMaxResults(numPerPage);
//			query.setFirstResult((pageNum - 1) * numPerPage);
//		}
//		List<E> entityList = query.list();
//		if (entityList == null || entityList.size()==0) {
//			return null;
//		}
//		return entityList;
//	}
//	@Override
//	public Long findCountByParamObject(Class<?> clazz,ParamObject paramObject,String extraSQLStr)throws DaoException{
//		if (paramObject==null && (extraSQLStr == null || extraSQLStr.length()==0)) {
//			return findTotalCount(clazz);
//		}
//		StringBuilder hqlBuf = new StringBuilder(" SELECT COUNT(*) FROM ");
//		hqlBuf.append(clazz.getName());
//		hqlBuf.append(" WHERE 1=1 ");
//		
//		Map<String,ParamItem> paramMap=paramObject.getQueryParams();
//		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
//		if (paramMap != null && paramMap.size() > 0) {
//			Iterator<?> iter = paramMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				@SuppressWarnings("unchecked")
//				Map.Entry<String,ParamItem> entry = (Map.Entry<String,ParamItem>) iter.next();
//				String paramName = entry.getKey();
//				ParamItem  paramValue = entry.getValue();
//				if(ZStrUtil.trimOBJToNull(paramName)!=null&&paramValue!=null){
//					Object field=ZStrUtil.trimOBJToNull(paramValue.getField());
//					Object value=ZStrUtil.trimOBJToNull(paramValue.getValue());
//					Object logic=ZStrUtil.trimOBJToNull(paramValue.getLogic());
//					if(field!=null&&value!=null){
//						if(logic==null){
//							logic=" =";
//						}
//						if("in".equalsIgnoreCase(String.valueOf(logic))||"not in".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							hqlBuf.append(" AND ").append(field).append(logic+"(:").append(paramName.trim()).append(") ");
//							queryParameter.put(paramName.trim(),value);
//						}else if("like".equalsIgnoreCase(String.valueOf(logic))||"not like".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							if(DaoConstant.likeKeyWordsList.contains(value)){
//								continue;
//							}
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
//							queryParameter.put(paramName.trim(),value);
//							
//						}else if("is null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))||"is not null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+" ");
//							
//						}else{
//							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
//							queryParameter.put(paramName.trim(),value);
//						}
//					}
//					
//				}
//				
//			}
//		}
//		if (extraSQLStr != null && extraSQLStr.length() > 0) {
//			hqlBuf.append(" ").append(extraSQLStr);
//		}
//		Query query=this.getCurrentSession().createQuery(hqlBuf.toString()).setCacheable(true);
//		
//		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
//		while (iterParameter.hasNext()) {
//			@SuppressWarnings("unchecked")
//			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
//			String paramName = entry.getKey();
//			Object paramValue = entry.getValue();
//			query.setParameter(paramName, paramValue);
//		}
//		Long count = (Long) query.uniqueResult();
//		return count != null ? count.longValue() : 0L;
//	}
//	@SuppressWarnings("unchecked")
//	@Override
//	public <E> List<E> findByHqlWhere(Class<E> clazz,String hqlWhere)throws DaoException {
//		String hql=" from "+clazz.getName()+"  ";
//		if(hqlWhere!=null&&hqlWhere.length()>0){
//			hql+=hqlWhere;
//		}
//		Query query=this.getCurrentSession().createQuery(hql).setCacheable(true);
//		List<E> entityList=query.setCacheable(true).list();
//		if (entityList == null || entityList.size()==0) {
//			return null;
//		}
//		return entityList;
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public <E> List<E> findByHqlWhereForPage(Class<E> clazz,String hqlWhere,int pageSize, int pageNumber)throws DaoException {
//		String hql=" from "+clazz.getName()+" ";
//		if(hqlWhere!=null&&hqlWhere.length()>0){
//			hql+=hqlWhere;
//		}
//		Query query=this.getCurrentSession().createQuery(hql);
//		query.setMaxResults(pageSize);
//		query.setFirstResult((pageNumber - 1) * pageSize);
//		List<E> entityList=query.setCacheable(true).list();
//		if (entityList == null || entityList.size()==0) {
//			return null;
//		}
//		return entityList;
//	}
//	 @Override
//	public Long findCountByHQLWhere(Class<?> clazz,String hqlWhere)throws DaoException {
//		String hql=" select count(*) from "+clazz.getName()+" ";
//		if(hqlWhere!=null&&hqlWhere.length()>0){
//			hql+=hqlWhere;
//		}
//		Long count=(Long) this.getCurrentSession().createQuery(hql).setCacheable(true).uniqueResult();
//		return count != null ? count.longValue() : 0;
//	}
//	
//	
//	//----------------------
//	@Override
//	public void truncateTable(String tableName) throws DaoException {
//		String sql = " TRUNCATE TABLE " + tableName;
//		this.executeUpdateSqlStatement(sql);
//	}
//	@Override
//	public void executeUpdateSqlStatement(final String sql) throws DaoException{
//		Session session = this.getCurrentSession();
//		session.doWork(new Work() {
//			public void execute(Connection conn) throws SQLException {
//				try {
//					PreparedStatement ps = conn.prepareStatement(sql);
//					ps.executeUpdate();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	@Override
//	public void executeUpdateSqlStatement(final String sql,final Object[] parameters)throws DaoException {
//		Session session = this.getCurrentSession();
//		session.doWork(new Work() {
//			public void execute(Connection conn) throws SQLException {
//				try {
//					PreparedStatement ps = conn.prepareStatement(sql);
//					int i=0;
//					if(parameters!=null&&parameters.length>0){
//						for(Object str:parameters){
//							i++;
//							ps.setString(i, (String) str);	
//						}
//					}
//					ps.executeUpdate();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public <E> List<E> findByHql(String hql) throws DaoException {
//		if(hql==null||hql.length()==0){
//			return null;
//		}
//		Query query=this.getCurrentSession().createQuery(hql);
//		List<E> entityList=query.list();
//		if (entityList == null || entityList.size()==0) {
//			return null;
//		}
//		return entityList;
//	}
//	@Override
//	public Long findCountByHQL(String hql)throws DaoException {
//		if(hql==null||hql.length()==0){
//			return null;
//		}
//		Long count=(Long) this.getCurrentSession().createQuery(hql).uniqueResult();
//		return count != null ? count.longValue() : 0L;
//	}
//
//}
