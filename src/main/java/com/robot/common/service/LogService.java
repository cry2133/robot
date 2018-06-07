package com.robot.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robot.common.domain.LogDO;
import com.robot.common.domain.PageDO;
import com.robot.common.utils.Query;
@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
