/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.web.utils;

import org.zht.framework.data.DataSet;

//import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastjsonUtil {
	
//	private static SerializerFeature[] features=new SerializerFeature[]
//			{SerializerFeature.WriteMapNullValue,
//			SerializerFeature.WriteNullListAsEmpty,
//			SerializerFeature.QuoteFieldNames,
//			SerializerFeature.WriteNullStringAsEmpty};  
	
	/**
	 * 返回的json数据如果为空，则不会运行fastjson的messageConvert
	 * 此处将null 数据转化为 ""
	 * @param obj
	 * @return
	 */
	public static Object convert(Object object){
		if(object==null){
			return new Object();
		}
		return object;
	}
	public static Object convert(DataSet dataSet){
		if(dataSet==null){
			return new DataSet(0L,null);
		}
		return dataSet;
	}
	
}
