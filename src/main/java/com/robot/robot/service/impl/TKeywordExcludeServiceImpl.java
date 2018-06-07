package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TKeywordExcludeDao;
import com.robot.robot.domain.TKeywordExcludeDO;
import com.robot.robot.service.TKeywordExcludeService;



@Service
public class TKeywordExcludeServiceImpl implements TKeywordExcludeService {
	@Autowired
	private TKeywordExcludeDao tKeywordExcludeDao;
	
	@Override
	public TKeywordExcludeDO get(Long id){
		return tKeywordExcludeDao.get(id);
	}
	
	@Override
	public List<TKeywordExcludeDO> list(Map<String, Object> map){
		return tKeywordExcludeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tKeywordExcludeDao.count(map);
	}
	
	@Override
	public int save(TKeywordExcludeDO tKeywordExclude){
		return tKeywordExcludeDao.save(tKeywordExclude);
	}
	
	@Override
	public int update(TKeywordExcludeDO tKeywordExclude){
		return tKeywordExcludeDao.update(tKeywordExclude);
	}
	
	@Override
	public int remove(Long id){
		return tKeywordExcludeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tKeywordExcludeDao.batchRemove(ids);
	}
	
}
