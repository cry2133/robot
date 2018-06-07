package com.robot.robot.dldao;

import com.robot.robot.domain.DlCbdlDO;
import com.robot.robot.domain.DlLcCbxxDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 根据抄表计划和运行电能表等客户档案信息通过抄表数据开放形成的抄表信息及计费电量的存储
 * @author yobi
 * @email ***
 * @date 2018-03-29 17:44:46
 */
@Mapper
public interface DlLcCbxxDao {

	DlLcCbxxDO get(String gzdbh);
	
	List<DlLcCbxxDO> list(Map<String,Object> map);
	
	List<DlCbdlDO> getlccbxxForAPP(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DlLcCbxxDO dlLcCbxx);
	
	int update(DlLcCbxxDO dlLcCbxx);
	
	int remove(String GZDBH);
	
	int batchRemove(String[] gzdbhs);
}
