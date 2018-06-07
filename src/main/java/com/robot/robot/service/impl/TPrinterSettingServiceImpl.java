package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TPrinterSettingDao;
import com.robot.robot.domain.TPrinterSettingDO;
import com.robot.robot.service.TPrinterSettingService;



@Service
public class TPrinterSettingServiceImpl implements TPrinterSettingService {
	@Autowired
	private TPrinterSettingDao tPrinterSettingDao;
	
	@Override
	public TPrinterSettingDO get(Long id){
		return tPrinterSettingDao.get(id);
	}
	
	@Override
	public List<TPrinterSettingDO> list(Map<String, Object> map){
		return tPrinterSettingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tPrinterSettingDao.count(map);
	}
	
	@Override
	public int save(TPrinterSettingDO tPrinterSetting){
		return tPrinterSettingDao.save(tPrinterSetting);
	}
	
	@Override
	public int update(TPrinterSettingDO tPrinterSetting){
		return tPrinterSettingDao.update(tPrinterSetting);
	}
	
	@Override
	public int remove(Long id){
		return tPrinterSettingDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tPrinterSettingDao.batchRemove(ids);
	}
	
}
