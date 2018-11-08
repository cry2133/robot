package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TRobotUserDao;
import com.robot.robot.domain.TRobotUserDO;
import com.robot.robot.service.TRobotUserService;



@Service
public class TRobotUserServiceImpl implements TRobotUserService {
	@Autowired
	private TRobotUserDao tRobotUserDao;
	
	@Override
	public TRobotUserDO get(Long id){
		return tRobotUserDao.get(id);
	}
	
	@Override
	public List<TRobotUserDO> list(Map<String, Object> map){
		return tRobotUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tRobotUserDao.count(map);
	}
	
	@Override
	public int save(TRobotUserDO tRobotUser){
		return tRobotUserDao.save(tRobotUser);
	}
	
	@Override
	public int update(TRobotUserDO tRobotUser){
		return tRobotUserDao.update(tRobotUser);
	}
	
	@Override
	public int remove(Long id){
		return tRobotUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tRobotUserDao.batchRemove(ids);
	}
	
}
