package com.robot.robot.dao;

import com.robot.robot.domain.TMajorDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 专业表
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
@Mapper
public interface TMajorDao {

	TMajorDO get(Long majorId);
	
	List<TMajorDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TMajorDO tMajor);
	
	int update(TMajorDO tMajor);
	
	int remove(Long major_id);
	
	int batchRemove(Long[] majorIds);
	
	TMajorDO getByName(String name);
}
