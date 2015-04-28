package com.zht.common.dauth.model;

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

import org.hibernate.validator.constraints.NotBlank;
import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.zht.common.rabc.model.RbacPermission;
@Entity()
@Table(name = "rbac_dataPrivilege")
public class RbacDataPrivilege extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private String actionMenthedFullName;
//	private String requestURI;
//	private String permissionCode;
	
	
	@NotBlank
	@Column(name = "name", length = 30, nullable = false,unique=false)
	private String name;
	
	@NotBlank
	@Column(name = "code", length = 60, nullable = false,unique=true)
	private String code ;
	
	@Column(name = "enabled",nullable = false )
	private Boolean enabled;//可用
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_permission_id")
	private RbacPermission rbacPermission;
	
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="dataPrivilege")
	private Set<RbacDataAccessExp> rbacDataAccessExps = new HashSet<RbacDataAccessExp>(0);


	public RbacPermission getRbacPermission() {
		return rbacPermission;
	}

	public void setRbacPermission(RbacPermission rbacPermission) {
		this.rbacPermission = rbacPermission;
	}

	public Set<RbacDataAccessExp> getRbacDataAccessExps() {
		return rbacDataAccessExps;
	}

	public void setRbacDataAccessExps(Set<RbacDataAccessExp> rbacDataAccessExps) {
		this.rbacDataAccessExps = rbacDataAccessExps;
	}
	


}
