package org.zht.framework.interceptors;

import java.io.Serializable;

public class CurrentReqestInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questURI;
	private String rquestPermissionCode;
	private String  requestMethedFullName;
	
	public CurrentReqestInfo() {
		super();
	}
	
	public CurrentReqestInfo(String questURI, String rquestPermissionCode,
			String requestMethedFullName) {
		super();
		this.questURI = questURI;
		this.rquestPermissionCode = rquestPermissionCode;
		this.requestMethedFullName = requestMethedFullName;
	}
	public String getQuestURI() {
		return questURI;
	}
	public void setQuestURI(String questURI) {
		this.questURI = questURI;
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
	
	
}
