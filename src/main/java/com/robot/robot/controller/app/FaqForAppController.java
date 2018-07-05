package com.robot.robot.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.common.utils.MapUtils;
import com.robot.common.utils.RequestUtil;
import com.robot.common.utils.TuLingUtils;
import com.robot.common.utils.XFyunUtils;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;
import com.robot.common.domain.TuLingReturn;
import com.robot.common.domain.XFyunReturn;
import com.robot.robot.controller.app.bean.FaqRequestBean;
import com.robot.robot.controller.app.bean.ResponseBean;
import com.robot.robot.controller.app.common.AppConstants;
import com.robot.robot.domain.TFaqDO;
import com.robot.robot.domain.TKeywordDO;
import com.robot.robot.domain.TKeywordMiddleDO;
import com.robot.robot.domain.TKeywordReplaceDO;
import com.robot.robot.domain.TNonexistentFaqDO;
import com.robot.robot.service.TFaqService;
import com.robot.robot.service.TKeywordMiddleService;
import com.robot.robot.service.TKeywordReplaceService;
import com.robot.robot.service.TKeywordService;
import com.robot.robot.service.TNonexistentFaqService;
import com.robot.robot.utils.SessionUtil;

/**
 * 
 * 机器人智能问答接口
 *
 * @Author 	Laogf
 * @Version 	1.0 
 * @Data 	2018年6月26日
 */
@Controller
@RequestMapping("/app/faq")
public class FaqForAppController {
	public static Logger log = Logger.getLogger(FaqForAppController.class);
	private final static String DEFAULT_NO_ANSWER = "对不起！本宝宝回答不了这个问题。";
	private final static int QUESTION_LIST_NUM = 5;
	
	@Autowired
	private TFaqService tFaqService;
	@Autowired
	private TKeywordService tKeywordService;
	@Autowired
	private TNonexistentFaqService tNonexistentFaqService;
	@Autowired
	private TKeywordMiddleService tKeywordMiddleService;
	@Autowired
	private TKeywordReplaceService tKeywordReplaceService;
	
