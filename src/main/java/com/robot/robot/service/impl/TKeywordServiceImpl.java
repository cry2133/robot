package com.robot.robot.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.robot.robot.dao.TKeywordDao;
import com.robot.robot.domain.TKeywordDO;
import com.robot.robot.domain.TKeywordReplaceDO;
import com.robot.robot.service.TKeywordReplaceService;
import com.robot.robot.service.TKeywordService;


@Service
public class TKeywordServiceImpl implements TKeywordService {
	 public static Logger log = Logger.getLogger(TKeywordServiceImpl.class); 
	
	@Autowired
	private TKeywordDao tKeywordDao;
	@Autowired
	private TKeywordReplaceService tKeywordReplaceService;
	
	@Override
	public TKeywordDO get(Long keywordId){
		return tKeywordDao.get(keywordId);
	}
	
	@Override
	public List<TKeywordDO> list(Map<String, Object> map){
		return tKeywordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tKeywordDao.count(map);
	}
	
	@Override
	public int save(TKeywordDO tKeyword){
		return tKeywordDao.save(tKeyword);
	}
	
	@Override
	public int update(TKeywordDO tKeyword){
		return tKeywordDao.update(tKeyword);
	}
	
	@Override
	public int remove(Long keywordId){
		return tKeywordDao.remove(keywordId);
	}
	
	@Override
	public int batchRemove(Long[] keywordIds){
		return tKeywordDao.batchRemove(keywordIds);
	}

	@Override
	public boolean existKeyword(String keyword) {
		boolean state=true;
		List<TKeywordDO> list=tKeywordDao.existKeyword(keyword);
		if(list.size()<=0){
			state=false;
		}
		return state;
	}

	@Override
	public String strReplace(String gc) {
		int len=gc.length();
		String c1=null;
		String c2=null;
		String c3=null;
		String c4=null;
		int i=0;
		List<TKeywordReplaceDO> list= tKeywordReplaceService.list(null);
		for (int p1 = 1; p1 <= len; p1++) {
			c1=subs(p1,gc,1);
//			log.info("同音字替换c1:"+c1);
			for (TKeywordReplaceDO tKeywordReplace:list) {
				c2 = tKeywordReplace.getCharkey();
//				log.info("同音字替换c2:"+c2);
				c3=subs(1,c2,1);
				if(c1!=null&&c3!=null){
					if(c1.equals(c3)==true){
						i=c2.length();
						c4=subs(p1,gc,i);
						if(c4!=null&&c2!=null){
							if(c4.equals(c2)==true){
//								log.info("同音字替换key:"+c4+" "+tKeywordReplace.getKeyword()+" "+gc);
								gc=gc.replace(c4, tKeywordReplace.getKeyword());
							}
						}
					}
				}
			}
		}
		return gc;
		
	
	}
	
	public String subs(int len,String content,int size){
		if (content == null||content.length()<len+size-1) {
		return null;
		}
		String s =null;
		s=content.substring(len-1, len-1+size);
		return s;
	}

	@Override
	public int getMaxId() {
		return tKeywordDao.getMaxId();
	}

	@Override
	public TKeywordDO getByName(String name) {
		return tKeywordDao.getByName(name);
	}
	
}
