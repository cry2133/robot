package com.robot.robot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robot.common.domain.NotfoundException;
import com.robot.common.exception.ImportFailException;
import com.robot.robot.dao.TFaqDao;
import com.robot.robot.domain.TFaqDO;
import com.robot.robot.domain.TKeywordDO;
import com.robot.robot.domain.TKeywordExcludeDO;
import com.robot.robot.domain.TKeywordMiddleDO;
import com.robot.robot.domain.TMajorDO;
import com.robot.robot.domain.TRepositoryDO;
import com.robot.robot.service.TFaqService;
import com.robot.robot.service.TKeywordExcludeService;
import com.robot.robot.service.TKeywordMiddleService;
import com.robot.robot.service.TKeywordService;
import com.robot.robot.service.TMajorService;
import com.robot.robot.service.TRepositoryService;




@Service
public class TFaqServiceImpl implements TFaqService {
	public static Logger log = Logger.getLogger(TFaqServiceImpl.class); 
	
	@Autowired
	private TFaqDao tFaqDao;
	@Autowired
	private TKeywordService tKeywordService;
	@Autowired
	private TKeywordExcludeService tKeywordExcludeService;
	@Autowired
	private TKeywordMiddleService tKeywordMiddleService;
	@Autowired
	private TRepositoryService tRepositoryService;
	@Autowired
	private TMajorService tMajorService;
	
	
	@Override
	public TFaqDO get(Long faqId){
		return tFaqDao.get(faqId);
	}

	
	@Override
	public List<TFaqDO> list(Map<String, Object> map){
		return tFaqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tFaqDao.count(map);
	}
	
	@Override
	public int countParent(String parentId){
		return tFaqDao.countParent(parentId);
	}
	
	@Override
	public int save(TFaqDO tFaq){
		return tFaqDao.save(tFaq);
	}
	
	@Override
	public int update(TFaqDO tFaq){
		return tFaqDao.update(tFaq);
	}
	
	@Override
	public int remove(Long faqId){
		return tFaqDao.remove(faqId);
	}
	
	@Override
	public int batchRemove(Long[] faqIds){
		return tFaqDao.batchRemove(faqIds);
	}

	@Override
	public long searchFaqForApp(String gc) throws NotfoundException {
	  boolean keynum[]=new boolean[tKeywordService.getMaxId()+1];
		int len=gc.length();
		String c1=null;
		String c2=null;
		String c3=null;
		String c4=null;
		int i=0;
		List<TKeywordDO> list = tKeywordService.list(null);
		for (int p1 = 1; p1 <= len; p1++) {
			c1=subs(p1,gc,1);
			for (TKeywordDO tKeyword:list) {
				c2 = tKeyword.getName();
//				log.info("搜索答案c2:"+c2);
				c3=subs(1,c2,1);
				if(c1!=null&&c3!=null){
					if(c1.equals(c3)==true){
						i=c2.length();
						c4=subs(p1,gc,i);
						if(c4!=null&&c2!=null){
							if(c4.equals(c2)==true){
//								log.info("搜索答案key:"+tKeyword.getKeywordId());
								keynum[tKeyword.getKeywordId().intValue()]=true;
							}
						}
					}
				}
			}
		}
		
		
		List<TKeywordExcludeDO> tKeywordExcludes = tKeywordExcludeService.list(null);
		for (TKeywordExcludeDO tKeywordExclude:tKeywordExcludes) {
			log.info("test:"+tKeywordExclude.getKeywordId().intValue());
			if(keynum[ tKeywordExclude.getKeywordId().intValue()]==true){
				keynum[tKeywordExclude.getExcludeId().intValue()]=false;
			}
		}
	
		int exists=0;
		String group=null;
		int[] mgroup=null;
		int truesum=0;
		int truesum_b=0;//匹配度对比
		long result=0l;
		long faqId=0l;
		long faqIdB=0l;		//备用
			for(int s=1;s<keynum.length;s++){
				if(keynum[s]==true){
					exists++;
					List<TKeywordMiddleDO> tKeywordMiddles=tKeywordMiddleService.getBykeywordId("%"+String.valueOf(s)+"%");
					for(TKeywordMiddleDO tKeywordMiddle : tKeywordMiddles){
						group=tKeywordMiddle.getKeygroup();
						mgroup=stringtoint(group);
						truesum=0;
						for(int i1=0;i1<mgroup.length;i1++){
							if(keynum[mgroup[i1]]==true){
								faqIdB=tKeywordMiddle.getFaqId();
								truesum++;
							}
						}
						if(truesum==mgroup.length){
							if(truesum>truesum_b){
								result=tKeywordMiddle.getMiddleId();
								faqId=tKeywordMiddle.getFaqId();
								s=keynum.length+2;
								truesum_b=truesum;
								continue;
							}
						}
					}
				}
			}
			if(exists==0||result==0){
				if(faqIdB ==0){
					log.error("未找到答案 gc="+gc);
				}else{
					return faqIdB;
				}
				
//				throw new NotfoundException();
			}
			return faqId;
	}
	
