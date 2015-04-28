/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DataRowTree extends HashMap<String, Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataRowTree() {
		super();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataRowTree( Map map) {
		if(map!=null){
			this.clear();this.putAll(map);
		}
	}
	private List<DataRowTree> children=new ArrayList<DataRowTree>();

	public void add(DataRowTree node){
		if(children==null) children=new ArrayList<DataRowTree>();
		children.add(node);
	}
	
	public void remove(DataRowTree node){
		if(children!=null){
			children.remove(node);
			if(children.size()==0){
				children=null;
			}
		}
	}
	
	public void removeAll(){
		if(children!=null){
			children.clear();
			if(children.size()==0){
				children=null;
			}
		}
	}
	public List<DataRowTree> getChildren() {
		return children;
	}
	public void setChildren(List<DataRowTree> children) {
		this.children = children;
	}
	
	
}
