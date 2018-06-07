package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TAppSoftwareDao;
import com.robot.robot.domain.TAppSoftwareDO;
import com.robot.robot.service.TAppSoftwareService;



@Service
public class TAppSoftwareServiceImpl implements TAppSoftwareService {
	@Autowired
	private TAppSoftwareDao tAppSoftwareDao;
	
	@Override
	public TAppSoftwareDO get(Integer id){
		return tAppSoftwareDao.get(id);
	}
	
	@Override
	public List<TAppSoftwareDO> list(Map<String, Object> map){
		return tAppSoftwareDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tAppSoftwareDao.count(map);
	}
	
	@Override
	public int save(TAppSoftwareDO tAppSoftware){
		return tAppSoftwareDao.save(tAppSoftware);
	}
	
	@Override
	public int update(TAppSoftwareDO tAppSoftware){
		return tAppSoftwareDao.update(tAppSoftware);
	}
	
	@Override
	public int remove(Integer id){
		return tAppSoftwareDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tAppSoftwareDao.batchRemove(ids);
	}

	@Override
	public TAppSoftwareDO getNewAPK(Map<String, Object> map) {
		return tAppSoftwareDao.getNewAPK(map);
	}
	
}
