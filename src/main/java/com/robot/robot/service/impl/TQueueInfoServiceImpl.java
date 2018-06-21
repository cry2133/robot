package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TQueueInfoDao;
import com.robot.robot.domain.TQueueInfoDO;
import com.robot.robot.service.TQueueInfoService;



@Service
public class TQueueInfoServiceImpl implements TQueueInfoService {
	@Autowired
	private TQueueInfoDao tQueueInfoDao;
	
	@Override
	public TQueueInfoDO get(Integer id){
		return tQueueInfoDao.get(id);
	}
	
	@Override
	public List<TQueueInfoDO> list(Map<String, Object> map){
		return tQueueInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tQueueInfoDao.count(map);
	}
	
	@Override
	public int save(TQueueInfoDO tQueueInfo){
		return tQueueInfoDao.save(tQueueInfo);
	}
	
	@Override
	public int update(TQueueInfoDO tQueueInfo){
		return tQueueInfoDao.update(tQueueInfo);
	}
	
	@Override
	public int remove(Integer id){
		return tQueueInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tQueueInfoDao.batchRemove(ids);
	}
	
}
