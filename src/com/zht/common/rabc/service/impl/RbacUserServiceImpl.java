/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.UUIDUtil;
import org.zht.framework.util.ZBeanUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.rabc.dao.IRbacMenuDao;
import com.zht.common.rabc.dao.IRbacModuleDao;
import com.zht.common.rabc.dao.IRbacPermissionDao;
import com.zht.common.rabc.dao.IRbacRoleDao;
import com.zht.common.rabc.dao.IRbacUserDao;
import com.zht.common.rabc.model.RbacGroup;
import com.zht.common.rabc.model.RbacGroupUser;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.service.IRbacRoleService;
import com.zht.common.rabc.service.IRbacUserService;
import com.zht.common.shiro.util.ShiroSecurityHelper;
import com.zht.common.sys.model.Department;
import com.zht.common.sys.model.DepartmentUser;
@Service
@Transactional(rollbackFor=Exception.class)
public class RbacUserServiceImpl  extends BaseServiceImpl<RbacUser>implements IRbacUserService{
	@Autowired
	private IRbacModuleDao  rbacMenuModuleDao;
	@Autowired
	private IRbacMenuDao  rbacMenuDao;
	@Autowired
	private IRbacRoleDao  rbacRoleDao;
	@Autowired
	private IRbacPermissionDao  rbacPermissionDao;
	
	@Autowired
	private IRbacUserDao  rbacUserDao;
	@Autowired
	private IRbacRoleService  rbacRoleService;
	@Override
	public RbacUser findUserByName(String username) {
		if(username==null){
			return null;
		}
		RbacUser user=	baseDaoImpl.findByUniqueParam(RbacUser.class, "userName", username);
		
		return user;
	}

	public void genUSer(String userName, String password) {
		RbacUser user=new RbacUser();
		String salt=System.nanoTime()+UUIDUtil.base58Uuid()+new Md5Hash(userName).toString();;//
		// String password_cipherText2= new Md5Hash(password,salt,2).toString(); 
		user.setSalt(salt);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findUserIdsByGroupId(Long groupId) {
		if(groupId==null){
			return null;
		}
		String hql=" select gu.rbacUser.id from RbacGroupUser  gu where gu.rbacGroup.id=:groupid ";
		List<Long> userIdList=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("groupId", groupId));
		return userIdList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findUserIdsByDepartmentId(Long departmentId) {
		if(departmentId==null){
			return null;
		}
		String hql=" select gu.rbacUser.id from SysDepartmentUser  du where du.sysDepartment.id=:departmentId ";
		List<Long> userIdList=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId));
		return userIdList;
	}
	
//----------------------GROUP@S-----------------------------------------------------------------------
	 	
	@Override
	public void removeUserFromGroup(Long[] userIds, Long groupId) {
		if(userIds==null||userIds.length==0||groupId==null){
			throw new ServiceLogicalException("请选择数据 ");
		}
		String hql=" delete from RbacGroupUser where rbacUser.id in(:userIds) and rbacGroup.id=:groupId";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("groupId", groupId).addAllowNull("userIds", userIds));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	 public void addUserToGroup(Long[] userIds,Long groupId){
		if(userIds==null||groupId==null||userIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		userIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(userIds);
		
		String hql="select gu.rbacUser.id from RbacGroupUser gu where gu.rbacGroup.id=:groupId";
		List<Long> userIdsInGU=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("groupId", groupId));
		if(userIdsInGU==null){
			userIdsInGU=new ArrayList<Long>();
		}
		List<RbacGroupUser> guListToBeAdd=new ArrayList<RbacGroupUser>();
			for(Long userId:userIds){
				if(!userIdsInGU.contains(userId)){
					guListToBeAdd.add(new RbacGroupUser(new RbacUser(userId),new RbacGroup(groupId)));
				}
				
			}
		if(guListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(guListToBeAdd);
		}
		
	}
	@Override
	public DataSet loadUserDataSetIsInGroup(ParamObject paramObject,RowMap rowMap, Long groupId, Boolean isIn) {
		if(groupId==null){
			return null;
		}
		String extroConditon="";
    	if(isIn!=null&&isIn){
    		extroConditon=" and id in ( select gu.rbacUser.id from RbacGroupUser gu where gu.rbacGroup.id="+groupId+") ";
    	}else if(isIn!=null&&!isIn){
    		extroConditon=" and id not in ( select gu.rbacUser.id  from RbacGroupUser gu where gu.rbacGroup.id="+groupId+" ) ";
    	}
    	DataSet grid= baseDaoImpl.loadDataSetFromOneEntity(RbacUser.class,paramObject,rowMap, extroConditon);
		return grid;
	}
