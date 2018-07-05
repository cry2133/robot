package com.robot.robot.service;

import com.robot.robot.domain.TQueueDO;

import java.util.List;
import java.util.Map;

/**
 * 智能排队
 * 
 */
public interface TQueueService {
	
	TQueueDO get(Long id);
	
	List<TQueueDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TQueueDO tQueue);
	
	int update(TQueueDO tQueue);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
