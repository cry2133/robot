package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TIdentityInfoDao;
import com.robot.robot.domain.TIdentityInfoDO;
import com.robot.robot.service.TIdentityInfoService;



@Service
public class TIdentityInfoServiceImpl implements TIdentityInfoService {
	@Autowired
	private TIdentityInfoDao tIdentityInfoDao;
	
	@Override
	public TIdentityInfoDO get(Long id){
		return tIdentityInfoDao.get(id);
	}
	
	@Override
	public List<TIdentityInfoDO> list(Map<String, Object> map){
		return tIdentityInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tIdentityInfoDao.count(map);
	}
	
	@Override
	public int save(TIdentityInfoDO tIdentityInfo){
		return tIdentityInfoDao.save(tIdentityInfo);
	}
	
	@Override
	public int update(TIdentityInfoDO tIdentityInfo){
		return tIdentityInfoDao.update(tIdentityInfo);
	}
	
	@Override
	public int remove(Long id){
		return tIdentityInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tIdentityInfoDao.batchRemove(ids);
	}

	@Override
	public TIdentityInfoDO selectByIdentityID(String identityID) {
		return tIdentityInfoDao.selectByIdentityID(identityID);
	}
	
}