	public String subs(int len,String content,int size){
		if (content == null||content.length()<len+size-1) {
		return null;
		}
		String s = new String();
		s=content.substring(len-1, len-1+size);
		return s;
	}
	
	public int[] stringtoint(String s){
		String[] strarr = s.split(",");
		int[] table_ids = new int[strarr.length];
		for(int i=0;i<strarr.length;i++){
		table_ids[i]=Integer.parseInt(strarr[i]);
		}
		return table_ids;
	}

	@Override
	public TFaqDO getLikeByQuestion(String question) {
		TFaqDO tfaq=new TFaqDO();
		question="+"+question;
		List<TFaqDO> tfaqs=tFaqDao.getLikeByQuestion(question);
		if(tfaqs.size()>0){
			tfaq=tfaqs.get(0);
		}
		return tfaq;
	}
	
	@Override
	public TFaqDO getLikeByQuestionForParent(String question,String parentId) {
		Map<String, Object> query = new HashMap<>(0);
        query.put("question", question);
        query.put("parentId", parentId);
		TFaqDO tfaq=new TFaqDO();
		question="+"+question;
		List<TFaqDO> tfaqs=tFaqDao.getLikeByQuestionForParent(query);
		if(tfaqs.size()>0){
			tfaq=tfaqs.get(0);
		}
		return tfaq;
	}

	@Override
	public boolean importFaqsExcelSources(String content) throws Exception {

        boolean isExist = false;
        String[] contentlist = content.split("##", -1);
//        if (StringUtils.isEmpty(contentlist[0]) && StringUtils.isEmpty(contentlist[2]) && StringUtils.isEmpty(contentlist[3])
			if (StringUtils.isEmpty(contentlist[0]) && StringUtils.isEmpty(contentlist[3])
        		&& StringUtils.isEmpty(contentlist[4])&& StringUtils.isEmpty(contentlist[5])) {
            throw new ImportFailException("参数不能为空");
        }
        
        String result = importFaqs(contentlist);    //插入问答数据
        if (!"0".equals(result)) {
            if (!"1".equals(result) && !"2".equals(result)) {
                throw new ImportFailException(result);
            } else {
                isExist = true;        //数据是否存在
            }
        }
        return isExist;
    
	}
	
	/**
	 * @param tFaqDO
	 * @param tKeywordDO
	 * @param tMajorDO
	 * @param tRepositoryDO
	 * @return	“1”：关键字组合已存在 	2：问题已存在
	 * @throws Exception
	 */
	@Transactional
	public String importFaqs(String[] contentlist) throws Exception {
        TMajorDO tMajor = new TMajorDO(contentlist);
        TRepositoryDO tRepository = new TRepositoryDO(contentlist);
        TFaqDO tFaq = new TFaqDO(contentlist); 
		
		String keygroupName=contentlist[5].replaceAll("，", ",");
		
		boolean flag_keygroupName=tKeywordMiddleService.existKeygroupName(keygroupName);
		if(flag_keygroupName){//关键字组合已存在
			return "1";
		}
		
		TFaqDO tfaq_e=tFaqDao.getByQuestion(tFaq.getQuestion());
		if(tfaq_e!=null)	{//问题已存在
			return "2";
		}
		
		//添加知识库
		TRepositoryDO tr=tRepositoryService.getByName(tRepository.getName());
		if(tr==null){
			tRepositoryService.save(tRepository);
			tFaq.setRepositoryId(tRepository.getRepositoryId());
		}else{
			tFaq.setRepositoryId(tr.getRepositoryId());
		}
		
		//添加专业
		TMajorDO tm=tMajorService.getByName(tMajor.getName());
		if(tm==null){
			tMajorService.save(tMajor);
			tFaq.setMajorId(tMajor.getMajorId());
		}else{
			tFaq.setMajorId(tm.getMajorId());
		}
		
		String[] keywords=keygroupName.split(",");
		String keygroup="";
		for(int i=0;i<keywords.length;i++){
			TKeywordDO tKeyword=tKeywordService.getByName(keywords[i]);
			if(tKeyword==null){
				TKeywordDO tk = new TKeywordDO(keywords[i]);
				tKeywordService.save(tk);
				if(i==0){
					keygroup=String.valueOf(tk.getKeywordId());
				}else{
					keygroup+=","+String.valueOf(tk.getKeywordId());
				}
			}else{
				if(i==0){
					keygroup=String.valueOf(tKeyword.getKeywordId());
				}else{
					keygroup+=","+String.valueOf(tKeyword.getKeywordId());
				}
			}
		}
		
		//插入问题
		tFaqDao.save(tFaq);
		
		//插入关键字关联表
		TKeywordMiddleDO tKeywordMiddle=new TKeywordMiddleDO(keygroupName,keygroup,tFaq.getFaqId());
		tKeywordMiddleService.save(tKeywordMiddle);
		
		return "0";
		
	}

	
}
