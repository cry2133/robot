package com.robot.robot.dao;

import com.robot.robot.domain.TKeywordMiddleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问答与关键字关系表
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:26:26
 */
@Mapper
public interface TKeywordMiddleDao {

	TKeywordMiddleDO get(Long middleId);
	
	List<TKeywordMiddleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TKeywordMiddleDO tKeywordMiddle);
	
	int update(TKeywordMiddleDO tKeywordMiddle);
	
	int remove(Long middle_id);
	
	int batchRemove(Long[] middleIds);
	
	List<TKeywordMiddleDO> getListByKeygroup(Map<String,Object> map);
	
	int removeByFaqId(Long faqId) ;
	
	TKeywordMiddleDO getByFaqId(Long faqId);
	
	int batchRemoveByFaqIds(Long[] faqIds);
	
	List<TKeywordMiddleDO> getBykeywordId(String keywordId);
}
