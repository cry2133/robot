package com.robot.robot.service;

import com.robot.robot.domain.TAppointmentDO;

import java.util.List;
import java.util.Map;

/**
 * 预约表
 * 
 * @author yobi
 * @email ***
 * @date 2018-01-18 15:24:03
 */
public interface TAppointmentService {
	
	TAppointmentDO get(Long id);
	
	List<TAppointmentDO> list(Map<String, Object> map);
	
	List<TAppointmentDO> selectByAppointmentTime(String appointmentTime);
	
	List<TAppointmentDO> selectByIdentityID(String identityid);
	
	int count(Map<String, Object> map);
	
	int save(TAppointmentDO tAppointment);
	
	int update(TAppointmentDO tAppointment);
	
	int remove(Long id);

	int deleteAppointmentTime(String identityID,String appointmentTime,String type);
	
	int batchRemove(Long[] ids);
}
