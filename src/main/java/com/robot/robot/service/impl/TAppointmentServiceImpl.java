package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TAppointmentDao;
import com.robot.robot.domain.TAppointmentDO;
import com.robot.robot.service.TAppointmentService;


@Service
public class TAppointmentServiceImpl implements TAppointmentService {
	@Autowired
	private TAppointmentDao tAppointmentDao;
	
	@Override
	public TAppointmentDO get(Long id){
		return tAppointmentDao.get(id);
	}
	
	@Override
	public List<TAppointmentDO> list(Map<String, Object> map){
		return tAppointmentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tAppointmentDao.count(map);
	}
	
	@Override
	public int save(TAppointmentDO tAppointment){
		return tAppointmentDao.save(tAppointment);
	}
	
	@Override
	public int update(TAppointmentDO tAppointment){
		return tAppointmentDao.update(tAppointment);
	}
	
	@Override
	public int remove(Long id){
		return tAppointmentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tAppointmentDao.batchRemove(ids);
	}

	@Override
	public List<TAppointmentDO> selectByAppointmentTime(String appointmentTime) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("appointmenttime", appointmentTime);
		return tAppointmentDao.list(map);
	}

	@Override
	public List<TAppointmentDO> selectByIdentityID(String identityid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("identityID", identityid);
		return tAppointmentDao.list(map);
	}

	@Override
	public int deleteAppointmentTime(String identityID, String appointmentTime, String type) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("identityID", identityID);
		map.put("appointmentTime", appointmentTime);
		map.put("type", type);
		return tAppointmentDao.deleteAppointmentTime(map);
	}
	
}
