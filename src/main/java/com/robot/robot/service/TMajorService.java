package com.robot.robot.service;

import com.robot.common.domain.Tree;
import com.robot.robot.domain.TMajorDO;

import java.util.List;
import java.util.Map;

/**
 * 专业表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
public interface TMajorService {
	
	TMajorDO get(Long majorId);
	
	List<TMajorDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TMajorDO tMajor);
	
	int update(TMajorDO tMajor);
	
	int remove(Long majorId);
	
	int batchRemove(Long[] majorIds);
	
	Tree<TMajorDO> getTree();
	
	TMajorDO getByName(String name);
}
