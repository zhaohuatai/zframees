package org.zht.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.zht.framework.service.impl.EasyuiTreeUtil;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.zht.common.rabc.model.RbacRole;
import com.zht.common.sys.model.Position;

public class ZPropertyUtil {
	/***
	* 获得一个类的方法的值
	* @param invoke 执行对象
	* @param methodName 方法名称
	* @return
	*/
	public static Object getInvokeValue(Object entity, String methodName) {
		Object obj = null;
		Method m = null;
		for (Class<?> clazz = entity.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				try {
					m = clazz.getDeclaredMethod(methodName);
				} catch (Exception e) {
					//e.printStackTrace();
				}
		}
		if (m != null) {
			try {
				obj = m.invoke(entity);
			} catch (IllegalArgumentException e) {
				LogUtil.genErrorLog(EasyuiTreeUtil.class, "getInvokeValue", "IllegalArgumentException:-972855736L", e);
				return -972855736L;
			} catch (IllegalAccessException e) {
				LogUtil.genErrorLog(EasyuiTreeUtil.class, "getInvokeValue", "IllegalAccessException:-972855736L", e);
				return -972855736L;
			} catch (InvocationTargetException e) {
				LogUtil.genErrorLog(EasyuiTreeUtil.class, "getInvokeValue", "InvocationTargetException:-972855736L", e);
				return -972855736L;
			} catch (Exception e) {
				LogUtil.genErrorLog(EasyuiTreeUtil.class, "getInvokeValue", "Exception:-972855736L", e);
				return -972855736L;
			}
		}
		return obj;
	}
	

	public static void main(String[] args){}
}
