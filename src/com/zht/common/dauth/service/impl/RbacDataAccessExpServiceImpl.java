package com.zht.common.dauth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.dauth.service.IRbacDataAccessExpService;

import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.dauth.dao.IRbacDataAccessExpDao;
import com.zht.common.dauth.model.RbacDataAccessExp;

@Service
@Transactional(rollbackFor=Exception.class)
public class RbacDataAccessExpServiceImpl extends BaseServiceImpl<RbacDataAccessExp> implements IRbacDataAccessExpService{
 	@Autowired
	private IRbacDataAccessExpDao rbacDataAccessExpDao;
 	//<rbac_dataPrivilege,Collection<RbacDataAccessExp>>
	@SuppressWarnings("unchecked")
	@Override
	public Map<String ,Collection<Map<String,Object>>> loadDataPrivilegeDataAccessExpMapSource() {
		String hql=" "
 				+ " select "
 				+ " new map("
 				+ " dae.daoAcessMethed as daoAcessMethed,"
 				+ " dae.authExp as authExp, "
 				+ " dae.calledTime as calledTime, "
 				+ " dae.dataPrivilege.code as dpCode "
 				+ " ) from RbacDataAccessExp  dae where 1=1"
 				+ " and dae.enabled=true and dae.dataPrivilege.enabled=true ";
 		List<Map<String,Object>> list=(List<Map<String,Object>>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC));
 		if(list==null||list.size()==0){
 			return null;
 		}
 		Map<String ,Collection<Map<String,Object>>> dataSource=new ConcurrentHashMap<String ,Collection<Map<String,Object>>>();
 		for(Map<String,Object> map :list){
 			String dpCode=(String) map.get("dpCode");
 			List<Map<String,Object>> roleCodeList=findDtaExpListByPdCode(list,dpCode);
 			if(dpCode!=null&&roleCodeList!=null){
 				dataSource.put(dpCode, roleCodeList);
 			}
 		}
 		return dataSource;
	}
 	private List<Map<String,Object>> findDtaExpListByPdCode(List<Map<String,Object>> list,String dpCode){
 		if(list==null||dpCode==null){
 			return null;
 		}
 		List<Map<String,Object>> roleCodeList=new ArrayList<Map<String,Object>>();
 		for(Map<String,Object> map:list){
 			String dpCode1=(String) map.get("dpCode");
 			if(dpCode.equals(dpCode1)){
 				String daoAcessMethed=(String) map.get("daoAcessMethed");
 				String authExp=(String) map.get("authExp");
 				Integer calledTime=(Integer)map.get("calledTime");
 				if(daoAcessMethed!=null&&authExp!=null){
 					Map<String,Object> mapss=new ConcurrentHashMap<String,Object>();
 					mapss.put("daoAcessMethed", daoAcessMethed);
 					mapss.put("authExp", authExp);
 					mapss.put("calledTime", calledTime);
 					roleCodeList.add(mapss);
 				}
 			}
 		}
 		return roleCodeList;
 	}
 
}