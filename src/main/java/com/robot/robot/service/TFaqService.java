package com.robot.robot.service;

import com.robot.common.domain.NotfoundException;
import com.robot.robot.domain.TFaqDO;
import com.robot.robot.domain.TRepositoryDO;

import java.util.List;
import java.util.Map;

/**
 * 问答表
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
	
	TFaqDO getLikeByQuestion(Map<String,Object> map);
	

	boolean importFaqsExcelSources(String content) throws Exception;


	List<TRepositoryDO> getRepositoryIdByRobotNo(String robotNo);

	List<TRepositoryDO> getRepositoryIdByUserId(Long userId);


}
