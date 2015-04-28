/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.hibernate;
//load方式检索不到的话会抛出org.hibernate.ObjectNotFoundException异常  ;get方法检索不到的话会返回null
// 而load方法的执行则比较复杂首先查找session的persistent Context中是否有缓存，如果有则直接返回 如果没有则判断是否是lazy，
//如果不是直接访问数据库检索，查到记录返回，查不到抛出异常
//get方法，hibernate会确认一下该id对应的数据是否存在，首先在session缓存中查找，然后在二级缓存中查找，还没有就查数据库，数据库中没有就返回null。
//load方法创建时首先查询session缓存，没有就创建代理，实际使用数据时才查询二级缓存和数据库。
//-------------------merge------------
//如果session中存在相同持久化标识(identifier)的实例，用用户给出的对象的状态覆盖旧有的持久实例 
//如果session没有相应的持久实例，则尝试从数据库中加载，或创建新的持久化实例,最后返回该持久实例 
//户给出的这个对象没有被关联到session上，它依旧是脱管的
//但当我们使用merge的时候，执行完成，我们提供的对象A还是脱管状态，hibernate或者new了一个B，或者检索到
//一个持久对象B，并把我们提供的对象A的所有的值拷贝到这个B，执行完成后B是持久状态，而我们提供的A还是托管状态
//-------------------persist------------
//persist把一个瞬态的实例持久化，但是并"不保证"标识符被立刻填入到持久化实例中，标识符的填入可能被推迟   到flush的时间。
//persist"保证"，当它在一个transaction外部被调用的时候并不触发一个Sql Insert
//通过继承Session/persistence context来封装一个长会话流程的时候，一个persist这样的函数是需要的
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;

public interface IHibernateBaseDao {

    public Session getCurrentSession()  throws DataAccessException;
    public Session getNewSession()  throws DataAccessException;
    
    public String getDatabaseInfo() throws DataAccessException;
  
    //------------------flush clear----------------------
    public void flush()   throws DataAccessException;
    public void clear()  throws DataAccessException; 
    public void evict(Object bean) throws DataAccessException; 
    
	//---------------executeUpdateSqlStatement-------
	public void truncateTable(String tableName)  throws DataAccessException;
	public void executeUpdateSqlStatement(final String sql) throws DataAccessException;
	public void executeUpdateSqlStatement(final String sql,final Object[] parameters) throws DataAccessException;
	public int executeUpdate(String queryStr, ParamObject paramObject)throws DataAccessException;
	
    //-------------------persist------------
    public void persist(Object bean) throws DataAccessException;
	public void persist(Collection<?> entities)throws DataAccessException;
	
	//-------------------save------------
	public void save(Object bean)throws DataAccessException;
	public void save(Collection<?> entities) throws DataAccessException;
	
	//-------------------update------------
    public void update(Object bean) throws DataAccessException;
	public void update(Collection<?> entities) throws DataAccessException;
	
	//-------------------saveOrUpdate------------
	public void saveOrUpdate(Object bean) throws DataAccessException;
	public void saveOrUpdate(Collection<?> entities)throws DataAccessException;
	
	//-------------------merge------------
	public void merge(Object entity) throws DataAccessException;
	public void merge(Collection<?> entities) throws DataAccessException;
	
	//-------------------delete------------
    public void delete(Object bean)throws DataAccessException;
    public void delete(Class<?> clazz, Serializable id)throws DataAccessException;
    public void deleteByIdsInCase(Class<?> clazz, Serializable[] ids)throws DataAccessException;
    public void deleteByIdsInCase(Class<?> clazz,List<Serializable> idList)throws DataAccessException;
    public void deleteAll(Class<?> clazz)throws DataAccessException;

//-------------------find-One<E>------------------------------------------------------
    public <E> E find(Class<E> clazz, Serializable id)throws DataAccessException;
    public <E> E findAndLock(Class<E> clazz,Serializable id) throws DataAccessException;
    public <E> E findByUniqueParam(Class<E> clazz,String uniqueParamName, String value)throws DataAccessException;
	public <E> E findByUniqueParams(Class<E> clazz,Map<String, Object> params)throws DataAccessException;
	
//-------------------find-field------------------------------------------------------	
	public Serializable findIdByUnique(Class<?> clazz, String filedName,Object value)throws DataAccessException;
	public Object findFiledByUnique(Class<?> clazz,String targetFieldToBeQuey, String uniqueFieldNanme,Object value)throws DataAccessException;
	public Serializable findParentIdBySelfUnique(Class<?> clazz,String parentPropertyName,String selfUniqueFieldName,Object selfUniqueFieldValue)throws DataAccessException;

//-------------------find-by--id--in------------------------------------------------------	
	public  <E> List<E> findByIdsInCase(Class<?> clazz, Serializable[] ids)throws DataAccessException; 
	public  <E> List<E> findByIdsInCase(Class<?> clazz, List<Serializable> idList)throws DataAccessException; 
//-------------------find findAndLock findTotalCount------------
    public Long findAllCount(Class<?> clazz)throws DataAccessException;
    @Deprecated
	public <E> List<E> findAll( Class<E> clazz)throws DataAccessException;
	@Deprecated
	public <E> List<E> findAllOverCache(Class<E> clazz) throws DataAccessException;
	@Deprecated
	public <E> List<E>  findByHqlWhere(Class<E> clazz,String hqlWhere) throws DataAccessException;
	@Deprecated
	public <E> List<E>  findByHqlWhereForPage(Class<E> clazz,String hqlWhere,int pageSize, int pageNumber)throws DataAccessException;
	public Long findCountByHQLWhere(Class<?> clazz,String hqlWhere)throws DataAccessException;
	@Deprecated
	public <E> List<E>findByHql(String hql) throws DataAccessException;
	public Long findCountByHQL(String hqlWhere)throws DataAccessException;
		

//---------------------------------------------------------------------------------------
	@Deprecated
	public <E> List<E> findListByParamObject(Class<E> clazz,ParamObject paramObject,String extraSQLStr)throws DataAccessException;
	@Deprecated
	public Long findCountByParamObject(Class<?> clazz,ParamObject paramObject,String extraSQLStr)throws DataAccessException;
	
	public DataSet loadDataSetFromOneEntity(Class<?> clazz,ParamObject paramObject,RowMap rowMap,String extraCondition)throws DataAccessException;
	
	
	
}
