/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;
public class ParamObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DESC=" DESC ";
	public static final String ASC=" ASC ";

	private Integer page=1;
	private Integer rows=10;
	
	private String order;//sort field
	private String sort;//direction

	private Boolean isSql;
	private Boolean isOffset;
	private Boolean isNeedCount;
	//<input name="params['userName'].field" value="${params['userName'].value}"/>
	private Map<String,Object> webParams =new HashMap<String,Object>();
	private Map<String,Object> queryParams=new HashMap<String,Object>();
	//@Deprecated
	public ParamObject() {
		initProperty();
		initType(POType.H_O_C);
	}
	public ParamObject(POType initType) {
		initProperty();
		initType(initType);
	}

	public ParamObject(POType initType,Integer page,Integer rows) {
		this.page = page;
		this.rows = rows;
		initProperty();
		initType(initType);
	}
	public ParamObject(POType initType,Integer page,Integer rows, String sort) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		initProperty();
		initType(initType);
	}
	public ParamObject(POType initType,Integer page,Integer rows, String sort, String order) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		this.order = order;
		initProperty();
		initType(initType);
	}

	public  Object getReqParam(String key){
		if(this.getWebParams()==null||key==null||key.length()==0){
			return null;
		}
		Object res=webParams.get(key);
		return ZStrUtil.trimToEmptyIfStr(res);
	}
	
	/**
	 * 本方法校验参数是否是null empty 空集合  如果为空，参数将不参与sql查询
	 * @param key
	 * @param item
	 */
	public void addParamItem(String key, ParamItem item) {
		addParamItem(key,item,false);
	}	
	
	public void addParamItem(String key, ParamItem item,boolean ignorEmpty) {
		if (key != null && item != null) {
			boolean isValueEmpty=item.getIsEmptyValue();
			if(ignorEmpty){
				 queryParams.put(key, item);
			}else{
				if(!isValueEmpty){
					 queryParams.put(key, item);
				}
			}
		}
	}
	public ParamObject addAllowNull(String key, Object value) {
		if(key!=null){
			this.queryParams.put(key, ZStrUtil.trimToNullIfStr(value));
		}
		return this;
	}
	public ParamObject addParam(String key, Object value) {
		if(key!=null&&value!=null){
			value=ZStrUtil.trimToNullIfStr(value);
			if(!ZBeanUtil.isEmptyValue(value)){
				this.queryParams.put(key, value);
			}
		}
		return this;
	}	
	public void initType(POType initType){
		 switch (initType) { 
		 	case S_O_C:  
		 		this.isSql=new Boolean(true);
				this.isOffset=new Boolean(true);
				this.isNeedCount=new Boolean(true);
	            break; 
		 	case S_O_NC:  
		 		this.isSql=new Boolean(true);
				this.isOffset=new Boolean(true);
				this.isNeedCount=new Boolean(false);
	            break;
		 	case S_NO_C:  
		 		this.isSql=new Boolean(true);
				this.isOffset=new Boolean(false);
				this.isNeedCount=new Boolean(true);
	            break;
		 	case S_NO_NC:  
		 		this.isSql=new Boolean(true);
				this.isOffset=new Boolean(false);
				this.isNeedCount=new Boolean(false);
	            break;
		 	case H_O_C:  
		 		this.isSql=new Boolean(false);
				this.isOffset=new Boolean(true);
				this.isNeedCount=new Boolean(true);
	            break;
		 	case H_O_NC:  
		 		this.isSql=new Boolean(false);
				this.isOffset=new Boolean(true);
				this.isNeedCount=new Boolean(false);
	            break;
		 	case H_NO_C:  
		 		this.isSql=new Boolean(false);
				this.isOffset=new Boolean(false);
				this.isNeedCount=new Boolean(true);
	            break;
		 	case H_NO_NC:  
		 		this.isSql=new Boolean(false);
				this.isOffset=new Boolean(false);
				this.isNeedCount=new Boolean(false);
	            break;
	        default:
	            this.isSql=new Boolean(false);
				this.isOffset=new Boolean(true);
				this.isNeedCount=new Boolean(true);
		 }
	}
	private void  initProperty(){
		if(queryParams==null){
			queryParams=new HashMap<String,Object>();
		}
		queryParams.clear();
		if(webParams==null){
			webParams=new HashMap<String,Object>();
		}
		webParams.clear();
		
		if(page==null){
			this.setPage(1);
		}
		if(rows==null){
			this.setRows(10);
		}
	}
	
	public static void setModelAttribute(Model model, ParamObject paramObject) {
		if(model==null||paramObject==null){
			return;
		}
		model.addAttribute("webParams",paramObject.getWebParams()).addAttribute("paramObject",paramObject);
	}

	
	
	
	public Map<String, Object> getWebParams() {
		return webParams;
	}

	public void setWebParams(Map<String, Object> webParams) {
		this.webParams = webParams;
	}
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Boolean getIsOffset() {
		if(isOffset==null){
			return true;
		}
		return isOffset;
	}

	public void setIsOffset(Boolean isOffset) {
		this.isOffset = isOffset;
	}

	public Boolean getIsSql() {
		return isSql;
	}

	public void setIsSql(Boolean isSql) {
		this.isSql = isSql;
	}

	public Boolean getIsNeedCount() {
		return isNeedCount;
	}

	public void setIsNeedCount(Boolean isNeedCount) {
		this.isNeedCount = isNeedCount;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}


	
}
