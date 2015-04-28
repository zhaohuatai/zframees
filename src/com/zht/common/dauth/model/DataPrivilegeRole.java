package com.zht.common.dauth.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.zht.common.rabc.model.RbacRole;
@Entity()
@Table(name = "rbac_dataPrivilege_role")
public class DataPrivilegeRole extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataPrivilegeRole() {}
	public DataPrivilegeRole(Long id) {
		this.setId(id);
	}
	public DataPrivilegeRole(RbacRole rbacRole,RbacDataPrivilege rbacDataPrivilege) {
		super();
		this.rbacDataPrivilege = rbacDataPrivilege;
		this.rbacRole = rbacRole;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_dataPrivilege_id")
	private RbacDataPrivilege rbacDataPrivilege;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_role_id")
	private RbacRole rbacRole;

	public RbacDataPrivilege getRbacDataPrivilege() {
		return rbacDataPrivilege;
	}

	public void setRbacDataPrivilege(RbacDataPrivilege rbacDataPrivilege) {
		this.rbacDataPrivilege = rbacDataPrivilege;
	}

	public RbacRole getRbacRole() {
		return rbacRole;
	}

	public void setRbacRole(RbacRole rbacRole) {
		this.rbacRole = rbacRole;
	}
	
	

}
