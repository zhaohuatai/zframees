package com.zht.common.rabc.util;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.util.LoadPackageClasses;

public class PermissionUtil {
	public static Set<Class<?>> scanAllController(String basePakage){
		if(ZStrUtil.isEmpty(basePakage)){
			return null;
		}
		String[] str=new String[]{basePakage};
		LoadPackageClasses loadPackageClasses=new LoadPackageClasses(str,Controller.class );
		Set<Class<?>> calssSet=new LinkedHashSet<Class<?>>();
		try {
			calssSet=loadPackageClasses.getClassSet();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return calssSet;
	}
}
