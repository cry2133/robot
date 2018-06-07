package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dldao.DlLcCbxxDao;
import com.robot.robot.domain.DlCbdlDO;
import com.robot.robot.domain.DlLcCbxxDO;
import com.robot.robot.service.DlLcCbxxService;



@Service
public class DlLcCbxxServiceImpl implements DlLcCbxxService {
	@Autowired
	private DlLcCbxxDao dlLcCbxxDao;
	
	@Override
	public DlLcCbxxDO get(String gzdbh){
		return dlLcCbxxDao.get(gzdbh);
	}
	
	@Override
	public List<DlLcCbxxDO> list(Map<String, Object> map){
		return dlLcCbxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dlLcCbxxDao.count(map);
	}
	
	@Override
	public int save(DlLcCbxxDO dlLcCbxx){
		return dlLcCbxxDao.save(dlLcCbxx);
	}
	
	@Override
	public int update(DlLcCbxxDO dlLcCbxx){
		return dlLcCbxxDao.update(dlLcCbxx);
	}
	
	@Override
	public int remove(String gzdbh){
		return dlLcCbxxDao.remove(gzdbh);
	}
	
	@Override
	public int batchRemove(String[] gzdbhs){
		return dlLcCbxxDao.batchRemove(gzdbhs);
	}

	@Override
	public List<DlCbdlDO> getlccbxxForAPP(Map<String, Object> map) {
		return dlLcCbxxDao.getlccbxxForAPP(map);
	}
	
}
