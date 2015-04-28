package com.zht.common.dauth.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.zht.framework.zhtdao.identity.PKBaseEntity;
import com.zht.common.rabc.model.RbacUser;
@Entity()
@Table(name = "rbac_dataPrivilege_user_reject")
public class DataPrivilegeUserReject extends PKBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataPrivilegeUserReject() {}
	public DataPrivilegeUserReject(Long id) {
		this.setId(id);
	}
	public DataPrivilegeUserReject(RbacUser rbacUser,RbacDataPrivilege rbacDataPrivilege) {
		super();
		this.rbacDataPrivilege = rbacDataPrivilege;
		this.rbacUser = rbacUser;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_dataPrivilege_id")
	private RbacDataPrivilege rbacDataPrivilege;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rbac_user_id")
	private RbacUser rbacUser;

	public RbacDataPrivilege getRbacDataPrivilege() {
		return rbacDataPrivilege;
	}

	public void setRbacDataPrivilege(RbacDataPrivilege rbacDataPrivilege) {
		this.rbacDataPrivilege = rbacDataPrivilege;
	}
}
