package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.Department;
/**
 * 部门Service接口接口
* @ClassName :IDepartmentService     
* @Description :   
* @createTime :2015年6月9日  下午4:15:00   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface IDepartmentService extends IBaseService<Department>{
	
	/**
	 * 加载部门树形TreeGrid，全部加载，不进行分页操作
	 * @return DataSet：部门树形菜单集合
	 */
	 public DataSet loadDepartmentTreeGrid();
	 
	 /**
	  * 加载部门树形菜单，全部加载
	  * 
	  * @return List<Map>：部门树形菜单集合
	  */
	 @SuppressWarnings("rawtypes")
	 public List<Map> loadDepartmentCombotree() ;
	 
	 /**
	  * 查询用户所在部门ID集合
	  * @param userId : 用户ID
	  * @return  List<Long>: 用户所在部门ID集合
	  */
	 public List<Long> findDepartmentIdsByUserId(Long userId);
	 
	 /**
	  * 查询用户所在部门名称集合
	  * @param userId : 用户ID
	  * @return
	  */
	 public List<String> findDepartmentNamesByUserId(Long userId);
	 
	 /**
	  * 查询自己的子孙部门ID集合
	  * @param deptId 部门ID
	  * @return  List<Long>： 自己的子孙部门ID集合
	  */
	 public List<Long> findChildDeptIdsBySelefId(Long deptId);
}