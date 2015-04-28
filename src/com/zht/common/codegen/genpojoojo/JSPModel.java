/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.genpojoojo;

import java.util.List;
import java.util.Map;

public class JSPModel  {
	private String str1="${";
	private String str2="}";
	
	
	private String entityClassName;
	
	private List<String> fieldList;
	
	List<Map<String,String>> fieldListMap;
	
	List<Map<String,String>> queyParamlist;
	
	private String bringBackDispaly;
	private String bringBackValue;
	private String actionNameSpace;
	
	public String getEntityClassName() {
		return entityClassName;
	}
	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	public List<String> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}
	public List<Map<String, String>> getFieldListMap() {
		return fieldListMap;
	}
	public void setFieldListMap(List<Map<String, String>> fieldListMap) {
		this.fieldListMap = fieldListMap;
	}
	public List<Map<String, String>> getQueyParamlist() {
		return queyParamlist;
	}
	public void setQueyParamlist(List<Map<String, String>> queyParamlist) {
		this.queyParamlist = queyParamlist;
	}
	public String getBringBackValue() {
		return bringBackValue;
	}
	public void setBringBackValue(String bringBackValue) {
		this.bringBackValue = bringBackValue;
	}
	public String getBringBackDispaly() {
		return bringBackDispaly;
	}
	public void setBringBackDispaly(String bringBackDispaly) {
		this.bringBackDispaly = bringBackDispaly;
	}
	public String getActionNameSpace() {
		return actionNameSpace;
	}
	public void setActionNameSpace(String actionNameSpace) {
		this.actionNameSpace = actionNameSpace;
	}

}
