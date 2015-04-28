/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.hibernate.impl;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.exception.DaoException;
import org.zht.framework.spring.SpringUtils;
import org.zht.framework.util.ZStrUtil;
import org.zht.framework.zhtdao.DaoConstant;
import org.zht.framework.zhtdao.hibernate.IHibernateBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.zht.common.dauth.thread.CurrentReqestInfo;
import com.zht.common.dauth.thread.RequestThreadLocal;
import com.zht.common.dauth.util.DataAuthParser;
import com.zht.common.shiro.ShiroDbRealm;
public class HibernateBaseDaoImpl implements IHibernateBaseDao {

	@Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public String getDatabaseInfo() {
		final StringBuilder buf = new StringBuilder("");
		this.getCurrentSession().doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				try {
					buf.append(conn.getMetaData().getDatabaseProductName()).append(" ");
					buf.append(conn.getMetaData().getDatabaseProductVersion()).append(" ");
					buf.append(conn.getMetaData().getDatabaseMajorVersion()).append(" ");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		return buf.toString();
	}
    /**
     * openSession 需要手动关闭session 意思是打开一个新的session
     * 
     * @return
     */
    @Override
    public Session getNewSession() {
        return sessionFactory.openSession();
    }
    @Transactional
    @Override
    public void flush() {
    	this.getCurrentSession().flush();
    }
    @Override
    public void clear() {
    	this.getCurrentSession().clear();
    }
    @Override
    public void evict(Object bean)throws DaoException {
    	this.getCurrentSession().evict(bean);
    }
    @SuppressWarnings("unchecked")
	@Override
    public <E> E  find(Class<E> clazz, Serializable id)throws DaoException {
    	if(id==null){
    		return null;
    	}
		Annotation annotationInClass = clazz.getAnnotation(Cache.class);
		E entity = null;
		if (annotationInClass == null) {
			 entity=(E) this.getCurrentSession().get(clazz, id);
		} else {
			try {
				entity = (E) this.getCurrentSession().load(clazz, id);
			} catch (Exception e) {
				return null;
			}
		}
		return  entity;
    }
    @SuppressWarnings("unchecked")
	@Override
    public <E> E  findAndLock(Class<E> clazz,Serializable id) throws DaoException {
    	if(id==null){
    		return null;
    	}
    	E entity = (E) this.getCurrentSession().get(clazz, id,  LockOptions.UPGRADE);
    	if(entity==null){
    		return null;
    	}
		return entity;
	}

    @Override
    public Long findAllCount(Class<?> c) {
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) (this.getCurrentSession().createQuery(hql).uniqueResult());
        return count != null ? count.longValue() : 0L;
    }

    //persist把一个瞬态的实例持久化，但是并"不保证"标识符被立刻填入到持久化实例中，标识符的填入可能被推迟   到flush的时间。
    //persist"保证"，当它在一个transaction外部被调用的时候并不触发一个Sql Insert
    //通过继承Session/persistence context来封装一个长会话流程的时候，一个persist这样的函数是需要的
    @Override
    public void persist(Object bean) throws DaoException{
    	if(bean!=null){
    		getCurrentSession().persist(bean);
    	}
    }
    @Override
	public void persist(Collection<?> entities) throws DaoException {
		if (entities != null && entities.size() > 0) {
			Iterator<?> it = entities.iterator();
			for (Object entity = null; it.hasNext();) {
				entity = it.next();
				if(entity!=null){
					persist(entity);
				}
			}
		}
	}
	//立即执行Sql insert，不管是不是在transaction内部还是外部
    @Override
	public void save(Object bean) throws DaoException{
    	if(bean!=null){
    		getCurrentSession().save(bean);
    	}
	}
    @Override
	public void save(Collection<?> entities) throws DaoException {
		if (entities != null && entities.size() > 0) {
			Iterator<?> it = entities.iterator();
			for (Object entity = null; it.hasNext();) {
				entity = it.next();
				if(entity!=null){
					save(entity);
				}
			}
		}
	}
	
    @Override
    public void update(Object bean) throws DaoException{
    	if(bean!=null){
    		this.getCurrentSession().update(bean);
    	}
    }
    