	/**
	 * 智能查询问答
	 */
	@RequestMapping("/searchAnswer")
	@ResponseBody
	public ResponseBean searchAnswer(HttpServletRequest request) throws Exception{
		
		String content=RequestUtil.getString(request, "content");
		String robotNo=RequestUtil.getString(request, "robotNo");

		//退出即清空session缓存
		if(content.equals(AppConstants.OUT)){
			SessionUtil.removeSessionAttribute(robotNo);
		}

		if(StringUtils.isEmpty(content)){
			return ResponseBean.fail("参数格式不对");
		}
		/*
		String parentId  = SessionUtil.getAttr(robotNo);		//获取当前机械人语音是否有上级问答
		log.info("------------------语音回答上级 parentId:"+parentId);
		log.info("------------------content:"+content +",robotNo:"+robotNo);
		
		
		ResponseBean res =new ResponseBean();
		if(StringUtils.isNotEmpty(parentId)){
			int countParent=tFaqService.countParent(parentId);
			if(countParent>0){
				//大于0即代表有下级问答
				res =parentSearch(content,robotNo,parentId);
			}else{
				//0即代表没有下级问答
				res =commonSearch(content,robotNo);
			}
		}else{
			res =commonSearch(content,robotNo);
		}
		return res;
		*/
		
		String parentContent  = SessionUtil.getAttr(robotNo);      //获取当前机械人语音是否有上级问答
		ResponseBean res =new ResponseBean();
		if(StringUtils.isNotEmpty(parentContent)){
			String question = content + parentContent;
			//上下文问答
			res = commonSearch(question,robotNo);
		}else{
			res = commonSearch(content,robotNo);
		}
		return res;
	}	
		
		
		/***********************注释为原回答逻辑*******************************/
//		String content=RequestUtil.getString(request, "content");
//		log.info("content:"+content);
//		
//		if(StringUtils.isEmpty(content)){
//			return ResponseBean.fail("参数格式不对");
//		}
//		//优先通过问答查询答案
//		TFaqDO tFaq=new TFaqDO();
//		tFaq=tFaqService.getLikeByQuestion(content);
//		if(tFaq.getFaqId() != null){
//			FaqRequestBean faqBean=new FaqRequestBean();
//			faqBean.setQuestion(tFaq.getQuestion());
//			faqBean.setAnswer(tFaq.getAnswer());
//			
//			return ResponseBean.success(faqBean);
//		}	
//		
//		//关键字查询
//		String gc=tKeywordService.strReplace(content);
//		long faqId=tFaqService.searchFaqForApp(gc);
//		if(faqId!=0){//通过关键字查询答案
//			tFaq=tFaqService.get(faqId);
//			
//			FaqRequestBean faqBean=new FaqRequestBean();
//			faqBean.setQuestion(content);
//			faqBean.setAnswer(tFaq.getAnswer());
//			
//			return ResponseBean.success(faqBean);
//		}else{
////			tFaq=tFaqService.getLikeByQuestion(content);
////			if(tFaq.getFaqId() != null){
////				FaqRequestBean faqBean=new FaqRequestBean();
////				faqBean.setQuestion(tFaq.getQuestion());
////				faqBean.setAnswer(tFaq.getAnswer());
////				
////				return ResponseBean.success(faqBean);
////				
////			}else{
//				//查询不到答案记录问题
//				TuLingReturn tuLingReturn = TuLingUtils.postTalk(content);
//				String result = tuLingReturn.getText();
//				log.info("未找到答案,通过图灵寻找 question:"+content+",result:"+result);
//				
//				FaqRequestBean faqBean=new FaqRequestBean();
//				faqBean.setQuestion(content);
//				faqBean.setAnswer(result);
//				
//				if(tNonexistentFaqService.existQuestion(gc)){
//					TNonexistentFaqDO tNonexistentFaq=new TNonexistentFaqDO();
//					tNonexistentFaq.setQuestion(gc);
//					tNonexistentFaq.setAnswer(result);
//					tNonexistentFaq.setCreatetime(new Date());
//					tNonexistentFaqService.save(tNonexistentFaq);
//				}
//				
//				return ResponseBean.success(faqBean);
////			}
//			
//		}
		


	
	public ResponseBean commonSearch(String content,String robotNo) throws Exception{
		
		FaqRequestBean faqBean=new FaqRequestBean();
		
		//优先通过问答查询答案
		TFaqDO tFaq = null;
		tFaq=tFaqService.getLikeByQuestion(content);
		if(StringUtils.isNotEmpty(tFaq.getAnswer())){
			faqBean.setQuestion(tFaq.getQuestion());
			faqBean.setAnswer(tFaq.getAnswer());
			
			//SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}	
		faqBean = getAnswerByAll(content, robotNo);
		return ResponseBean.success(faqBean);
	}
	
	public ResponseBean parentSearch(String content,String robotNo,String parentId) throws Exception{
		
		FaqRequestBean faqBean=new FaqRequestBean();
		
		//优先通过问答查询答案
		TFaqDO tFaq = null;
		tFaq=tFaqService.getLikeByQuestionForParent(content, parentId);
		if(tFaq != null){
			faqBean.setQuestion(tFaq.getQuestion());
			faqBean.setAnswer(tFaq.getAnswer());
			
			SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return ResponseBean.success(faqBean);
		}
		faqBean = getAnswerByAll(content, robotNo);
		return ResponseBean.success(faqBean);
	}

	
	/**
	 * 
	* @Functionlity  公共问题查找答案（知识库==>讯飞==>图灵）
	* @Date  2018年6月14日
	 */
	public FaqRequestBean getAnswerByAll(String content, String robotNo) throws Exception{
		FaqRequestBean faqBean = new FaqRequestBean();
		faqBean.setQuestion(content);
		
		//getAnswerByGc(content); 
		
		//全文检索找不到答案，继续用关键字搜索，全部符合即给出答案，符合部分按照用户意图给出问题提示
		faqBean = getAnswerByjieBaFenCi(content);   //结巴分词关键字查找问答
		if(StringUtils.isEmpty(faqBean.getAnswer())){
			faqBean = getAnswerByXFyun(content);  //本地知识库查询不到答案转<讯飞>记录问题
			if(StringUtils.isEmpty(faqBean.getAnswer())){
				faqBean = getAnswerByTuLing(content);  //<讯飞>+知识库查询不到答案转<图灵>记录问题
				if(StringUtils.isEmpty(faqBean.getAnswer())){
					faqBean.setAnswer(DEFAULT_NO_ANSWER);
				}
			}
		}
		return faqBean;
	}

	/**
	 * 关键字查询问答（原查询方式--暂时不用）
	 */
	public FaqRequestBean getAnswerByGc(String content) throws Exception{
		TFaqDO tFaq = new TFaqDO();
		FaqRequestBean faqBean = new FaqRequestBean();
		//关键字替换
		String gc=tKeywordService.strReplace(content);
		//关键字查询
		long faqId=tFaqService.searchFaqForApp(gc);
		if(faqId!=0){//通过关键字查询答案
			tFaq=tFaqService.get(faqId);
			faqBean.setAnswer(tFaq.getAnswer());
			
			//SessionUtil.setRequestAttribute(robotNo, String.valueOf(tFaq.getFaqId()));
			return faqBean;
		}
		return faqBean;
	}
	
	
	/**
	 * 结巴分词关键字查询答案
	 */
	public FaqRequestBean getAnswerByjieBaFenCi(String content){
		FaqRequestBean faqBean = new FaqRequestBean();
		List<String> questionList = new ArrayList<String>();
		
		TFaqDO tFaqDO = null;
		//结巴分词提取关键字
		JiebaSegmenter segmenter = new JiebaSegmenter();
		List<SegToken> segToken = segmenter.process(content, SegMode.INDEX);
		
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		Map<String,Integer> map3 = new HashMap<String,Integer>();
		List<TKeywordMiddleDO> kwM = tKeywordMiddleService.list(map2);
		//初始化map3匹配数组
		for(int k=0; k<kwM.size(); k++){
			String faqID = kwM.get(k).getFaqId().toString();
			map3.put(faqID, 0);
		}
		
		for (int s=0; s<segToken.size(); s++ ){
			Long kwID = null;
			
			String jieBaWord = segToken.get(s).word;
			map.put("name", jieBaWord);
			List<TKeywordDO> kwList = tKeywordService.list(map);
			//关键字替换查找
			if(kwList.size() <= 0){
				Map<String,Object> getReplaceByCharKey = new HashMap<String,Object>();
				getReplaceByCharKey.put("charkey", jieBaWord);
				List<TKeywordReplaceDO> kwRep = tKeywordReplaceService.list(getReplaceByCharKey);
				//找到替换关键字
				if(kwRep.size()>0){
					for (TKeywordReplaceDO kwr : kwRep){
						kwID = kwr.getKeywordId();
					}
				}
			}else if(kwList.size()>0){
				for (TKeywordDO kw : kwList){
					kwID = kw.getKeywordId();
				}
			}
			
			//关键字存在则查看关联问题
			if(kwID != null){
				
				//加入关键字查询次数记录
				TKeywordDO tKeyword = new TKeywordDO();
				tKeyword.setKeywordId(kwID);
				tKeyword.setAmount(tKeywordService.get(kwID).getAmount()+1);
				tKeywordService.update(tKeyword);
				
				for(int k=0; k<kwM.size(); k++){
					String []  kwmArray = kwM.get(k).getKeygroup().split(",");
					String faqID = kwM.get(k).getFaqId().toString();
					for (int i=0; i<kwmArray.length; i++){
						//Long kwmID = Long.parseLong(kwmArray[i]);
						String kwmID = kwmArray[i];
						if (kwID.toString().equals(kwmID)){
							int m = map3.get(faqID);
							if(m==0 || m>0){
								map3.put(faqID, m+1);
							}
						}
					}
				}
			}
		}

		Map<String,Double> map4 = new HashMap<String,Double>();
		//答案为匹配个数最多的,map3的数据过滤
		Map<String,Integer> map6 = MapUtils.getMapByMaxIntValue(map3);
		for (String m6Key : map6.keySet()) {
        	double value = map6.get(m6Key);
        	//判断分词关键字是否存在，如果存在则存入与关键字组合的占比
            if(value>0){
            	int kwmc= tFaqService.get(Long.parseLong(m6Key)).getKeygroup().split(",").length;
            	double v = value/kwmc;
            	map4.put(m6Key, v);
            }
	    }

		if(map4.size()>0){
			Map<String,Double> map5 = new HashMap<String,Double>();
			for (String m4Key : map4.keySet()) {
				double value = map4.get(m4Key);
	            if(value>=1){
	            	double m4Value = tFaqService.get(Long.parseLong(m4Key)).getKeygroup().split(",").length;
	            	map5.put(m4Key, m4Value);
	            }
			 }
			if(map5.size()>0){
				String maxFaqIDstring = MapUtils.getKeyByMaxDoubleValue(map5);
				tFaqDO = tFaqService.get(Long.parseLong(maxFaqIDstring));
				//把匹配到的加入答案
				faqBean.setAnswer(tFaqDO.getAnswer());
				
				//加入一次回答次数记录
				TFaqDO tFaq = new TFaqDO();
				tFaq.setFaqId(Long.parseLong(maxFaqIDstring));
				tFaq.setAmount(tFaqDO.getAmount()+1);
				tFaqService.update(tFaq);
				
				return faqBean;
			}else{
				//没匹配到答案给出一个匹配度最高的答案
				Map<String,Double> newSortMap = MapUtils.mapSort(map4);
				for(String key : newSortMap.keySet()){
					if(newSortMap.get(key) != null){
						String answer = tFaqService.get(Long.parseLong(key)).getAnswer();
						faqBean.setAnswer(answer);
						newSortMap.remove(key);  //把回答的问题去掉不提示
						break;
					}
				}
				for(String key : newSortMap.keySet()){
					tFaqDO = tFaqService.get(Long.parseLong(key));
					String question = tFaqDO.getQuestion();
					questionList.add(question) ;
					if(questionList.size()==QUESTION_LIST_NUM){
						break;    //控制匹配问题数为5条
					}
				}
				if(questionList.size()>1){
					faqBean.setQuestionList(questionList);
				}
				return faqBean;
			}
		}
		
		return faqBean;

		/*
		//如果问题中关键字个数与匹配个数一样证明完全匹配问题可以给出答案，否则给出问题提示
		if(natchCount>=1){
			//判断是否有多个意图（上下文处理）
			String intentionEntity = tFaqDO.getIntentionEntity();
			String intentionEntity2 = tFaqDO.getIntentionEntity2();
			String intentionEntity3 = tFaqDO.getIntentionEntity3();
			
			if(StringUtils.isNotEmpty(intentionEntity)){
				Pattern p=Pattern.compile(intentionEntity);  
				Matcher m=p.matcher(content);    //正则表达式匹配
				if(m.find()){
					if(StringUtils.isEmpty(intentionEntity2)){
						//找到完整答案，没有其他意图
						SessionUtil.removeSessionAttribute(robotNo);
				        return tFaqDO.getAnswer();
					}
					Pattern p2 = Pattern.compile(intentionEntity2);  
					Matcher m2 = p2.matcher(content);    //正则表达式匹配
					//只有第二个意图实体存在，才判断是否还有其他意图
					if(m2.find()){
						if(StringUtils.isEmpty(intentionEntity3)){
							//找到完整答案，没有其他意图
							SessionUtil.removeSessionAttribute(robotNo);
					        return tFaqDO.getAnswer();
						}
						Pattern p3 = Pattern.compile(intentionEntity3);  
						Matcher m3 = p3.matcher(content);    //正则表达式匹配
						if(m3.find()){
							//找到完整答案，没有其他意图
							SessionUtil.removeSessionAttribute(robotNo);
					        return tFaqDO.getAnswer();
						}else{
							SessionUtil.setRequestAttribute(robotNo, content);
							return tFaqDO.getIntentionText3();
						}
					}else{
						SessionUtil.setRequestAttribute(robotNo, content);
						return tFaqDO.getIntentionText2();
					}
				}else{
					SessionUtil.setRequestAttribute(robotNo, content);
					return tFaqDO.getIntentionText();
				}
	        }
			//找到完整答案，清除缓存
			SessionUtil.removeSessionAttribute(robotNo);
	        return tFaqDO.getAnswer();
		}else if(natchCount==0){
			return null;
		}else{
			return "你可以这样问："+tFaqDO.getQuestion();
		}
		*/
	}
	
	
	/**
	 * 讯飞获取问答
	 */
	public FaqRequestBean getAnswerByXFyun(String content) throws IOException, Exception{
		TNonexistentFaqDO tNonexistentFaq = new TNonexistentFaqDO();
		FaqRequestBean faqBean = new FaqRequestBean();
		
		XFyunReturn xfReturn = XFyunUtils.runChat(content);
		String result = xfReturn.getText();
		if(StringUtils.isNotEmpty(result)){
			log.info("-------------------------------通过 < 讯飞 > 寻找 question:"+content+",result:"+result+"-------------------------------");
			faqBean.setAnswer(result);
			
			/**
			 * 保存知识库没有的问答
			 */
			if(tNonexistentFaqService.existQuestion(content)){
				tNonexistentFaq.setQuestion(content);
				tNonexistentFaq.setAnswer(result);
				tNonexistentFaq.setCreatetime(new Date());
				tNonexistentFaqService.save(tNonexistentFaq);
			}
			return faqBean;
		}
		return faqBean;
	}
	
	
	/**
	 * 图灵获取问答
	 */
	public FaqRequestBean getAnswerByTuLing(String content) throws Exception {
		TNonexistentFaqDO tNonexistentFaq = new TNonexistentFaqDO();
		FaqRequestBean faqBean = new FaqRequestBean();
		
		TuLingReturn tuLingReturn = TuLingUtils.postTalk(content);
		String TLresult = tuLingReturn.getText();
		if(StringUtils.isNotEmpty(TLresult)){
			log.info("-------------------------------通过 < 图灵 >  寻找 question:"+content+",result:"+TLresult+"-------------------------------");
			faqBean.setAnswer(TLresult);
			
			/**
			 * 保存知识库没有的问答
			 */
			if(tNonexistentFaqService.existQuestion(content)){
				tNonexistentFaq.setQuestion(content);
				tNonexistentFaq.setAnswer(TLresult);
				tNonexistentFaq.setCreatetime(new Date());
				tNonexistentFaqService.save(tNonexistentFaq);
			}
			return faqBean;
		}
		return faqBean;
	}
	
	
}
