/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.zht.framework.annos.CurrentTimeStamp;
import org.zht.framework.zhtdao.identity.PKBaseEntity;
@Entity()
@Table(name = "zht_gen_entity")
public class GenEntity  extends PKBaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenEntity() {
		super();
	}

	@Column(name = "name", length = 100,  nullable = false,unique=true)
	private String name;
	/**
	 * 初始时候为   v0.0
	 */
	@Column(name = "version", length = 10 )
	private String version;
	//@NotNull
	@Column(name = "gen_info", length = 60 )
	private String genInfo;
	
	@Column(name = "remark", length = 60 )
	private String remark;
	
	@Column(name = "tableName", length = 60 )
	private String tableName;
	
	@Column(name = "controllerNameSpace", length = 60 )
	private String controllerNameSpace;//controller namesapce
	
	@Column(name = "isTree")
	public Boolean isTree;//是否树形结构
	
	@Column(name = "module_name", length = 60 )
	private String moduleName;//生成路径模块名
	
	@Column(name = "entityDisName", length = 60 )
	private String entityDisName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date careateTime;
	
	@CurrentTimeStamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modify_time")
	private Date lastModifyTime;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE, mappedBy="genEntity")
	private Set<GenEntityProperty> genEntityPropertyList= new HashSet<GenEntityProperty>(0);
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCareateTime() {
		return careateTime;
	}

	public void setCareateTime(Date careateTime) {
		this.careateTime = careateTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getGenInfo() {
		return genInfo;
	}

	public void setGenInfo(String genInfo) {
		this.genInfo = genInfo;
	}


	public Set<GenEntityProperty> getGenEntityPropertyList() {
		return genEntityPropertyList;
	}

	public void setGenEntityPropertyList(
			Set<GenEntityProperty> genEntityPropertyList) {
		this.genEntityPropertyList = genEntityPropertyList;
	}

	public String getControllerNameSpace() {
		return controllerNameSpace;
	}

	public void setControllerNameSpace(String controllerNameSpace) {
		this.controllerNameSpace = controllerNameSpace;
	}

	public Boolean getIsTree() {
		return isTree;
	}

	public void setIsTree(Boolean isTree) {
		this.isTree = isTree;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEntityDisName() {
		return entityDisName;
	}

	public void setEntityDisName(String entityDisName) {
		this.entityDisName = entityDisName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	
}
