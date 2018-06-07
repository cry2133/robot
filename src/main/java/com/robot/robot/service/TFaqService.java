package com.robot.robot.service;

import com.robot.common.domain.NotfoundException;
import com.robot.robot.domain.TFaqDO;

import java.util.List;
import java.util.Map;

/**
 * 问答表
 * 
 * @author yobi
 * @email ***
 * @date 2017-11-30 19:29:57
 */
public interface TFaqService {
	
	TFaqDO get(Long faqId);
	
	List<TFaqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	//计算下级的数据数量
	int countParent(String parentId);
	
	int save(TFaqDO tFaq);
	
	int update(TFaqDO tFaq);
	
	int remove(Long faqId);
	
	int batchRemove(Long[] faqIds);
	
	/**
	 * 查询问答 for app
	 * @param gc
	 * @return
	 */
	long searchFaqForApp(String gc)  throws NotfoundException ;
	
	TFaqDO getLikeByQuestion(String question);
	
	TFaqDO getLikeByQuestionForParent(String question,String parentId);
	
	boolean importFaqsExcelSources(String content) throws Exception;
}
