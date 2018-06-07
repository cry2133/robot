package com.robot.robot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.common.domain.Tree;
import com.robot.common.utils.BuildTree;
import com.robot.robot.dao.TMajorDao;
import com.robot.robot.domain.TMajorDO;
import com.robot.robot.service.TMajorService;



@Service
public class TMajorServiceImpl implements TMajorService {
	@Autowired
	private TMajorDao tMajorDao;
	
	@Override
	public TMajorDO get(Long majorId){
		return tMajorDao.get(majorId);
	}
	
	@Override
	public List<TMajorDO> list(Map<String, Object> map){
		return tMajorDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tMajorDao.count(map);
	}
	
	@Override
	public int save(TMajorDO tMajor){
		return tMajorDao.save(tMajor);
	}
	
	@Override
	public int update(TMajorDO tMajor){
		return tMajorDao.update(tMajor);
	}
	
	@Override
	public int remove(Long majorId){
		return tMajorDao.remove(majorId);
	}
	
	@Override
	public int batchRemove(Long[] majorIds){
		return tMajorDao.batchRemove(majorIds);
	}

	@Override
	public  Tree<TMajorDO> getTree() {
		List<Tree<TMajorDO>> trees = new ArrayList<Tree<TMajorDO>>();
		List<TMajorDO> tMajors = tMajorDao.list(new HashMap<String,Object>(16));
		for (TMajorDO tMajor : tMajors) {
			Tree<TMajorDO> tree = new Tree<TMajorDO>();
			tree.setId(tMajor.getMajorId().toString());
			tree.setParentId(tMajor.getParent().toString());
			tree.setText(tMajor.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<TMajorDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public TMajorDO getByName(String name) {
		return tMajorDao.getByName(name);
	}

}
