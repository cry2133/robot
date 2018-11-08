package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TEntranceEntityDao;
import com.robot.robot.domain.TEntranceEntityDO;
import com.robot.robot.service.TEntranceEntityService;



@Service
public class TEntranceEntityServiceImpl implements TEntranceEntityService {
	@Autowired
	private TEntranceEntityDao tEntranceEntityDao;
	
	@Override
	public TEntranceEntityDO get(Long id){
		return tEntranceEntityDao.get(id);
	}
	
	@Override
	public List<TEntranceEntityDO> list(Map<String, Object> map){
		return tEntranceEntityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tEntranceEntityDao.count(map);
	}
	
	@Override
	public int save(TEntranceEntityDO tEntranceEntity){
		return tEntranceEntityDao.save(tEntranceEntity);
	}
	
	@Override
	public int update(TEntranceEntityDO tEntranceEntity){
		return tEntranceEntityDao.update(tEntranceEntity);
	}
	
	@Override
	public int remove(Long id){
		return tEntranceEntityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tEntranceEntityDao.batchRemove(ids);
	}
	
}
