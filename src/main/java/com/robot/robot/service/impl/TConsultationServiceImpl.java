package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TConsultationDao;
import com.robot.robot.domain.TConsultationDO;
import com.robot.robot.service.TConsultationService;



@Service
public class TConsultationServiceImpl implements TConsultationService {
	@Autowired
	private TConsultationDao tConsultationDao;
	
	@Override
	public TConsultationDO get(Long id){
		return tConsultationDao.get(id);
	}
	
	@Override
	public List<TConsultationDO> list(Map<String, Object> map){
		return tConsultationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tConsultationDao.count(map);
	}
	
	@Override
	public int save(TConsultationDO tConsultation){
		return tConsultationDao.save(tConsultation);
	}
	
	@Override
	public int update(TConsultationDO tConsultation){
		return tConsultationDao.update(tConsultation);
	}
	
	@Override
	public int remove(Long id){
		return tConsultationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tConsultationDao.batchRemove(ids);
	}
	
}
