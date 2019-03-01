package com.robot.robot.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TRobotLogDao;
import com.robot.robot.domain.TRobotLogDO;
import com.robot.robot.service.TRobotLogService;

import javax.annotation.Resource;


@Service
public class TRobotLogServiceImpl implements TRobotLogService {
	@Resource
	private TRobotLogDao tRobotLogDao;
	
	@Override
	public TRobotLogDO get(Integer id){
		return tRobotLogDao.get(id);
	}
	
	@Override
	public List<TRobotLogDO> list(Map<String, Object> map){
		return tRobotLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tRobotLogDao.count(map);
	}
	
	@Override
	public int save(TRobotLogDO tRobotLog){
		return tRobotLogDao.save(tRobotLog);
	}
	
	@Override
	public int update(TRobotLogDO tRobotLog){
		return tRobotLogDao.update(tRobotLog);
	}
	
	@Override
	public int remove(Integer id){
		return tRobotLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tRobotLogDao.batchRemove(ids);
	}

	@Override
	public List<TRobotLogDO> statisticsList(Map<String, Object> map) {
		return tRobotLogDao.statisticsList(map);
	}

}
