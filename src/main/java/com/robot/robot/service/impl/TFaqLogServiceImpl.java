package com.robot.robot.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TFaqLogDao;
import com.robot.robot.domain.TFaqLogDO;
import com.robot.robot.service.TFaqLogService;

import javax.annotation.Resource;


@Service
public class TFaqLogServiceImpl implements TFaqLogService {
	@Resource
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

	@Override
	public List<TFaqLogDO> statistics(Map<String,Object> map){
		return tFaqLogDao.statistics(map);
	}

	@Override
	public List<TFaqLogDO> all(Map<String,Object> map){
		return tFaqLogDao.all(map);
	}
	
}
