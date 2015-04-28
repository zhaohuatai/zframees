/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZBeanUtil;

import com.zht.common.rabc.dao.IRbacRoleDao;
import com.zht.common.rabc.model.RbacGroup;
import com.zht.common.rabc.model.RbacGroupRole;
import com.zht.common.rabc.model.RbacRole;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.model.RbacUserRole;
import com.zht.common.rabc.model.RbacUserRoleReject;
//import com.zht.common.rabc.model.SysDepartment;
//import com.zht.common.rabc.model.SysDepartmentRole;
import com.zht.common.rabc.service.IRbacGroupService;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.sys.service.IPositionService;
@Service
@Transactional(rollbackFor=Exception.class)
public class RbacRoleServiceImpl extends BaseServiceImpl<RbacRole> implements IRbacRoleService{
	@Autowired
	private IRbacRoleDao  rbacRoleDao;
	@Autowired 
	private IRbacGroupService rbacGroupService;
	@Autowired 
	private IPositionService positionService;
	@Override
	public void deleteRbacRole(Long[] ids) {
		if(ids==null||ids.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		String hql_A="delete from RbacRolePermission  where rbacRole.id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_A, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
		String hql_B="delete from RbacGroupRole where rbacRole.id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_B, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
		String hql_C="delete from SysDepartmentRole where rbacRole.id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_C, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
		String hql_D="delete from RbacUserRole where rbacRole.id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_D, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
		String hql_E="delete from RbacUserRoleReject where rbacRole.id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_E, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
		String hql_F="delete from RbacRole where id in (:ids) ";
		baseDaoImpl.executeUpdate(hql_F, new ParamObject(POType.H_NO_NC).addAllowNull("ids", ids));
		
	}
//-------------------------------------USER@S----------------------------------------------------------------

	public List<?> findRoleCodeUserHaveForCombox(Long userId,Boolean isEnable){
		List<String>  roleCodeList=findRoleCodeUserHaveInPatternA(userId,isEnable);
		if(!ZBeanUtil.isEmptyValue(roleCodeList)){
			String hql=" select new map( r.id as id ,r.name as name ) from RbacRole r where r.code in (:roleCodeList)";
			List<?> list= baseDaoImpl.findJustList(hql,new ParamObject(POType.H_NO_NC).addAllowNull("roleCodeList", roleCodeList));
			return list;
		}
		return null;
	}
	
	 public List<String> findRoleCodeUserHaveInPatternA(String userName,Boolean isEnable){
		 Long userId=(Long) baseDaoImpl.findIdByUnique(RbacUser.class, "userName", userName);
		 if(userId==null){
			 return null;
		 }
		 return findRoleCodeUserHaveInPatternA( userId,  isEnable);
	 }
	 
		@SuppressWarnings("unchecked")
		@Override
		public List<String> findRoleCodeUserHaveInPatternA(Long userId, Boolean isEnable) {
			//A: userRole(+)
			List<String> fromUserRole=findRoleCodeInUserRoleByUserId(userId,isEnable);
			//B: userRole(-)
			List<String> fromUserRoleReject=findRoleCodeInUserRoleRejectByUserId(userId,isEnable);
			//C: user extends group (+)
			List<String> fromUserEGroups=findRoleCodeFromUserExtendsGroupsByUserId(userId,isEnable);
			
			List<String> finalList=new ArrayList<String>();
			if(fromUserRole!=null){
				finalList.addAll(fromUserRole);
			}
			if(fromUserEGroups!=null){
				finalList.addAll(fromUserEGroups);
			}
			finalList=(List<String>) ZBeanUtil.removeDuplicateWithOrder(finalList);
			if(fromUserRoleReject!=null){
				finalList.removeAll(fromUserRoleReject);
			}
			return finalList;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<String> findRoleCodeUserHaveInPatternB(Long userId,Boolean isEnable) {
			// from position + from userRole - from userRoleReject
			//1:roleIdFromPosition(+)
			List<Long> positionIds=positionService.findPositionIdsByRbacUserId(userId);
			List<String> roleIdFromPosition=new ArrayList<String>();
			if(!ZBeanUtil.isEmptyValue(positionIds)){
				String hql="select p.rbacRole.code from Position p where p.id in (:positionIds) and p.rbacRole.enabled=:isEnable";
				 roleIdFromPosition=(List<String>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC)
				 					.addAllowNull("positionIds", positionIds).addAllowNull("isEnable", isEnable));
			}
			
			//2: fromUserRole(+)
			List<String> fromUserRole=findRoleCodeInUserRoleByUserId(userId,isEnable);
			//3: fromUserRoleReject(-)
			List<String> fromUserRoleReject=findRoleCodeInUserRoleRejectByUserId(userId,isEnable);
		
			List<String> finalList=new ArrayList<String>();
			
			if(roleIdFromPosition!=null&&roleIdFromPosition.size()>0){
				finalList.addAll(roleIdFromPosition);
			}
			
			if(fromUserRole!=null&&fromUserRole.size()>0){
				finalList.addAll(fromUserRole);
			}
			finalList=(List<String>) ZBeanUtil.removeDuplicateWithOrder(finalList);
			if(fromUserRoleReject!=null&&fromUserRoleReject.size()>0){
				finalList.removeAll(fromUserRoleReject);
			}
			return finalList;
		}

		@Override
		public List<String> findRoleCodeUserHaveInPatternB(String userName,Boolean isEnable) {
			 Long userId=(Long) baseDaoImpl.findIdByUnique(RbacUser.class, "userName", userName);
			 if(userId==null){
				 return null;
			 }
			 return findRoleCodeUserHaveInPatternA( userId,  isEnable);
		}
		
		
		@SuppressWarnings("unchecked")
		@Override
		public List<String> findRoleCodeInUserRoleByUserId(Long userId, Boolean isEnable) {
			if(userId==null){
				return null;
			}
			ParamObject po= new ParamObject(POType.H_NO_NC);
			String hqlFromUserRole=" select ur.rbacRole.code as code from RbacUserRole ur where  ur.rbacUser.id=:userId ";
			if(isEnable!=null){
				hqlFromUserRole+=" and ur.rbacRole.enabled=:isEnable ";
				po.addAllowNull("isEnable", isEnable);
			}
			po.addAllowNull("userId", userId);
			List<String> listFromUserRole=(List<String>) baseDaoImpl.findJustList(hqlFromUserRole,po);
			return listFromUserRole;
		}
		/**
		 * ParamObject:fdfd,dff,dfds
		 * 
		 */
		@Override
		public DataSet findRoleDataSetInUserRoleByUserId(Long userId, Boolean isEnable) {
			if(userId==null){
				return null;
			}
			 ParamObject po= new ParamObject(POType.H_O_C);
				String hql="select "
						 + "ur.rbacRole.id as id, "
						 + "ur.rbacRole.code as code, "
						 + "ur.rbacRole.name as name, "
						 + "ur.rbacRole.enabled as enabled, "
						 + "ur.rbacRole.description as description "
						 + "@from RbacUserRole ur where ur.rbacUser.id=:userId";
						if(isEnable!=null){
							hql+="and ur.rbacRole.enabled=:isEnable";
							po.addAllowNull("isEnable", isEnable);
						}
						po.addAllowNull("userId", userId);
				DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
			return dataSet; 
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<String> findRoleCodeInUserRoleRejectByUserId(Long userId,Boolean isEnable) {
			if(userId==null){
				return null;
			}
			ParamObject po= new ParamObject(POType.H_NO_NC);
			String hqlFromUserRoleReject=" select urj.rbacRole.code as code from  RbacUserRoleReject urj where  urj.rbacUser.id=:userId ";
			if(isEnable!=null){
				hqlFromUserRoleReject+=" and urj.rbacRole.enabled=:isEnable ";
				po.addAllowNull("isEnable", isEnable);
			}
			po.addAllowNull("userId", userId);
			List<String> listFromUserRoleReject=(List<String>) baseDaoImpl.findJustList(hqlFromUserRoleReject,po);
			return listFromUserRoleReject;
		}
		@Override
		public DataSet findRoleDataSetInUserRoleRejectByUserId(Long userId, Boolean isEnable) {
			if(userId==null){
				return null;
			}
			 ParamObject po= new ParamObject(POType.H_O_C);
				String hql="select "
						 + "urj.rbacRole.id as id, "
						 + "urj.rbacRole.code as code, "
						 + "urj.rbacRole.name as name, "
						 + "urj.rbacRole.enabled as enabled, "
						 + "urj.rbacRole.description as description "
						 + "@from RbacUserRoleReject urj where urj.rbacUser.id=:userId";
						if(isEnable!=null){
							hql+="and urj.rbacRole.enabled=:isEnable";
							po.addAllowNull("isEnable", isEnable);
						}
						po.addAllowNull("userId", userId);
				DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
			return dataSet; 
		}
		
		@Override
		public List<String> findRoleCodeFromUserExtendsGroupsByUserId(Long userId,Boolean isEnable) {
			if(userId==null){
				return null;
			}
			List<Long> groupList=rbacGroupService.findGroupIdsByUserId(userId);
			if(groupList==null||groupList.size()==0){
				return null;
			}
			List<String> list=new ArrayList<String>();
			for(Long groupId : groupList){
				List<String> grouHave=findRoleCodeGroupHave(groupId,isEnable);
				if(grouHave!=null){
					list.addAll(grouHave);
				}
			}
			return list;
		}
		
		@Override
		public DataSet findRoleDataSetFromUserExtendsGroupsByUserId(Long userId, Boolean isEnable) {
			if(userId==null){
				return null;
			}
			 List<String> roleCodeListFromGroups=findRoleCodeFromUserExtendsGroupsByUserId(userId,isEnable);
			 if(roleCodeListFromGroups==null){
				 return null;
			 }
			 ParamObject po= new ParamObject(POType.H_O_C);
			  String hqlp=" select "
						 + " r.id as id, "
				 		 + " r.code as code, "
						 + " r.name as name, "
						 + " r.enabled as enabled, "
						 + " r.description as description "
						 + " @from RbacRole r where r.code in(:roleCodeListFromGroups)";
			  if(isEnable!=null){
				    hqlp+=" and r.enabled=:isEnable ";
					po.addAllowNull("isEnable", isEnable);
			  }
			  po.addAllowNull("roleCodeListFromGroups", roleCodeListFromGroups);
			  
			DataSet dataSetp=baseDaoImpl.loadDataSet(hqlp,po);
			return dataSetp;
		}
		@Override
		public DataSet findRoleDataSetNotInUserRole$UserRoleReject(Long userId,Boolean isEnable) {
			List<String> roleCodeInUserRoleReject=findRoleCodeInUserRoleRejectByUserId(userId,isEnable);
			List<String> roleCodeInUserRole=findRoleCodeInUserRoleByUserId(userId,isEnable);
			
			List<String> roleCodePlusList=new ArrayList<String>();
			if(roleCodeInUserRole!=null){
				roleCodePlusList.addAll(roleCodeInUserRole);
			}
			if(roleCodeInUserRoleReject!=null){
				roleCodePlusList.addAll(roleCodeInUserRoleReject);
			}
			
			ParamObject po=new ParamObject(POType.H_O_C);
			 String hql="select "
						 + "r.id as id, "
				 		 + "r.code as code, "
						 + "r.name as name, "
						 + "r.enabled as enabled, "
						 + "r.description as description "
						 + "@from RbacRole r where 1=1";
			if (!ZBeanUtil.isEmptyValue(roleCodePlusList)) {
				hql += " and  r.code not in(:roleCodePlusList)";
				po.addAllowNull("roleCodePlusList", roleCodePlusList);
			}
			if (isEnable != null) {
				hql += " and r.enabled=:isEnable ";
				po.addAllowNull("isEnable", isEnable);
			}
			DataSet dataSetp=baseDaoImpl.loadDataSet(hql,po);
			return dataSetp;
		}
		@Override
		public void removeRolesFromUserRole(Long[] roleIds, Long userId) {
			if(userId==null||roleIds==null||roleIds.length==0){
				throw new ServiceLogicalException("请选择数据");
			}
			String hql="delete from RbacUserRole ur  where ur.rbacUser.id=:userId and ur.rbacRole.id in (:roleIds)";
			baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).
					addAllowNull("userId", userId).
					addAllowNull("roleIds", roleIds));
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public void addRolesToUserRole(Long[] roleIds, Long userId) {
			if(userId==null||roleIds==null||roleIds.length==0){
				throw new ServiceLogicalException("请选择数据");
			}
			roleIds=(Long[]) ZBeanUtil.removeDuplicateWithOrder(roleIds);
			String hql="select ur.rbacRole.id from RbacUserRole ur where ur.rbacUser.id=:userId";
			List<Long> roleIdInUserRole=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
			if(roleIdInUserRole==null){
				roleIdInUserRole=new ArrayList<Long>();
			}
			List<RbacUserRole> grsList=new ArrayList<RbacUserRole>();
			for(Long roleId:roleIds){
				if(!roleIdInUserRole.contains(roleId)){
					grsList.add(new RbacUserRole(new RbacUser(userId),new RbacRole(roleId)));
				}
			}
			if(grsList!=null&&grsList.size()>0){
				baseDaoImpl.saveOrUpdate(grsList);
			}
		}

		@Override
		public void removeRolesFromUserRoleReject(Long[] roleIds, Long userId) {
			if(userId==null||roleIds==null||roleIds.length==0){
				throw new ServiceLogicalException("请选择数据");
			}
			String hql="delete from RbacUserRoleReject urj  where urj.rbacUser.id=:userId and urj.rbacRole.id in (:roleIds)";
			baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).
					addAllowNull("userId", userId).
					addAllowNull("roleIds", roleIds));
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public void addRolesToUserRoleReject(Long[] roleIds, Long userId) {
			if(userId==null||roleIds==null||roleIds.length==0){
				throw new ServiceLogicalException("请选择数据");
			}
			roleIds=(Long[]) ZBeanUtil.removeDuplicateWithOrder(roleIds);
			String hql="select urj.rbacRole.id from RbacUserRoleReject urj where urj.rbacUser.id=:userId";
			List<Long> roleIdInUserRoleReject=(List<Long>) baseDaoImpl.findJustList(hql,
					new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
			if(roleIdInUserRoleReject==null){
				roleIdInUserRoleReject=new ArrayList<Long>();
			}
			List<RbacUserRoleReject> grsList=new ArrayList<RbacUserRoleReject>();
			for(Long roleId:roleIds){
				if(!roleIdInUserRoleReject.contains(roleId)){
					grsList.add(new RbacUserRoleReject(new RbacUser(userId),new RbacRole(roleId)));
				}
			}
			if(grsList!=null&&grsList.size()>0){
				baseDaoImpl.saveOrUpdate(grsList);
			}
			
		}
//--------------------------GROUP$S--------------------------------------------------------------------------------------------	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRoleCodeInGroupRole(Long groupId,Boolean isEnable){
		if(groupId==null){
			return null;
		}
		ParamObject po=new ParamObject(POType.H_NO_NC);
		
		String hqlFromGroupRole=" select gr.rbacRole.code as code from RbacGroupRole gr where  gr.rbacGroup.id=:groupId ";
		if(isEnable!=null){
			hqlFromGroupRole+=" and gr.rbacRole.enabled="+isEnable+" ";
		}
		po.addAllowNull("groupId", groupId);
		List<String> listFromGroup=(List<String>) baseDaoImpl.findJustList(hqlFromGroupRole,po);
		return listFromGroup;
	}
	
