package com.robot.robot.service;

import com.robot.robot.domain.TQueueInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 智能排队
 * 
 * @author yobi
 * @email ***
 * @date 2018-06-19 18:30:53
 */
public interface TQueueInfoService {
	
	TQueueInfoDO get(Integer id);
	
	List<TQueueInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TQueueInfoDO tQueueInfo);
	
	int update(TQueueInfoDO tQueueInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
