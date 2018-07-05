package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TQueueDao;
import com.robot.robot.domain.TQueueDO;
import com.robot.robot.service.TQueueService;



@Service
public class TQueueServiceImpl implements TQueueService {
	@Autowired
	private TQueueDao tQueueDao;
	
	@Override
	public TQueueDO get(Long id){
		return tQueueDao.get(id);
	}
	
	@Override
	public List<TQueueDO> list(Map<String, Object> map){
		return tQueueDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tQueueDao.count(map);
	}
	
	@Override
	public int save(TQueueDO tQueue){
		return tQueueDao.save(tQueue);
	}
	
	@Override
	public int update(TQueueDO tQueue){
		return tQueueDao.update(tQueue);
	}
	
	@Override
	public int remove(Long id){
		return tQueueDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tQueueDao.batchRemove(ids);
	}
	
}
