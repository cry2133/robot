package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TRobotDao;
import com.robot.robot.domain.TRobotDO;
import com.robot.robot.service.TRobotService;



@Service
public class TRobotServiceImpl implements TRobotService {
	@Autowired
	private TRobotDao tRobotDao;
	
	@Override
	public TRobotDO get(Long id){
		return tRobotDao.get(id);
	}
	
	@Override
	public List<TRobotDO> list(Map<String, Object> map){
		return tRobotDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tRobotDao.count(map);
	}
	
	@Override
	public int save(TRobotDO tRobot){
		return tRobotDao.save(tRobot);
	}
	
	@Override
	public int update(TRobotDO tRobot){
		return tRobotDao.update(tRobot);
	}
	
	@Override
	public int remove(Long id){
		return tRobotDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tRobotDao.batchRemove(ids);
	}

	@Override
	public List<TRobotDO> selectByRobotID(String robotNo) {
		return tRobotDao.selectByRobotID(robotNo);
	}
	
	@Override
	public List<TRobotDO> selectByRobotIDandPassowrd(String robotNo,String password) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("robotNo", robotNo);
		map.put("password", password);
		return tRobotDao.list(map);
	}

	@Override
	public int updateByrobot(TRobotDO tRobot) {
		return tRobotDao.updateByrobot(tRobot);
	}
	
}
