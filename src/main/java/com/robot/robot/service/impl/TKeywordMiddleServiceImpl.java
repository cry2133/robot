package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TKeywordMiddleDao;
import com.robot.robot.domain.TKeywordMiddleDO;
import com.robot.robot.service.TKeywordMiddleService;



@Service
public class TKeywordMiddleServiceImpl implements TKeywordMiddleService {
	@Autowired
	private TKeywordMiddleDao tKeywordMiddleDao;
	
	@Override
	public TKeywordMiddleDO get(Long middleId){
		return tKeywordMiddleDao.get(middleId);
	}
	
	@Override
	public List<TKeywordMiddleDO> list(Map<String, Object> map){
		return tKeywordMiddleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tKeywordMiddleDao.count(map);
	}
	
	@Override
	public int save(TKeywordMiddleDO tKeywordMiddle){
		return tKeywordMiddleDao.save(tKeywordMiddle);
	}
	
	@Override
	public int update(TKeywordMiddleDO tKeywordMiddle){
		return tKeywordMiddleDao.update(tKeywordMiddle);
	}
	
	@Override
	public int remove(Long middleId){
		return tKeywordMiddleDao.remove(middleId);
	}
	
	@Override
	public int batchRemove(Long[] middleIds){
		return tKeywordMiddleDao.batchRemove(middleIds);
	}

	@Override
	public boolean existKeygroup(String keygroup,Long faqId) {
		boolean status =true;
		Map<String, Object> map=new HashMap<String, Object>();
		if(faqId !=0){
			map.put("faqId", faqId);
		}
		map.put("keygroup", keygroup);
		List<TKeywordMiddleDO> list=tKeywordMiddleDao.getListByKeygroup(map);
		if(list.size()<=0){
			status =false;
		}
		return status;
	}

	@Override
	public int removeByFaqId(Long faqId) {
		return tKeywordMiddleDao.removeByFaqId(faqId);
	}

	@Override
	public TKeywordMiddleDO getByFaqId(Long faqId) {
		return tKeywordMiddleDao.getByFaqId(faqId);
	}

	@Override
	public int batchRemoveByFaqIds(Long[] faqIds) {
		return tKeywordMiddleDao.batchRemoveByFaqIds(faqIds);
	}

	@Override
	public List<TKeywordMiddleDO> getBykeywordId(String keywordId) {
		return tKeywordMiddleDao.getBykeywordId(keywordId);
	}
	
	@Override
	public boolean existKeygroupName(String keygroupName) {
		boolean status =true;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keygroupName", keygroupName);
		List<TKeywordMiddleDO> list=tKeywordMiddleDao.getListByKeygroup(map);
		if(list.size()<=0){
			status =false;
		}
		return status;
	}
}
