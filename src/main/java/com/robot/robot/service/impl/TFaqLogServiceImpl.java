package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TFaqLogDao;
import com.robot.robot.domain.TFaqLogDO;
import com.robot.robot.service.TFaqLogService;



@Service
public class TFaqLogServiceImpl implements TFaqLogService {
	@Autowired
	private TFaqLogDao tFaqLogDao;
	
	@Override
	public TFaqLogDO get(Long id){
		return tFaqLogDao.get(id);
	}
	
	@Override
	public List<TFaqLogDO> list(Map<String, Object> map){
		return tFaqLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tFaqLogDao.count(map);
	}
	
	@Override
	public int save(TFaqLogDO tFaqLog){
		return tFaqLogDao.save(tFaqLog);
	}
	
	@Override
	public int update(TFaqLogDO tFaqLog){
		return tFaqLogDao.update(tFaqLog);
	}
	
	@Override
	public int remove(Long id){
		return tFaqLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tFaqLogDao.batchRemove(ids);
	}
	
}
