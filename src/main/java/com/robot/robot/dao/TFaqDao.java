package com.robot.robot.dao;

import com.robot.robot.domain.TFaqDO;

import java.util.List;
import java.util.Map;

import com.robot.robot.domain.TRepositoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 问答表
 */
@Mapper
@Repository
public interface TFaqDao {

	TFaqDO get(Long faqId);

	List<TFaqDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);
	
	int countParent(String parentId);
	
	int save(TFaqDO tFaq);
	
	int update(TFaqDO tFaq);
	
	int remove(Long faq_id);
	
	int batchRemove(Long[] faqIds);

	List<TFaqDO> getLikeByQuestion(Map<String,Object> map);
	
	TFaqDO getByQuestion(String question);

	List<TRepositoryDO> getRepositoryIdByRobotNo(String robotNo);

	List<TRepositoryDO> getRepositoryIdByUserId(Long userId);

}
