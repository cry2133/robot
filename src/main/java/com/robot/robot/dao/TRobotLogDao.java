package com.robot.robot.dao;

import com.robot.robot.domain.TRobotLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机器人故障日志表
 * @author yobi
 * @email ***
 * @date 2018-01-23 11:17:42
 */
@Mapper
public interface TRobotLogDao {

	TRobotLogDO get(Integer id);
	
	List<TRobotLogDO> list(Map<String,Object> map);
	
	/** 机器人日志统计 */
	List<TRobotLogDO> statisticsList(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TRobotLogDO tRobotLog);
	
	int update(TRobotLogDO tRobotLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
