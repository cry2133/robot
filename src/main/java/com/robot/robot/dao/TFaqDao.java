package com.robot.robot.dao;

import com.robot.robot.domain.TFaqDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 问答表
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
@Mapper
public interface TFaqDao {

	TFaqDO get(Long faqId);
	
	List<TFaqDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int countParent(String parentId);
	
	int save(TFaqDO tFaq);
	
	int update(TFaqDO tFaq);
	
	int remove(Long faq_id);
	
	int batchRemove(Long[] faqIds);

	List<TFaqDO> getLikeByQuestion(String question);
	
	List<TFaqDO> getLikeByQuestionForParent(Map<String,Object> map);
	
	TFaqDO getByQuestion(String question);
}
