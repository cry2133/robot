package com.robot.robot.dldao;

import com.robot.robot.domain.DhKhdfDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 客户电费-来源回流库
 * @author yobi
 * @email ***
 * @date 2018-03-28 14:05:50
 */
@Mapper
public interface DhKhdfDao {
	
	List<DhKhdfDO> list(Map<String,Object> map);
	
	List<DhKhdfDO> allList(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
