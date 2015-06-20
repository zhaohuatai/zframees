package com.zht.common.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.zht.common.sys.service.IDepartmentService;
import com.zht.common.sys.util.DepartmentUtil;

import org.zht.framework.data.DataSet;
import org.zht.framework.data.POType;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;

import com.zht.common.sys.dao.IDepartmentDao;
import com.zht.common.sys.model.Department;

@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl extends BaseServiceImpl<Department>
		implements IDepartmentService {
	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public DataSet loadDepartmentTreeGrid() {
		String hql = " select " + "g.id as id ,"
				+ "g.parentDepartment.id as _parentId ,"
				+ "g.name as name ," + "g.enabled as enabled,"
				+ "g.createTime as createTime," + "g.creater as creater "
				+ "@from Department g ";

		DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(POType.H_NO_NC));
		return dataSet;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map> loadDepartmentCombotree() {
		List<Long> rootIdList = (List<Long>) 
				baseDaoImpl.findJustList("select id from Department g where g.parentDepartment.id is null ",
						new ParamObject(POType.H_NO_NC));
		if (rootIdList == null || rootIdList.size() == 0) {
			throw new ServiceLogicalException("未发现数据根节点，请检查数据 ");
		}
		if (rootIdList.size() > 1) {
			throw new ServiceLogicalException("发现多个根节点数据，请检查数据 ");
		}
		String hql = " select g.id as id ,"
				+ " g.parentDepartment.id as _parentId, "
				+ " g.name as text "
				+ " @from Department g ";
		DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(POType.H_NO_NC));
		Map root = dataSet.getRows().get(0);
		DepartmentUtil.traverse(root, dataSet.getRows());
		List<Map> mapList = new ArrayList<Map>();
		mapList.add(root);
		return mapList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findDepartmentIdsByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		String hql = " select du.department.id from DepartmentUserDetail  du where du.userDetail.id=:userId ";
		List<Long> groupIdList = (List<Long>) baseDaoImpl.findJustList(hql,
				new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		return groupIdList;
	}
	public List<String> findDepartmentNamesByUserId(Long userId){
		List<Long> list= findDepartmentIdsByUserId(userId);
		if(list==null||list.size()==0){
			return null;
		}
		String hql="select d.name from Department d where d.id in(:list)";
		List<String> list2=(List<String>) baseDaoImpl.findJustList(hql, new ParamObject(POType.H_NO_NC).addAllowNull("list", list));
		return list2;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findChildDeptIdsBySelefId(Long deptId) {
		if (deptId == null) {
			return null;
		}
		String hqlA = " select id from Department  d where d.id=:deptId ";
		List<Long> groupIdList = (List<Long>) baseDaoImpl.findJustList(hqlA,new ParamObject(POType.H_NO_NC).addAllowNull("deptId", deptId));
		if(groupIdList==null||groupIdList.size()==0){
			return null;
		}
		Map<Long,Long> treeIdsMapSource=loadTreeMap();
		if(treeIdsMapSource==null||treeIdsMapSource.size()==0){
			return null;
		}
		List<Long> finalIds=new ArrayList<Long>();
		
		String hql = " select id from Department d ";
		List<Long> deptIdList = (List<Long>) baseDaoImpl.findJustList(hql,new ParamObject(POType.H_NO_NC));
		if(deptIdList==null||deptIdList.size()==0){
			return null;
		}
		for(Long id:deptIdList){
			if(isOrignFromSelf(treeIdsMapSource,deptId,id)){
				finalIds.add(id);
			}
		}
		return finalIds;
	}
	
	private boolean isOrignFromSelf(Map<Long,Long> treeMap,Long selfId,Long tagertNodeId){
		if(selfId==null||tagertNodeId==null){
			return false;
		}
		if(tagertNodeId.equals(selfId)){
			return false;
		}
		Long pid=getParentId(treeMap,tagertNodeId);
		if(pid==null){
			return false;
		}
		if(pid.equals(selfId)){
			return true;
		}
		while(pid!= null){
			if(pid.equals(selfId)){
				return true;
			}
			pid=getParentId(treeMap,pid);
		}
		return false;
	}
	
	private Long getParentId(Map<Long,Long> treeMap,Long selfId){
		if(selfId==null){
			return null;
		}
		return treeMap.get(selfId);
	}
	@SuppressWarnings("unchecked")
	private Map<Long,Long> loadTreeMap(){
		String hql = " select new map (g.id as id , g.parentDepartment.id as _parentId) @from Department g ";
		List<Map<Long,Long>> deptIdList = (List<Map<Long,Long>>) baseDaoImpl.findJustList(hql,new ParamObject(POType.H_NO_NC));
		if(deptIdList!=null&&deptIdList.size()>0){
			Map<Long,Long> treeMap=new HashMap<Long, Long>();
			for(Map<Long,Long> map:deptIdList){
				Long selfId=map.get("id");
				Long parentId=map.get("_parentId");
				if(selfId!=null){
					treeMap.put(selfId, parentId);
				}
				
			}
			return treeMap;
		}
		return null;
	}
}