    @Override
	public void update(Collection<?> entities) throws DaoException {
		if (entities != null && entities.size() > 0) {
			Iterator<?> it = entities.iterator();
			for (Object entity = null; it.hasNext();) {
				entity = it.next();
				if(entity!=null){
					update(entity);
				}
			}
		}
	}
    @Override
	public void saveOrUpdate(Object bean) throws DaoException {
    	if(bean!=null){
    		this.getCurrentSession().saveOrUpdate(bean);
    	}
	}

    @Override
	public void saveOrUpdate(Collection<?> entities) throws DaoException {
		if (entities != null && entities.size() > 0) {
			Iterator<?> it = entities.iterator();
			for (Object entity = null; it.hasNext();) {
				entity = it.next();
				if (entity != null) {
					saveOrUpdate(entity);
				}
			}
		}
	}
    @Override
	public void merge(Object entity) throws DaoException {
    	if(entity!=null){
    		this.getCurrentSession().merge(entity);
    	}
	}
    
    @Override
	public void merge(Collection<?> entities) throws DaoException {
		if (entities != null && entities.size() > 0) {
			Iterator<?> it = entities.iterator();
			for (Object entity = null; it.hasNext();) {
				entity = it.next();
				if (entity != null) {
					merge(entity);
				}
			}
		}
	} 
    
    @Override
    public void delete(Object bean)throws DaoException  {
    	if(bean!=null){
    		this.getCurrentSession().delete(bean);
    	}
    }
    
    @Override
    public void delete(Class<?> clazz, Serializable id) throws DaoException {
        Session session =this.getCurrentSession();
        Object obj = session.get(clazz, id);
        if (obj != null) {
        	session.delete(obj);
		}
     
    }
	@Override
    @SuppressWarnings("unchecked")
	public  <E> List<E> findByIdsInCase(Class<?> clazz, Serializable[] ids)throws DaoException  {
		if (ids != null && ids.length > 0) {
			String sql = "  from " + clazz.getName() + " where id in ( :ids )";
			Query query=this.getCurrentSession().createQuery(sql);
			query.setParameterList("ids", ids);
			List<?> list=query.list();
			if(list!=null&&list.size()>0){
				return (List<E>) list;
			}
		}
		return null;
    }
	@SuppressWarnings("unchecked")
	public  <E> List<E> findByIdsInCase(Class<?> clazz, List<Serializable> idList)throws DaoException {
		if (idList != null && idList.size() > 0) {
			String sql = "  from " + clazz.getName() + " where id in ( :idList )";
			Query query=this.getCurrentSession().createQuery(sql);
			query.setParameterList("idList", idList);
			List<?> list=query.list();
			if(list!=null&&list.size()>0){
				return (List<E>) list;
			}
		}
		return null;
	}
    @Override
    public void deleteByIdsInCase(Class<?> clazz, Serializable[] ids)throws DaoException  {
		if (ids != null && ids.length > 0) {
			String sql = " delete from " + clazz.getName() + " where id in ( :ids )";
			Query query=this.getCurrentSession().createQuery(sql);
			query.setParameterList("ids", ids);
			query.executeUpdate();
		}
    }
    
    @Override
    public void deleteByIdsInCase(Class<?> clazz,List<Serializable> idList)throws DaoException  {
		if (idList != null &&  idList.size() > 0) {
			String sql = " delete from " + clazz.getName() + " where id in ( :idList )";
			Query query=this.getCurrentSession().createQuery(sql);
			query.setParameterList("idList", idList);
			query.executeUpdate();
		}
    }
    
    @Override
    public void deleteAll(Class<?> clazz) throws DaoException {
		String hql = "delete from " + clazz.getName() + " ";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}
    
