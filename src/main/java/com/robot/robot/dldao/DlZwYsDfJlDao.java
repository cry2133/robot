package com.robot.robot.dldao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.robot.robot.domain.DlZwYsDfJlDO;

/**
 * 
 * 应收电费记录
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月6日
 */
@Mapper
public interface DlZwYsDfJlDao {

	List<DlZwYsDfJlDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
