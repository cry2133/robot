package com.robot.robot.service;

import com.robot.robot.domain.TKeywordReplaceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
public interface TKeywordReplaceService {
	
	TKeywordReplaceDO get(Long replaceId);
	
	List<TKeywordReplaceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TKeywordReplaceDO tKeywordReplace);
	
	int update(TKeywordReplaceDO tKeywordReplace);
	
	int remove(Long replaceId);
	
	int batchRemove(Long[] replaceIds);
}
