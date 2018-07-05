package com.robot.robot.dao;

import com.robot.robot.domain.TQueueDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 智能排队
 */
@Mapper
public interface TQueueDao {

	TQueueDO get(Long id);
	
	List<TQueueDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TQueueDO tQueue);
	
	int update(TQueueDO tQueue);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
