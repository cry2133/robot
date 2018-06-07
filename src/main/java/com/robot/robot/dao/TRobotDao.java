package com.robot.robot.dao;

import com.robot.robot.domain.TRobotDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机械人账户
 * @author yobi
 * @email ***
 * @date 2018-01-16 10:58:34
 */
@Mapper
public interface TRobotDao {

	TRobotDO get(Long id);
	
	List<TRobotDO> list(Map<String,Object> map);
	
	List<TRobotDO> selectByRobotID(String robotNo);
	
	int count(Map<String,Object> map);
	
	int save(TRobotDO tRobot);
	
	int update(TRobotDO tRobot);
	
	int updateByrobot(TRobotDO tRobot);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
