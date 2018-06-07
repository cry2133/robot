package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TTaxInfoDao;
import com.robot.robot.domain.TTaxInfoDO;
import com.robot.robot.service.TTaxInfoService;



@Service
public class TTaxInfoServiceImpl implements TTaxInfoService {
	@Autowired
	private TTaxInfoDao tTaxInfoDao;
	
	@Override
	public TTaxInfoDO get(Integer id){
		return tTaxInfoDao.get(id);
	}
	
	@Override
	public List<TTaxInfoDO> list(Map<String, Object> map){
		return tTaxInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tTaxInfoDao.count(map);
	}
	
	@Override
	public int save(TTaxInfoDO tTaxInfo){
		return tTaxInfoDao.save(tTaxInfo);
	}
	
	@Override
	public int update(TTaxInfoDO tTaxInfo){
		return tTaxInfoDao.update(tTaxInfo);
	}
	
	@Override
	public int remove(Integer id){
		return tTaxInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tTaxInfoDao.batchRemove(ids);
	}
	
}
