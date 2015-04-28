/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DataSet {

	private Long total;
	private List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> footer=new ArrayList<Map<String,Object>>();//合计列
	private String simpleFooter;//合计列
	
//	public DataSet() {
//	}
	public DataSet( List<Map<String,Object>>  rows) {
		this.rows = rows;
	}
	public DataSet(Long total, List<Map<String,Object>>  rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public DataSet(Long total, List<Map<String,Object>>  rows,List<Map<String,Object>>  footer) {
		this.total = total;
		this.rows = rows;
		this.footer = footer;
	}
//	public Map<String,Object> findUniqueRow(String columnName,Object value){
//		if(rows!=null&&rows.size()>0){
//			for(Map<String,Object> map:rows){
//				Object obj = map.get(columnName);
//				if(obj!=null&&obj.equals(value)){
//					return map;
//				}
//			}
//		}
//		return null;
//	}
//	public DataTable getDataTable(){
//		if(rows!=null&&rows.size()>0){
//			return new DataTable(rows);
//		}
//		return null;
//	}
	
	/**
	 * 查询只有一条记录的时候
	 * @param key
	 * @return
	 */
	public Object getDataIfOneRecord(String key){
		
		if(key==null||this.rows==null|rows.size()==0){
			return null;
		}
		return rows.get(0).get(key);
	}
	public Map<String,Object> findUniqueRow(String columnName,Object value){
		if(rows!=null&&rows.size()>0){
			for(Map<String,Object> map:rows){
				Object obj = map.get(columnName);
				if(obj!=null&&obj.equals(value)){
					return map;
				}
			}
		}
		return null;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<Map<String,Object>>  getRows() {
		return rows;
	}
	public void setRows(List<Map<String,Object>>  rows) {
		this.rows = rows;
	}
	public List<Map<String,Object>>  getFooter() {
		return footer;
	}
	public void setFooter(List<Map<String,Object>>  footer) {
		this.footer = footer;
	}

	public String getSimpleFooter() {
		return simpleFooter;
	}

	public void setSimpleFooter(String simpleFooter) {
		this.simpleFooter = simpleFooter;
	}
	


}