//----------------------Department@S-----------------------------------------------------------------------	
	@Override
	public DataSet loadUserDataSetIsInDepartment(ParamObject paramObject,RowMap rowMap, Long departmentId, Boolean isIn) {
		if(departmentId==null){
			return null;
		}
		String extroConditon="";
    	if(isIn!=null&&isIn){
    		extroConditon=" and id in ( select gu.rbacUser.id from DepartmentUser gu where gu.department.id="+departmentId+") ";
    	}else if(isIn!=null&&!isIn){
    		extroConditon=" and id not in ( select gu.rbacUser.id  from DepartmentUser gu where gu.department.id="+departmentId+" ) ";
    	}
    	DataSet grid= baseDaoImpl.loadDataSetFromOneEntity(RbacUser.class,paramObject,rowMap, extroConditon);
		return grid;
	}
	@Override
	public void removeUserFromDepartment(Long[] userIds, Long departmentId) {
		if(userIds==null||userIds.length==0||departmentId==null){
			throw new ServiceLogicalException("请选择数据 ");
		}
		String hql=" delete from DepartmentUser where rbacUser.id in(:userIds) and department.id=:departmentId";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId).addAllowNull("userIds", userIds));

		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void addUserToDepartment(Long[] userIds, Long departmentId) {
		if(userIds==null||departmentId==null||userIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		userIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(userIds);
		
		String hql="select gu.rbacUser.id from DepartmentUser gu where gu.department.id=:departmentId";
		List<Long> userIdsInGU=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("departmentId", departmentId));
		if(userIdsInGU==null){
			userIdsInGU=new ArrayList<Long>();
		}
		List<DepartmentUser> guListToBeAdd=new ArrayList<DepartmentUser>();
			for(Long userId:userIds){
				if(!userIdsInGU.contains(userId)){
					guListToBeAdd.add(new DepartmentUser(new RbacUser(userId),new Department(departmentId)));
				}
			}
		if(guListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(guListToBeAdd);
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkDefaultRole(String username) {
		if(ZStrUtil.isEmptyAfterTrimE(username)){
			throw new ServiceLogicalException("用户名参数不正确");
		}
		String hql=" select r.code from RbacRole r where enabled=true and "
				+ " r.id in (select u.defaultRbacRole.id as rid from RbacUser u where u.duserName=:username)";
		List<String> userDefaultRoleCode=(List<String>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("username", username));
		if(ZBeanUtil.isEmptyValue(userDefaultRoleCode)){
			throw new ServiceLogicalException("当前默认角色未找到，或者处于禁用状态");
		}
		
		List<String> list = rbacRoleService.findRoleCodeUserHaveInPatternA(username, true);
		if(list==null||list.size()==0){
			return false;
		}
		if(!list.contains(userDefaultRoleCode.get(0))){
			return false;
		}
		return  true;
	}
	@Override
	public void kickOutUser(Long userId) {
		List<?> nameList=baseDaoImpl.findJustList("select userName as userName from RbacUser u where u.id=:userId", 
				new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		if(nameList!=null&&nameList.size()>0){
			ShiroSecurityHelper.kickOutUser((String) nameList.get(0));
		}
	}
	
}
