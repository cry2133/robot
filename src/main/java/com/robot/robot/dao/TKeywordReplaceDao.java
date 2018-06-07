package com.robot.robot.dao;

import com.robot.robot.domain.TKeywordReplaceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yobi
 * @email ***
 * @date 2017-12-12 14:48:51
 */
@Mapper
public interface TKeywordReplaceDao {

	TKeywordReplaceDO get(Long replaceId);
	
	List<TKeywordReplaceDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TKeywordReplaceDO tKeywordReplace);
	
	int update(TKeywordReplaceDO tKeywordReplace);
	
	int remove(Long replace_id);
	
	int batchRemove(Long[] replaceIds);
}
