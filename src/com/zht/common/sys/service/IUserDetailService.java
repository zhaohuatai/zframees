package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.UserDetail;
/**
 * 用户详细信息Service接口
* @ClassName :IUserDetailService     
* @Description :   
* @createTime :2015年6月9日  下午4:51:43   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface IUserDetailService extends IBaseService<UserDetail>{
	/**
	 * 创建用户信息，同时创建RbacUser信息
	 * @param userDetail : 用户信息
	 */
	public void createUserDetail(UserDetail userDetail);
	
	/**
	 *  加载map(name,id)形式的用户集合
	 * @return  map(name,id)形式的用户集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Map>loadUserDetailCombox();
	
	/**
	 * 
	 * @param paramObject :查询参数 :<br/>
     * userName : 用户名  类型 String <br/>
     * enabled : 是否启用 (rbacUser.enabled)  类型 Boolean <br/>
     * userNum : 用户编号,   类型 : String <br/>
     * depetId : 所属部门 (DepartmentUserDetail),   类型 : Long
	 * @return
	 */
	public DataSet loadUserDetailDataSet(ParamObject paramObject);
	
	/**
	 *  将用户添加到部门中DepartmentUserDetail
	 * @param deptIds : 部门ids String形式：1,2,3
	 * @param userId :用户ID
	 */
	 public void assignmentUserToDept(String deptIds,Long userId);
	 /**
	  * 将用户从部门移除
	  * @param userIds ：用户IDS
	  * @param departmentId ：部门ID
	  */
	 @Deprecated
	 public void removeUserFromDepartment(Long[] userIds,Long departmentId);
	 /**
	  * 将用户添加到该部门
	  * @param userIds ：用户IDS
	  * @param departmentId ：部门ID
	  */
	 @Deprecated
	 public void addUserToDepartment(Long[] userIds,Long departmentId);
	 /**
	  * 查询部门内直接用户 ，只在本部门ID 内的用户，不包括子部门
	  * @param departmentId ：部门iD
	  * @return
	  */
	 public List<Long> findUserIdsByDepartmentId(Long departmentId);
	 
	 /**
	  * 从UserDetail 加载DataSet，只在本部门ID 内的用户，不包括子部门
	  * @param paramObject  ：UserDetail属性集合
	  * @param rowMap ：UserDetail 属性属性集合
	  * @param departmentId :部门ID
	  * @param isIn ：是否在部门ID 中
	  * @return
	  */
	 public DataSet loadUserDetailDataSetIsInDept(ParamObject paramObject,RowMap rowMap,Long departmentId,Boolean isIn);
	
	 /**
	  * 给用户分配职位
	  * @param positonIds ：职位ID字符串  String形式：1,2,3
	  * @param defaltPositonId ：默认职位ID
	  * @param userId :用户ID
	  */
	 public void assignPositionUser(String positonIds,Long defaltPositonId,Long userId);
	 
	 /**
	  * 当用户部门 或者角色关系发生变化时，同步到用户职位对应关系中
	  * @param userId :用户ID
	  */
	 public void  processUserPositionChangeWhenNoifyed(Long userId);
}