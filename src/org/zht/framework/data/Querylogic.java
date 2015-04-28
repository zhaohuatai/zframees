/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.data;

import java.util.Arrays;
import java.util.List;

public class Querylogic {
	public static final String L_equal="=";
	public static final String L_notEqual="<>";		
	public static final String L_bigerThan=">";	
	public static final String L_bigerThanEq=">=";	
	public static final String L_lessThan ="<";	
	public static final String L_lessThanEq="<=";	
	public static final String L_like="like";	
	public static final String L_notLike="not like";	
	public static final String L_In="in";	
	public static final String L_NotIn="not in";	
	public static final String L_isNull="is null";	
	public static final String L_isNotNull="is not null";	
	
	
	public static final String T_Long="Long";	
	public static final String T_Integer="Integer";	
	public static final String T_Boolean="Boolean";	
	
	public static final List<String> likeKeyWordsList=Arrays.asList(new String[]{
			"%%","%","_","__",
			"[]","%[]","[]%","%[]%","[^]","%[^]","[^]%","%[^]%","[!]","%[!]","[!]%","%[!]%",
			"[]","_[]","[]_","_[]_","[^]","_[^]","[^]_","_[^]_","[!]","_[!]","[!]_","_[!]_",
			
			"'%%'","'%","'_'","'__'",
			"'[]'","'%[]'","'[]%'","'%[]%'","'[^]'","'%[^]'","'[^]%'","'%[^]%'","'[!]'","'%[!]'","'[!]%'","'%[!]%'",
			"'[]'","'_[]'","'[]_'","'_[]_'","'[^]'","'_[^]'","'[^]_'","'_[^]_'","'[!]'","'_[!]'","'[!]_'","'_[!]_'"
			});
}
