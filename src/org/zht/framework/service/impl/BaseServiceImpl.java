/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.service.impl;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
//import org.hibernate.mapping.Table;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.annos.CurrentTimeStamp;
import org.zht.framework.annos.TreeConstruct;
import org.zht.framework.annos.TreeParentFied;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.IBaseService;
import org.zht.framework.spring.DynamicSessionFactory;
import org.zht.framework.spring.SessionFactoryContextHolder;
import org.zht.framework.spring.SpringUtils;
import org.zht.framework.util.ZPropertyUtil;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;
import org.zht.framework.zhtdao.base.impl.BaseDaoImpl;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.zht.common.dynamic.dataSource.SessionFactoryType;
//@Service
@Transactional(rollbackFor=Exception.class)
@SuppressWarnings("hiding")
public class BaseServiceImpl<M extends PKBaseEntity> implements IBaseService<M>{
	@Resource(name = "baseDaoImpl")
	protected BaseDaoImpl baseDaoImpl;
	
	protected DynamicSessionFactory dynamicSessionFactory = (DynamicSessionFactory) SpringUtils.getBean("dynamicSessionFactory");
	
//	private LocalSessionFactoryBean factory = (LocalSessionFactoryBean) SpringUtils.getBean("&sessionFactory");
	
	private Class<? extends PKBaseEntity> entityClass;
	//直接加载，不要作为函数内部变量，
//	protected final PersistentClass clazz = factory.getConfiguration().getClassMapping(this.getEntityClass().getName());
	protected final PersistentClass clazz = parserPersistentClass(); 
//	protected final Table table = clazz.getTable(); 
	protected MethodAccess access = MethodAccess.get(this.getEntityClass());
	
//--------------------------------------------------------------------------------------------------------	
	@SuppressWarnings("unused")
	private PersistentClass parserPersistentClass(){
		PersistentClass clazz=null;
		
		List<String> resolvedSessionFactoryNames=dynamicSessionFactory.getResolvedSessionFactoryNames();
		if(resolvedSessionFactoryNames==null){
			 throw new IllegalStateException("sessionFactory dynamic router not initialized");
		}
		for(String key : resolvedSessionFactoryNames){
			LocalSessionFactoryBean factory = (LocalSessionFactoryBean) SpringUtils.getBean("&"+key+"");
			 clazz=factory.getConfiguration().getClassMapping(this.getEntityClass().getName());
			if(clazz!=null){
				return clazz;
			}
		}
		if(clazz==null){
			throw new IllegalStateException("target persistent Class not configed in all of the sessionFactories");
		}
		return clazz;
	}
	
