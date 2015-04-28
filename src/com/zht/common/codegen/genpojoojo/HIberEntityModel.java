/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.genpojoojo;

import java.util.List;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;


public class HIberEntityModel  {
	
	private String entityFullClassName;
	private String entitySimpleClassName;
	private String packageName;
	private String tableName;
	private GenEntity genEntity;
	private List<GenEntityProperty> genEntityPropertyList;
	
	//@Column(name="display",unique=true, nullable=false,length=32)private String display;
//	private List<String> propertyContentList;
//	
//	private List<String> propertyList;

	public String getEntityFullClassName() {
		return entityFullClassName;
	}

	public void setEntityFullClassName(String entityFullClassName) {
		this.entityFullClassName = entityFullClassName;
	}

	public String getEntitySimpleClassName() {
		return entitySimpleClassName;
	}

	public void setEntitySimpleClassName(String entitySimpleClassName) {
		this.entitySimpleClassName = entitySimpleClassName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public GenEntity getGenEntity() {
		return genEntity;
	}

	public void setGenEntity(GenEntity genEntity) {
		this.genEntity = genEntity;
	}

	public List<GenEntityProperty> getGenEntityPropertyList() {
		return genEntityPropertyList;
	}

	public void setGenEntityPropertyList(
			List<GenEntityProperty> genEntityPropertyList) {
		this.genEntityPropertyList = genEntityPropertyList;
	}



//	public List<String> getPropertyContentList() {
//		return propertyContentList;
//	}
//
//	public void setPropertyContentList(List<String> propertyContentList) {
//		this.propertyContentList = propertyContentList;
//	}
//
//	public List<String> getPropertyList() {
//		return propertyList;
//	}
//
//	public void setPropertyList(List<String> propertyList) {
//		this.propertyList = propertyList;
//	}
	

	

}
