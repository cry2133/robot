package com.robot.robot.service;

import com.robot.robot.domain.TKeywordDO;

import java.util.List;
import java.util.Map;

/**
 * 关键字表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
public interface TKeywordService {
	
	TKeywordDO get(Long keywordId);
	
	List<TKeywordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TKeywordDO tKeyword);
	
	int update(TKeywordDO tKeyword);
	
	int remove(Long keywordId);
	
	int batchRemove(Long[] keywordIds);
	
	boolean existKeyword(String keyword);
	
	/**
	 * 关键字替换
	 * @param keyword
	 * @return
	 */
	String strReplace(String keyword);
	
	int getMaxId();
	
	TKeywordDO getByName(String keyword);
}
