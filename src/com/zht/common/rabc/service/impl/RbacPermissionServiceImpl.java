/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZBeanUtil;

import com.zht.common.rabc.dao.IRbacMenuDao;
import com.zht.common.rabc.dao.IRbacPermissionDao;
import com.zht.common.rabc.model.RbacPermission;
import com.zht.common.rabc.model.RbacRole;
import com.zht.common.rabc.model.RbacRolePermission;
import com.zht.common.rabc.model.RbacUser;
import com.zht.common.rabc.model.RbacUserPermission;
import com.zht.common.rabc.model.RbacUserPermissionReject;
import com.zht.common.rabc.service.IRbacPermissionService;
import com.zht.common.rabc.util.PermissionUtil;
/**
 * 
* @ClassName :RbacPermissionServiceImpl     
* @Description :   
* @createTime :2015年4月3日  下午4:41:22   
* @author ：zhaohuatai   
* @version :1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RbacPermissionServiceImpl extends BaseServiceImpl<RbacPermission> implements IRbacPermissionService{
	@Autowired
	private IRbacMenuDao  rbacMenuDao;
	@Autowired
	private IRbacPermissionDao  rbacPermissionDao;
	
	@Override
	public void scanPacgeToloadPermis(String packagz) {
		Set<Class<?>> calssSet=PermissionUtil.scanAllController(packagz);
		if(calssSet==null||calssSet.size()==0){
			throw new ServiceLogicalException("未扫描到任何类");
		}
		List<RbacPermission> pList=new ArrayList<RbacPermission>();
		for(Class<?> calzz:calssSet){
			Method[] methods=calzz.getDeclaredMethods();
			String clazzUrl="";
			if(calzz.isAnnotationPresent(RequestMapping.class)){
				RequestMapping rm=calzz.getAnnotation(RequestMapping.class);
				String[] clazzUrlArray=rm.value();
				clazzUrl=clazzUrlArray[0];
			}
			if(methods==null||methods.length==0){
				continue;
			}
			for(Method method:methods){
				String url="";
				if(method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping mrp=method.getAnnotation(RequestMapping.class);
					String[] methodUrl=mrp.value();
					System.out.println(clazzUrl+methodUrl[0]);//permission--URL
					url=clazzUrl+methodUrl[0];
				}
				if(method.isAnnotationPresent(RequiresPermissions.class)){
					Annotation rp=method.getAnnotation(RequiresPermissions.class);
					String[] methodRPerms=((RequiresPermissions) rp).value();//permission--Code
					if(methodRPerms!=null&&methodRPerms.length>1){//多个 pemis
						for(String str:methodRPerms){
							RbacPermission perms=new RbacPermission();
							perms.setUrl(url);
							perms.setCode(str);
							perms.setName("SCAN");
							perms.setEnabled(true);
							perms.setType("P");
							pList.add(perms);
						}
					}else if(methodRPerms!=null&&methodRPerms.length==1){
						RbacPermission perms=new RbacPermission();
						perms.setUrl(url);
						perms.setCode(methodRPerms[0]);
						pList.add(perms);
						perms.setName("SCAN");perms.setEnabled(true);
						perms.setType("P");
						pList.add(perms);
					}else if(methodRPerms==null||methodRPerms.length==0){
						RbacPermission perms=new RbacPermission();
						perms.setUrl(url);
						perms.setCode("");
						perms.setName("SCAN");perms.setEnabled(true);perms.setType("P");
						pList.add(perms);
						pList.add(perms);
					}
				}
				
			}
			for(RbacPermission p:pList){
				if(baseDaoImpl.findIdByUnique(RbacPermission.class, "code",p.getCode())==null){
					$base_save(p);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllPermsUserHaveInDefaultRole(String userName) {
		if(userName==null){
			return null;
		}
		String hqlFromDefaultRole=" select rp.rbacPermission.code from RbacRolePermission rp where "
				+ " rp.rbacRole.id in (select u.defaultRbacRole.id from RbacUser u where u.userName =:userName) ";
		List<String> listFromDefaultRole=(List<String>) rbacPermissionDao.findJustList(hqlFromDefaultRole, new ParamObject(POType.H_NO_NC).addParam("userName", userName));
		
		String hqlFormUP=" select up.rbacPermission.code from RbacUserPermission up  where"
				+ " up.rbacUser.userName =:userName)";
		List<String> listFormUP=(List<String>) rbacPermissionDao.findJustList(hqlFormUP, new ParamObject(POType.H_NO_NC).addParam("userName", userName));
		
		String hql=" select upr.rbacPermission.code from RbacUserPermissionReject upr"
				+ "  where upr.rbacUser.userName =:userName)";
		List<String> listFromUPR=(List<String>) rbacPermissionDao.findJustList(hql, new ParamObject(POType.H_NO_NC).addParam("userName", userName));
		
		List<String> codeList=new ArrayList<String>();
		
		if(listFromDefaultRole!=null){
			codeList.addAll(listFromDefaultRole);
		}
		if(listFormUP!=null){
			codeList.addAll(listFormUP);
		}
		codeList=(List<String>) ZBeanUtil.removeDuplicateWithOrder(codeList);
		if(listFromUPR!=null){
			codeList.removeAll(listFromUPR);
		}
		return codeList;
		
	}

	@Override
	public void deletePermission(Long[] ids) {
		if(ZBeanUtil.isEmptyValue(ids)){
			return;
		}
		String hqlA=" delete from RbacUserPermission up where up.rbacPermission.id in (:ids) ";
		String hqlB=" delete from RbacUserPermissionReject upr where upr.rbacPermission.id in (:ids) ";
		String hqlC=" delete from RbacRolePermission rp where rp.rbacPermission.id in (:ids) ";
		String hqlD=" update RbacMenu m set m.rbacPermission.id=null where m.rbacPermission.id in (:ids) ";
		
		baseDaoImpl.executeUpdate(hqlA, new ParamObject(POType.H_NO_C).addAllowNull("ids", ids));
		baseDaoImpl.executeUpdate(hqlB, new ParamObject(POType.H_NO_C).addAllowNull("ids", ids));
		baseDaoImpl.executeUpdate(hqlC, new ParamObject(POType.H_NO_C).addAllowNull("ids", ids));
		baseDaoImpl.executeUpdate(hqlD, new ParamObject(POType.H_NO_C).addAllowNull("ids", ids));
		baseDaoImpl.deleteByIdsInCase(RbacPermission.class, ids);
	}

	public DataSet loadPermissionForRoleAssign(ParamObject paramObject,RowMap rowMap) {
		
		Long roleId=ZBeanUtil.parseLong(paramObject.getReqParam("roleId"));
		Boolean isInRole=ZBeanUtil.parseBoolean(paramObject.getReqParam("isInRole")) ;
		
		if(roleId==null||isInRole==null){
			return null;
		}
		String extroConditon="";
    	if(isInRole!=null&&isInRole){
    		extroConditon=" and id in ( select gu.rbacPermission.id from RbacRolePermission gu where gu.rbacRole.id="+roleId+") ";
    	}else if(isInRole!=null&&!isInRole){
    		extroConditon=" and id not in ( select gu.rbacPermission.id  from RbacRolePermission gu where gu.rbacRole.id="+roleId+" ) ";
    	}
    	DataSet grid= baseDaoImpl.loadDataSetFromOneEntity(RbacPermission.class,paramObject,rowMap, extroConditon);
		return grid;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void addPermissionsToRole(Long[] permIds, Long roleId) {
		if(roleId==null||permIds==null||permIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		permIds=(Long[]) ZBeanUtil.removeDuplicateWithOrder(permIds);//去掉重复
		String hql="select rp.rbacPermission.id from RbacRolePermission rp where rp.rbacRole.id=:roleId";
		List<Long> permIdsInRP=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("roleId", roleId));
		if(permIdsInRP==null){
			permIdsInRP=new ArrayList<Long>();
		}
		List<RbacRolePermission> rpsListToBeAdd=new ArrayList<RbacRolePermission>();
			for(Long permissionId:permIds){
				if(!permIdsInRP.contains(permissionId)){
					rpsListToBeAdd.add(new RbacRolePermission(new RbacRole(roleId),new RbacPermission(permissionId)));
				}
				
			}
		if(rpsListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(rpsListToBeAdd);
		}
	}
	@Override
	 public void removePermsFromRole(Long[] permIds,Long roleId){
		 if(roleId==null||permIds==null||permIds.length==0){
			 throw new ServiceLogicalException("请选择数据");
			}
		 	List<Long> permissionsIds=Arrays.asList(permIds);
		 	
			String hql="delete from RbacRolePermission rp  where rp.rbacRole.id=:roleId and rp.rbacPermission.id in (:permissionsIds)";
			baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addParam("roleId", roleId).addParam("permissionsIds", permissionsIds));
	 }


	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findPermissionIdsByRoleId(Long roleId) {
		if(roleId==null){
			return null;
		}
		String hql="select rp.rbacPermission.id from RbacRolePermission rp  where rp.rbacRole.id=:roleId";
		List<Long> list=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addParam("roleId", roleId));
		return list;
	}
//---------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Long> findPermissionIdsInUserPermissionByUserId(ParamObject po,Long userId){
		if(userId==null){
			return null;
		}
		po.initType(POType.H_NO_NC);
		String hql=" select up.rbacPermission.id as id from  RbacUserPermission up where  up.rbacUser.id=:userId ";
		if(!ZBeanUtil.isEmptyValue(po.getReqParam("enabled"))){
			hql+=" and up.rbacPermission.enabled=:enabled ";
			po.addAllowNull("enabled", po.getReqParam("enabled"));
		}
		if(!ZBeanUtil.isEmptyValue(po.getReqParam("code"))){
			hql+=" and up.rbacPermission.code=:code ";
			po.addAllowNull("code", po.getReqParam("code"));
		}
		if(!ZBeanUtil.isEmptyValue(po.getReqParam("name"))){
			hql+=" and up.rbacPermission.name=:name ";
			po.addAllowNull("name", po.getReqParam("name"));
		}
		po.addAllowNull("userId", userId);
		List<Long> listFromUserPermission=(List<Long>) baseDaoImpl.findJustList(hql,po);
		return listFromUserPermission;
	}
	@Override
	public DataSet findPermissionDataSetInUserPermissionByUserId(ParamObject po,Long userId) {
			if(userId==null){
				return null;
			}
			String hql="select "
					 + "up.rbacPermission.id as id, "
					 + "up.rbacPermission.code as code, "
					 + "up.rbacPermission.name as name, "
					 + "up.rbacPermission.enabled as enabled, "
					 + "up.rbacPermission.url as url, "
					 + "up.rbacPermission.type as type, "
					 + "up.rbacPermission.description as description "
					 + "@from RbacUserPermission up where up.rbacUser.id=:userId";
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("enabled"))){
				hql+=" and up.rbacPermission.enabled=:enabled ";
				po.addAllowNull("enabled", po.getReqParam("enabled"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("code"))){
				hql+=" and up.rbacPermission.code=:code ";
				po.addAllowNull("code", po.getReqParam("code"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("name"))){
				hql+=" and up.rbacPermission.name=:name ";
				po.addAllowNull("name", po.getReqParam("name"));
			}
			po.addAllowNull("userId", userId);
			DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
		return dataSet; 
	}
	 @SuppressWarnings("unchecked")
	public List<Long> findPermissionIdsInUserPermissionRejectByUserId(ParamObject po,Long userId){
			if(userId==null){
				return null;
			}
			po.initType(POType.H_O_C);
			String hql=" select upr.rbacPermission.id as id from  RbacUserPermissionReject upr where  upr.rbacUser.id=:userId ";
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("enabled"))){
				hql+=" and upr.rbacPermission.enabled=:enabled ";
				po.addAllowNull("enabled", po.getReqParam("enabled"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("code"))){
				hql+=" and upr.rbacPermission.code=:code ";
				po.addAllowNull("code", po.getReqParam("code"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("name"))){
				hql+=" and upr.rbacPermission.name=:name ";
				po.addAllowNull("name", po.getReqParam("name"));
			}
			po.addAllowNull("userId", userId);
			List<Long> listFromUserPermissionReject=(List<Long>) baseDaoImpl.findJustList(hql,po);
			return listFromUserPermissionReject;
	 }
	 

	 public DataSet findPermissionDataSetInUserPermissionRejectByUserId(ParamObject po,Long userId){
			if(userId==null){
				return null;
			}
				String hql="select "
						 + "upr.rbacPermission.id as id, "
						 + "upr.rbacPermission.code as code, "
						 + "upr.rbacPermission.name as name, "
						 + "upr.rbacPermission.enabled as enabled, "
						 + "upr.rbacPermission.url as url, "
						 + "upr.rbacPermission.type as type, "
						 + "upr.rbacPermission.description as description "
						 + "@from RbacUserPermissionReject upr where upr.rbacUser.id=:userId";
				if(!ZBeanUtil.isEmptyValue(po.getReqParam("enabled"))){
					hql+=" and upr.rbacPermission.enabled=:enabled ";
					po.addAllowNull("enabled", po.getReqParam("enabled"));
				}
				if(!ZBeanUtil.isEmptyValue(po.getReqParam("code"))){
					hql+=" and upr.rbacPermission.code=:code ";
					po.addAllowNull("code", po.getReqParam("code"));
				}
				if(!ZBeanUtil.isEmptyValue(po.getReqParam("name"))){
					hql+=" and upr.rbacPermission.name=:name ";
					po.addAllowNull("name", po.getReqParam("name"));
				}
				po.addAllowNull("userId", userId);
				DataSet dataSet=baseDaoImpl.loadDataSet(hql,po);
			return dataSet; 
	 }
	 public DataSet findPermissionDataSetNotInUserPermAndUserPermReject(ParamObject po,Long userId){
		 po.initType(POType.H_NO_NC);
		 List<Long> permissionIdsInUserPermission=findPermissionIdsInUserPermissionByUserId(po,userId);
		 List<Long> permissionIdsInUserPermissionReject=findPermissionIdsInUserPermissionRejectByUserId(po,userId);
				
			List<Long> permissionIdsPlusList=new ArrayList<Long>();
			if(permissionIdsInUserPermission!=null){
				permissionIdsPlusList.addAll(permissionIdsInUserPermission);
			}
			if(permissionIdsInUserPermissionReject!=null){
				permissionIdsPlusList.addAll(permissionIdsInUserPermissionReject);
			}
			po.initType(POType.H_O_C);
			 String hql="select "
					 + "p.id as id, "
					 + "p.code as code, "
					 + "p.name as name, "
					 + "p.enabled as enabled, "
					 + "p.url as url, "
					 + "p.type as type, "
					 + "p.description as description "
					 + "@from RbacPermission p where 1=1";
			if (!ZBeanUtil.isEmptyValue(permissionIdsPlusList)) {
				hql += " and  p.id not in(:permissionIdsPlusList)";
				po.addAllowNull("permissionIdsPlusList", permissionIdsPlusList);
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("enabled"))){
				hql+=" and p.enabled=:enabled ";
				po.addAllowNull("enabled", po.getReqParam("enabled"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("code"))){
				hql+=" and p.code=:code ";
				po.addAllowNull("code", po.getReqParam("code"));
			}
			if(!ZBeanUtil.isEmptyValue(po.getReqParam("name"))){
				hql+=" and p.name=:name ";
				po.addAllowNull("name", po.getReqParam("name"));
			}
			DataSet dataSetp=baseDaoImpl.loadDataSet(hql,po);
			return dataSetp;
	 }
	@SuppressWarnings("unchecked")
	public void addPermissionsToUserPermission(Long[] permissionIds, Long userId) {
		if(userId==null||permissionIds==null||permissionIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		permissionIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(permissionIds);//去掉重复
		
		String hql="select up.rbacPermission.id from RbacUserPermission up where up.rbacUser.id=:userId";
		List<Long> permIdsInUP=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		if(permIdsInUP==null){
			permIdsInUP=new ArrayList<Long>();
		}
		List<RbacUserPermission> upsListToBeAdd=new ArrayList<RbacUserPermission>();
			for(Long permissionId:permissionIds){
				if(!permIdsInUP.contains(permissionId)){
					upsListToBeAdd.add(new RbacUserPermission(new RbacUser(userId),new RbacPermission(permissionId)));
				}
				
			}
		if(upsListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(upsListToBeAdd);
		}
	}

	@Override
	public void removePermissionsFromUserPermission(Long[] permissionIds,Long userId) {
		 if(userId==null||permissionIds==null||permissionIds.length==0){
			 throw new ServiceLogicalException("请选择数据");
		 }
		String hql="delete from RbacUserPermission up  where up.rbacUser.id=:userId and up.rbacPermission.id in (:permissionsIds)";
		baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId).addAllowNull("permissionsIds", permissionIds));
	}


	@SuppressWarnings("unchecked")
	@Override
	public void addPermissionsToUserPermissionReject(Long[] permissionIds,
			Long userId) {
		if(userId==null||permissionIds==null||permissionIds.length==0){
			throw new ServiceLogicalException("请选择数据");
		}
		permissionIds= (Long[]) ZBeanUtil.removeDuplicateWithOrder(permissionIds);//去掉重复
		
		String hql="select upr.rbacPermission.id from RbacUserPermissionReject upr where upr.rbacUser.id=:userId";
		List<Long> permIdsInUPR=(List<Long>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		if(permIdsInUPR==null){
			permIdsInUPR=new ArrayList<Long>();
		}
		List<RbacUserPermissionReject> uprsListToBeAdd=new ArrayList<RbacUserPermissionReject>();
			for(Long permissionId:permissionIds){
				if(!permIdsInUPR.contains(permissionId)){
					uprsListToBeAdd.add(new RbacUserPermissionReject(new RbacUser(userId),new RbacPermission(permissionId)));
				}
				
			}
		if(uprsListToBeAdd.size()>0){
			baseDaoImpl.saveOrUpdate(uprsListToBeAdd);
		}
		
	}

	@Override
	public void removePermissionsFromUserPermissionReject(Long[] permissionIds,Long userId) {
		 if(userId==null||permissionIds==null||permissionIds.length==0){
			 throw new ServiceLogicalException("请选择数据");
		 }
			String hql="delete from RbacUserPermissionReject upr  where upr.rbacUser.id=:userId and upr.rbacPermission.id in (:permissionsIds)";
			baseDaoImpl.executeUpdate(hql, new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId).addAllowNull("permissionsIds", permissionIds));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> findAllPermissionListForComobox() {
		String hql = " select new map(p.id as id ,p.code as text) @from RbacPermission p order by p.id ";
		List<Map> mapList=(List<Map>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
		return mapList;
	}


}
