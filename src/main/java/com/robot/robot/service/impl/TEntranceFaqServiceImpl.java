package com.robot.robot.service.impl;

import com.robot.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TEntranceFaqDao;
import com.robot.robot.domain.TEntranceFaqDO;
import com.robot.robot.service.TEntranceFaqService;

import javax.annotation.Resource;


@Service
public class TEntranceFaqServiceImpl implements TEntranceFaqService {
	@Resource
	private TEntranceFaqDao tEntranceFaqDao;
	
	@Override
	public TEntranceFaqDO get(Long id){
		return tEntranceFaqDao.get(id);
	}
	
	@Override
	public List<TEntranceFaqDO> list(Map<String, Object> map){
		return tEntranceFaqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tEntranceFaqDao.count(map);
	}
	
	@Override
	public int save(TEntranceFaqDO tEntranceFaq){
		return tEntranceFaqDao.save(tEntranceFaq);
	}
	
	@Override
	public int update(TEntranceFaqDO tEntranceFaq){
		return tEntranceFaqDao.update(tEntranceFaq);
	}
	
	@Override
	public int remove(Long id){
		return tEntranceFaqDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tEntranceFaqDao.batchRemove(ids);
	}


	/**
	 * 多轮问答问题-全文检索
	 */
	@Override
	public List<TEntranceFaqDO> getLikeByQuestion(String question,String robotNo){
		Map<String,Object> map = new HashMap<>();
		map.put("question",question);
		map.put("robotNo",robotNo);
		List<TEntranceFaqDO> tEntranceFaq = tEntranceFaqDao.getLikeByQuestion(map);
		return tEntranceFaqDao.getLikeByQuestion(map);
	}
	
}
