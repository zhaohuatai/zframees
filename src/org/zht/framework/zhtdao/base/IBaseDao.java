/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.base;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.zhtdao.hibernate.IHibernateBaseDao;

public interface IBaseDao extends IHibernateBaseDao{
	
	public DataSet loadDataSet(String queryStr,ParamObject paramObject)throws DataAccessException;
	
	public List<?> findJustList(String queryStr,ParamObject paramObject) throws DataAccessException;
	

}
