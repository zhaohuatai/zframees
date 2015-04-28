/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.sys.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.zht.common.rabc.model.RbacUser;
/**
 * 
* @ClassName :DepartmentUser     
* @Description :   
* @createTime :2015年4月3日  下午4:13:49   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "sys_department_user")
public class DepartmentUser  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public DepartmentUser() {
		
	}
	public DepartmentUser(Long id) {
		this.setId(id);
	}
	
	public DepartmentUser(RbacUser rbacUser, Department department) {
		this.rbacUser = rbacUser;
		this.department = department;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sys_department_id")
	private Department department;

	public RbacUser getRbacUser() {
		return rbacUser;
	}

	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
