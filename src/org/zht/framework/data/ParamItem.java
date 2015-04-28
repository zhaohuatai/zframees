/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;

public class ParamItem {
	private Object field;
	private Object value;
	private String logic;
	private String type;
	private Boolean isEmptyValue=new Boolean(false);
	public ParamItem() {}

//	public ParamItem(Object field, Object value) {
//		isEmptyValue=ZBeanUtil.isEmptyValue(value);
//		this.field = field;
//		this.value = value;
//	}

	/**
	 * @param field
	 * @param value
	 * @param logic
	 */
	public ParamItem(Object field, Object value, String logic) {
		isEmptyValue=ZBeanUtil.isEmptyValue(value);
		//isEmptyValue=isNullIfBoolean( value,  logic);
		if(!ZStrUtil.trimToEmptyIfStr(field).toString().contains(".")){
			this.field = RowMap.queryAsAlias+"."+field;
		}else{
			this.field = field;
		}
		this.field = field;
		this.value = value;
		this.logic = logic;
	}
	/**
	 * for like
	 * @param field
	 * @param expStr
	 * @param value
	 * @param logic
	 */
	public ParamItem(Object field,String expStr, Object value, String logic) {
		isEmptyValue=ZBeanUtil.isEmptyValue(value);
		//isEmptyValue=isNullIfBoolean( value,  logic);
		if(!ZStrUtil.trimToEmptyIfStr(field).toString().contains(".")){
			this.field = RowMap.queryAsAlias+"."+field;
		}else{
			this.field = field;
		}
		
		if(logic.equalsIgnoreCase("like")&&!isEmptyValue){
			this.value = expStr.replace("??", (CharSequence) value);
		}
		this.logic = logic;
	}
	public ParamItem(Object field, Object value, String logic,String type) {
		isEmptyValue=ZBeanUtil.isEmptyValue(value);
		//isEmptyValue=isNullIfBoolean( value,  logic);
		if(!ZStrUtil.trimToEmptyIfStr(field).toString().contains(".")){
			this.field = RowMap.queryAsAlias+"."+field;
		}else{
			this.field = field;
		}
		this.value = value;
		this.logic = logic;
		if("Long".equals(type)){
			this.value = ZBeanUtil.parseLong(value);
		}else if("Integer".equals(type)){
			this.value = ZBeanUtil.parseInteger(value);
		}else if("Boolean".equals(type)){
			this.value = ZBeanUtil.parseBoolean(value);
		}
	}
	
	public Object getField() {
		return field;
	}

	public void setField(Object field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsEmptyValue() {
		return isEmptyValue;
	}

	public void setIsEmptyValue(Boolean isEmptyValue) {
		this.isEmptyValue = isEmptyValue;
	}

	public static void main(String[] args) {
		
		System.out.println();
	}

	
}