	public DataSet findRoleDataSetInGroupRole(Long groupId,Boolean isEnable){
		if(groupId==null){
			return null;
		}
		 ParamObject po= new ParamObject(POType.H_O_C);
			String hql="select "
					 + "gr.rbacRole.id as id, "
					 + "gr.rbacRole.code as code, "
					 + "gr.rbacRole.name as name, "
					 + "gr.rbacRole.enabled as enabled, "
					 + "gr.rbacRole.description as description "
					 + "@from RbacGroupRole gr where gr.rbacGroup.id=:groupId";
					if(isEnable!=null){
						hql+="and gr.rbacRole.enabled=:isEnable";
						po.addAllowNull("isEnable", isEnable);
					}
					po.addAllowNull("groupId", groupId);
			DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
			return dataSet; 
	}
	public DataSet findRoleDataSetNotInGroupRole(Long groupId,Boolean isEnable){
		if(groupId==null){return null;}
		 ParamObject po= new ParamObject(POType.H_O_C);
			String hql=" select "
					 + " r.id as id, "
					 + " r.code as code, "
					 + " r.name as name, "
					 + " r.enabled as enabled, "
					 + " r.description as description "
					 + " @from RbacRole r where "
					 + " r.id not in( select rgj.rbacRole.id from RbacGroupRole rgj where rgj.rbacGroup.id=:groupId)";
					if(isEnable!=null){
						hql+="and r.enabled=:isEnable";
						po.addAllowNull("isEnable", isEnable);
					}
					po.addAllowNull("groupId", groupId);
			DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
			return dataSet; 
	 }
	@Override
	public List<String> findRoleCodeFromParentGroups(Long groupId,Boolean isEnable){
		if(groupId==null){
			return null;
		}
		List<String> allList=new ArrayList<String>();
		while(groupId!=null){
			groupId=(Long) baseDaoImpl.findParentIdBySelfUnique(RbacGroup.class, "parentRbacGroup", "id", groupId);
			if(groupId!=null){
				List<String> listFromGroup=findRoleCodeInGroupRole(groupId, isEnable);
				if(listFromGroup!=null){
					allList.addAll(listFromGroup);
				}
			}
		}
		return allList;
	}
	 public DataSet findRoleDataSetFromGroupParents(Long groupId,Boolean isEnable){
		 if(groupId==null){
				return null;
		 }
		 List<String> roleCodelistFromParents=findRoleCodeFromParentGroups(groupId,isEnable);
		 if(roleCodelistFromParents==null||roleCodelistFromParents.size()==0){
			 return null;
		 }
		 ParamObject po=new ParamObject(POType.H_O_C);
		 String hqlp=  " select "
					 + " r.id as id, "
			 		 + " r.code as code, "
					 + " r.name as name, "
					 + " r.enabled as enabled, "
					 + " r.description as description "
					 + " @from RbacRole r where r.code in(:roleCodeList)";
		 if(isEnable!=null){
			 hqlp+="and r.enabled=:isEnable";
				po.addAllowNull("isEnable", isEnable);
		}
		po.addAllowNull("roleCodeList", roleCodelistFromParents);
		DataSet dataSetp=baseDaoImpl.loadDataSet(hqlp,po);
		return dataSetp;
	 }

