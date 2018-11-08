package com.robot.robot.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TEntityDao;
import com.robot.robot.domain.TEntityDO;
import com.robot.robot.service.TEntityService;

import javax.annotation.Resource;


@Service
public class TEntityServiceImpl implements TEntityService {
	@Resource
	private TEntityDao tEntityDao;
	
	@Override
	public TEntityDO get(Long id){
		return tEntityDao.get(id);
	}
	
	@Override
	public List<TEntityDO> list(Map<String, Object> map){
		return tEntityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tEntityDao.count(map);
	}
	
	@Override
	public int save(TEntityDO tEntity){
		return tEntityDao.save(tEntity);
	}
	
	@Override
	public int update(TEntityDO tEntity){
		return tEntityDao.update(tEntity);
	}
	
	@Override
	public int remove(Long id){
		return tEntityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tEntityDao.batchRemove(ids);
	}
	
}
