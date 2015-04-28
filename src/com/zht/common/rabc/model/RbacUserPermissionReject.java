package com.zht.common.rabc.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zht.framework.zhtdao.identity.PKBaseEntity;
/**
 * 必须继承角色，
 * 配置在此处不具有该权限
 * @author z_huatai
 *
 */
@Entity()
@Table(name = "rbac_user_permission_reject")
public class RbacUserPermissionReject  extends PKBaseEntity{
	private static final long serialVersionUID = 1L;

	public RbacUserPermissionReject() {}
	
	public RbacUserPermissionReject(Long id) {
		this.setId(id);
	}
	public RbacUserPermissionReject(RbacUser rbacUser,RbacPermission rbacPermission) {
		this.setRbacUser(rbacUser);
		this.setRbacPermission(rbacPermission);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_rbacPermission_id")
	private RbacPermission rbacPermission;
	
	public RbacUser getRbacUser() {
		return rbacUser;
	}

	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}

	public RbacPermission getRbacPermission() {
		return rbacPermission;
	}

	public void setRbacPermission(RbacPermission rbacPermission) {
		this.rbacPermission = rbacPermission;
	}

	
}
