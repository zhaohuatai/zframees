package org.zht.framework.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zht.framework.spring.DataSourceContextHolder;

public class RequestThreadLocal {
	
	private static final ThreadLocal<CurrentReqestInfo> contextHolder = new ThreadLocal<CurrentReqestInfo>();
	protected final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

	public static void setDataSourceName(CurrentReqestInfo currentReqestInfo) {
		contextHolder.set(currentReqestInfo);
	}

	public static CurrentReqestInfo getCurrentReqestInfo() {
		return contextHolder.get();
	}

	public static void clearCurrentReqestInfo() {
		contextHolder.remove();
	}
}
