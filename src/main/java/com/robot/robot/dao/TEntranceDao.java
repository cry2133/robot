package com.robot.robot.dao;

import com.robot.robot.domain.TEntranceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 场景入口表
 * @author laoGF
 * @email ***
 * @date 2018-09-04 10:53:08
 */
@Mapper
public interface TEntranceDao {

	TEntranceDO get(Long id);
	
	List<TEntranceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TEntranceDO tEntrance);
	
	int update(TEntranceDO tEntrance);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	Long getEntranceIdByQuestion(Map<String, Object> map);
}
