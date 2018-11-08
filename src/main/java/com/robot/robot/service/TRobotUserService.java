package com.robot.robot.service;

import com.robot.robot.domain.TRobotUserDO;

import java.util.List;
import java.util.Map;

/**
 * 机器人编号管理表
 * 
 * @author laoGF
 * @email ***
 * @date 2018-08-27 18:19:08
 */
public interface TRobotUserService {
	
	TRobotUserDO get(Long id);
	
	List<TRobotUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TRobotUserDO tRobotUser);
	
	int update(TRobotUserDO tRobotUser);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
