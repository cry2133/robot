package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TNonexistentFaqDao;
import com.robot.robot.domain.TNonexistentFaqDO;
import com.robot.robot.service.TNonexistentFaqService;



@Service
public class TNonexistentFaqServiceImpl implements TNonexistentFaqService {
	@Autowired
	private TNonexistentFaqDao tNonexistentFaqDao;
	
	@Override
	public TNonexistentFaqDO get(Long id){
		return tNonexistentFaqDao.get(id);
	}
	
	@Override
	public List<TNonexistentFaqDO> list(Map<String, Object> map){
		return tNonexistentFaqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tNonexistentFaqDao.count(map);
	}
	
	@Override
	public int save(TNonexistentFaqDO tNonexistentFaq){
		return tNonexistentFaqDao.save(tNonexistentFaq);
	}
	
	@Override
	public int update(TNonexistentFaqDO tNonexistentFaq){
		return tNonexistentFaqDao.update(tNonexistentFaq);
	}
	
	@Override
	public int remove(Long id){
		return tNonexistentFaqDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tNonexistentFaqDao.batchRemove(ids);
	}

	@Override
	public boolean existQuestion(String question) {
		boolean state=false;
		List<TNonexistentFaqDO> list=tNonexistentFaqDao.existQuestion(question);
		if(list.size()<=0){
			state=true;
		}
		return state;
	}
	
}
