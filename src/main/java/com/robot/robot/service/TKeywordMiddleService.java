package com.robot.robot.service;

import com.robot.robot.domain.TKeywordMiddleDO;

import java.util.List;
import java.util.Map;

/**
 * 问答与关键字关系表
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:26:26
 */
public interface TKeywordMiddleService {
	
	TKeywordMiddleDO get(Long middleId);
	
	List<TKeywordMiddleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TKeywordMiddleDO tKeywordMiddle);
	
	int update(TKeywordMiddleDO tKeywordMiddle);
	
	int remove(Long middleId);
	
	int batchRemove(Long[] middleIds);
	
	boolean existKeygroup(String keygroup,Long faqId);
	
	TKeywordMiddleDO getByFaqId(Long faqId);
	
	int removeByFaqId(Long faqId);
	
	int batchRemoveByFaqIds(Long[] faqIds);
	
	List<TKeywordMiddleDO> getBykeywordId(String keywordId);
	
	boolean existKeygroupName(String keygroupName);
}
