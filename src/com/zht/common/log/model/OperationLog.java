package com.zht.common.log.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.zht.framework.annos.CurrentTimeStamp;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

public class OperationLog extends PKBaseEntity{
	/**
	 * 
	 */
	@Transient//
	private static final long serialVersionUID = 1L;
	
	@Transient//成功访问的
	public static final String TYPE_OPERATION = "1";
	@Transient//出现异常的
	public static final String TYPE_EXCEPTION = "2";

	@Column(name="userName", nullable=false, length=40)
	private String userName;
	
	@Column(name="jsessionId", length=40)
	private String jsessionId ;
	
	@Column(name="remoteIpAddr")
	private Long remoteIpAddr ;
	
	@Column(name="description", length=100)
	private String accept;
	
	@Column(name="userAgent", length=255)
	private String userAgent ;//操作系统（包括版本号）浏览器（包括版本号）和用户个人偏好的代码。
	
	@Column(name="requestURI", length=255)
	private String requestURI ;
	
	@Column(name="methed", length=20)
	private String methed;
	
	@Column(name="params", length=256)
	private String params;
	
	@Column(name="referer", length=256)
    private String referer;
	
	@Column(name="exception", length=256)
	private String exception; 	// 异常信息
	
	@Column(name="type", length=1)
	private String type; 		// 日志类型
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="operateTime")
	@CurrentTimeStamp
	private Date operateTime;	// 日志创建时间
	
	public OperationLog() {}
	/**
	 * 用户操作log 构造函数
	 * @param userName
	 * @param jsessionId
	 * @param remoteIpAddr
	 * @param accept
	 * @param userAgent
	 * @param requestURI
	 * @param methed
	 * @param params
	 * @param headers
	 * @param referer
	 * @param type
	 */
	public OperationLog(String userName, String jsessionId,
			Long remoteIpAddr, String accept, String userAgent,
			String requestURI, String methed, String params, 
			String referer,  String type) {
		this.userName = userName;
		this.jsessionId = jsessionId;
		this.remoteIpAddr = remoteIpAddr;
		this.accept = accept;
		this.userAgent = userAgent;
		this.requestURI = requestURI;
		this.methed = methed;
		if(params!=null&&params.length()>255){
			this.params = params.substring(0, 255);
		}else{
			this.params = params;
		}
		
		this.referer = referer;
		this.type = type;
	}
	/**
	 * ERROR log构造函数
	 * @param userName
	 * @param jsessionId
	 * @param requestURI
	 * @param methed
	 * @param params
	 * @param exception
	 * @param type
	 */
	public OperationLog(String userName,String exception,String type) {
		this.userName = userName;
		this.exception=exception;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJsessionId() {
		return jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	public Long getRemoteIpAddr() {
		return remoteIpAddr;
	}

	public void setRemoteIpAddr(Long remoteIpAddr) {
		this.remoteIpAddr = remoteIpAddr;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getMethed() {
		return methed;
	}

	public void setMethed(String methed) {
		this.methed = methed;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}


	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
