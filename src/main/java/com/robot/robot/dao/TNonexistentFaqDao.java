package com.robot.robot.dao;

import com.robot.robot.domain.TNonexistentFaqDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 未找到的问答表
 * @author yobi
 * @email ***
 * @date 2017-12-18 11:43:24
 */
@Mapper
public interface TNonexistentFaqDao {

	TNonexistentFaqDO get(Long id);
	
	List<TNonexistentFaqDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TNonexistentFaqDO tNonexistentFaq);
	
	int update(TNonexistentFaqDO tNonexistentFaq);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<TNonexistentFaqDO> existQuestion(String question);
}
