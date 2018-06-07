package com.robot.robot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robot.robot.dldao.DlZwYsDfJlDao;
import com.robot.robot.domain.DlZwYsDfJlDO;
import com.robot.robot.service.DlZwYsDfJlService;

@Service
public class DlZwYsDfJlServiceImpl implements DlZwYsDfJlService {

	@Autowired
	private DlZwYsDfJlDao dlZwYsDfJlDao;
	
	@Override
	public List<DlZwYsDfJlDO> list(Map<String, Object> map) {
		return dlZwYsDfJlDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dlZwYsDfJlDao.count(map);
	}

}
