package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.Department;

public interface IDepartmentService extends IBaseService<Department>{
	
	 public DataSet loadDepartmentTreeGrid();
	 
	 @SuppressWarnings("rawtypes")
	 public List<Map> loadDepartmentCombotree() ;
	 
	 public void deleteDepartment(Long id);
	 
	 public List<Long> findDepartmentIdsByUserId(Long userId);
}