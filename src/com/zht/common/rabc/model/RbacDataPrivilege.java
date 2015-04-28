package com.zht.common.rabc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.zht.framework.zhtdao.identity.PKBaseEntity;

import com.zht.common.sys.model.Position;

public class RbacDataPrivilege extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String actionMenthedFullName;
	private String requestURI;
	private String permissionCode;
	
	private RbacPermission rbacPermission;
	
	private RbacRole rbacRole;
	
	private Position position;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="dataPrivilege")
	private Set<RbacDataAccessExp> rbacDataAccessExps = new HashSet<RbacDataAccessExp>(0);
	
	
	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public RbacRole getRbacRole() {
		return rbacRole;
	}

	public void setRbacRole(RbacRole rbacRole) {
		this.rbacRole = rbacRole;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getActionMenthedFullName() {
		return actionMenthedFullName;
	}

	public void setActionMenthedFullName(String actionMenthedFullName) {
		this.actionMenthedFullName = actionMenthedFullName;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

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
