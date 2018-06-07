package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dldao.DhKhdfDao;
import com.robot.robot.domain.DhKhdfDO;
import com.robot.robot.service.DhKhdfService;



@Service
public class DhKhdfServiceImpl implements DhKhdfService {
	@Autowired
	private DhKhdfDao dhKhdfDao;
	
	
	@Override
	public List<DhKhdfDO> list(Map<String, Object> map){
		return dhKhdfDao.list(map);
	}


	@Override
	public int count(Map<String, Object> map) {
		return dhKhdfDao.count(map);
	}


	@Override
	public List<DhKhdfDO> allList(Map<String, Object> map) {
		return dhKhdfDao.allList(map);
	}

}
