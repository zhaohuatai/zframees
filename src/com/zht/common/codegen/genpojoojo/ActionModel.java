/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.genpojoojo;

import java.util.List;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;

public class ActionModel  {
	
	private String packageName;
	private String entitySimpleClassName;
	private String servicePackageName;
	private String entityFullClassName;
	private String controllerNameSpace;
	private List<GenEntityProperty> genEntityPropertyList;
	private GenEntity genEntity;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getEntitySimpleClassName() {
		return entitySimpleClassName;
	}
	public void setEntitySimpleClassName(String entitySimpleClassName) {
		this.entitySimpleClassName = entitySimpleClassName;
	}
	public String getServicePackageName() {
		return servicePackageName;
	}
	public void setServicePackageName(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}
	public String getEntityFullClassName() {
		return entityFullClassName;
	}
	public void setEntityFullClassName(String entityFullClassName) {
		this.entityFullClassName = entityFullClassName;
	}
	public String getControllerNameSpace() {
		return controllerNameSpace;
	}
	public void setControllerNameSpace(String controllerNameSpace) {
		this.controllerNameSpace = controllerNameSpace;
	}

	public List<GenEntityProperty> getGenEntityPropertyList() {
		return genEntityPropertyList;
	}
	public void setGenEntityPropertyList(
			List<GenEntityProperty> genEntityPropertyList) {
		this.genEntityPropertyList = genEntityPropertyList;
	}
	public GenEntity getGenEntity() {
		return genEntity;
	}
	public void setGenEntity(GenEntity genEntity) {
		this.genEntity = genEntity;
	}


}
