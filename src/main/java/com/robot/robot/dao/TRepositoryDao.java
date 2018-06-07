package com.robot.robot.dao;

import com.robot.robot.domain.TRepositoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 知识词库表
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:58
 */
@Mapper
public interface TRepositoryDao {

	TRepositoryDO get(Long repositoryId);
	
	List<TRepositoryDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TRepositoryDO tRepository);
	
	int update(TRepositoryDO tRepository);
	
	int remove(Long repository_id);
	
	int batchRemove(Long[] repositoryIds);

	TRepositoryDO getByName(String name);
}
