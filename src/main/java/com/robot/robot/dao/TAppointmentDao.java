package com.robot.robot.dao;

import com.robot.robot.domain.TAppointmentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 预约表
 * @author yobi
 * @email ***
 * @date 2018-01-18 15:24:03
 */
@Mapper
public interface TAppointmentDao {

	TAppointmentDO get(Long id);
	
	List<TAppointmentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TAppointmentDO tAppointment);
	
	int update(TAppointmentDO tAppointment);
	
	int remove(Long id);
	
	int deleteAppointmentTime(Map<String,Object> map);

	int batchRemove(Long[] ids);
}
