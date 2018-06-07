package com.robot.robot.service;

import com.robot.robot.domain.TKeywordExcludeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
public interface TKeywordExcludeService {
	
	TKeywordExcludeDO get(Long id);
	
	List<TKeywordExcludeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TKeywordExcludeDO tKeywordExclude);
	
	int update(TKeywordExcludeDO tKeywordExclude);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
