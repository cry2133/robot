package com.robot.robot.service;

import com.robot.robot.domain.TEntranceEntityDO;

import java.util.List;
import java.util.Map;

/**
 * 场景入口与词槽实体关联表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-09-04 14:11:09
 */
public interface TEntranceEntityService {
	
	TEntranceEntityDO get(Long id);
	
	List<TEntranceEntityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TEntranceEntityDO tEntranceEntity);
	
	int update(TEntranceEntityDO tEntranceEntity);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
