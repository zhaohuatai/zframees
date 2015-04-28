package com.zht.common.dauth.thread;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CurrentReqestInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rquestURI;
	private String rquestPermissionCode;
	private String requestMethedFullName;
	
	private Map<String,Integer> daoMethedAcessName;
	
	public CurrentReqestInfo() {
		super();
	}
	
	public CurrentReqestInfo(String questURI, String rquestPermissionCode,
			String requestMethedFullName) {
		super();
		this.rquestURI = questURI;
		this.rquestPermissionCode = rquestPermissionCode;
		this.requestMethedFullName = requestMethedFullName;
	}
	
	public String getRquestURI() {
		return rquestURI;
	}

	public void setRquestURI(String rquestURI) {
		this.rquestURI = rquestURI;
	}

	public String getRquestPermissionCode() {
		return rquestPermissionCode;
	}
	public void setRquestPermissionCode(String rquestPermissionCode) {
		this.rquestPermissionCode = rquestPermissionCode;
	}
	public String getRequestMethedFullName() {
		return requestMethedFullName;
	}
	public void setRequestMethedFullName(String requestMethedFullName) {
		this.requestMethedFullName = requestMethedFullName;
	}

	public Map<String, Integer> getDaoMethedAcessName() {
		return daoMethedAcessName;
	}

	public void setDaoMethedAcessName(Map<String, Integer> daoMethedAcessName) {
		this.daoMethedAcessName = daoMethedAcessName;
	}

	
	
	
}
