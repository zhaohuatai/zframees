package com.zht.common.codegen.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zht.framework.util.ZStrUtil;

public class AnnScan {

	public static void main(String[] args) {
//		List<EntityViewModel> list=loadEntityList("com.zht");
//		for(EntityViewModel v :list){
//			System.out.println(v.toString());
//		}
		
		Set<Class<?>> calssSet=scanAllController("com.zht.common.rabc");
		for(Class<?> calzz:calssSet){
			System.out.println("------------------"+calzz.getName()+"--------------------------------------------------");
			//String className=calzz.getName().replace(".", "_");
			Method[] methods=calzz.getDeclaredMethods();
			String clazzRP="";
			if(calzz.isAnnotationPresent(RequestMapping.class)){
				RequestMapping rm=calzz.getAnnotation(RequestMapping.class);
				String[] nameSpaceUrl=rm.value();
				clazzRP=nameSpaceUrl[0];
			}
			for(Method method:methods){
				if(method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping mrp=method.getAnnotation(RequestMapping.class);
					String[] methodUrl=mrp.value();
					System.out.println(clazzRP+methodUrl[0]);
				}
				if(method.isAnnotationPresent(RequiresPermissions.class)){
					Annotation rp=method.getAnnotation(RequiresPermissions.class);
					String[] methodRPerms=((RequiresPermissions) rp).value();
					System.out.println(calzz.getName()+"->"+method.getName()+"||"+methodRPerms[0]);
					
				}
				
				
			}
			
		}
	}
	
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
