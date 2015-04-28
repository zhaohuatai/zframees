/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro;

import java.io.Serializable;
import java.util.List;
/**
 * 
* @ClassName :ShiroSessionUser     
* @Description :   
* @createTime :2015年4月3日  下午4:17:50   
* @author ：zhaohuatai   
* @version :1.0
 */
public class ShiroSessionUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long personId;
	private String userName;
	private Long currentRoleId;
	private String currentRoleCode;
	private Long currentPositionId;
	
	private List<String> roleCodeList;
	private List<String> depetNameList;

	private Long currentDeptId;
	private Long currentGroupId;
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCurrentRoleId() {
		return currentRoleId;
	}
	public void setCurrentRoleId(Long currentRoleId) {
		this.currentRoleId = currentRoleId;
	}
	public String getCurrentRoleCode() {
		return currentRoleCode;
	}
	public void setCurrentRoleCode(String currentRoleCode) {
		this.currentRoleCode = currentRoleCode;
	}
	public List<String> getRoleCodeList() {
		return roleCodeList;
	}
	public void setRoleCodeList(List<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}
	public List<String> getDepetNameList() {
		return depetNameList;
	}
	public void setDepetNameList(List<String> depetNameList) {
		this.depetNameList = depetNameList;
	}
	public Long getCurrentDeptId() {
		return currentDeptId;
	}
	public void setCurrentDeptId(Long currentDeptId) {
		this.currentDeptId = currentDeptId;
	}
	public Long getCurrentGroupId() {
		return currentGroupId;
	}
	public void setCurrentGroupId(Long currentGroupId) {
		this.currentGroupId = currentGroupId;
	}
	public Long getCurrentPositionId() {
		return currentPositionId;
	}
	public void setCurrentPositionId(Long currentPositionId) {
		this.currentPositionId = currentPositionId;
	}
	
}
