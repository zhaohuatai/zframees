/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.base.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.loader.JoinWalker;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.transform.Transformers;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.DaoException;
import org.zht.framework.zhtdao.base.IBaseDao;
import org.zht.framework.zhtdao.hibernate.impl.HibernateBaseDaoImpl;

import com.zht.common.rabc.model.RbacUser;
public class BaseDaoImpl extends HibernateBaseDaoImpl implements IBaseDao{
//	public static String getCriteriaSql(Criteria criteria) {  
//	    CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;//转型  
//	    SessionImplementor session = criteriaImpl.getSession();//获取SESSION  
//	    SessionFactoryImplementor factory = session.getFactory();//获取FACTORY  
//	    CriteriaQueryTranslator translator = new CriteriaQueryTranslator(factory, criteriaImpl, criteriaImpl  
//	        .getEntityOrClassName(), CriteriaQueryTranslator.ROOT_SQL_ALIAS);  
//	    String[] implementors = factory.getImplementors(criteriaImpl.getEntityOrClassName());  
//	    
//	    CriteriaJoinWalker walker = new CriteriaJoinWalker((OuterJoinLoadable) factory  
//	        .getEntityPersister(implementors[0]), translator, factory, criteriaImpl, criteriaImpl  
//	        .getEntityOrClassName(), session.getEnabledFilters());  
//	    
//	    return walker.getSQLString();  
//	}  
	public DataSet loadDataSet(String queryStr,ParamObject paramObject)throws DaoException {
		if(paramObject==null){
			throw new DaoException("参数错误");
		}
		if(paramObject.getIsNeedCount()==null){
			throw new DaoException("[NeedCount]参数为空");
		}
		if(paramObject.getIsSql()==null){
			throw new DaoException("[isSql]参数为空");
		}
		if(paramObject.getIsOffset()==null){
			throw new DaoException("[isOffset]参数为空");
		}
		if(queryStr==null||!(queryStr.contains("@from")||queryStr.contains("@FROM"))){
			throw new DaoException("查询语句错误,或未找到[@from]标识");
		}
		String queryCountStr=" select count(*) from "+StringUtils.substringAfter(queryStr, "@from");
		//处理grouby 的情况统计问题，过滤掉子查询
		String countTemp=queryCountStr.replaceAll("\\((.*)\\)", "").replaceAll("[\\s]+", " ").toLowerCase();
		if(countTemp.contains("group by")&&paramObject.getIsSql()){
			queryCountStr="SELECT count(*) from ( "+queryCountStr+" ) as countAlias ";
		}
		queryStr=queryStr.replace("@from", "from").replace("@FROM", "FROM");
		Query queryData,queryCount;
		if(paramObject.getIsSql()){
			queryData=this.getCurrentSession().createSQLQuery(queryStr).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			queryCount=this.getCurrentSession().createSQLQuery(queryCountStr);
		}else{
			queryData=this.getCurrentSession().createQuery(queryStr).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			queryCount=this.getCurrentSession().createQuery(queryCountStr);
		}
		
		String tempSql=new String(queryStr);
		Map<String,Object> parmeterSet =paramObject.getQueryParams();
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
						queryData.setParameter(key, paramValue);
						queryCount.setParameter(key, paramValue);
					}else{
						if(paramValue instanceof Collection){
							queryData.setParameterList(key, (Collection<?>) paramValue);
							queryCount.setParameterList(key, (Collection<?>) paramValue);
						}else if(paramValue.getClass().isArray()){
							queryData.setParameterList(key,(Object[]) paramValue);
							queryCount.setParameterList(key,(Object[]) paramValue);
						}else if("true".equalsIgnoreCase(""+paramValue)||"false".equalsIgnoreCase(""+paramValue)){
							queryData.setParameter(key, Boolean.valueOf(""+paramValue));
							queryCount.setParameter(key, Boolean.valueOf(""+paramValue));
						}else{
							queryData.setParameter(key, paramValue);
							queryCount.setParameter(key, paramValue);
						}
					}
					
					
				}
			}
		}
		if(paramObject.getIsOffset()){
			queryData.setMaxResults(paramObject.getRows());  
	        queryData.setFirstResult((paramObject.getPage()-1)*paramObject.getRows()); 
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> entityList= queryData.list();
		
		Long total=0L;
		if(paramObject.getIsNeedCount()){
			if(countTemp.contains("group by")&&!paramObject.getIsSql()){
				//有group by 而且是hql的话，这样处理（hibernate不支持 select from（select...））
				List<?> listss=queryCount.list();
				total=(long) (listss==null?0:listss.size());
			}else{
				//没有group by 或者 用sql的话，queryStr，已经在上面处理过，此处可直接用
				total=(Long) queryCount.uniqueResult();
			}
		}
		
		return new DataSet(total,entityList);
	}

	@Override
	public List<?> findJustList(String queryStr, ParamObject paramObject)throws DaoException  {
		if(paramObject==null){
			throw new DaoException("参数错误");
		}
		if(paramObject.getIsNeedCount()==null){
			throw new DaoException("[NeedCount]参数为空");
		}
		if(paramObject.getIsSql()==null){
			throw new DaoException("[isSql]参数为空");
		}
		if(paramObject.getIsOffset()==null){
			throw new DaoException("[isOffset]参数为空");
		}
		queryStr=queryStr.replace("@from", "from").replace("@FROM", "FROM");
		Query queryData;
		if(paramObject.getIsSql()){
			queryData=this.getCurrentSession().createSQLQuery(queryStr);
		}else{
			queryData=this.getCurrentSession().createQuery(queryStr);
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
						queryData.setParameter(key, paramValue);
					}else{
						if(paramValue instanceof Collection){
							queryData.setParameterList(key, (Collection<?>) paramValue);
						}else if(paramValue.getClass().isArray()){
							queryData.setParameterList(key,(Object[]) paramValue);
						}else if("true".equalsIgnoreCase(""+paramValue)||"false".equalsIgnoreCase(""+paramValue)){
							queryData.setParameter(key, Boolean.valueOf(""+paramValue));
						}else{
							queryData.setParameter(key, paramValue);
						}
					}
					
					
				}
			}
		}
		List<?> entityList= queryData.list();
		if(entityList!=null&&entityList.size()>0){
			return entityList;
		}
		return null;
	}
	
	//=====================================================================
}