	@SuppressWarnings("unchecked")
	public  Class<?> getEntityClass() {
		Type type = getClass().getGenericSuperclass();
		if(entityClass==null){
			entityClass = (Class<? extends PKBaseEntity>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}
		return entityClass;
	}
	
	protected  void changeFactoryType (SessionFactoryType factoryType) {
		SessionFactoryContextHolder.setSessionFactoryName(factoryType.name());
	}
	
//--------------------------------------------------------------------------------------------------------
	@Override
	public void $base_saveOrUpdate(M m) {
		if(m==null){
			throw new ServiceLogicalException("提交数据为空，请检查数据");
		}
		if(m.getId()!=null){
			$base_update(m);
		}else{
			$base_save( m);
		}
	}
	
//---------------------------------------------$$base_add------S-----------------------------------------------------
	@Override
	public void $base_save(M m){
		if(m==null){
			throw new ServiceLogicalException("提交数据为空，请检查数据");
		}
		//------------------此处校验可以去掉，提高效率-------------------------------------
		//找到第一个不能为空的，但是提交数据为空，停止运行 ，抛出异常,,后期去掉该检查，由hibernate校验完成**************
		String checkNotNullRes=checkNotNull(m);//找到第一个不能为空的，但是提交数据为空，停止运行 ，抛出异常
		if(checkNotNullRes!=null){
			throw new ServiceLogicalException(checkNotNullRes);
		}
		//检查长度
		String checkLenth=checkLenth(m);//检查长度
		if(checkLenth!=null){
			throw new ServiceLogicalException(checkLenth);
		}
		//-----------------------------------------------------------------------
		//系统只允许存在一个根节点
		String isMultiTreeRoot =checkIsMultiTreeRootForAddNew(m);
		if(isMultiTreeRoot!=null){
			throw new ServiceLogicalException(isMultiTreeRoot);
		}
		//-----------------------------------------------------------------------
		String checkUniqueRes=checkUnique(m);
		if(checkUniqueRes!=null){
			throw new ServiceLogicalException(checkUniqueRes);
		}
		//--------------------checkmanyTone$OneToOneNullForeign---------------------------------------------------
		String isNullAblezz=checkmanyTone$OneToOneNullForeign(m);
		if(isNullAblezz!=null){
			throw new ServiceLogicalException(isNullAblezz);
		}
		//-----------------------------------------------------------------------
		generateCurrentTimeStamp(m);
		baseDaoImpl.saveOrUpdate(m);
	}
//---------------------------------------------$$base_add------E-----------------------------------------------------	
//---------------------------------------------$base_update------S-----------------------------------------------------	 
		@SuppressWarnings("unchecked")
		@Override
		public void $base_update(M m) {
			if(m==null||m.getId()==null){
				throw new ServiceLogicalException("提交数据为空，请检查数据");
			}
			//------------------此处校验可以去掉，提高效率-------------------------------------
			//检查非空的字段
			String checkNotNullRes=checkNotNull(m);//找到第一个不能为空的，但是提交数据为空，停止运行 ，抛出异常
			if(checkNotNullRes!=null){
				throw new ServiceLogicalException(checkNotNullRes);
			}
			//检查长度
			String checkLenth=checkLenth(m);//检查长度
			if(checkLenth!=null){
				throw new ServiceLogicalException(checkLenth);
			}
			//-------------------------------------------------------
			
			//如果是树形结构
			String checkParentRes=checkParentNodeIfTree(m);
			if(checkParentRes!=null){
				throw new ServiceLogicalException(checkParentRes);
			}
			//检查唯一性的字段
			String checkUniqueRes=checkUnique(m);
			if(checkUniqueRes!=null){
				throw new ServiceLogicalException(checkUniqueRes);
			}
			//--------------------checkmanyTone$OneToOneNullForeign---------------------------------------------------
			String isNullAblezz=checkmanyTone$OneToOneNullForeign(m);
			if(isNullAblezz!=null){
				throw new ServiceLogicalException(isNullAblezz);
			}
			generateCurrentTimeStamp(m);
			//-------------------------------------------------------
			M temp=(M) baseDaoImpl.find(this.getEntityClass(), m.getId());//根据id 查找 对应的数据
			ZBeanUtil.copy(m, temp, true);//复制
			baseDaoImpl.saveOrUpdate(temp);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void $base_update(M m,boolean ignorNull) {
			if(m==null||m.getId()==null){
				throw new ServiceLogicalException("提交数据为空，请检查数据");
			}
			//------------------此处校验可以去掉，提高效率-------------------------------------
			//检查非空的字段
			String checkNotNullRes=checkNotNull(m);//找到第一个不能为空的，但是提交数据为空，停止运行 ，抛出异常
			if(checkNotNullRes!=null){
				throw new ServiceLogicalException(checkNotNullRes);
			}
			//检查长度
			String checkLenth=checkLenth(m);//检查长度
			if(checkLenth!=null){
				throw new ServiceLogicalException(checkLenth);
			}
			//-------------------------------------------------------
			
			//如果是树形结构
			String checkParentRes=checkParentNodeIfTree(m);
			if(checkParentRes!=null){
				throw new ServiceLogicalException(checkParentRes);
			}
			//检查唯一性的字段
			String checkUniqueRes=checkUnique(m);
			if(checkUniqueRes!=null){
				throw new ServiceLogicalException(checkUniqueRes);
			}
			generateCurrentTimeStamp(m);
			M temp=(M) baseDaoImpl.find(this.getEntityClass(), m.getId());//根据id 查找 对应的数据
			ZBeanUtil.copy(m, temp, ignorNull);//复制
			baseDaoImpl.saveOrUpdate(temp);
		}
//---------------------------------------------$base_update------E-----------------------------------------------------	 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<Map> $base_loadCombotree() {
			if(!this.entityClass.isAnnotationPresent(TreeConstruct.class)){
				return null;
			}
			boolean istreeF=false;
			String fieldname="";
			 Field[] fields=this.entityClass.getDeclaredFields();
			 if(fields==null||fields.length==0){
				 return null;
			 }
			 for(Field f:fields){
				 if(f.isAnnotationPresent(TreeParentFied.class)){
					 istreeF=true;
					 fieldname=f.getName();
					 break;
				 }
			 }
			 if(!istreeF||fieldname==null||fieldname.length()==0){
				 return null;
			 }
			 
			List<Long> rootIdList = (List<Long>) 
					baseDaoImpl.findJustList("select id from "+this.entityClass.getSimpleName()+" g where g."+fieldname+".id is null ",
							new ParamObject(POType.H_NO_NC));
			if (rootIdList == null || rootIdList.size() == 0) {
				throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
			}
			if (rootIdList.size() > 1) {
				throw new ServiceLogicalException("发现多个根节点数据，请检查数据 ");
			}
			String hql = " select g.id as id ,"
					+ " g."+fieldname+".id as _parentId, "
					+ " g.name as text "
					+ " @from "+this.entityClass.getSimpleName()+" g ";
			DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(
					POType.H_NO_NC));
			Map root = dataSet.getRows().get(0);
			EasyuiTreeUtil.traverse(root, dataSet.getRows());
			List<Map> mapList = new ArrayList<Map>();
			mapList.add(root);
			return mapList;
		}
		
//----------------------------------------------$base_delete$Just------S---------------------------------------------------------------------	
		@Override
		public void $base_delete$Just(M m) {
			if(m==null||m.getId()==null){
				throw new ServiceLogicalException("请选择要删除的数据");
			}
			baseDaoImpl.delete(this.getEntityClass(), m.getId());
		}
		@Override
		public void $base_delete$Just(Serializable id){
			if(id==null){
				throw new ServiceLogicalException("请选择要删除的数据");
			}
			baseDaoImpl.delete(this.getEntityClass(), id);
		}
		@Override
		public void $base_deleteByIdsInCase$Just(Serializable[] ids)  {
			if(ids==null||ids.length==0){
				throw new ServiceLogicalException("请选择要删除的数据");
			}
			if (ids != null && ids.length > 0) {
				baseDaoImpl.deleteByIdsInCase(this.getEntityClass(), ids);
			}
		}
		@Override
		public void $base_deleteAll() {
			baseDaoImpl.deleteAll(this.getEntityClass());
		}
//----------------------------------------------$base_delete$Just-------------E--------------------------------------------------------------
//----------------------------------------------$base_find------------s--------------------------------------------------------------
		@SuppressWarnings("unchecked")
		@Override
		public <M> M $base_find(Serializable id) {
			return (M) baseDaoImpl.find(this.getEntityClass(), id);
		}

