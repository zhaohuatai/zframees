package com.zht.common.sys.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.zht.common.rabc.model.RbacUser;


@javax.persistence.Entity()
@javax.persistence.Table(name = "sys_user_detail")
public class UserDetail extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public UserDetail() {}
	public UserDetail(Long id) {
		this.setId(id);
	}
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.validation.constraints.NotNull 	@org.hibernate.validator.constraints.NotBlank	@javax.persistence.Column(name = "userName",unique = false,nullable = false,length = 40)
	private java.lang.String userName;
	
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.validation.constraints.NotNull 	@org.hibernate.validator.constraints.NotBlank	@javax.persistence.Column(name = "user_num",unique = true,nullable = false,length = 40)
	private java.lang.String userNum;
	
	@org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")	@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)	@javax.persistence.Column(name = "birth")
	private java.util.Date birth;
	
	@javax.persistence.Column(name = "sex",unique = false,nullable = true)
	private java.lang.Short sex;
	
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.persistence.Column(name = "email",unique = false,nullable = true,length = 40)
	private java.lang.String email;
	
	@org.hibernate.validator.constraints.Length(min=0,max=30)	@javax.persistence.Column(name = "phone",unique = false,nullable = true,length = 30)
	private java.lang.String phone;
	
	@org.hibernate.validator.constraints.Length(min=0,max=18)	@javax.persistence.Column(name = "per_id_num",unique = false,nullable = true,length = 18)
	private java.lang.String perIdNum;
	
	@org.hibernate.validator.constraints.Length(min=0,max=20)	@javax.persistence.Column(name = "qq_num",unique = false,nullable = true,length = 20)
	private java.lang.String qqNum;
	
	@org.hibernate.validator.constraints.Length(min=0,max=30)	@javax.persistence.Column(name = "weixin_Num",unique = false,nullable = true,length = 30)
	private java.lang.String weixinNum;
	
	/**
	 * 共享主键，
	 */
    @OneToOne(mappedBy="userDetail",fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
	 private RbacUser rbacUser;
	
	//<-------------------------------------------->
	public void setUserName(java.lang.String userName){
		this.userName=userName;
	}
	public java.lang.String getUserName(){
		return this.userName;
	}
	
	public void setUserNum(java.lang.String userNum){
		this.userNum=userNum;
	}
	public java.lang.String getUserNum(){
		return this.userNum;
	}
	
	public void setBirth(java.util.Date birth){
		this.birth=birth;
	}
	public java.util.Date getBirth(){
		return this.birth;
	}
	
	public void setSex(java.lang.Short sex){
		this.sex=sex;
	}
	public java.lang.Short getSex(){
		return this.sex;
	}
	
	public void setEmail(java.lang.String email){
		this.email=email;
	}
	public java.lang.String getEmail(){
		return this.email;
	}
	
	public void setPhone(java.lang.String phone){
		this.phone=phone;
	}
	public java.lang.String getPhone(){
		return this.phone;
	}
	
	public void setPerIdNum(java.lang.String perIdNum){
		this.perIdNum=perIdNum;
	}
	public java.lang.String getPerIdNum(){
		return this.perIdNum;
	}
	
	public void setQqNum(java.lang.String qqNum){
		this.qqNum=qqNum;
	}
	public java.lang.String getQqNum(){
		return this.qqNum;
	}
	
	public void setWeixinNum(java.lang.String weixinNum){
		this.weixinNum=weixinNum;
	}
	public java.lang.String getWeixinNum(){
		return this.weixinNum;
	}
	public RbacUser getRbacUser() {
		return rbacUser;
	}
	public void setRbacUser(RbacUser rbacUser) {
		this.rbacUser = rbacUser;
	}
	
	

}
