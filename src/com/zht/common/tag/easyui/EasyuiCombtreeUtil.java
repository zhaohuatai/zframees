/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.tag.easyui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
* @ClassName :DepartmentUtil     
* @Description :   
* @createTime :2015年4月3日  下午4:18:54   
* @author ：zhaohuatai   
* @version :1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EasyuiCombtreeUtil {
	
	public static  List findChildList(List dataList,Map root){
		if(root==null||dataList==null){
			return null;
		}
		List childList=new ArrayList();
		for(Object obj :dataList){
			Object pid=((Map)obj).get("_parentId");
			if(pid!=null&&pid.equals(root.get("id"))){
				childList.add(obj);
			}
		}
		return childList;
	}
	public static void traverse(Map root,List AllDataList){
		if(root==null){
			return;
		}
		List childList=findChildList(AllDataList,root);
		if(childList!=null){
			root.put("children", childList);
			for(Object child:childList){
				traverse((Map)child,AllDataList);
			}
		}
	}
}