	/**
	 * from_grouprole + from_parents  --> 去掉重复
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findRoleCodeGroupHave(Long groupId,Boolean isEnable) {
		if(groupId==null){
			return null;
		}
		// from  grouprole
		List<String> listFromGroupRole=findRoleCodeInGroupRole( groupId, isEnable);
		// from parents
		List<String> listFromParents=findRoleCodeFromParentGroups(groupId,isEnable);
		
		List<String> list=new ArrayList<String>();
		if(listFromGroupRole!=null&&listFromGroupRole.size()>0){
			list.addAll(listFromGroupRole);
		}
		if(listFromParents!=null&&listFromParents.size()>0){
			list.addAll(listFromParents);
		}
		//去掉重复
		list=(List<String>) ZBeanUtil.removeDuplicateWithOrder(list);
		return list;
	}

	@Override
	public void removeRolesFromGroupRole(Long[] roleIds, Long groupId) {
		if(groupId==null||roleIds==null||roleIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		String hql="delete from RbacGroupRole gr  where gr.rbacGroup.id=:groupId and gr.rbacRole.id in (:roleIds)";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).
				addAllowNull("groupId", groupId).
				addAllowNull("roleIds", roleIds));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addRolesToGroupRole(Long[] roleIds, Long groupId) {
		if(groupId==null||roleIds==null||roleIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		roleIds=(Long[]) ZBeanUtil.removeDuplicateWithOrder(roleIds);
		String hql="select gr.rbacRole.id from RbacGroupRole gr where gr.rbacGroup.id=:groupId";
		List<Long> roleIdInGroupRole=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("groupId", groupId));
		if(roleIdInGroupRole==null){
			roleIdInGroupRole=new ArrayList<Long>();
		}
		
		List<RbacGroupRole> grsList=new ArrayList<RbacGroupRole>();
		for(Long roleId:roleIds){
			if(!roleIdInGroupRole.contains(roleId)){
				grsList.add(new RbacGroupRole(new RbacGroup(groupId),new RbacRole(roleId)));
			}
		}
		baseDaoImpl.saveOrUpdate(grsList);
		
	}




	
}
