package com.robot.robot.service;

import com.robot.robot.domain.TEntityDO;

import java.util.List;
import java.util.Map;

/**
 * 实体表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-09-03 14:09:43
 */
public interface TEntityService {
	
	TEntityDO get(Long id);
	
	List<TEntityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TEntityDO tEntity);
	
	int update(TEntityDO tEntity);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