		@Override
		public Long $base_findTotalCount() {
			return baseDaoImpl.findAllCount(this.getEntityClass());
		}
		@SuppressWarnings("unchecked")
		@Override
		public <M> List<M> $base_findAll(){
			return (List<M>) baseDaoImpl.findAll(this.getEntityClass());
		}
		@SuppressWarnings("unchecked")
		@Override
		public  <M> List<M> $base_findByIdsInCase(Serializable[] ids){
			if(ids!=null&&ids.length==0){
				return null;
			}
			return (List<M>) baseDaoImpl.findByIdsInCase(this.getEntityClass(),ids);
		}
		@Override
		public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap){
			return baseDaoImpl.loadDataSetFromOneEntity(this.getEntityClass(), paramObject, rowMap,null);
		}
		
		@Override
		public DataSet $base_loadDataSetFromOneEntity(ParamObject paramObject,RowMap rowMap,String extraCondition){
			return baseDaoImpl.loadDataSetFromOneEntity(this.getEntityClass(), paramObject, rowMap,extraCondition);
		}
		@Override
		@SuppressWarnings({ "unchecked" })
		public <M> List<M> $base_findListByParamObject(ParamObject paramObject,String extraSQLStr){
			return (List<M>) baseDaoImpl.findListByParamObject(this.getEntityClass(), paramObject, extraSQLStr);
		}
		@Override
		public Long $base_findCountByParamObject(ParamObject paramObject,String extraSQLStr){
			return baseDaoImpl.findCountByParamObject(this.getEntityClass(), paramObject, extraSQLStr);
		}
		
