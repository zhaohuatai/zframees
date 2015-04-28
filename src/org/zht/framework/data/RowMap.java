/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Transient;

import org.zht.framework.util.ZStrUtil;

import com.zht.common.rabc.model.RbacRole;

public class RowMap extends ConcurrentHashMap<String,String>{
	
	public static final String queryAsAlias="ZHTCORE$ZHT";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final List<String> basecTypeList=Arrays.asList(new String[]{
			"String","Long","Integer","Short","Byte","Character","Boolean",
			"String","long","int","short","byte","Character","boolean",
			"double","float","Date","Calendar","Time","Timestamp","byte[]"});
	/**
	 * 
	 * @param key: idsplay
	 * @param value: field
	 */
	public RowMap(String...args){
		if(args==null||args.length==0||(args.length%2!=0)){
			return;
		}
		for(int i=0;i<args.length;i+=2){
			String key=args[i];
			String value=args[i+1];
			this.put(key, value);
		}
	}
	public static void main(String[] as){
		RowMap rowMap =new RowMap(RbacRole.class);
		rowMap.addColMap("userId", "rbacRolePermission.id");
		System.out.println(rowMap.getMapStr());
	
	}
	public RowMap(Class<?> clazz){
		this(clazz,new String());
	}
	public RowMap(Class<?> clazz,String...ignorProperty){
		if(clazz==null){
			return;
		}
		Field[] fields = clazz.getDeclaredFields();
		if(fields!=null&&fields.length>0){
			for(Field field:fields){
				String fieldName=field.getName();
				if("serialVersionUID".equals(fieldName)){
					continue;
				}
				if(field.isAnnotationPresent(Transient.class)){
					continue;
				}
				String className = field.getType().getSimpleName();
				if(basecTypeList.contains(className)){
					if(ignorProperty!=null&&Arrays.asList(ignorProperty).contains(field.getName())){
						continue;
					}else{
						this.put(""+fieldName, ""+fieldName);
					}
				}
			}
		}
		this.put("id", "id");
	}
	/**
	 * 
	 * @param key :idsplay
	 * @param value: field
	 * @return
	 */
	public RowMap addColMap(String key,String value){
		if(key!=null&&key.length()>0&&value!=null&&value.length()>0){
			this.put(key, value);
		}
		return this;
	}
	public RowMap updateColMap(String key,String value){
		if(key!=null&&key.length()>0&&value!=null&&value.length()>0){
			this.put(key, value);
		}
		return this;
	}
	public RowMap removeColMap(String key){
		if(key!=null&&key.length()>0){
			this.remove(key);
		}
		return this;
	}
	
	public String getMapStr(){
		if(this.size()==0){
			return null;
		}
		String str="";
		for (Map.Entry<String, String> entry : this.entrySet()) {
			String key=entry.getKey();
			String value=entry.getValue();
			if(key!=null&&key.length()>0&&value!=null&&value.length()>0){
				if(value.contains(".")){
					str+=" "+ value +" as " +key+", ";
				}else{
					str+=" "+ RowMap.queryAsAlias+"."+value +" as " +key+", ";
				}
				
			}
		}
		if(str.length()>0){
			str=str.substring(0,str.lastIndexOf(","));
		}
		return str;
	}
	
	public  String getLeftJoinStr(){
		List<String> leftJounList=new ArrayList<String>();
		if(this.size()==0){
			return null;
		}
		String leftJoin=" left join ";
		String str="";
		for (Map.Entry<String, String> entry : this.entrySet()) {
			String key=entry.getKey();
			String value=entry.getValue();
			if(key!=null&&key.length()>0&&value!=null&&value.length()>0){
				if(value.contains(".")){
					String lj=ZStrUtil.substringBefore(value, ".");
					if(!leftJounList.contains(lj)){
						str+=leftJoin+" "+ RowMap.queryAsAlias+"."+lj+" as " +lj+",";
						leftJounList.add(lj);
					}
					
				}
			}
		}
		if(str.length()>0){
			str=str.substring(0,str.lastIndexOf(","));
		}
		return str;
	}
	

	
//	public static void main(String[] as){
//		RowMap RowMap =new RowMap();
//		RowMap.addColMap("k1", "v1").addColMap("k2","v2").addColMap("k3", "v3");
//		for (Map.Entry<String, String> entry : RowMap.entrySet()){
//			String key=entry.getKey();
//			String value=entry.getValue();
//			String str=" "+ value +" as " +key+" ";
//			System.out.println(str);
//		}
//		System.out.println(RowMap.getMapStr());
//	}
	
}
