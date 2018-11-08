package com.robot.robot.service.impl;

import com.robot.robot.dao.TEntranceEntityDao;
import com.robot.robot.domain.TEntranceEntityDO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TEntranceDao;
import com.robot.robot.domain.TEntranceDO;
import com.robot.robot.service.TEntranceService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class TEntranceServiceImpl implements TEntranceService {
	@Resource
	private TEntranceDao tEntranceDao;
	@Resource
	private TEntranceEntityDao tEntranceEntityDao;
	
	@Override
	public TEntranceDO get(Long id){
		return tEntranceDao.get(id);
	}
	
	@Override
	public List<TEntranceDO> list(Map<String, Object> map){
		return tEntranceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tEntranceDao.count(map);
	}

	@Transactional
	@Override
	public int save(TEntranceDO tEntrance){
		int count = tEntranceDao.save(tEntrance);
		Long id = tEntrance.getId();
		List<Long> entityIds = tEntrance.getEntityIds();
		if(entityIds.size() > 0){
			List<TEntranceEntityDO> list = new ArrayList<>();
			for (Long entityId : entityIds) {
				TEntranceEntityDO ee = new TEntranceEntityDO();
				ee.setEntranceId(id);
				ee.setEntityId(entityId);
				list.add(ee);
			}
			tEntranceEntityDao.batchSave(list);
		}
		return count;
	}

	@Transactional
	@Override
	public int update(TEntranceDO tEntrance){
		tEntranceEntityDao.removeByEntranceId(tEntrance.getId());
		List<Long> entityIds = tEntrance.getEntityIds();
		if(entityIds.size() > 0){
			List<TEntranceEntityDO> list = new ArrayList<>();
			for (Long entityId : entityIds) {
				TEntranceEntityDO ee = new TEntranceEntityDO();
				ee.setEntranceId(tEntrance.getId());
				ee.setEntityId(entityId);
				list.add(ee);
			}
			tEntranceEntityDao.batchSave(list);
		}
		return tEntranceDao.update(tEntrance);
	}

	@Transactional
	@Override
	public int remove(Long id){
		tEntranceEntityDao.removeByEntranceId(id);
		return tEntranceDao.remove(id);
	}


	@Override
	public int batchRemove(Long[] ids){
		return tEntranceDao.batchRemove(ids);
	}
	
}
