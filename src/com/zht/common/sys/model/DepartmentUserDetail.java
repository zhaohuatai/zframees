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
public class DepartmentUserDetail  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public DepartmentUserDetail() {
		
	}
	public DepartmentUserDetail(Long id) {
		this.setId(id);
	}
	
	public DepartmentUserDetail(UserDetail userDetail, Department department) {
		this.userDetail = userDetail;
		this.department = department;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private UserDetail userDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sys_department_id")
	private Department department;


	public UserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

}
