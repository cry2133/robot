package com.robot.robot.service;

import com.robot.robot.domain.TRobotDO;

import java.util.List;
import java.util.Map;

/**
 * 机械人账户
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-16 10:58:34
 */
public interface TRobotService {
	
	TRobotDO get(Long id);
	
	List<TRobotDO> list(Map<String, Object> map);
	
	List<TRobotDO> selectByRobotID(String robotNo);
	
	List<TRobotDO> selectByRobotIDandPassowrd(String robotNo,String password);
	
	int count(Map<String, Object> map);
	
	int save(TRobotDO tRobot);
	
	int update(TRobotDO tRobot);
	
	int updateByrobot(TRobotDO tRobot);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
