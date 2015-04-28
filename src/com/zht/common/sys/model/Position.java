package com.zht.common.sys.model;
@javax.persistence.Entity()
@javax.persistence.Table(name = "sys_position")
public class Position extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public Position() {}
	public Position(Long id) {
		this.setId(id);
	}
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.persistence.Column(name = "name",unique = false,nullable = true,length = 40)
	private java.lang.String name;
	
	@org.zht.framework.annos.CurrentTimeStamp	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)	@javax.persistence.Column(name = "modify_time")
	private java.util.Date modifyTime;
	
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.persistence.Column(name = "creater",unique = false,nullable = true,length = 40)
	private java.lang.String creater;
	
	@org.hibernate.validator.constraints.Length(min=0,max=60)	@javax.persistence.Column(name = "remark",unique = false,nullable = true,length = 60)
	private java.lang.String remark;
	
	@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)	@javax.persistence.JoinColumn(name="depatment_id")
	private com.zht.common.sys.model.Department department;
	
	//<-------------------------------------------->
	public void setName(java.lang.String name){
		this.name=name;
	}
	public java.lang.String getName(){
		return this.name;
	}
	
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime=modifyTime;
	}
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
	
	public void setCreater(java.lang.String creater){
		this.creater=creater;
	}
	public java.lang.String getCreater(){
		return this.creater;
	}
	
	public void setRemark(java.lang.String remark){
		this.remark=remark;
	}
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	public void setDepartment(com.zht.common.sys.model.Department department){
		this.department=department;
	}
	public com.zht.common.sys.model.Department getDepartment(){
		return this.department;
	}
	
	

}
