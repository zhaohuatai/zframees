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
* @ClassName :RbacUserPosition     
* @Description :   
* @createTime :2015年4月3日  下午4:14:29   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "sys_position_user")
public class PositionUserDetail  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public PositionUserDetail() {}
	
	public PositionUserDetail(Long id) {
		this.setId(id);
	}
	public PositionUserDetail(UserDetail userDetail,Position position) {
		this.setUserDetail(userDetail);
		this.setPosition(position);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
	private UserDetail userDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="position_id")
	private Position position;
	
	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	
}
