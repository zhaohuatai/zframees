package com.zht.common.sys.service.impl;

import java.util.ArrayList;
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

		DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(
				POType.H_NO_NC));
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
				+ " g.parentDepartment.id as _parentId "
				+ " g.name as text "
				+ " @from Department g ";
		DataSet dataSet = baseDaoImpl.loadDataSet(hql, new ParamObject(
				POType.H_NO_NC));
		Map root = dataSet.getRows().get(0);
		DepartmentUtil.traverse(root, dataSet.getRows());
		List<Map> mapList = new ArrayList<Map>();
		mapList.add(root);
		return mapList;
	}

	@Override
	public void deleteDepartment(Long id) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findDepartmentIdsByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		String hql = " select du.department.id from DepartmentUser  du where du.rbacUser.id=:userId ";
		List<Long> groupIdList = (List<Long>) baseDaoImpl.findJustList(hql,
				new ParamObject(POType.H_NO_NC).addAllowNull("userId", userId));
		return groupIdList;
	}

}