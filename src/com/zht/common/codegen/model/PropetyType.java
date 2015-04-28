/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.model;

import java.util.LinkedHashMap;
import java.util.Map;

//	java.io.Serializable	serializable	VARBINARY、BLOB
//	java.sql.Clob	clob	CLOB
//	java.sql.Blob	blob	BLOB
//	java.lang.Class	class	VARCHAR
//	java.util.Locale	locale	VARCHAR
//	java.util.TimeZone	timezone	VARCHAR
//	java.util.Currency
public class PropetyType {
	
	public static Map<String,String> poMap=new LinkedHashMap<>();
	
	public void init(){
		poMap.put("String",  "java.lang.String");
		poMap.put("Long",    "java.lang.Long");
		poMap.put("Integer", "java.lang.Integer");
		poMap.put("Float",   "java.lang.Double");
		poMap.put("Double",  "java.lang.String");
		poMap.put("Short",   "java.lang.Short");
		poMap.put("Byte",    "java.lang.Byte");
		poMap.put("Boolean", "java.lang.Boolean");
		poMap.put("Character", "java.lang.Character");
		poMap.put("Date",     "java.sql.Date");
		poMap.put("Time",     "java.sql.Time");
		poMap.put("Timestamp","java.sql.Timestamp");
		poMap.put("byte[]",   "java.util.byte[]");
		poMap.put("BigDecimal", "java.math.BigDecimal");
	}

}
