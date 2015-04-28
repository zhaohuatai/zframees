package com.zht.common.dauth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zht.framework.zhtdao.identity.PKBaseEntity;
@Entity()
@Table(name = "rbac_dataAccess_exp")
public class RbacDataAccessExp extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	public static final String defaultProvideA="current-posiotn-deparment";

	@Column(name = "daoAcessMethed", length = 30, nullable = false,unique=false)
	private String daoAcessMethed;
	
	@Column(name = "calledTime" ,nullable = false,unique=false)
	private Integer calledTime;
	
	@Column(name = "authExp", length = 100, nullable = false,unique=false)
	private String authExp;
	
	@Column(name = "fieldName", length = 50, nullable = true,unique=false)
	private String fieldName;
	
	@Column(name = "columnName", length = 50, nullable = true,unique=false)
	private String columnName;
	
	@Column(name = "enabled",nullable = false )
	private Boolean enabled;//可用
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_dataPrivilege_id")
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
