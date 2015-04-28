package com.zht.common.rabc.model;

import org.zht.framework.zhtdao.identity.PKBaseEntity;

public class RbacDataAccessExp extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String defaultProvideA="current-posiotn-deparment";
	
	private String daoAcessMethed;
	
	private String authExp;
	
	private String fieldName;
	
	private String columnName;
	
	private RbacDataPrivilege dataPrivilege;

	public String getDaoAcessMethed() {
		return daoAcessMethed;
	}

	public void setDaoAcessMethed(String daoAcessMethed) {
		this.daoAcessMethed = daoAcessMethed;
	}

	public String getAuthExp() {
		return authExp;
	}

	public void setAuthExp(String authExp) {
		this.authExp = authExp;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public RbacDataPrivilege getDataPrivilege() {
		return dataPrivilege;
	}

	public void setDataPrivilege(RbacDataPrivilege dataPrivilege) {
		this.dataPrivilege = dataPrivilege;
	}

	public static String getDefaultprovidea() {
		return defaultProvideA;
	}
	
	
	
}
