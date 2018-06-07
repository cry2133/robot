package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dldao.DlKhYdkhDao;
import com.robot.robot.domain.DhKhxxDO;
import com.robot.robot.domain.DlKhYdkhDO;
import com.robot.robot.service.DlKhYdkhService;



@Service
public class DlKhYdkhServiceImpl implements DlKhYdkhService {
	@Autowired
	private DlKhYdkhDao dlKhYdkhDao;
	
	@Override
	public DlKhYdkhDO get(String yhbh){
		return dlKhYdkhDao.get(yhbh);
	}
	
	@Override
	public List<DlKhYdkhDO> list(Map<String, Object> map){
		return dlKhYdkhDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dlKhYdkhDao.count(map);
	}
	
	@Override
	public int save(DlKhYdkhDO dlKhYdkh){
		return dlKhYdkhDao.save(dlKhYdkh);
	}
	
	@Override
	public int update(DlKhYdkhDO dlKhYdkh){
		return dlKhYdkhDao.update(dlKhYdkh);
	}
	
	@Override
	public int remove(String yhbh){
		return dlKhYdkhDao.remove(yhbh);
	}
	
	@Override
	public int batchRemove(String[] yhbhs){
		return dlKhYdkhDao.batchRemove(yhbhs);
	}

	@Override
	public List<DhKhxxDO> getForZjhm(String zjhm) {
		return dlKhYdkhDao.getForZjhm(zjhm);
	}
	
}
