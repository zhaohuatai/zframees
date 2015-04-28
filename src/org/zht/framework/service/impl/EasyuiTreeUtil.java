package org.zht.framework.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EasyuiTreeUtil {

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
