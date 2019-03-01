package com.robot.robot.service;

import com.robot.robot.domain.TFaqLogDO;

import java.util.List;
import java.util.Map;

/**
 * 问答记录表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:39
 */
public interface TFaqLogService {
	
	TFaqLogDO get(Long id);
	
	List<TFaqLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TFaqLogDO tFaqLog);
	
	int update(TFaqLogDO tFaqLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TFaqLogDO> statistics(Map<String,Object> map);

	List<TFaqLogDO> all(Map<String,Object> map);

}
