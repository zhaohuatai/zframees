/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataTable extends ArrayList<DataRow>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataTable() {
		super();
	}

//	public DataTable(List<DataRow> c) {
//		super(c);
//	}
	
	@SuppressWarnings("unchecked")
	public DataTable(List<Map<String,Object>> data) {
		this.clear();
		this.addAll((Collection<? extends DataRow>) data);
	}
	
	public DataRow getDataRow(int index){
		if(index>this.size()||index<0){
			return null;
		}
		Map<String,Object> obj=this.get(index);
		if(obj!=null){
			return new DataRow(obj);
		}
		return null;
	}
	public DataRow findUniqueRow(String columnName,Object value){
		if(this.size()>0){
			for(int i=0;i<this.size();i++){
				DataRow row=this.getDataRow(i);
					Object obj = row.get(columnName);
					if(obj!=null&&obj.equals(value)){
						return row;
					}
				}
		}
		return null;
	}
}
