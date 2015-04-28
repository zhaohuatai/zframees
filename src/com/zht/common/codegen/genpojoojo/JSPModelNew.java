/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.genpojoojo;

import java.util.List;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;

public class JSPModelNew  {
	private String jstlstart="${";
	private String jstlend="}";
	
	private String entitySimpleClassName;
	private String entityFullClassName;
	private String controllerNameSpace;
	private GenEntity genEntity;
	private List<GenEntityProperty> genEntityPropertyList;

	public String getJstlstart() {
		return jstlstart;
	}
	public void setJstlstart(String jstlstart) {
		this.jstlstart = jstlstart;
	}
	public String getJstlend() {
		return jstlend;
	}
	public void setJstlend(String jstlend) {
		this.jstlend = jstlend;
	}
	public String getEntitySimpleClassName() {
		return entitySimpleClassName;
	}
	public void setEntitySimpleClassName(String entitySimpleClassName) {
		this.entitySimpleClassName = entitySimpleClassName;
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
	
	

}
