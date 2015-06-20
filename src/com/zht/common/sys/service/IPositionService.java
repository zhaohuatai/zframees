package com.zht.common.sys.service;

import java.util.List;
import java.util.Map;

import org.zht.framework.service.IBaseService;

import com.zht.common.sys.model.Position;
/**
 * 职位Service接口
* @ClassName :IPositionService     
* @Description :   
* @createTime :2015年6月9日  下午4:15:55   
* @author ：zhaohuatai   
* @version :1.0
 */
public interface IPositionService extends IBaseService<Position>{

	/**
	 * 加载map(name,id)形式的职位集合
	 * @return  map(name,id)形式的职位集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> loadPositionCombox();
	
	/**
	 * 获取用户所拥有的职位ID集合（在PositionUserDetail 记录中的数据）
	 * @param userId : 用户ID
	 * @return 用户所拥有的职位ID集合
	 */
	public List<Long> findPositionIdsByUserId(Long userId);
	
	/**
	 * 根据用户所在部门集合 和 用户所拥有角色集合 获取用户能够拥有的职位ID集合
	 * @param userId : 用户ID
	 * @return 用户能够拥有的职位ID集合
	 */
	public List<Long> findAvilablePositionIdsByUserId(Long userId);
	
	/**
	 *  根据用户所在部门集合 和 用户所拥有角色集合 获取用户能够拥有的职位的map(name,id)形式的集合
	 * @param userId : 用户ID
	 * @return 用户能够拥有的职位的map(name,id)形式的集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> loadAvilablePositionComboxByUserId(Long userId);
	

}