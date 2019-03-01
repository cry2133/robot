package com.robot.robot.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robot.common.domain.Tree;
import com.robot.common.utils.BuildTree;
import com.robot.robot.dao.TRepositoryDao;
import com.robot.robot.domain.TRepositoryDO;
import com.robot.robot.service.TRepositoryService;

import javax.annotation.Resource;

@Service
public class TRepositoryServiceImpl implements TRepositoryService {
	@Resource
	private TRepositoryDao tRepositoryDao;
	
	@Override
	public TRepositoryDO get(Long repositoryId){
		return tRepositoryDao.get(repositoryId);
	}
	
	@Override
	public List<TRepositoryDO> list(Map<String, Object> map){
		return tRepositoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tRepositoryDao.count(map);
	}
	
	@Override
	public int save(TRepositoryDO tRepository){
		return tRepositoryDao.save(tRepository);
	}
	
	@Override
	public int update(TRepositoryDO tRepository){
		return tRepositoryDao.update(tRepository);
	}
	
	@Override
	public int remove(Long repositoryId){
		return tRepositoryDao.remove(repositoryId);
	}
	
	@Override
	public int batchRemove(Long[] repositoryIds){
		return tRepositoryDao.batchRemove(repositoryIds);
	}

	@Override
	public Tree<TRepositoryDO> getTree() {
		List<Tree<TRepositoryDO>> trees = new ArrayList<Tree<TRepositoryDO>>();
		List<TRepositoryDO> tRepositorys = tRepositoryDao.list(new HashMap<String,Object>(16));
		for (TRepositoryDO tRepository : tRepositorys) {
			Tree<TRepositoryDO> tree = new Tree<TRepositoryDO>();
			tree.setId(tRepository.getRepositoryId().toString());
			tree.setText(tRepository.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<TRepositoryDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public TRepositoryDO getByName(String name) {
		return tRepositoryDao.getByName(name);
	}

	@Override
	public Long getIdByUserId(Long userId){
		return tRepositoryDao.getIdByUserId(userId);
	}
	
}
