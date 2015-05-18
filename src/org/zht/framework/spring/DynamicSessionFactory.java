package org.zht.framework.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;

public class DynamicSessionFactory implements InitializingBean{
	
	private Map<Object, Object> targetSessionFactories;
	private Object defaultTargetSessionFactory;
	
	private Map<Object, SessionFactory> resolvedSessionFactories;
	private SessionFactory resolvedDefaultSessionFactory;
	private List<String> resolvedSessionFactoryNames;
	
	public SessionFactory determineTargetSessionFactory(){
		  if(this.resolvedSessionFactories==null){
			  throw new IllegalStateException("SessionFactory dynamic router not initialized");
		  }
		Object lookupKey =SessionFactoryContextHolder.getSessionFactoryName();
//	    Object lookupKey ="sessionFactory";// determineCurrentLookupKey();
	    
	    SessionFactory dataSource = (SessionFactory)this.resolvedSessionFactories.get(lookupKey);
	    if ((dataSource == null) || (lookupKey == null)) {
	      dataSource = this.resolvedDefaultSessionFactory;
	    }
	    if (dataSource == null) {
	      throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
	    }
	    return dataSource;
	  }

	@Override
	public void afterPropertiesSet() throws Exception {
		  if (this.targetSessionFactories == null) {
		      throw new IllegalArgumentException("Property 'targetSessionFactories' is required");
		    }
		    this.resolvedSessionFactories = new HashMap<Object, SessionFactory>(this.targetSessionFactories.size());
		    
		    this.resolvedSessionFactoryNames=new ArrayList<String>(this.targetSessionFactories.size());
		    
		    for (Map.Entry<?,?> entry : this.targetSessionFactories.entrySet()) {
		      Object lookupKey = resolveSpecifiedLookupKey(entry.getKey());
		      SessionFactory dataSource = resolveSpecifiedSessionFactory(entry.getValue());
		      this.resolvedSessionFactories.put(lookupKey, dataSource);
		      this.resolvedSessionFactoryNames.add((String) lookupKey);
		    }
		    if (this.defaultTargetSessionFactory != null){
		      this.resolvedDefaultSessionFactory = resolveSpecifiedSessionFactory(this.defaultTargetSessionFactory);
		    }
	}

	protected Object resolveSpecifiedLookupKey(Object lookupKey) {
		
		return lookupKey;
	}

	protected SessionFactory resolveSpecifiedSessionFactory(Object sessionFactory)throws IllegalArgumentException {
		if ((sessionFactory instanceof SessionFactory)) {
			return (SessionFactory) sessionFactory;
		}
		throw new IllegalArgumentException("Illegal sessionFactory value - only [org.hibernate.SessionFactory] supported: "+ sessionFactory);
	}
	  
	
	
	
	public List<String> getResolvedSessionFactoryNames() {
		return resolvedSessionFactoryNames;
	}

	//=================\
	public Map<Object, Object> getTargetSessionFactories() {
		return targetSessionFactories;
	}

	public void setTargetSessionFactories(
			Map<Object, Object> targetSessionFactories) {
		this.targetSessionFactories = targetSessionFactories;
	}

	public Object getDefaultTargetSessionFactory() {
		return defaultTargetSessionFactory;
	}

	public void setDefaultTargetSessionFactory(
			Object defaultTargetSessionFactory) {
		this.defaultTargetSessionFactory = defaultTargetSessionFactory;
	}
}
