/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.genpojoojo;

public class ServiceInterfaceModel  {
	private String packageName;
	private String entitySimpleClassName;
	private String entityFullClassName;
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
	public String getEntityFullClassName() {
		return entityFullClassName;
	}
	public void setEntityFullClassName(String entityFullClassName) {
		this.entityFullClassName = entityFullClassName;
	}
	
}
