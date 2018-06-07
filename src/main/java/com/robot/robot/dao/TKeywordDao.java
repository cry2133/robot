package com.robot.robot.dao;

import com.robot.robot.domain.TKeywordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 关键字表
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
@Mapper
public interface TKeywordDao {

	TKeywordDO get(Long keywordId);
	
	List<TKeywordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TKeywordDO tKeyword);
	
	int update(TKeywordDO tKeyword);
	
	int remove(Long keyword_id);
	
	int batchRemove(Long[] keywordIds);

	List<TKeywordDO> existKeyword(String keyword);
	
	int getMaxId();
	
	TKeywordDO getByName(String name);
}