    //-------------------find------------
    @Override
	@SuppressWarnings("unchecked")
	public <E> List<E>  findAll( Class<E> clazz) throws DaoException {
		List<E> entities = new ArrayList<E>();
		entities = this.getCurrentSession().createQuery("from " + clazz.getName()).list();
		if(entities!=null&&entities.size()>0){
			return entities;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> findAllOverCache(Class<E> clazz) throws DaoException {
		List<E> entities =this.getCurrentSession().createCriteria(clazz).setCacheable(true).list();
		if(entities!=null&&entities.size()>0){
			return entities;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	 @Override
	public <E> E findByUniqueParam(Class<E> clazz,String uniqueParamName, String value) throws DaoException {
		if (uniqueParamName==null||"".equals(uniqueParamName)||value==null||"".equals(value)) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		buf.append(" FROM ").append(clazz.getName());
		buf.append(" WHERE ").append(uniqueParamName.trim()).append(" =:").append("uniqueParam").append("");
		Query query=this.getCurrentSession().createQuery(buf.toString());
		query.setParameter("uniqueParam", value.trim());
		List<E> entityList = query.list();
		if(entityList==null||entityList.size()==0){
			return null;
		}
		return entityList.get(0);
	}

	// paramObject.addQuery("userName", new ParamMap("user.loginName","zhaohutai"));;
	@Override@SuppressWarnings("unchecked")
	public <E> E findByUniqueParams(Class<E> clazz,Map<String, Object> params) throws DaoException {
		if (params == null ||params.size()==0) {
			return null;
		}
		StringBuilder hqlBuf = new StringBuilder(" FROM ");
		hqlBuf.append(clazz.getName()).append(" WHERE ");
		
		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
		if (params != null && params.size() > 0) {
			Iterator<?> iter = params.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<?,?> entry = (Map.Entry<?,?>) iter.next();
				String paramName = (String) entry.getKey();
				Object  paramValue = entry.getValue();
				if(ZStrUtil.trimToNullIfStr(paramName)!=null&&paramValue!=null){
					String field=(String) ZStrUtil.trimToNullIfStr(paramName);
					Object value=ZStrUtil.trimToNullIfStr(paramValue);
					if(field!=null&&value!=null){
						hqlBuf.append(" AND ").append(field).append(" =:").append(field);
						queryParameter.put(""+field, value);
					}
					
				}
				
			}
		}
		Query query=this.getCurrentSession().createQuery(hqlBuf.toString());
		
		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
		while (iterParameter.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
			String paramName = entry.getKey();
			Object paramValue = entry.getValue();
			query.setParameter(paramName, paramValue);
		}
		List<E> entityList = query.list();
		if (entityList == null || entityList.size()==0) {
			return null;
		}
		return entityList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> findByHqlWhere(Class<E> clazz,String hqlWhere)throws DaoException {
		String hql=" from "+clazz.getName()+"  ";
		if(hqlWhere!=null&&hqlWhere.length()>0){
			hql+=hqlWhere;
		}
		Query query=this.getCurrentSession().createQuery(hql);
		List<E> entityList=query.list();
		if (entityList == null || entityList.size()==0) {
			return null;
		}
		return entityList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> findByHqlWhereForPage(Class<E> clazz,String hqlWhere,int pageSize, int pageNumber)throws DaoException {
		String hql=" from "+clazz.getName()+" ";
		if(hqlWhere!=null&&hqlWhere.length()>0){
			hql+=hqlWhere;
		}
		Query query=this.getCurrentSession().createQuery(hql);
		query.setMaxResults(pageSize);
		query.setFirstResult((pageNumber - 1) * pageSize);
		List<E> entityList=query.list();
		if (entityList == null || entityList.size()==0) {
			return null;
		}
		return entityList;
	}
	 @Override
	public Long findCountByHQLWhere(Class<?> clazz,String hqlWhere)throws DaoException {
		String hql=" select count(*) from "+clazz.getName()+" ";
		if(hqlWhere!=null&&hqlWhere.length()>0){
			
			hql+=hqlWhere;
			String countTemp=hql.replaceAll("\\((.*)\\)", "").replaceAll("[\\s]+", " ").toLowerCase();
			if(countTemp.contains("group by")){
				hql=new String("SELECT count(*) from ( "+hql+" )");
			}
			
		}
		Long count=(Long) this.getCurrentSession().createQuery(hql).uniqueResult();
		return count != null ? count.longValue() : 0;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> findByHql(String hql) throws DaoException {
		if(hql==null||hql.length()==0){
			return null;
		}
		Query query=this.getCurrentSession().createQuery(hql);
		List<E> entityList=query.list();
		if (entityList == null || entityList.size()==0) {
			return null;
		}
		return entityList;
	}
	@Override
	public Long findCountByHQL(String hql)throws DaoException {
		if(hql==null||hql.length()==0){
			return null;
		}
		Long count=(Long) this.getCurrentSession().createQuery(hql).uniqueResult();
		return count != null ? count.longValue() : 0L;
	}
	@Override
	public Serializable findIdByUnique(Class<?> clazz, String filedName,Object value) {
		String hql = " select id from " + clazz.getName()+ " where "+filedName+"=:value";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("value", ZStrUtil.trimToEmptyIfStr(value));
		List<?> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return (Serializable) list.get(0);
	}
	@Override
	public Object findFiledByUnique(Class<?> clazz,String targetFieldToBeQuery, String uniqueFieldNanme,Object value) {
		String hql = " select "+targetFieldToBeQuery+" from " + clazz.getName()+ " where "+uniqueFieldNanme+"=:value";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("value", ZStrUtil.trimToEmptyIfStr(value));
		List<?> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return  list.get(0);
	}
	
	@Override
	public Serializable findParentIdBySelfUnique(Class<?> clazz,String parentPropertyName,String selfUniqueFieldName,Object selfUniqueFieldValue) {
		String hql = " select "+parentPropertyName+".id  from " + clazz.getName()+ " where "+selfUniqueFieldName+"=:selfUniqueFieldValue";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("selfUniqueFieldValue", ZStrUtil.trimToEmptyIfStr(selfUniqueFieldValue));
		List<?> list = query.list();
		if (list == null || list.size() == 0) {
			return null;
		}
		return (Serializable) list.get(0);
	}
//----------------------------------------------------------------------------------------------------------
	@Override@SuppressWarnings("unchecked")
	//paramObject.addQuery("userName", new ParamMap("user.userName","%+zhaohutai+%", "like"));;
	//.append(paramName.trim()
	public <E> List<E> findListByParamObject(Class<E> clazz,ParamObject paramObject,String extraCondition)throws DaoException {
		if (paramObject==null && (extraCondition == null || extraCondition.length()==0)) {
			return this.findAll(clazz);
		}
		StringBuilder hqlBuf = new StringBuilder(" FROM ");
		hqlBuf.append(clazz.getName()).append(" ").append("WHERE 1=1 ");
		
		Map<String,Object> paramMap=paramObject.getQueryParams();
		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
		if (paramMap != null && paramMap.size() > 0) {
			Iterator<?> iter = paramMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String,ParamItem> entry = (Map.Entry<String,ParamItem>) iter.next();
				String paramName = entry.getKey();
				ParamItem  paramItem = entry.getValue();
				if(ZStrUtil.trimToNullIfStr(paramName)!=null&&paramItem!=null){
					Object field=ZStrUtil.trimToNullIfStr(paramItem.getField());
					Object value=ZStrUtil.trimToNullIfStr(paramItem.getValue());
					Object logic=ZStrUtil.trimToNullIfStr(paramItem.getLogic());
					if(field!=null&&value!=null){
						if(logic==null){
							logic=" =";
						}
						if("in".equalsIgnoreCase(String.valueOf(logic))||"not in".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(logic+"(:").append(paramName.trim()).append(") ");
							queryParameter.put(paramName.trim(),value);
						}else if("like".equalsIgnoreCase(String.valueOf(logic))||"not like".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							if(DaoConstant.likeKeyWordsList.contains(value)){
								continue;
							}
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
							
						}else if("is null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))||"is not null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+" ");
							
						}else{
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
						}
					}
					
				}
				
			}
		}
		if(extraCondition != null && extraCondition.length() > 0){
			hqlBuf.append(" "+extraCondition);
		}
		Object orderField=ZStrUtil.trimToNullIfStr(paramObject.getSort());
		Object orderDirection=ZStrUtil.trimToNullIfStr(paramObject.getOrder());
		if(orderField!=null){
			if(orderDirection==null||!"asc".equalsIgnoreCase(""+orderDirection)||!"desc".equalsIgnoreCase(""+orderDirection)){
				orderDirection=" asc ";
			}
			hqlBuf.append(" order by ").append(orderField);
			hqlBuf.append(" ").append(orderDirection);
			hqlBuf.append(" ");
			
		}
		Query query=this.getCurrentSession().createQuery(hqlBuf.toString());
		
		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
		while (iterParameter.hasNext()) {
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
			String paramName = entry.getKey();
			Object paramValue = entry.getValue();
			if(paramValue instanceof Collection){
				query.setParameterList(paramName, (Collection<?>) paramValue);
				query.setParameterList(paramName, (Collection<?>) paramValue);
			}else if(paramValue.getClass().isArray()){
				query.setParameterList(paramName,  (Object[])(paramValue));
				query.setParameterList(paramName,  (Object[])(paramValue));
			}else if("true".equalsIgnoreCase(""+paramValue)||"false".equalsIgnoreCase(""+paramValue)){
				query.setParameter(paramName, Boolean.valueOf(""+paramValue));
			}else{
				query.setParameter(paramName, paramValue);
			}
		}
		if(paramObject.getIsOffset()!=null&&paramObject.getIsOffset()){
			Integer numPerPage=paramObject.getRows();
			Integer pageNum=paramObject.getPage();
			if(numPerPage!=null&&numPerPage>0&&pageNum>0&&pageNum!=null){
				query.setMaxResults(numPerPage);
				query.setFirstResult((pageNum - 1) * numPerPage);
			}
		}
		
		Integer numPerPage=paramObject.getRows();
		Integer pageNum=paramObject.getPage();
		if(numPerPage!=null&&numPerPage>0&&pageNum>0&&pageNum!=null){
			query.setMaxResults(numPerPage);
			query.setFirstResult((pageNum - 1) * numPerPage);
		}
		List<E> entityList = query.list();
		if (entityList == null || entityList.size()==0) {
			return null;
		}
		return entityList;
	}

	@Override
	public Long findCountByParamObject(Class<?> clazz,ParamObject paramObject,String extraCondition)throws DaoException{
		if (paramObject==null && (extraCondition == null || extraCondition.length()==0)) {
			return findAllCount(clazz);
		}
		StringBuilder hqlBuf = new StringBuilder(" SELECT COUNT(*) FROM ");
		hqlBuf.append(clazz.getName()).append(" ").append("WHERE 1=1 ");
		
		Map<String,Object> paramMap=paramObject.getQueryParams();
		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
		if (paramMap != null && paramMap.size() > 0) {
			Iterator<?> iter = paramMap.entrySet().iterator();
			while (iter.hasNext()) {
				@SuppressWarnings("unchecked")
				Map.Entry<String,ParamItem> entry = (Map.Entry<String,ParamItem>) iter.next();
				String paramName = entry.getKey();
				ParamItem  paramValue = entry.getValue();
				if(ZStrUtil.trimToNullIfStr(paramName)!=null&&paramValue!=null){
					Object field=ZStrUtil.trimToNullIfStr(paramValue.getField());
					Object value=ZStrUtil.trimToNullIfStr(paramValue.getValue());
					Object logic=ZStrUtil.trimToNullIfStr(paramValue.getLogic());
					if(field!=null&&value!=null){
						if(logic==null){
							logic=" =";
						}
						if("in".equalsIgnoreCase(String.valueOf(logic))||"not in".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(logic+"(:").append(paramName.trim()).append(") ");
							queryParameter.put(paramName.trim(),value);
						}else if("like".equalsIgnoreCase(String.valueOf(logic))||"not like".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							if(DaoConstant.likeKeyWordsList.contains(value)){
								continue;
							}
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
							
						}else if("is null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))||"is not null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+" ");
							
						}else{
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
						}
					}
					
				}
				
			}
		}
		if(extraCondition != null && extraCondition.length() > 0){
			hqlBuf.append(" "+extraCondition);
			String countTemp=extraCondition.replaceAll("\\((.*)\\)", "").replaceAll("[\\s]+", " ").toLowerCase();
			if(countTemp.contains("group by")){
				hqlBuf=new StringBuilder("SELECT count(*) from ( "+hqlBuf+" )");
			}
		}
		Query query=this.getCurrentSession().createQuery(hqlBuf.toString());
		
		Iterator<?> iterParameter = queryParameter.entrySet().iterator();
		while (iterParameter.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iterParameter.next();
			String paramName = entry.getKey();
			Object paramValue = entry.getValue();
			if(paramValue instanceof Collection){
				query.setParameterList(paramName, (Collection<?>) paramValue);
				query.setParameterList(paramName, (Collection<?>) paramValue);
			}else if(paramValue.getClass().isArray()){
				query.setParameterList(paramName,  (Object[])(paramValue));
				query.setParameterList(paramName,  (Object[])(paramValue));
			}else if("true".equalsIgnoreCase(""+paramValue)||"false".equalsIgnoreCase(""+paramValue)){
				query.setParameter(paramName, Boolean.valueOf(""+paramValue));
			}else{
				query.setParameter(paramName, paramValue);
			}
		}
		Long total=0L;
//		if (extraCondition != null && (extraCondition.contains("group by")||extraCondition.contains("GROUP BY"))) {
//			List<?> list=query.list();
//			total=(long) (list==null?0L:list.size());
//		}else{
//			 total=(Long) query.uniqueResult();
//		}
		 total=(Long) query.uniqueResult();
		return total;
	}
	
//----------------------------------------------------------------------------------------------------------
	
	////paramObject.addQuery("userName", new ParamItem("user.userName","%+zhaohutai+%", "like"));;
	public DataSet loadDataSetFromOneEntity(Class<?> clazz,ParamObject paramObject,RowMap rowMap,String extraCondition){
		if(rowMap==null||rowMap.size()==0){
			return null;
		}
	
		String leftJoinStr=rowMap.getLeftJoinStr();
		StringBuilder hqlData = new StringBuilder(" select  ").append(rowMap.getMapStr()).append(" ");
		StringBuilder hqlCount=new StringBuilder(" select count(*) ");
		StringBuilder hqlBuf = new StringBuilder(" from  ");
		hqlBuf.append(clazz.getName() +" as "+RowMap.queryAsAlias+" ");
		if(leftJoinStr!=null&&leftJoinStr.length()>0){
			hqlBuf.append(leftJoinStr);
		}
		hqlBuf.append(" WHERE 1=1 ");
		//例如限制当前部门
		// " and "+RowMap.queryAsAlias+".id in (select id from DepartmentUser du where du.department ="++") "
		String dataAuthExps=SpringUtils.getBean(DataAuthParser.class).parserExpList("loadDataSetFromOneEntity");
		if(dataAuthExps!=null){
			hqlBuf.append(" "+dataAuthExps+" ");
		}
		Map<String,Object> paramMap=paramObject.getQueryParams();
		Map<String,Object> queryParameter=new LinkedHashMap<String,Object>();
		if (paramMap != null && paramMap.size() > 0) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()){
				String paramName = entry.getKey();
				ParamItem  paramItem =(ParamItem) entry.getValue();
				if(ZStrUtil.trimToNullIfStr(paramName)!=null&&paramItem!=null){
					Object field=ZStrUtil.trimToNullIfStr(paramItem.getField());
					Object value=ZStrUtil.trimToNullIfStr(paramItem.getValue());
					Object logic=ZStrUtil.trimToNullIfStr(paramItem.getLogic());
					if(field!=null&&value!=null){
						if(logic==null){
							logic=" =";
						}
						if("in".equalsIgnoreCase(String.valueOf(logic))||"not in".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(logic+"(:").append(paramName.trim()).append(") ");
							queryParameter.put(paramName.trim(),value);
						}else if("like".equalsIgnoreCase(String.valueOf(logic))||"not like".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							if(DaoConstant.likeKeyWordsList.contains(value)){
								continue;
							}
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
							
						}else if("is null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))||"is not null".equalsIgnoreCase(String.valueOf(logic).replaceAll("[\\s]+", " "))){
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+" ");
							
						}else{
							hqlBuf.append(" AND ").append(field).append(" ").append(logic+":").append(paramName.trim());
							queryParameter.put(paramName.trim(),value);
						}
					}
					
				}
				
			}
		}
		
		if(extraCondition != null && extraCondition.length() > 0){
			hqlBuf.append(" "+extraCondition);
		}
		
		hqlCount.append(hqlBuf);
		//处理grouby 的情况统计问题，过滤掉子查询
		if(extraCondition!=null){
			String countTemp=extraCondition.replaceAll("\\((.*)\\)", "").replaceAll("[\\s]+", " ").toLowerCase();
			if(countTemp.contains("group by")){
				hqlCount=new StringBuilder("SELECT count(*) from ( "+hqlCount+" )");
			}
		}
		
		Object orderField=ZStrUtil.trimToNullIfStr(paramObject.getSort());
		String realField=rowMap.get(""+orderField);
		Object orderDirection=ZStrUtil.trimToNullIfStr(paramObject.getOrder());
		if(realField!=null){
			if(orderDirection==null||(!"asc".equalsIgnoreCase(""+orderDirection)&&!"desc".equalsIgnoreCase(""+orderDirection))){
				orderDirection=" asc ";
			}
			hqlBuf.append(" order by ").append(realField);
			hqlBuf.append(" ").append(orderDirection);
			hqlBuf.append(" ");
		}
		String d=(hqlData.append(hqlBuf)).toString();
		Query queryData= this.getCurrentSession().createQuery(d).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Query queryCount=this.getCurrentSession().createQuery(hqlCount.toString());
		for (Map.Entry<String,Object> entry : queryParameter.entrySet()){
			String paramName = entry.getKey();
			Object paramValue = entry.getValue();
			if(paramValue instanceof Collection){
				queryData.setParameterList(paramName, (Collection<?>) paramValue);
				queryCount.setParameterList(paramName, (Collection<?>) paramValue);
			}else if(paramValue.getClass().isArray()){
				queryData.setParameterList(paramName,  (Object[])(paramValue));
				queryCount.setParameterList(paramName,  (Object[])(paramValue));
			}else if("true".equalsIgnoreCase(""+paramValue)||"false".equalsIgnoreCase(""+paramValue)){
				queryData.setParameter(paramName, Boolean.valueOf(""+paramValue));
				queryCount.setParameter(paramName, Boolean.valueOf(""+paramValue));
			}else{
				queryData.setParameter(paramName, paramValue);
				queryCount.setParameter(paramName, paramValue);
			}
		}
		if(paramObject.getIsOffset()!=null&&paramObject.getIsOffset()){
			Integer numPerPage=paramObject.getRows();
			Integer pageNum=paramObject.getPage();
			if(numPerPage!=null&&numPerPage>0&&pageNum>0&&pageNum!=null){
				queryData.setMaxResults(numPerPage);
				queryData.setFirstResult((pageNum - 1) * numPerPage);
			}
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> entityList= queryData.list();
		Long total=0L;
		
		//hibernate 不支持 select count(*) from (select,,,,)
//		if (extraCondition != null && (extraCondition.contains("group by")||extraCondition.contains("GROUP BY"))) {
//			List<?> list=queryCount.list();
//			total=(long) (list==null?0L:list.size());
//		}else{
//			 total=(Long) queryCount.uniqueResult();
//		}
		total=(Long) queryCount.uniqueResult();
		return new DataSet(total,entityList);
		
	}
//============================================================================================
	
	//----------------------
	@Override
	public void truncateTable(String tableName) throws DaoException {
		String sql = " TRUNCATE TABLE " + tableName;
		this.executeUpdateSqlStatement(sql);
	}
	
	@Override
	public void executeUpdateSqlStatement(final String sql) throws DaoException{
		Session session = this.getCurrentSession();
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void executeUpdateSqlStatement(final String sql,final Object[] parameters)throws DaoException {
		Session session = this.getCurrentSession();
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				try {
					PreparedStatement ps = conn.prepareStatement(sql);
					int i=0;
					if(parameters!=null&&parameters.length>0){
						for(Object str:parameters){
							i++;
							ps.setString(i, (String) str);	
						}
					}
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public int executeUpdate(String queryStr, ParamObject paramObject) throws DaoException{
		Query query=null;
		if(paramObject.getIsSql()){
			query=this.getCurrentSession().createSQLQuery(queryStr);
		}else{
			query=this.getCurrentSession().createQuery(queryStr);
		}
		Map<String,Object> parmeterSet =paramObject.getQueryParams();
		String tempSql=new String(queryStr);
		if(parmeterSet!=null&&parmeterSet.size()>0){
			while(tempSql.contains(":")){
				 tempSql=StringUtils.substringAfter(tempSql, ":");
				String key=StringUtils.substringBefore(tempSql," ");
				if(key!=null&&key.trim().endsWith(")")){
					key=key.replace(")", "");
				}
				if(parmeterSet.keySet().contains(key)){
					Object paramValue=parmeterSet.get(key);
					//如果参数是null////？？？？
					if(paramValue==null){
						query.setParameter(key, paramValue);
					}else{
						if(paramValue instanceof Collection){
							query.setParameterList(key, (Collection<?>) paramValue);
						}else if(paramValue.getClass().isArray()){
							query.setParameterList(key,(Object[]) paramValue);
						}else{
							query.setParameter(key, paramValue);
						}
					}
					
					
				}
			}
		}
		return query.executeUpdate();
	}
}
