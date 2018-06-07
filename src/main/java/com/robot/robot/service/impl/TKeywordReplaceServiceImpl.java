package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TKeywordReplaceDao;
import com.robot.robot.domain.TKeywordReplaceDO;
import com.robot.robot.service.TKeywordReplaceService;



@Service
public class TKeywordReplaceServiceImpl implements TKeywordReplaceService {
	@Autowired
	private TKeywordReplaceDao tKeywordReplaceDao;
	
	@Override
	public TKeywordReplaceDO get(Long replaceId){
		return tKeywordReplaceDao.get(replaceId);
	}
	
	@Override
	public List<TKeywordReplaceDO> list(Map<String, Object> map){
		return tKeywordReplaceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tKeywordReplaceDao.count(map);
	}
	
	@Override
	public int save(TKeywordReplaceDO tKeywordReplace){
		return tKeywordReplaceDao.save(tKeywordReplace);
	}
	
	@Override
	public int update(TKeywordReplaceDO tKeywordReplace){
		return tKeywordReplaceDao.update(tKeywordReplace);
	}
	
	@Override
	public int remove(Long replaceId){
		return tKeywordReplaceDao.remove(replaceId);
	}
	
	@Override
	public int batchRemove(Long[] replaceIds){
		return tKeywordReplaceDao.batchRemove(replaceIds);
	}
	
}
