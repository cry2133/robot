package com.robot.robot.dao;

import com.robot.robot.domain.TRobotUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机器人编号管理表
 * @author laoGF
 * @email ***
 * @date 2018-08-27 18:19:08
 */
@Mapper
public interface TRobotUserDao {

	TRobotUserDO get(Long id);
	
	List<TRobotUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TRobotUserDO tRobotRole);
	
	int update(TRobotUserDO tRobotRole);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
