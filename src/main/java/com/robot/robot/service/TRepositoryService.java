package com.robot.robot.service;

import com.robot.common.domain.Tree;
import com.robot.robot.domain.TRepositoryDO;

import java.util.List;
import java.util.Map;

/**
 * 知识词库表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
public interface TRepositoryService {
	
	TRepositoryDO get(Long repositoryId);
	
	List<TRepositoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TRepositoryDO tRepository);
	
	int update(TRepositoryDO tRepository);
	
	int remove(Long repositoryId);
	
	int batchRemove(Long[] repositoryIds);
	
	Tree<TRepositoryDO> getTree();
	
	TRepositoryDO getByName(String name);
}
