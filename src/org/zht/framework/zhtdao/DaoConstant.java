/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao;

import java.util.Arrays;
import java.util.List;

public class DaoConstant {
	public static final List<String> likeKeyWordsList=Arrays.asList(new String[]{
			"%%","%","_","__",
			"[]","%[]","[]%","%[]%","[^]","%[^]","[^]%","%[^]%","[!]","%[!]","[!]%","%[!]%",
			"[]","_[]","[]_","_[]_","[^]","_[^]","[^]_","_[^]_","[!]","_[!]","[!]_","_[!]_",
			
			"'%%'","'%","'_'","'__'",
			"'[]'","'%[]'","'[]%'","'%[]%'","'[^]'","'%[^]'","'[^]%'","'%[^]%'","'[!]'","'%[!]'","'[!]%'","'%[!]%'",
			"'[]'","'_[]'","'[]_'","'_[]_'","'[^]'","'_[^]'","'[^]_'","'_[^]_'","'[!]'","'_[!]'","'[!]_'","'_[!]_'"
			});
}
