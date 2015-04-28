/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

/**
 * 
* @ClassName :RbacModule     
* @Description :   
* @createTime :2015年4月3日  下午4:16:07   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_module")
public class RbacModule extends PKBaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(name="display",unique=true, nullable=false,length=32)
	private String display;
	/**
	 * 描述、备注
	 */
	@Column(name="remark",unique=false, nullable=true,length=30)
    private String remark;
	/**
	 * 显示图标
	 */
	@Column(name="icon",nullable=true,length=20)
    private String icon;
	/**
	 * 是否被禁用
	 */
	@Column(name = "enabled",columnDefinition="BOOLEAN default 1",  nullable = false)
	private Boolean enabled;
	/**
	 * 显示顺序
	 */
	@Column(name="dis_index", nullable=false)
    private Integer disIndex;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="rbacModule")
	@javax.persistence.OrderBy("disIndex")
	private Set<RbacMenu> rbacMenus = new HashSet<RbacMenu>(0);
	
	public RbacModule() {
		super();
	}
	public RbacModule(Long id) {
		super();
		this.id=id;
	}
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}


	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDisIndex() {
		return disIndex;
	}
	public void setDisIndex(Integer disIndex) {
		this.disIndex = disIndex;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Set<RbacMenu> getRbacMenus() {
		return rbacMenus;
	}
//	@JSONField(serialize=false)
	public void setRbacMenus(Set<RbacMenu> rbacMenus) {
		this.rbacMenus = rbacMenus;
	}

}
