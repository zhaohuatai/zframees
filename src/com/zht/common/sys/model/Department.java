package com.zht.common.sys.model;
@org.zht.framework.annos.TreeConstruct()
@javax.persistence.Entity()
@javax.persistence.Table(name = "sys_department")
public class Department extends org.zht.framework.zhtdao.identity.PKBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public Department() {}
	public Department(Long id) {
		this.setId(id);
	}
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.validation.constraints.NotNull 	@org.hibernate.validator.constraints.NotBlank	@javax.persistence.Column(name = "name",unique = false,nullable = false,length = 40)
	private java.lang.String name;
	
	@org.hibernate.validator.constraints.Length(min=0,max=60)	@javax.persistence.Column(name = "remark",unique = false,nullable = true,length = 60)
	private java.lang.String remark;
	
	@org.zht.framework.annos.CurrentTimeStamp	@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)	@javax.persistence.Column(name = "modify_time")
	private java.util.Date modifyTime;
	
	@javax.persistence.ManyToOne(fetch=javax.persistence.FetchType.LAZY)	@javax.persistence.JoinColumn(name="parent_department_id")	@org.zht.framework.annos.TreeParentFied()
	private com.zht.common.sys.model.Department parentDepartment;
	
	@org.hibernate.validator.constraints.Length(min=0,max=40)	@javax.persistence.Column(name = "creaater",unique = false,nullable = true,length = 40)
	private java.lang.String creater;
	
	@javax.persistence.OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL, mappedBy="parentDepartment")
	private java.util.Set<com.zht.common.sys.model.Department> childDepartments;
	
	
	private java.util.Date dfs;
	
	//<-------------------------------------------->
	public void setName(java.lang.String name){
		this.name=name;
	}
	public java.lang.String getName(){
		return this.name;
	}
	
	public void setRemark(java.lang.String remark){
		this.remark=remark;
	}
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime=modifyTime;
	}
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
	
	public void setParentDepartment(com.zht.common.sys.model.Department parentDepartment){
		this.parentDepartment=parentDepartment;
	}
	public com.zht.common.sys.model.Department getParentDepartment(){
		return this.parentDepartment;
	}
	
	public void setCreater(java.lang.String creater){
		this.creater=creater;
	}
	public java.lang.String getCreater(){
		return this.creater;
	}
	
	public void setChildDepartments(java.util.Set<com.zht.common.sys.model.Department> childDepartments){
		this.childDepartments=childDepartments;
	}
	public java.util.Set<com.zht.common.sys.model.Department> getChildDepartments(){
		return this.childDepartments;
	}
	
	public void setDfs(java.util.Date dfs){
		this.dfs=dfs;
	}
	public java.util.Date getDfs(){
		return this.dfs;
	}
	
	

}
