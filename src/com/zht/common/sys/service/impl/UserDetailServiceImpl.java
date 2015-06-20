package com.zht.common.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;

import com.zht.common.event.RoleOrDeptChangedEvent;
import com.zht.common.rabc.model.RbacRole;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.shiro.util.EndecryptUtils;
import com.zht.common.sys.service.IDepartmentService;
import com.zht.common.sys.service.IUserDetailService;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.sys.dao.IUserDetailDao;
import com.zht.common.sys.model.Department;
import com.zht.common.sys.model.DepartmentUserDetail;
import com.zht.common.sys.model.Position;
import com.zht.common.sys.model.PositionUserDetail;
import com.zht.common.sys.model.UserDetail;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserDetailServiceImpl extends BaseServiceImpl<UserDetail> implements IUserDetailService{
 	@Autowired
	private IUserDetailDao userDetailDao;
 	@Autowired
	private IDepartmentService departmentService;
 	@Autowired
	private IRbacRoleService rbacRoleService;
 	

	
	
	@Override
	public DataSet loadUserDetailDataSet(ParamObject paramObject) {
		
		StringBuffer hql=new StringBuffer("");
				 hql.append(" select ");
				 hql.append(" ru.id as id ,");
				 hql.append(" ru.userName as userName,");
				 hql.append(" ud.userNum as userNum,");
				 hql.append(" ru.alias as alias ,");
				 hql.append(" ru.enabled as enabled,");
				 hql.append(" ru.accountNonExpired as accountNonExpired,");
				 hql.append(" ru.credentialsNonExpired as credentialsNonExpired,");
				 hql.append(" ru.accountNonLocked as accountNonLocked,");
				 hql.append(" ro.name as defaultRoleName,");
				 hql.append(" ru.description as description,");
				 hql.append(" ud.birth as birth, ");
				 hql.append(" ud.sex as sex, ");
				 hql.append(" ud.email as email, ");
				 hql.append(" ud.phone as phone, ");
				 hql.append(" ud.perIdNum as perIdNum, ");
				 hql.append(" ud.qqNum as qqNum, ");
				 hql.append(" ud.weixinNum as weixinNum ");
				 
				 hql.append(" @from UserDetail ud ");
				 hql.append(" left outer join ud.rbacUser  ru ");
				 hql.append(" left outer join ru.defaultRbacRole  ro ");
				 hql.append("  where 1=1 ");
				 if(!ZBeanUtil.isEmptyValue(paramObject.getReqParam("userName"))){
					 hql.append(" and ru.userName=:userName ");
					 paramObject.addAllowNull("userName", paramObject.getReqParam("userName"));
				}
				 if(!ZBeanUtil.isEmptyValue(paramObject.getReqParam("enabled"))){
					 hql.append(" and ru.enabled=:enabled ");
					 paramObject.addAllowNull("enabled", paramObject.getReqParam("enabled"));
				}
				if(!ZBeanUtil.isEmptyValue(paramObject.getReqParam("userNum"))){
					 hql.append(" and ud.userNum=:userNum ");
					 paramObject.addAllowNull("userNum", paramObject.getReqParam("userNum"));
				}
				if(!ZBeanUtil.isEmptyValue(paramObject.getReqParam("depetId"))){
					hql.append(" and ud.id in( select d.userDetail.id from DepartmentUserDetail d where d.department.id in(:depetId) ) ");
					 paramObject.addAllowNull("depetId", ZStrUtil.parseToLongArray((String) paramObject.getReqParam("depetId")));
				}
				DataSet ds=userDetailDao.loadDataSet(hql.toString(), paramObject);
				return	ds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findUserIdsByDepartmentId(Long departmentId) {
		if(departmentId==null){
			return null;
		}
		String hql=" select gu.userDetail.id from DepartmentUserDetail  du where du.department.id=:departmentId ";
		List<Long> userIdList=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId));
		return userIdList;
	}
	 @Autowired  
	  private ApplicationContext applicationContext; 
	@Override
	public void assignmentUserToDept(String deptIds,Long userId){
		if(deptIds==null||userId==null||deptIds.length()==0){
			throw new ServiceLogicalException("请选择数据");
		}
		Long[]depts= ZStrUtil.parseToLongArray(deptIds);
		depts= (Long[]) ZBeanUtil.removeDuplicateWithOrder(depts);
		String hqlD=" delete from DepartmentUserDetail  du where du.userDetail.id =:userId ";
		baseDaoImpl.executeUpdate(hqlD, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		baseDaoImpl.flush();
		
		List<DepartmentUserDetail> guListToBeAdd=new ArrayList<DepartmentUserDetail>();
		for(Long deptId:depts){
			if(!guListToBeAdd.contains(userId)){
				guListToBeAdd.add(new DepartmentUserDetail(new UserDetail(userId),new Department(deptId)));
			}
		}
		if(guListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(guListToBeAdd);
		}
		
		applicationContext.publishEvent(new RoleOrDeptChangedEvent(userId));
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void  processUserPositionChangeWhenNoifyed(Long userId){
		UserDetail userDetail=baseDaoImpl.find(UserDetail.class, userId);
		if(userDetail==null){
			throw new ServiceLogicalException("未查询到数据");
		}
		if(userDetail.getDefaultPosition()==null){
			return;
		}
		String hql=" select new map(pu.position.department.id as deptId,pu.position.rbacRole.code as roleCode, pu.position.id as poid) from PositionUserDetail pu where pu.userDetail.id=:userId";
		List<Map> mapList=(List<Map>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		if(mapList==null||mapList.size()==0){
			return;
		}
		//部门，角色
		List<Long> deptIduserHave = departmentService.findDepartmentIdsByUserId(userId);
		if (deptIduserHave == null || deptIduserHave.size() == 0) {
			clearUserPositionByUserId( userId);
		}
		List<String> roleCodeUserHave = rbacRoleService.findRoleCodeUserHave(userId, true);
		if (roleCodeUserHave == null || roleCodeUserHave.size() == 0) {
			clearUserPositionByUserId(userId);
		}
				
		for(Map map : mapList){
			if(!deptIduserHave.contains(map.get("deptId"))){
				deleteUserPositionByPositionId(userId,(Long) map.get("poid"));
			}
			if(!roleCodeUserHave.contains(map.get("roleCode"))){
				deleteUserPositionByPositionId(userId,(Long) map.get("poid"));
			}
		}
		
	}
	private  void deleteUserPositionByPositionId(Long userId,Long positionId){
		if(positionId==null){
			return ;
		}
		String hql="delete from PositionUserDetail where position.id=:positionId";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("positionId", positionId));
		String hql2="update  UserDetail set defaultPosition=null where id=:userId and defaultPosition.id=:positionId";
		baseDaoImpl.executeUpdate(hql2, new ParamObject(POType.H_NO_NC).
				addAllowNull("userId", userId).
				addAllowNull("positionId", positionId));
	}
	
	private  void clearUserPositionByUserId(Long userId){
		if(userId==null){
			return ;
		}
		String hql="delete from PositionUserDetail where userDetail.id=:userId";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		String hql2="update  UserDetail set defaultPosition=null where id=:userId";
		baseDaoImpl.executeUpdate(hql2, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void assignPositionUser(String positonIds,Long defaltPositonId,Long userId){
		if(positonIds==null||userId==null||positonIds.length()==0||defaltPositonId==null){
			throw new ServiceLogicalException("请选择数据");
		}
		//---------------------------------校验---------------------------------------------------
		UserDetail userDetail=baseDaoImpl.find(UserDetail.class, userId);
		if(userDetail==null){
			throw new ServiceLogicalException("未查询到数据");
		}
		//部门，角色
		List<Long> deptIduserHave=departmentService.findDepartmentIdsByUserId(userId);
		if(deptIduserHave==null||deptIduserHave.size()==0){
			throw new ServiceLogicalException("该用户没有分配任何部门，请先对该用户分配部门");
		}
		List<String> roleCodeUserHave=rbacRoleService.findRoleCodeUserHave(userId, true);
		if(roleCodeUserHave==null||roleCodeUserHave.size()==0){
			throw new ServiceLogicalException("该用户没有分配任何角色，请先对该用户分配角色");
		}
		Long[] poIds = ZStrUtil.parseToLongArray(positonIds);
		
		poIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(poIds);
		if(!Arrays.asList(poIds).contains(defaltPositonId)){
			throw new ServiceLogicalException("默认职位必须在选定职位中");
		}
		String hql=" select new map(p.department.id as deptId,p.rbacRole.code as roleCode, p.id as poid) from Position p where p.id in(:poIds)";
		List<Map> deptmapInSelectedPo=(List<Map>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("poIds", poIds));
		if(deptmapInSelectedPo==null||deptmapInSelectedPo.size()==0){
			throw new ServiceLogicalException("未查询到职位相关数据");
		}
		for(Map map : deptmapInSelectedPo){
			//[2, 3, 4, 5]
			//[{poid=1, deptId=2, roleCode=admin}, 
			//{poid=2, deptId=3, roleCode=admin}]
			if(!deptIduserHave.contains(map.get("deptId"))){
				String name=(String) baseDaoImpl.findFiledByUnique(Position.class, "name", "id", map.get("poid"));
				throw new ServiceLogicalException("职位【"+name+"所属部门，没有分配给该用户，请先将该用户分配到该部门");
			}
			if(!roleCodeUserHave.contains(map.get("roleCode"))){
				String name=(String) baseDaoImpl.findFiledByUnique(Position.class, "name", "id", map.get("poid"));
				throw new ServiceLogicalException("职位【"+name+"所属角色，没有分配给该用户，请先将该角色分配给该用户");
			}
		}
		
		//---------------------------------分配---------------------------------------------------
		
		String hqlD=" delete from PositionUserDetail  pu where pu.userDetail.id =:userId ";
		baseDaoImpl.executeUpdate(hqlD, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		baseDaoImpl.flush();
		
		List<PositionUserDetail> guListToBeAdd=new ArrayList<PositionUserDetail>();
		for(Long pid:poIds){
			if(!guListToBeAdd.contains(userId)){
				guListToBeAdd.add(new PositionUserDetail(new UserDetail(userId),new Position(pid)));
			}
		}
		if(guListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(guListToBeAdd);
		}
		//----------------------------------------------
		userDetail.setDefaultPosition(new Position(defaltPositonId));
		RbacUser ru=userDetail.getRbacUser();
		if(ru==null){
			throw new ServiceLogicalException("未查询到该用户数据");
		}
		String hjqls=" select p.rbacRole.id as id from Position p where p.id=:defaltPositonId";
		List<?> roleId=baseDaoImpl.findJustList(hjqls, new ParamObject(POType.H_NO_NC).addAllowNull("defaltPositonId", defaltPositonId));
		if(roleId==null||roleId.size()==0){
			throw new ServiceLogicalException("未找到默认值职位对应的角色");
		}
		ru.setDefaultRbacRole(new RbacRole((Long) roleId.get(0)));
		baseDaoImpl.saveOrUpdate(ru);
		baseDaoImpl.saveOrUpdate(userDetail);
	}
	@Deprecated
	@SuppressWarnings("unchecked")
	@Override
	public void addUserToDepartment(Long[] userIds, Long departmentId) {
		if(userIds==null||departmentId==null||userIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		userIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(userIds);
		
		String hql="select gu.userDetail.id from DepartmentUserDetail gu where gu.department.id=:departmentId";
		List<Long> userIdsInGU=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId));
		if(userIdsInGU==null){
			userIdsInGU=new ArrayList<Long>();
		}
		List<DepartmentUserDetail> guListToBeAdd=new ArrayList<DepartmentUserDetail>();
			for(Long userId:userIds){
				if(!userIdsInGU.contains(userId)){
					guListToBeAdd.add(new DepartmentUserDetail(new UserDetail(userId),new Department(departmentId)));
				}
			}
		if(guListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(guListToBeAdd);
		}
	}
	@Deprecated
	@Override
	public void removeUserFromDepartment(Long[] userIds, Long departmentId) {
		if(userIds==null||userIds.length==0||departmentId==null){
			throw new ServiceLogicalException("请选择数据 ");
		}
		String hql=" delete from DepartmentUserDetail where userDetail.id in(:userIds) and department.id=:departmentId";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId).addAllowNull("userIds", userIds));
	}
	@Override
	public DataSet loadUserDetailDataSetIsInDept(ParamObject paramObject,RowMap rowMap, Long departmentId, Boolean isIn) {
		if(departmentId==null){
			return null;
		}
		String extroConditon="";
    	if(isIn!=null&&isIn){
    		extroConditon=" and id in ( select gu.userDetail.id from DepartmentUserDetail gu where gu.department.id="+departmentId+") ";
    	}else if(isIn!=null&&!isIn){
    		extroConditon=" and id not in ( select gu.userDetail.id  from DepartmentUser gu where gu.department.id="+departmentId+" ) ";
    	}
    	DataSet grid= baseDaoImpl.loadDataSetFromOneEntity(UserDetail.class,paramObject,rowMap, extroConditon);
		return grid;
	}
	@Cacheable(value="cacheUserDetailCombox")
	@Override@SuppressWarnings({"unchecked","rawtypes"})
	public List<Map> loadUserDetailCombox() {
		String hql="select new map(userName as text,id as id )from UserDetail u";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", null);
		map.put("text", "请选择");
//		map.put("", "");
		List<Map> list=(List<Map>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
		List<Map> list2=new ArrayList<Map>();
		list2.add(map);
		list2.addAll(list);
		return list2;
	}

	@Override
	public void createUserDetail(UserDetail userDetail) {
		if(userDetail==null||userDetail.getUserNum()==null){
			throw new ServiceLogicalException("请完善数据");
		}
		String userNum=(String) baseDaoImpl.findFiledByUnique(UserDetail.class, "userNum", "userNum", userDetail.getUserNum());
		if(!ZStrUtil.isNullAfterTrimN(userNum)){
			throw new ServiceLogicalException("编号【"+userNum+"】的用户已经存在，请重新填写");
		}
		
		RbacUser user=new RbacUser();
		user.setAccountNonExpired(false);
		user.setAccountNonLocked(false);
		user.setCredentialsNonExpired(false);
		user.setUserDetail(userDetail);
		user.setEnabled(true);
		user.setUserName(userDetail.getUserNum());
		String[] pas=EndecryptUtils.genDefaultUserPassSalt(userDetail.getUserNum(), userDetail.getUserNum());
		user.setPassword(pas[0]);
		user.setSalt(pas[1]);
		
		super.$base_save(userDetail);
		baseDaoImpl.saveOrUpdate(user);
		//super.$base_save(userDetail);
	}
}