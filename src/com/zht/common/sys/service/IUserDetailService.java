package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.UserDetail;

public interface IUserDetailService extends IBaseService<UserDetail>{
	
	public void createUserDetail(UserDetail userDetail);
	
	@SuppressWarnings("rawtypes")
	public List<Map>loadUserDetailCombox();
	
	public DataSet loadUserDetailDataSet(ParamObject paramObject);
	
	 public void assignmentUserToDept(String deptIds,Long userId);
	 @Deprecated
	 public void removeUserFromDepartment(Long[] userIds,Long departmentId);
	 @Deprecated
	 public void addUserToDepartment(Long[] userIds,Long departmentId);
	 
	 public List<Long> findUserIdsByDepartmentId(Long departmentId);
	 public DataSet loadUserDetailDataSetIsInDept(ParamObject paramObject,RowMap rowMap,Long departmentId,Boolean isIn);
	
	 public void assignPositionUser(String positonIds,Long defaltPositonId,Long userId);
	 
	 
	 public void  processUserPositionChangeWhenNoifyed(Long userId);
}