//----------------------------------------------$base_find$Just-------------E--------------------------------------------------------------
		private String  checkLenth(M m){
			// final PersistentClass clazz = factory.getConfiguration().getClassMapping(m.getClass().getName()); 
			 if(clazz==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据映射类，请联系管理员");
			 }
			// final Table table = clazz.getTable(); 
//			 if(table==null){
//				 throw new ServiceLogicalException("[系统错误]未找到该数据表，请联系管理员");
//			 }
			 @SuppressWarnings("unchecked")
			final Iterator<Property> iterator = clazz.getPropertyIterator();
			 if(iterator==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据表中任何字段，请联系管理员");
			 } 
			// MethodAccess access = MethodAccess.get(m.getClass());
			while (iterator.hasNext()) {
				Property property = iterator.next();
				if("id".equals(property.getName())){
					continue;
				}
				Iterator<?> columnIterator = property.getColumnIterator();
				if (columnIterator.hasNext()) {
					Column column = (Column) columnIterator.next();
					int lenth=column.getLength();
					 Object value = (Object)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(property.getName()));
					 if(value!=null){
						 if(value instanceof java.lang.String){
							 if(lenth<(((java.lang.String) value).length())){
								 return ("字段 ["+property.getName()+"] 长度超过限制，请检查数据");
							 }
							
						 } 
					 }
				}
			 }
			return null;
		 }
		
		private String  checkNotNull(M m){
			// final PersistentClass clazz = factory.getConfiguration().getClassMapping(m.getClass().getName()); 
			 if(clazz==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据映射类，请联系管理员");
			 }
			 //final Table table = clazz.getTable(); 
//			 if(table==null){
//				 throw new ServiceLogicalException("[系统错误]未找到该数据表，请联系管理员");
//			 }
			 @SuppressWarnings("unchecked")
			final Iterator<Property> iterator = clazz.getPropertyIterator();
			 if(iterator==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据表中任何字段，请联系管理员");
			 } 
			// MethodAccess access = MethodAccess.get(m.getClass());
			while (iterator.hasNext()) {
				Property property = iterator.next();
				if("id".equals(property.getName())){
					continue;
				}
				Iterator<?> columnIterator = property.getColumnIterator();
				if (columnIterator.hasNext()) {
					Column column = (Column) columnIterator.next();
					if (!column.isNullable()) {
						
						 Object value = (Object)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(property.getName()));
						 //System.out.println(property.getName()+" "+value);
						 if(ZStrUtil.trimToNullIfStr(value)==null){
							return ("必填字段 ["+property.getName()+"] 为空，请检查数据");
						 }
						
					}
				}
			 }
			return null;
		 }
		
		@SuppressWarnings("unchecked")
		private String  checkmanyTone$OneToOneNullForeign(M m){
			// final PersistentClass clazz = factory.getConfiguration().getClassMapping(m.getClass().getName()); 
			 if(clazz==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据映射类，请联系管理员");
			 }
			final Iterator<Property> iterator = clazz.getPropertyIterator();
			if(iterator==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据表中任何字段，请联系管理员");
			} 
			// MethodAccess access = MethodAccess.get(m.getClass());
			while (iterator.hasNext()) {
				Property property = iterator.next();
				if("id".equals(property.getName())){
					continue;
				}
				Field field=null;
				try {
					 field=m.getClass().getDeclaredField(property.getName());
					if(!(field.isAnnotationPresent(javax.persistence.ManyToOne.class)||field.isAnnotationPresent(javax.persistence.OneToOne.class))){
						continue;
					}
				}catch (Exception e) {
					continue;
				}
				Iterator<?> columnIterator = property.getColumnIterator();
				if (columnIterator.hasNext()) {
					 Column column = (Column) columnIterator.next();
					 Object value = (Object)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(property.getName()));
					if (!column.isNullable()) {
						 if(ZStrUtil.trimToNullIfStr(value)==null){
							return ("必填字段 ["+property.getName()+"] 为空，请检查数据");
						 }
						 MethodAccess  accessZZZ =MethodAccess.get(value.getClass());
						 Object foreignModelId=(Object)accessZZZ.invoke(value, "getId");
						 if(foreignModelId==null){
							 return ("必填字段 ["+property.getName()+"] 为空，请检查数据");
						 }
					}else{
						if(ZStrUtil.trimToNullIfStr(value) != null){
							Object foreignModelId =null;
							    foreignModelId =ZPropertyUtil.getInvokeValue(value,"getId");
							    
								if(foreignModelId!=null&&foreignModelId.equals(-972855736L)){
									throw new ServiceLogicalException("[系统错误]，请联系管理员");
								}
								if (foreignModelId == null) {
									value=null;
									access.invoke(m, "set"+ZStrUtil.toUpCaseFirst(property.getName()),value);
								}
//							
						}
//						else{
//							valueTemp=null;
//							access.invoke(m, "set"+ZStrUtil.toUpCaseFirst(property.getName()),valueTemp);
//						}
					}
				}
			 }
			return null;
		 }
		 private String checkUnique(M m){
//			 final PersistentClass clazz = factory.getConfiguration().getClassMapping(m.getClass().getName()); 
			 if(clazz==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据映射类，请联系管理员");
			 }
			// final Table table = clazz.getTable(); 
//			 if(table==null){
//				 throw new ServiceLogicalException("[系统错误]未找到该数据表，请联系管理员");
//			 }
			 @SuppressWarnings("unchecked")
			 final Iterator<Property> iterator = clazz.getPropertyIterator();
			 if(iterator==null){
				 throw new ServiceLogicalException("[系统错误]未找到该数据表中任何字段，请联系管理员");
			 } 
			 //MethodAccess access = MethodAccess.get(m.getClass());
			while (iterator.hasNext()) {
				Property property = iterator.next();
				if("id".equals(property.getName())){
					continue;
				}
				Iterator<?> columnIterator = property.getColumnIterator();
				if (columnIterator.hasNext()) {
					Column column = (Column) columnIterator.next();
					if (column.isUnique()) {
						
						 Object value = (Object)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(property.getName()));
						 //System.out.println(property.getName()+" "+value);
						 Object id=baseDaoImpl.findIdByUnique(m.getClass(),property.getName(),value);
						 if(id!=null&&!id.equals(m.getId())){
							return ("系统中已经存在唯一且值为["+value+"]的记录，请检查数据");
						}
					}
				}
			}
			 return null;
		 }
		
		@SuppressWarnings("unchecked")
		private String checkIsMultiTreeRootForAddNew(M m){
			//系统只允许存在一个根节点
			if(m.getClass().isAnnotationPresent(TreeConstruct.class)){
				String parentPropertyName=getParentPropertyName(m);
				if(parentPropertyName==null||parentPropertyName.isEmpty()){
					return ("树形结构的实体，但是不存在树形父节点，请联系管理人员");
				}
				//MethodAccess access = MethodAccess.get(this.getEntityClass());
				M parent = (M)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(parentPropertyName));
				if(parent==null||parent.getId()==null){//没有父节点,但是系统只允许一个根节点
					List<Long> rootlist=(List<Long>) baseDaoImpl.findJustList(" select id from "+m.getClass().getName()+" where "+parentPropertyName+".id is null", new ParamObject(POType.H_NO_NC));
					if(rootlist!=null&&rootlist.size()>1){
						return ("系统中已经存在多个该数据的根节点，请联系管理人员");
					}
					if(rootlist!=null&&rootlist.size()>0){
						return ("系统中已经存在一个该类型数据的根节点，请选择或填写父节点数据");
					}
				}
			}
			return null;
		}
		@SuppressWarnings("unchecked")
		private String checkParentNodeIfTree(M m) {
			//如果是树形结构
			if(m.getClass().isAnnotationPresent(TreeConstruct.class)){
				String parentPropertyName=getParentPropertyName(m);
				if(parentPropertyName==null||parentPropertyName.isEmpty()){
					throw new ServiceLogicalException("树形结构的实体，但是不存在父亲节点树形，请检查数据");
				}
				
				//MethodAccess access = MethodAccess.get(m.getClass());
				M parent = (M)access.invoke(m, "get"+ZStrUtil.toUpCaseFirst(parentPropertyName));
				if(parent!=null&&parent.getId()!=null){//修改的不是根节点
					Object pid=baseDaoImpl.findIdByUnique(m.getClass(),"id",parent.getId());
					if(pid==null){
						return ("父节点未查询到，请检查数据");
					}
					if(pid!=null&&pid.equals(m.getId())){
						return ("父节点不能是自己");
					}
					while(!(parent==null||parent.getId()==null)){
						pid=baseDaoImpl.findParentIdBySelfUnique(m.getClass(),parentPropertyName,"id",pid);
						if(pid==null){
							break;
						}
						if(m.getId().equals(pid)||m.getId()==pid){
							return ("父节点不能是自己的子节点");
						}
					}
				}else{//修改的是根节点,,没有父节点
					List<Long> rootlist=(List<Long>) baseDaoImpl.findJustList(" select id from "+m.getClass().getName()+" where "+parentPropertyName+".id is null", new ParamObject(POType.H_NO_NC));
					if(rootlist!=null&&rootlist.size()>1){
						return ("系统中已经存在多个该数据的根节点，请联系管理人员");
					}
					if(rootlist!=null&&rootlist.size()>0){
						if(!rootlist.get(0).equals(m.getId())){
							return ("系统中已经存在一个该数据的根节点，请检查数据");
						}
					}
				}
			}
			return null;
		}

		
	

	 
	 
	 private String getParentPropertyName(M m){
		 Field[] fields=m.getClass().getDeclaredFields();
		 if(fields==null||fields.length==0){
			 return null;
		 }
		 for(Field f:fields){
			 if(f.isAnnotationPresent(TreeParentFied.class)){
				 return f.getName();
			 }
		 }
		 return null;
	 }
	 
	 private void generateCurrentTimeStamp(M m){
		 Field[] fields=m.getClass().getDeclaredFields();
		 if(fields==null||fields.length==0){
			 return;
		 }
		 for(Field f:fields){
			 if(f.isAnnotationPresent(CurrentTimeStamp.class)){
				// MethodAccess access = MethodAccess.get(m.getClass());
				 access.invoke(m, "set"+ZStrUtil.toUpCaseFirst(f.getName()), new Date(System.currentTimeMillis()));  
			 }
		 }
	 }
}
