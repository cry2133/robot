package com.robot.robot.service;

import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.domain.TNewFaqDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 新问答表
 * 
 * @author laoGF
 * @date 2018-08-09 19:03:40
 */
public interface TNewFaqService {
	
	TNewFaqDO get(Long id);
	
	List<TNewFaqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TNewFaqDO tNewFaq);
	
	int update(TNewFaqDO tNewFaq);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	FaqRequestBean searchAnswer(String question,String robotNo);

	String getLikeByQuestion(String question,String robotNo);

	String getAnswerByXunFei(String question) throws Exception;

	String getAnswerByTuLing(String question) throws Exception;

	List<String> intelligentSearch(String question, Map<String, String> stringMap);

	Long keywordCompare(String question, Map<String, String> stringMap, double matchValue);

	Long nearestDocument(String question, Map<String, String> stringMap, String model_file_name) throws IOException;

	List<String> verify(TNewFaqDO tNewFaq);

}
