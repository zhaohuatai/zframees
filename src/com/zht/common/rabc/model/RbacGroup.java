/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.zht.framework.annos.CurrentTimeStamp;
import org.zht.framework.annos.TreeConstruct;
import org.zht.framework.annos.TreeParentFied;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

/**
 * 
* @ClassName :RbacGroup     
* @Description :   
* @createTime :2015年4月3日  下午4:13:04   
* @author ：zhaohuatai   
* @version :1.0
 */
@Entity()
@Table(name = "rbac_group")
@TreeConstruct
public class RbacGroup  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacGroup() {}
	
	public RbacGroup(Long id) {
		this.setId(id);
	}
	@NotBlank
	@Column(name = "name", length = 30, nullable = false,unique=true)
	private String name;
	
	@Column(name = "description", length = 60 )
	private String description; //描述
	@NotNull
	@Column(name = "enabled",nullable = false )
	private Boolean enabled;//可用
	
	@CurrentTimeStamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "creater", length = 30 )
	private String creater;
	
	@TreeParentFied
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parent_id" )
    private RbacGroup parentRbacGroup;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="parentRbacGroup")
	private Set<RbacGroup> childRbacGroups = new HashSet<RbacGroup>(0);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public RbacGroup getParentRbacGroup() {
		return parentRbacGroup;
	}

	public void setParentRbacGroup(RbacGroup parentRbacGroup) {
		this.parentRbacGroup = parentRbacGroup;
	}

	public Set<RbacGroup> getChildRbacGroups() {
		return childRbacGroups;
	}

	public void setChildRbacGroups(Set<RbacGroup> childRbacGroups) {
		this.childRbacGroups = childRbacGroups;
	}

}
